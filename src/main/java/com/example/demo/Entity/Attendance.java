package com.example.demo.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attendance_seq_gen")
    @SequenceGenerator(
            name = "attendance_seq_gen",
            sequenceName = "attendance_seq",
            allocationSize = 1
    )
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(optional = false)
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "att_date", nullable = false)
    private LocalDate attDate = LocalDate.now();

    @Column(nullable = false)
    private String status; // PRESENT, ABSENT, LATE, EXCUSED

    public Attendance() {}

    public Long getId() { return id; }
    public Student getStudent() { return student; }
    public Course getCourse() { return course; }
    public LocalDate getAttDate() { return attDate; }
    public String getStatus() { return status; }

    public void setId(Long id) { this.id = id; }
    public void setStudent(Student student) { this.student = student; }
    public void setCourse(Course course) { this.course = course; }
    public void setAttDate(LocalDate attDate) { this.attDate = attDate; }
    public void setStatus(String status) { this.status = status; }
}
