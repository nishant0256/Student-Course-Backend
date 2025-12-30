package com.example.demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.DTO.StudentDto;
import com.example.demo.Entity.Course;
import com.example.demo.Entity.Student;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Repositories.CourseRepository;
import com.example.demo.Repositories.StudentRepository;

@Service
@Transactional
public class StudentService {

    private final StudentRepository studentRepo;
    private final CourseRepository courseRepo;

    public StudentService(StudentRepository studentRepo, CourseRepository courseRepo) {
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
    }

    // ================= CREATE =================
    public StudentDto createStudent(StudentDto dto) {

        Student student = new Student();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setAge(dto.getAge());
        student.setGender(dto.getGender());
        student.setPhone(dto.getPhone());

        if (dto.getCourseId() != null) {
            Course course = courseRepo.findById(dto.getCourseId())
                    .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
            student.setCourse(course);
        }

        return mapToStudentDto(studentRepo.save(student));
    }

    // ================= GET BY ID =================
    @Transactional(readOnly = true)
    public StudentDto getStudentById(Long id) {
        Student student = studentRepo.findByIdWithCourse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        return mapToStudentDto(student);
    }

    // ================= GET ALL =================
    @Transactional(readOnly = true)
    public List<StudentDto> getAllStudents() {
        return studentRepo.findAllWithCourse()
                .stream()
                .map(this::mapToStudentDto)
                .toList();
    }

    // ================= UPDATE =================
    public StudentDto updateStudent(Long id, StudentDto dto) {

        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setAge(dto.getAge());
        student.setGender(dto.getGender());
        student.setPhone(dto.getPhone());

        // allow course change or removal
        if (dto.getCourseId() != null) {
            Course course = courseRepo.findById(dto.getCourseId())
                    .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
            student.setCourse(course);
        } else {
            student.setCourse(null);
        }

        return mapToStudentDto(student);
    }

    public void deleteStudent(Long id) {

        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        // 🔥 VERY IMPORTANT (break FK relations safely)
        student.setCourse(null);              // remove course FK
        student.getAttendanceList().clear();  // delete attendance
        student.getGrades().clear();          // delete grades

        // ✅ NOW cascade + orphanRemoval will work
        studentRepo.delete(student);
    }


    // ================= ENROLL =================
    public StudentDto enrollStudent(Long studentId, Long courseId) {

        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        if (student.getCourse() != null) {
            throw new IllegalStateException("Student already enrolled in a course");
        }

        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        student.setCourse(course);

        return mapToStudentDto(student);
    }

    // ================= DTO MAPPER =================
    private StudentDto mapToStudentDto(Student student) {

        StudentDto dto = new StudentDto();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        dto.setAge(student.getAge());
        dto.setGender(student.getGender());
        dto.setPhone(student.getPhone());

        if (student.getCourse() != null) {
            dto.setCourseId(student.getCourse().getId());
            dto.setCourseTitle(student.getCourse().getTitle());
        }

        return dto;
    }
}
