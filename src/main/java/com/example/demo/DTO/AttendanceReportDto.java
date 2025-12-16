package com.example.demo.DTO;

public class AttendanceReportDto {

    private Long studentId;
    private String studentName;

    private long totalClasses;
    private long present;
    private long absent;
    private long late;
    private long excused;

    private double attendancePercentage;

    public AttendanceReportDto() {}

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public long getTotalClasses() { return totalClasses; }
    public void setTotalClasses(long totalClasses) { this.totalClasses = totalClasses; }

    public long getPresent() { return present; }
    public void setPresent(long present) { this.present = present; }

    public long getAbsent() { return absent; }
    public void setAbsent(long absent) { this.absent = absent; }

    public long getLate() { return late; }
    public void setLate(long late) { this.late = late; }

    public long getExcused() { return excused; }
    public void setExcused(long excused) { this.excused = excused; }

    public double getAttendancePercentage() { return attendancePercentage; }
    public void setAttendancePercentage(double attendancePercentage) {
        this.attendancePercentage = attendancePercentage;
    }
}
