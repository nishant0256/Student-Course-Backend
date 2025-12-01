package com.example.demo.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.demo.DTO.EnrollmentRequest;
import com.example.demo.DTO.StudentDto;
import com.example.demo.Service.StudentService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    // ✅ Constructor Injection (BEST PRACTICE)
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // ✅ CREATE STUDENT
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDto create(@RequestBody StudentDto dto) {
        return studentService.createStudent(dto);
    }

    // ✅ GET ALL STUDENTS (WITH COURSES)
    @GetMapping
    public List<StudentDto> getAll() {
        return studentService.getAllStudents();
    }

    // ✅ GET STUDENT BY ID
    @GetMapping("/{id}")
    public StudentDto getById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    // ✅ UPDATE STUDENT
    @PutMapping("/{id}")
    public StudentDto update(@PathVariable Long id, @RequestBody StudentDto dto) {
        return studentService.updateStudent(id, dto);
    }

    // ✅ DELETE STUDENT
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    // ✅ ENROLL STUDENT (🔥 CRITICAL FIX POINT 🔥)
    @PostMapping("/enroll")
    public StudentDto enrollStudent(@RequestBody EnrollmentRequest request) {
        return studentService.enrollStudent(
                request.getStudentId(),
                request.getCourseId()
        );
    }
}
