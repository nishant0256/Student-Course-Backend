package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.DTO.GradeDto;
import com.example.demo.DTO.GradeRequest;
import com.example.demo.Service.GradeService;

@CrossOrigin
@RestController
@RequestMapping("/grades")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @GetMapping
    public List<GradeDto> getAll() {
        return gradeService.getAllGrades();
    }


    @PostMapping
    public GradeDto create(@RequestBody GradeRequest request) {
        return gradeService.createGrade(request);
    }

    @PutMapping("/{id}")
    public GradeDto update(@PathVariable Long id, @RequestBody GradeRequest request) {
        return gradeService.updateGrade(id, request);
    }

    @GetMapping("/{id}")
    public GradeDto getById(@PathVariable Long id) {
        return gradeService.getGrade(id);
    }

    @GetMapping("/student/{studentId}")
    public List<GradeDto> getByStudent(@PathVariable Long studentId) {
        return gradeService.getGradesByStudent(studentId);
    }

    @GetMapping("/course/{courseId}")
    public List<GradeDto> getByCourse(@PathVariable Long courseId) {
        return gradeService.getGradesByCourse(courseId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        gradeService.deleteGrade(id);
    }
}
