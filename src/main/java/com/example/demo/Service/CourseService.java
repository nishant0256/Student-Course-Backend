package com.example.demo.Service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.CourseDto;
import com.example.demo.Entity.Course;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Repositories.CourseRepository;

@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepo;
	@Autowired
	private ModelMapper mapper;
	
	
	// CREATE
    public CourseDto createCourse(CourseDto dto) {
        Course course = mapper.map(dto, Course.class);
        Course saved = courseRepo.save(course);
        return mapper.map(saved, CourseDto.class);
    }

    // UPDATE
    public CourseDto updateCourse(Long id, CourseDto dto) {
        Course course = courseRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        course.setTitle(dto.getTitle());
        course.setDescription(dto.getDescription());

        Course updated = courseRepo.save(course);
        return mapper.map(updated, CourseDto.class);
    }

    // GET BY ID
    public CourseDto getCourseById(Long id) {
        Course course = courseRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        return mapper.map(course, CourseDto.class);
    }

    // GET ALL
    public List<CourseDto> getAllCourses() {
        return courseRepo.findAll()
                .stream()
                .map(c -> mapper.map(c, CourseDto.class))
                .toList();
    }

    // DELETE
    public void deleteCourse(Long id) {
    	Course course = courseRepo.findById(id)
    			.orElseThrow(()  -> new ResourceNotFoundException("Course Not Found"));
    	courseRepo.delete(course);
    }

}
