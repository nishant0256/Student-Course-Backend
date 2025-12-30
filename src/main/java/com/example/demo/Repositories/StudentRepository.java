package com.example.demo.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // ================= READ ONLY =================

    @Query("""
        SELECT s
        FROM Student s
        LEFT JOIN FETCH s.course
    """)
    List<Student> findAllWithCourse();

    @Query("""
        SELECT s
        FROM Student s
        LEFT JOIN FETCH s.course
        WHERE s.id = :id
    """)
    Optional<Student> findByIdWithCourse(@Param("id") Long id);

    // ================= DELETE (SAFE) =================
    // Uses primary key only, no fetch, no proxy issues
    void deleteById(Long id);
}
