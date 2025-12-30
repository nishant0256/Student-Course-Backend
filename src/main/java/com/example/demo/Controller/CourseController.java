package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.DTO.CourseDto;
import com.example.demo.Service.CourseService;

@CrossOrigin
@RestController
@RequestMapping({"/courses","/batches"})
public class CourseController {

    @Autowired
    private CourseService service;

    @PostMapping
    public CourseDto create(@RequestBody CourseDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public CourseDto update(@PathVariable Long id, @RequestBody CourseDto dto) {
        return service.update(id, dto);
    }

    @GetMapping("/{id}")
    public CourseDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<CourseDto> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
