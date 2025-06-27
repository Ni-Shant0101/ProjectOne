package com.solutions.crm.request;

public class verifyOtpRequest {

	private String email;

	private int otp;

	public verifyOtpRequest(String email, int otp) {
		super();
		this.email = email;
		this.otp = otp;
	}

	public verifyOtpRequest() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public int getOtp() {
		return otp;
	}

}
