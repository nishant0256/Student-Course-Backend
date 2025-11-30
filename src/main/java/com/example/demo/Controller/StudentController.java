package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.EnrollmentRequest;
import com.example.demo.DTO.StudentDto;

@CrossOrigin
@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private  com.example.demo.Service.StudentService studentService;
	
	@PostMapping
    public StudentDto create(@RequestBody StudentDto dto) {
        return studentService.createStudent(dto);
    }

    @GetMapping
    public List<StudentDto> getAll() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public StudentDto getById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PutMapping("/{id}")
    public StudentDto update(@PathVariable Long id, @RequestBody StudentDto dto) {
        return studentService.updateStudent(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    @PostMapping("/enroll")
    public StudentDto enrollStudent(@RequestBody EnrollmentRequest request) {
    	return studentService.enrollStudent(request.getStudentId(), request.getCourseId());
    }
	
	
}
