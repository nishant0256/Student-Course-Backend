package com.example.demo.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.demo.DTO.CourseDto;
import com.example.demo.Service.CourseService;

@RestController
@RequestMapping("/courses")
@CrossOrigin(origins = "*")
public class CourseController {

    private final CourseService courseService;

    // ✅ Constructor Injection (BEST PRACTICE)
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // ✅ CREATE COURSE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseDto createCourse(@RequestBody CourseDto dto) {
        return courseService.createCourse(dto);
    }

    // ✅ UPDATE COURSE
    @PutMapping("/{id}")
    public CourseDto updateCourse(@PathVariable Long id,
                                  @RequestBody CourseDto dto) {
        return courseService.updateCourse(id, dto);
    }

    // ✅ GET COURSE BY ID
    @GetMapping("/{id}")
    public CourseDto getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    // ✅ GET ALL COURSES
    @GetMapping
    public List<CourseDto> getAllCourses() {
        return courseService.getAllCourses();
    }

    // ✅ DELETE COURSE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }
}
