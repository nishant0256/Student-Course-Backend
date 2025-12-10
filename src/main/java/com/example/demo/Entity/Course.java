package com.example.demo.Entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
public class Course {

    @Id
    private Long id;

    private String title;
    private String description;

    @ManyToMany(mappedBy = "courses")
    @JsonBackReference   // ✅ FIX #2 (BREAKS LOOP)
    private List<Student> students = new ArrayList<>();

    public Course() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

    // getters & setters (UNCHANGED)
    
}
