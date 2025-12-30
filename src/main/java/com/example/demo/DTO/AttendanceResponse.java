package com.example.demo.DTO;

import java.time.LocalDate;

public class AttendanceResponse {

    private Long id;
    private Long studentId;
    private Long courseId;
    private String status;
    private LocalDate date;

    public AttendanceResponse() {}

    public AttendanceResponse(Long id, Long studentId, Long courseId, String status, LocalDate date) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.status = status;
        this.date = date;
    }

    public Long getId() { return id; }
    public Long getStudentId() { return studentId; }
    public Long getCourseId() { return courseId; }
    public String getStatus() { return status; }
    public LocalDate getDate() { return date; }

    public void setId(Long id) { this.id = id; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }
    public void setStatus(String status) { this.status = status; }
    public void setDate(LocalDate date) { this.date = date; }
}
