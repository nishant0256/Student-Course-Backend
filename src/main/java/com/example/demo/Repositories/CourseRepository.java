package com.example.demo.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    // ✅ THIS METHOD MUST EXIST
    List<Course> findByIsActiveTrue();

    List<Course> findByCategoryAndIsActiveTrueOrderByStartDateAsc(String category);

    List<Course> findByTitleContainingIgnoreCaseAndIsActiveTrue(String keyword);

    List<Course> findByIsNewTrueAndIsActiveTrue();
}
