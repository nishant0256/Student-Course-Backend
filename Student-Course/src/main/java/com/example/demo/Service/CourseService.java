package com.example.demo.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.CourseDto;
import com.example.demo.Entity.Course;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Repositories.CourseRepository;

@Service
public class CourseService {

    private final CourseRepository courseRepo;
    private final ModelMapper mapper;

    public CourseService(CourseRepository courseRepo, ModelMapper mapper) {
        this.courseRepo = courseRepo;
        this.mapper = mapper;
    }

    // ✅ CREATE COURSE
    public CourseDto createCourse(CourseDto dto) {
        Course course = mapper.map(dto, Course.class);
        Course saved = courseRepo.save(course);
        return mapCourseToDto(saved);
    }

    // ✅ UPDATE COURSE
    public CourseDto updateCourse(Long id, CourseDto dto) {

        Course course = courseRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Course not found with id: " + id)
                );

        course.setTitle(dto.getTitle());
        course.setDescription(dto.getDescription());

        Course updated = courseRepo.save(course);
        return mapCourseToDto(updated);
    }

    // ✅ GET COURSE BY ID
    public CourseDto getCourseById(Long id) {

        Course course = courseRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Course not found with id: " + id)
                );

        return mapCourseToDto(course);
    }

    // ✅ GET ALL COURSES
    public List<CourseDto> getAllCourses() {
        return courseRepo.findAll()
                .stream()
                .map(this::mapCourseToDto)
                .collect(Collectors.toList());
    }

    // ✅ DELETE COURSE
    public void deleteCourse(Long id) {
        Course course = courseRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Course not found with id: " + id)
                );

        courseRepo.delete(course);
    }

    // ✅ ✅ MISSING METHOD (THIS FIXES YOUR ERROR)
    private CourseDto mapCourseToDto(Course course) {
        return mapper.map(course, CourseDto.class);
    }
}
