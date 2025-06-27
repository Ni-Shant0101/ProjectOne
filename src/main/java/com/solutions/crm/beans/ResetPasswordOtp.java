package com.solutions.crm.beans;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class ResetPasswordOtp {

	private static final long OTP_VALID_DURATION = 24 * 60 * 60 * 1000; // 24 Hrs

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "otp_count")
	@SequenceGenerator(name = "otp_count", initialValue = 1, allocationSize = 1)
	@Column(length = 16)
	private int id;

	@JsonManagedReference
	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "user_id")
	private Users user;

	@Column(length = 32)
	private String user_email;

	@Column(length = 16)
	private int otp;

	@Column(length = 2)
	private int status;

	@Column(length = 100)
	private String reset_token;

	@Column(name = "otp_requested_time")
	private LocalDateTime otpRequestedTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public LocalDateTime getOtpRequestedTime() {
		return otpRequestedTime;
	}

	public void setOtpRequestedTime(LocalDateTime otpRequestedTime) {
		this.otpRequestedTime = otpRequestedTime;
	}

	public String getReset_token() {
		return reset_token;
	}

	public void setReset_token(String reset_token) {
		this.reset_token = reset_token;
	}

	public boolean isOTPRequired() {
		if (this.getOtp() == 0) {
			return false;
		}

		long currentTimeInMillis = System.currentTimeMillis();

		ZonedDateTime zdt = ZonedDateTime.of(this.otpRequestedTime, ZoneId.systemDefault());
		long otpRequestedTimeInMillis = zdt.toInstant().toEpochMilli();

		if (otpRequestedTimeInMillis + OTP_VALID_DURATION < currentTimeInMillis) {
			// OTP expires
			return false;
		}

		return true;
	}

}
