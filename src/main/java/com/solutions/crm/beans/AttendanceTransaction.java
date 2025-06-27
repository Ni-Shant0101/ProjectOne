package com.solutions.crm.beans;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class AttendanceTransaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mapped_id; // auto-increment

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" }) // Ignore Hibernate-specific fields
	@JoinColumn(name = "user_id")
	private Users user_id;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(columnDefinition = "DATETIME" , updatable = false)
	private LocalDateTime attendance_date;

	@JsonFormat(pattern = "HH:mm:ss")
	private Time login_time;

	@JsonFormat(pattern = "HH:mm:ss")
	private Time logout_time; // initially null

	@Column(length = 100)
	private String location; // format of latitude

	@Column(length = 100)
	private String photoUrl;

	@Column(length = 2)
	private String status;

	public AttendanceTransaction(int mapped_id, @NotNull Users user_id, LocalDateTime attendance_date, Time login_time,
			Time logout_time, String location, String photoUrl, String status) {
		super();
		this.mapped_id = mapped_id;
		this.user_id = user_id;
		this.attendance_date = attendance_date;
		this.login_time = login_time;
		this.logout_time = logout_time;
		this.location = location;
		this.photoUrl = photoUrl;
		this.status = status;
	}

	public AttendanceTransaction() {
		super();
	}

	public int getMapped_id() {
		return mapped_id;
	}

	public void setMapped_id(int mapped_id) {
		this.mapped_id = mapped_id;
	}

	public Users getUser_id() {
		return user_id;
	}

	public void setUser_id(Users user_id) {
		this.user_id = user_id;
	}

	public LocalDateTime getAttendance_date() {
		return attendance_date;
	}

	public void setAttendance_date(LocalDateTime attendance_date) {
		this.attendance_date = attendance_date;
	}

	public Time getLogin_time() {
		return login_time;
	}

	public void setLogin_time(Time login_time) {
		this.login_time = login_time;
	}

	public Time getLogout_time() {
		return logout_time;
	}

	public void setLogout_time(Time logout_time) {
		this.logout_time = logout_time;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
