package com.example.demo.Controller;

import com.example.demo.DTO.AttendanceRequest;
import com.example.demo.DTO.AttendanceResponse;
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

    // ✅ MARK ATTENDANCE
    @PostMapping("/mark")
    public AttendanceResponse markAttendance(@RequestBody AttendanceRequest request) {

        Attendance saved = attendanceService.markAttendance(
                request.getStudentId(),
                request.getCourseId(),
                request.getStatus(),
                request.getDate()
        );

        return mapToResponse(saved);
    }

    // ✅ GET ALL
    @GetMapping
    public List<AttendanceResponse> getAllAttendance() {
        return attendanceService.getAllAttendance()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // ✅ GET BY STUDENT (FIXED ❗)
    @GetMapping("/student/{studentId}")
    public List<AttendanceResponse> getByStudent(@PathVariable Long studentId) {
        return attendanceService.getByStudent(studentId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // ✅ GET BY COURSE (FIXED ❗)
    @GetMapping("/course/{courseId}")
    public List<AttendanceResponse> getByCourse(@PathVariable Long courseId) {
        return attendanceService.getByCourse(courseId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @DeleteMapping("/{id}")
    public String deleteAttendance(@PathVariable Long id) {
        attendanceService.deleteAttendance(id);
        return "Attendance deleted successfully";
    }

    // 🔁 CENTRAL MAPPER (VERY IMPORTANT)
    private AttendanceResponse mapToResponse(Attendance a) {
        return new AttendanceResponse(
                a.getId(),
                a.getStudent().getId(),
                a.getCourse().getId(),
                a.getStatus(),
                a.getAttDate()
        );
    }
}
