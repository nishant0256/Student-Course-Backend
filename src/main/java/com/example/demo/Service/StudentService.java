package com.example.demo.Service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.StudentDto;
import com.example.demo.Entity.Course;
import com.example.demo.Entity.Student;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Repositories.CourseRepository;
import com.example.demo.Repositories.StudentRepository;

@Service
public class StudentService  {

	@Autowired
	private StudentRepository studentRepo;
	@Autowired
	private CourseRepository courseRepo;
	@Autowired
	private ModelMapper mapper;
//	CREATE
	public StudentDto createStudent(StudentDto dto) {
		Student student = mapper.map(dto, Student.class);
		Student saved = studentRepo.save(student);
		return mapper.map(saved, StudentDto.class);			
	}
	
//	UPDATE
	public StudentDto updateStudent(Long id, StudentDto dto) {
		Student student = studentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        student.setName(dto.getName());
        student.setAge(dto.getAge());
        student.setEmail(dto.getEmail());

        Student updated = studentRepo.save(student);
        return mapper.map(updated, StudentDto.class);
	}
	
	// GET BY ID
    public StudentDto getStudentById(Long id) {
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        return mapper.map(student, StudentDto.class);
    }

    // GET ALL
    public List<StudentDto> getAllStudents() {
        return studentRepo.findAll()
                .stream()
                .map(s -> mapper.map(s, StudentDto.class))
                .toList();
    }

 // DELETE
    public void deleteStudent(Long id) {
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        studentRepo.delete(student);
    }

//ENROLL A STUDENT INTO A COURSE
    public StudentDto enrollStudent(Long studentId, Long courseId) {
    	Student student = studentRepo.findById(studentId)
    			.orElseThrow(()  -> new ResourceNotFoundException("Student Not Found"));
    	Course course = courseRepo.findById(courseId)
    			.orElseThrow(()  -> new ResourceNotFoundException("Course Not Found"));
    	student.getCourses().add(course);
    	studentRepo.save(student);
    	return mapper.map(student, StudentDto.class);
    			
    }

}
	