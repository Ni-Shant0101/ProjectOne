package com.solutions.crm.request;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class AttendanceRequest {

	@Column(nullable = false)
	private Time loginTime;

	@Column(nullable = false)
	private Time logoutTime;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(nullable = false)
	private LocalDateTime attendanceDate;

	@Column(length = 100)
	private String location;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" }) // Ignore Hibernate-specific fields
	@JoinColumn(name = "user_id")
	private Integer user_id;

	@Column(length = 100)
	private String monthId;

	@Column(nullable = false)
	private Integer financialYear;

	private String status;

	public Time getLoginTime() {
		return loginTime;
	}

	public Time getLogoutTime() {
		return logoutTime;
	}

	public LocalDateTime getAttendanceDate() {
		return attendanceDate;
	}

	public String getLocation() {
		return location;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public String getMonthId() {
		return monthId;
	}

	public Integer getFinancialYear() {
		return financialYear;
	}

	public String getStatus() {
		return status;
	}

}
