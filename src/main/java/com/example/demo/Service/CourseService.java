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

    // ================= CREATE =================
    public CourseDto create(CourseDto dto) {
        Course course = mapper.map(dto, Course.class);

        // defaults
        if (course.getIsActive() == null) course.setIsActive(true);
        if (course.getIsNew() == null) course.setIsNew(false);

        Course saved = courseRepo.save(course);
        return mapper.map(saved, CourseDto.class);
    }

    // ================= UPDATE =================
    public CourseDto update(Long id, CourseDto dto) {

        Course course = courseRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        // update only safe fields
        course.setTitle(dto.getTitle());
        course.setSubtitle(dto.getSubtitle());
        course.setCategory(dto.getCategory());
        course.setLanguage(dto.getLanguage());
        course.setDescription(dto.getDescription());
        course.setImageUrl(dto.getImageUrl());

        course.setPrice(dto.getPrice());
        course.setOriginalPrice(dto.getOriginalPrice());
        course.setDiscountPercent(dto.getDiscountPercent());

        course.setStartDate(dto.getStartDate());
        course.setEndDate(dto.getEndDate());

        // optional flags
        if (dto.getIsNew() != null) {
            course.setIsNew(dto.getIsNew());
        }
        if (dto.getIsActive() != null) {
            course.setIsActive(dto.getIsActive());
        }

        Course updated = courseRepo.save(course);
        return mapper.map(updated, CourseDto.class);
    }

    // ================= GET BY ID =================
    public CourseDto getById(Long id) {
        Course course = courseRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        return mapper.map(course, CourseDto.class);
    }

    // ================= GET ALL ACTIVE =================
    public List<CourseDto> getAll() {
        return courseRepo.findByIsActiveTrue()
                .stream()
                .map(c -> mapper.map(c, CourseDto.class))
                .toList();
    }

    // ================= DELETE (SOFT DELETE) =================
    public void delete(Long id) {
        Course course = courseRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        course.setIsActive(false);
        courseRepo.save(course);
    }
}
