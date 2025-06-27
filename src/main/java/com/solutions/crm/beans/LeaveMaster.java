package com.solutions.crm.beans;

import javax.persistence.*;

@Entity
@Table(name = "Leave_Master")
public class LeaveMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leave_id")
    private int leave_id;

    @Column(name = "leave_name", nullable = false)
    private String leaveName;

    @Column(name = "available", nullable = false)
    private String available;

    // Constructors
    public LeaveMaster() {
    }

    public LeaveMaster(String leaveName, String available) {
        this.leaveName = leaveName;
        this.available = available;
    }

    

    public int getLeave_id() {
		return leave_id;
	}

	public void setLeave_id(int leave_id) {
		this.leave_id = leave_id;
	}

	public String getLeaveName() {
        return leaveName;
    }

    public void setLeaveName(String leaveName) {
        this.leaveName = leaveName;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }
}

