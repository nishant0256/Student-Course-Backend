package com.example.demo.Service;

import com.example.demo.Entity.Attendance;
import com.example.demo.Entity.Course;
import com.example.demo.Entity.Student;
import com.example.demo.Repositories.AttendanceRepository;
import com.example.demo.Repositories.CourseRepository;
import com.example.demo.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepo;

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private CourseRepository courseRepo;

    public Attendance markAttendance(Long studentId, Long courseId, String status, LocalDate date) {

        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Attendance attendance = new Attendance();
        attendance.setStudent(student);
        attendance.setCourse(course);
        attendance.setStatus(status);
        attendance.setAttDate(date != null ? date : LocalDate.now());

        return attendanceRepo.save(attendance);
    }

    public List<Attendance> getAllAttendance() {
        return attendanceRepo.findAll();
    }

    public List<Attendance> getByStudent(Long studentId) {
        return attendanceRepo.findByStudent_Id(studentId);
    }

    public List<Attendance> getByCourse(Long courseId) {
        return attendanceRepo.findByCourse_Id(courseId);
    }

    public void deleteAttendance(Long id) {
        attendanceRepo.deleteById(id);
    }
}
