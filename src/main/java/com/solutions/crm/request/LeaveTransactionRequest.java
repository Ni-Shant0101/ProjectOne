package com.solutions.crm.request;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class LeaveTransactionRequest {

    @Column(name = "leave_type", length = 50)
    private String leaveType;

    @Column(name = "leave_subject", length = 100)
    private String leaveSubject;

    @Column(name = "from_date")
    private LocalDate fromDate;

    @Column(name = "to_date")
    private LocalDate toDate;

    @Column(name = "leave_reason", columnDefinition = "TEXT")
    private String leaveReason;

    @Column(name = "total_days")
    private int totalDays;

    @Column(name = "status")
    private int status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "user_id")
    private int user_id;

	public String getLeaveType() {
		return leaveType;
	}

	public String getLeaveSubject() {
		return leaveSubject;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public String getLeaveReason() {
		return leaveReason;
	}

	public int getTotalDays() {
		return totalDays;
	}

	public int getStatus() {
		return status;
	}

	public int getUser_id() {
		return user_id;
	}
    
    
}
