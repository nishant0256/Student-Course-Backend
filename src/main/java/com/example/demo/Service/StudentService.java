package com.example.demo.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.CourseDto;
import com.example.demo.DTO.StudentDto;
import com.example.demo.Entity.Course;
import com.example.demo.Entity.Student;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Repositories.CourseRepository;
import com.example.demo.Repositories.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private CourseRepository courseRepo;

    // ✅ CREATE
    public StudentDto createStudent(StudentDto dto) {

        Student student = new Student();
        student.setId(dto.getId());
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setAge(dto.getAge());
        student.setGender(dto.getGender());
        student.setPhone(dto.getPhone());

        if (dto.getCourses() != null) {
            List<Course> courses = dto.getCourses()
                    .stream()
                    .map(c -> courseRepo.findById(c.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Course not found")))
                    .toList();
            student.setCourses(courses);
        }

        Student saved = studentRepo.save(student);
        return mapToStudentDto(saved);
    }

    // ✅ GET BY ID
    public StudentDto getStudentById(Long id) {
        return mapToStudentDto(
                studentRepo.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Student not found"))
        );
    }

    // ✅ GET ALL
    public List<StudentDto> getAllStudents() {
        return studentRepo.findAll()
                .stream()
                .map(this::mapToStudentDto)
                .toList();
    }

    // ✅ UPDATE
    public StudentDto updateStudent(Long id, StudentDto dto) {

        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setAge(dto.getAge());
        student.setGender(dto.getGender());
        student.setPhone(dto.getPhone());

        return mapToStudentDto(studentRepo.save(student));
    }

    // ✅ DELETE
    public void deleteStudent(Long id) {
        studentRepo.deleteById(id);
    }

    // ✅ ENROLL STUDENT
    public StudentDto enrollStudent(Long studentId, Long courseId) {

        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        if (student.getCourses().size() >= 2) {
            throw new IllegalStateException("Student can enroll in only 2 courses");
        }

        if (!student.getCourses().contains(course)) {
            student.getCourses().add(course);
        }

        return mapToStudentDto(studentRepo.save(student));
    }

    // ✅ SAFE MANUAL MAPPERS (NO RECURSION)
    private StudentDto mapToStudentDto(Student student) {
        StudentDto dto = new StudentDto();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        dto.setAge(student.getAge());
        dto.setGender(student.getGender());
        dto.setPhone(student.getPhone());
        dto.setCreatedDate(student.getCreatedDate());

        dto.setCourses(
                student.getCourses()
                       .stream()
                       .map(this::mapToCourseDto)
                       .toList()
        );

        return dto;
    }

    private CourseDto mapToCourseDto(Course course) {
        CourseDto dto = new CourseDto();
        dto.setId(course.getId());
        dto.setTitle(course.getTitle());
        dto.setDescription(course.getDescription());
        return dto;
    }
}
