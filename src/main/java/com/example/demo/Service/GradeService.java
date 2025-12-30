package com.example.demo.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.GradeDto;
import com.example.demo.DTO.GradeRequest;
import com.example.demo.Entity.Course;
import com.example.demo.Entity.Grade;
import com.example.demo.Entity.Student;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Repositories.CourseRepository;
import com.example.demo.Repositories.GradeRepository;
import com.example.demo.Repositories.StudentRepository;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepo;

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private CourseRepository courseRepo;

    // CREATE
    public GradeDto createGrade(GradeRequest request) {

        Student student = studentRepo.findById(request.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        Course course = courseRepo.findById(request.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        Grade grade = new Grade();
        grade.setId(request.getId());
        grade.setStudent(student);
        grade.setCourse(course);
        grade.setAssessmentType(request.getAssessmentType());
        grade.setScore(request.getScore());
        grade.setMaxScore(request.getMaxScore());
        grade.setGradedDate(LocalDate.now());

        String letter = calculateLetter(request.getScore(), request.getMaxScore());
        grade.setGradeLetter(letter);

        Grade saved = gradeRepo.save(grade);
        return toDto(saved);
    }

    public List<GradeDto> getAllGrades() {
        return gradeRepo.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }


    // UPDATE
    public GradeDto updateGrade(Long id, GradeRequest request) {
        Grade grade = gradeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Grade not found"));

        if (request.getStudentId() != null) {
            Student student = studentRepo.findById(request.getStudentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
            grade.setStudent(student);
        }

        if (request.getCourseId() != null) {
            Course course = courseRepo.findById(request.getCourseId())
                    .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
            grade.setCourse(course);
        }

        if (request.getAssessmentType() != null) {
            grade.setAssessmentType(request.getAssessmentType());
        }

        if (request.getScore() != null) {
            grade.setScore(request.getScore());
        }

        if (request.getMaxScore() != null) {
            grade.setMaxScore(request.getMaxScore());
        }

        if (request.getScore() != null && request.getMaxScore() != null) {
            String letter = calculateLetter(request.getScore(), request.getMaxScore());
            grade.setGradeLetter(letter);
        }

        Grade updated = gradeRepo.save(grade);
        return toDto(updated);
    }

    // GET BY ID
    public GradeDto getGrade(Long id) {
        Grade grade = gradeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Grade not found"));
        return toDto(grade);
    }

    // GET BY STUDENT
    public List<GradeDto> getGradesByStudent(Long studentId) {
        return gradeRepo.findByStudent_Id(studentId)
                .stream()
                .map(this::toDto)
                .toList();
    }

    // GET BY COURSE
    public List<GradeDto> getGradesByCourse(Long courseId) {
        return gradeRepo.findByCourse_Id(courseId)
                .stream()
                .map(this::toDto)
                .toList();
    }

    // DELETE
    public void deleteGrade(Long id) {
        Grade grade = gradeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Grade not found"));
        gradeRepo.delete(grade);
    }

    private String calculateLetter(Double score, Double maxScore) {
        if (score == null || maxScore == null || maxScore == 0) {
            return null;
        }
        double percent = (score / maxScore) * 100.0;
        if (percent >= 90) return "A";
        if (percent >= 80) return "B";
        if (percent >= 70) return "C";
        if (percent >= 60) return "D";
        return "F";
    }

    private GradeDto toDto(Grade grade) {
        GradeDto dto = new GradeDto();
        dto.setId(grade.getId());
        dto.setStudentId(grade.getStudent().getId());
        dto.setStudentName(grade.getStudent().getName());
        dto.setCourseId(grade.getCourse().getId());
        dto.setCourseTitle(grade.getCourse().getTitle());
        dto.setAssessmentType(grade.getAssessmentType());
        dto.setScore(grade.getScore());
        dto.setMaxScore(grade.getMaxScore());
        dto.setGradeLetter(grade.getGradeLetter());
        dto.setGradedDate(grade.getGradedDate());
        return dto;
    }
}
