package com.example.demo.Controller;

import com.example.demo.DTO.AttendanceRequest;
import com.example.demo.Entity.Attendance;
import com.example.demo.Service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
@CrossOrigin
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/mark")
    public Attendance markAttendance(@RequestBody AttendanceRequest request) {
        return attendanceService.markAttendance(
                request.getStudentId(),
                request.getCourseId(),
                request.getStatus(),
                request.getDate()
        );
    }

    @GetMapping
    public List<Attendance> getAllAttendance() {
        return attendanceService.getAllAttendance();
    }

    @GetMapping("/student/{studentId}")
    public List<Attendance> getByStudent(@PathVariable Long studentId) {
        return attendanceService.getByStudent(studentId);
    }

    @GetMapping("/course/{courseId}")
    public List<Attendance> getByCourse(@PathVariable Long courseId) {
        return attendanceService.getByCourse(courseId);
    }

    @DeleteMapping("/{id}")
    public String deleteAttendance(@PathVariable Long id) {
        attendanceService.deleteAttendance(id);
        return "Attendance deleted successfully";
    }
}
