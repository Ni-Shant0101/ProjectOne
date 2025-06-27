package com.solutions.crm.request;

import java.time.LocalDateTime;

public class UserRequest {

	private String first_name;

	private String last_name;

	private String email;

	private String username;

	private String password;

	private String role;
	
	//private LocalDateTime created_at;

	public UserRequest() {
		super();
	}

	public String getFirst_name() {
		return first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public String getEmail() {
		return email;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}

//	public LocalDateTime getCreated_at() {
//		return created_at;
//	}

}
