package com.solutions.crm.beans;
import java.time.LocalDate;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Leave_Transaction")
public class LeaveTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int transaction_id;

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
    private Users user_id;

	public LeaveTransaction(int transaction_id, String leaveType, String leaveSubject, LocalDate fromDate,
			LocalDate toDate, String leaveReason, int totalDays, int status, Users user_id) {
		super();
		this.transaction_id = transaction_id;
		this.leaveType = leaveType;
		this.leaveSubject = leaveSubject;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.leaveReason = leaveReason;
		this.totalDays = totalDays;
		this.status = status;
		this.user_id = user_id;
	}

	public LeaveTransaction() {
		super();
	}


	public int getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public String getLeaveSubject() {
		return leaveSubject;
	}

	public void setLeaveSubject(String leaveSubject) {
		this.leaveSubject = leaveSubject;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	public String getLeaveReason() {
		return leaveReason;
	}

	public void setLeaveReason(String leaveReason) {
		this.leaveReason = leaveReason;
	}

	public int getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(int totalDays) {
		this.totalDays = totalDays;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Users getUser_id() {
		return user_id;
	}

	public void setUser_id(Users user_id) {
		this.user_id = user_id;
	}
    
    
}

