package com.example.demo.Entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;


@Entity
@Table(name = "course") // explicit table name (lowercase 'course')
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_seq_gen")
    @SequenceGenerator(name = "course_seq_gen", sequenceName = "course_seq", allocationSize = 1)
    private Long id;

    private String title;
    private String description;

    @ManyToMany(mappedBy = "courses")
    @JsonBackReference
    private List<Student> students = new ArrayList<>();

    public Course() {}
    

    // (Optional constructor – safe to keep)
    public Course(String title, String description) {
        this.title = title;
        this.description = description;
    }
    // getters / setters (keep your existing ones)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public List<Student> getStudents() { return students; }
    public void setStudents(List<Student> students) { this.students = students; }
}
