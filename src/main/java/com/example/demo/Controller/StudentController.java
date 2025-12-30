package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.DTO.EnrollmentRequest;
import com.example.demo.DTO.StudentDto;
import com.example.demo.Service.StudentService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // ================= CREATE =================
    @PostMapping
    public ResponseEntity<StudentDto> create(@RequestBody StudentDto dto) {
        StudentDto saved = studentService.createStudent(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // ================= GET ALL =================
    @GetMapping
    public ResponseEntity<List<StudentDto>> getAll() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    // ================= GET BY ID =================
    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    // ================= UPDATE =================
    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> update(
            @PathVariable Long id,
            @RequestBody StudentDto dto
    ) {
        return ResponseEntity.ok(studentService.updateStudent(id, dto));
    }

    // ================= DELETE =================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build(); // ✅ 204 No Content
    }

    // ================= ENROLL =================
    @PostMapping("/enroll")
    public ResponseEntity<StudentDto> enroll(
            @RequestBody EnrollmentRequest request
    ) {
        StudentDto dto = studentService.enrollStudent(
                request.getStudentId(),
                request.getCourseId()
        );
        return ResponseEntity.ok(dto);
    }
}
