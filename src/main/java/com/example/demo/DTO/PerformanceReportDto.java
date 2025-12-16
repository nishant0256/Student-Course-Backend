package com.example.demo.DTO;

import java.util.List;
import java.util.Map;

public class PerformanceReportDto {
    private Long studentId;
    private String studentName;
    private Double gpa; // simple percentage/GPA
    private List<Map<String, Object>> courseScores; // [{ courseId, courseTitle, percent, gradeLetter, date }]
    private List<Map<String, Object>> trend; // [{ date, percent }] (for line chart)

    // getters & setters
    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public Double getGpa() { return gpa; }
    public void setGpa(Double gpa) { this.gpa = gpa; }
    public List<Map<String, Object>> getCourseScores() { return courseScores; }
    public void setCourseScores(List<Map<String, Object>> courseScores) { this.courseScores = courseScores; }
    public List<Map<String, Object>> getTrend() { return trend; }
    public void setTrend(List<Map<String, Object>> trend) { this.trend = trend; }
}
