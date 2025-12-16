package com.example.demo.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Grade;

public interface GradeRepository extends JpaRepository<Grade, Long> {

    List<Grade> findByStudent_Id(Long studentId);

    List<Grade> findByCourse_Id(Long courseId);
}
