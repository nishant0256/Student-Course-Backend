package com.example.demo.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.DTO.CourseDto;
import com.example.demo.DTO.StudentDto;
import com.example.demo.Entity.Course;
import com.example.demo.Entity.Student;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Repositories.CourseRepository;
import com.example.demo.Repositories.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository studentRepo;
    private final CourseRepository courseRepo;
    private final ModelMapper mapper;

    public StudentService(StudentRepository studentRepo,
                          CourseRepository courseRepo,
                          ModelMapper mapper) {
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
        this.mapper = mapper;
    }

    // ✅ CREATE STUDENT
    public StudentDto createStudent(StudentDto dto) {
        Student student = mapper.map(dto, Student.class);
        Student saved = studentRepo.save(student);
        return mapStudentToDto(saved);
    }

    // ✅ UPDATE STUDENT
    public StudentDto updateStudent(Long id, StudentDto dto) {
        Student student = studentRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found with id: " + id)
                );

        student.setName(dto.getName());
        student.setAge(dto.getAge());
        student.setEmail(dto.getEmail());

        Student updated = studentRepo.save(student);
        return mapStudentToDto(updated);
    }

    // ✅ GET STUDENT BY ID
    public StudentDto getStudentById(Long id) {
        Student student = studentRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found with id: " + id)
                );

        return mapStudentToDto(student);
    }

    // ✅ GET ALL STUDENTS
    public List<StudentDto> getAllStudents() {
        return studentRepo.findAll()
                .stream()
                .map(this::mapStudentToDto)
                .collect(Collectors.toList());
    }

    // ✅ DELETE STUDENT
    public void deleteStudent(Long id) {
        Student student = studentRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found with id: " + id)
                );

        studentRepo.delete(student);
    }

    // ✅ ENROLL STUDENT INTO COURSE (🔥 FINAL FIX 🔥)
    @Transactional
    public StudentDto enrollStudent(Long studentId, Long courseId) {

        Student student = studentRepo.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found with id: " + studentId)
                );

        Course course = courseRepo.findById(courseId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Course not found with id: " + courseId)
                );

        // ✅ SAFE ID-BASED CHECK (NO duplicates)
        boolean alreadyEnrolled = student.getCourses()
                .stream()
                .anyMatch(c -> c.getId().equals(courseId));

        if (!alreadyEnrolled) {
            student.getCourses().add(course);
        }

        Student savedStudent = studentRepo.save(student);
        return mapStudentToDto(savedStudent);
    }

    // ✅ MANUAL & RELIABLE MAPPING
    private StudentDto mapStudentToDto(Student student) {

        StudentDto dto = mapper.map(student, StudentDto.class);

        if (student.getCourses() != null && !student.getCourses().isEmpty()) {
            List<CourseDto> courseDtos = student.getCourses()
                    .stream()
                    .map(course -> mapper.map(course, CourseDto.class))
                    .collect(Collectors.toList());

            dto.setCourses(courseDtos);
        }

        return dto;
    }
}
