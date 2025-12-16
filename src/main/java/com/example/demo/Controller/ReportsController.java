package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.DTO.AttendanceReportDto;
import com.example.demo.DTO.EnrollmentSummaryDto;
import com.example.demo.DTO.PerformanceReportDto;
import com.example.demo.Service.ReportsService;

@CrossOrigin
@RestController
@RequestMapping("/reports")
public class ReportsController {

    @Autowired
    private ReportsService reportsService;

    @GetMapping("/student/{id}/performance")
    public PerformanceReportDto studentPerformance(@PathVariable Long id) {
        return reportsService.getPerformanceReportForStudent(id);
    }

    @GetMapping("/student/{id}/attendance")
    public AttendanceReportDto studentAttendance(@PathVariable Long id) {
        return reportsService.getAttendanceReportForStudent(id);
    }

    @GetMapping("/courses/enrollment-summary")
    public List<EnrollmentSummaryDto> enrollmentSummary() {
        return reportsService.getEnrollmentSummary();
    }
}
