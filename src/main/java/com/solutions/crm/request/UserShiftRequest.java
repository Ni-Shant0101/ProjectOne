package com.solutions.crm.request;

import java.sql.Time;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class UserShiftRequest {
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private int user_id;

    @Column(name = "start_day", length = 50)
    private String startDay;

    @Column(name = "end_day", length = 50)
    private String endDay;

    @Column(name = "start_time")
    private Time startTime;

    @Column(name = "end_time")
    private Time endTime;

	public int getUser_id() {
		return user_id;
	}

	public String getStartDay() {
		return startDay;
	}

	public String getEndDay() {
		return endDay;
	}

	public Time getStartTime() {
		return startTime;
	}

	public Time getEndTime() {
		return endTime;
	}
    
    
}
