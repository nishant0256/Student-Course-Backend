package com.example.demo.Service;

import java.util.*;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.AttendanceReportDto;
import com.example.demo.DTO.EnrollmentSummaryDto;
import com.example.demo.DTO.PerformanceReportDto;
import com.example.demo.Entity.Attendance;
import com.example.demo.Entity.Course;
import com.example.demo.Entity.Grade;
import com.example.demo.Entity.Student;
import com.example.demo.Repositories.AttendanceRepository;
import com.example.demo.Repositories.CourseRepository;
import com.example.demo.Repositories.GradeRepository;
import com.example.demo.Repositories.StudentRepository;

@Service
public class ReportsService {

    @Autowired
    private GradeRepository gradeRepo;

    @Autowired
    private AttendanceRepository attendanceRepo;

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private CourseRepository courseRepo;

    // Performance report for a student
    public PerformanceReportDto getPerformanceReportForStudent(Long studentId) {
        Student student = studentRepo.findById(studentId).orElseThrow();
        List<Grade> grades = gradeRepo.findByStudent_Id(studentId);

        PerformanceReportDto dto = new PerformanceReportDto();
        dto.setStudentId(studentId);
        dto.setStudentName(student.getName());

        // courseScores
        List<Map<String,Object>> courseScores = grades.stream()
            .map(g -> {
                Map<String,Object> m = new HashMap<>();
                m.put("courseId", g.getCourse().getId());
                m.put("courseTitle", g.getCourse().getTitle());
                double percent = g.getMaxScore() != null && g.getMaxScore() > 0 ? (g.getScore() / g.getMaxScore()) * 100.0 : 0.0;
                m.put("percent", Math.round(percent * 100.0) / 100.0);
                m.put("gradeLetter", g.getGradeLetter());
                m.put("date", g.getGradedDate() != null ? g.getGradedDate().toString() : null);
                return m;
            })
            .sorted(Comparator.comparing(m -> (String)m.get("date"), Comparator.nullsFirst(Comparator.naturalOrder())))
            .collect(Collectors.toList());

        dto.setCourseScores(courseScores);

        // trend: group by gradedDate (date -> avg percent)
        Map<String, List<Double>> byDate = new TreeMap<>();
        DateTimeFormatter fmt = DateTimeFormatter.ISO_LOCAL_DATE;
        for (Grade g : grades) {
            String date = g.getGradedDate() != null ? g.getGradedDate().format(fmt) : "unknown";
            double p = g.getMaxScore() != null && g.getMaxScore() > 0 ? (g.getScore() / g.getMaxScore()) * 100.0 : 0.0;
            byDate.computeIfAbsent(date, k -> new ArrayList<>()).add(p);
        }
        List<Map<String,Object>> trend = byDate.entrySet().stream()
            .map(e -> {
                Map<String,Object> m = new HashMap<>();
                double avg = e.getValue().stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
                m.put("date", e.getKey());
                m.put("percent", Math.round(avg * 100.0) / 100.0);
                return m;
            })
            .collect(Collectors.toList());
        dto.setTrend(trend);

        // calculate GPA/overall percent as average percent across all grades
        double overall = grades.stream()
            .mapToDouble(g -> {
                if (g.getMaxScore() == null || g.getMaxScore() == 0) return 0.0;
                return (g.getScore() / g.getMaxScore()) * 100.0;
            })
            .average()
            .orElse(0.0);
        dto.setGpa(Math.round(overall * 100.0) / 100.0);

        return dto;
    }

    // Attendance report for a student
    public AttendanceReportDto getAttendanceReportForStudent(Long studentId) {
        Student student = studentRepo.findById(studentId).orElseThrow();
        List<Attendance> records = attendanceRepo.findByStudent_Id(studentId);

        AttendanceReportDto dto = new AttendanceReportDto();
        dto.setStudentId(studentId);
        dto.setStudentName(student.getName());

        long total = records.size();
        long present = records.stream().filter(r -> "PRESENT".equalsIgnoreCase(r.getStatus())).count();
        long absent = records.stream().filter(r -> "ABSENT".equalsIgnoreCase(r.getStatus())).count();
        long late = records.stream().filter(r -> "LATE".equalsIgnoreCase(r.getStatus())).count();
        long excused = records.stream().filter(r -> "EXCUSED".equalsIgnoreCase(r.getStatus())).count();

        dto.setTotalClasses(total);
        dto.setPresent(present);
        dto.setAbsent(absent);
        dto.setLate(late);
        dto.setExcused(excused);

        double perc = total == 0 ? 0.0 : (present * 100.0) / total;
        dto.setAttendancePercentage(Math.round(perc * 100.0) / 100.0);

        return dto;
    }

    // Enrollment summary for all courses
    public List<EnrollmentSummaryDto> getEnrollmentSummary() {
        List<Course> all = courseRepo.findAll();
        List<EnrollmentSummaryDto> out = new ArrayList<>();
        for (Course c : all) {
            EnrollmentSummaryDto e = new EnrollmentSummaryDto();
            e.setCourseId(c.getId());
            e.setCourseTitle(c.getTitle());
            long count = c.getStudents() == null ? 0 : c.getStudents().size();
            e.setEnrolledCount(count);
            out.add(e);
        }
        return out;
    }
}
