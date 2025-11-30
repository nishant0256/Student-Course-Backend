package com.example.demo.DTO;


public class EnrollmentRequest {

	private Long studentId;
	private Long courseId;
	
	public EnrollmentRequest () {}

	public EnrollmentRequest(Long studentId, Long courseId) {
		super();
		this.studentId = studentId;
		this.courseId = courseId;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	
	
}
