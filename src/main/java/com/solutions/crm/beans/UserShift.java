package com.solutions.crm.beans;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Time;

@Entity
@Table(name = "User_Shift")
public class UserShift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int shift_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Ignore Hibernate-specific fields

    @JoinColumn(name = "user_id")
    private Users user_id;

    @Column(name = "start_day", length = 50)
    private String startDay;

    @Column(name = "end_day", length = 50)
    private String endDay;

    @Column(name = "start_time")
    private Time startTime;

    @Column(name = "end_time")
    private Time endTime;
    
    private int status;

	public UserShift(int shift_id, Users user_id, String startDay, String endDay, Time startTime,int status, Time endTime) {
		super();
		this.shift_id = shift_id;
		this.user_id = user_id;
		this.startDay = startDay;
		this.endDay = endDay;
		this.status=status;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public UserShift() {
		super();
	}
	
	

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getShift_id() {
		return shift_id;
	}

	public void setShift_id(int shift_id) {
		this.shift_id = shift_id;
	}

	public Users getUser_id() {
		return user_id;
	}

	public void setUser_id(Users user_id) {
		this.user_id = user_id;
	}

	public String getStartDay() {
		return startDay;
	}

	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}

	public String getEndDay() {
		return endDay;
	}

	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

    
}

