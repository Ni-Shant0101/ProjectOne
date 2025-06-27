package com.solutions.crm.beans;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Attendance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int attendance_id;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(nullable = false)
	private LocalDateTime attendanceDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" }) // Ignore Hibernate-specific fields
	@JoinColumn(name = "attendanceTransactionId")
	private AttendanceTransaction attendanceTransaction;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" }) // Ignore Hibernate-specific fields
	@JoinColumn(name = "user_id")
	private Users user_id;

	@Column(length = 100)
	private String monthId;

	@Column(nullable = false)
	private Integer financialYear;

	private String status;

	public Attendance(int attendance_id, LocalDateTime attendanceDate, @NotNull Users user_id, String monthId,
			Integer financialYear, String status) {
		super();
		this.attendance_id = attendance_id;
		this.attendanceDate = attendanceDate;
		this.user_id = user_id;
		this.monthId = monthId;
		this.financialYear = financialYear;
		this.status = status;
	}

	public Attendance() {
		super();
	}

	public int getAttendance_id() {
		return attendance_id;
	}

	public void setAttendance_id(int attendance_id) {
		this.attendance_id = attendance_id;
	}

	public LocalDateTime getAttendanceDate() {
		return attendanceDate;
	}

	public void setAttendanceDate(LocalDateTime attendanceDate) {
		this.attendanceDate = attendanceDate;
	}

	public Users getUser_id() {
		return user_id;
	}

	public void setUser_id(Users user_id) {
		this.user_id = user_id;
	}

	public String getMonthId() {
		return monthId;
	}

	public void setMonthId(String monthId) {
		this.monthId = monthId;
	}

	public Integer getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(Integer financialYear) {
		this.financialYear = financialYear;
	}

	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public AttendanceTransaction getAttendanceTransaction() {
		return attendanceTransaction;
	}

	public void setAttendanceTransaction(AttendanceTransaction attendanceTransaction) {
		this.attendanceTransaction = attendanceTransaction;
	}

}
