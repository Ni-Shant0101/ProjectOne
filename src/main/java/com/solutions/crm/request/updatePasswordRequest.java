package com.solutions.crm.request;

public class updatePasswordRequest {

	private String email;

	private String password;

	private int institute_id;

	public updatePasswordRequest(String email, String password, int institute_id) {
		super();
		this.email = email;
		this.password = password;
		this.institute_id = institute_id;
	}

	public updatePasswordRequest() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public int getInstitute_id() {
		return institute_id;
	}

}
