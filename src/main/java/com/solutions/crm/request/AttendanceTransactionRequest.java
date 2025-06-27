package com.solutions.crm.request;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Column;
import com.fasterxml.jackson.annotation.JsonFormat;

public class AttendanceTransactionRequest {

    private int user_id;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
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

	public int getUser_id() {
		return user_id;
	}

	
	public LocalDateTime getAttendance_date() {
		return attendance_date;
	}


	public Time getLogin_time() {
		return login_time;
	}

	public Time getLogout_time() {
		return logout_time;
	}

	public String getLocation() {
		return location;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public String getStatus() {
		return status;
	}

	public void setPhotoUrl(String filePath) {
		// TODO Auto-generated method stub
		
	}
    
    
	
}
