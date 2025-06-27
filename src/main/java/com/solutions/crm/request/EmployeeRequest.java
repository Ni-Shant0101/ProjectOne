package com.solutions.crm.request;

import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class EmployeeRequest {

	 private String name;
	    private String email;
	    private String image;
	    private String gender;
	    private String mobile;
	    private String alternateMobile;
	    private String address;
	    private String designation;
	    private String aadharNo;
	    private String panNo;
	    private int status;

	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	    @Column(updatable = false)
		private LocalDateTime created_at;

		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
		private LocalDateTime updated_at;
		
	    @Column(name = "joining_date")
	    private Date joiningDate;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	    @JoinColumn(name = "user_id")
	    private int user_id;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	    @JoinColumn(name = "department_id")
	    private int department_id;

		public String getName() {
			return name;
		}

		public String getEmail() {
			return email;
		}

		public String getImage() {
			return image;
		}

		public String getGender() {
			return gender;
		}

		public String getMobile() {
			return mobile;
		}

		public String getAlternateMobile() {
			return alternateMobile;
		}

		public String getAddress() {
			return address;
		}

		public String getDesignation() {
			return designation;
		}

		public String getAadharNo() {
			return aadharNo;
		}

		public String getPanNo() {
			return panNo;
		}

		public int getStatus() {
			return status;
		}

		public LocalDateTime getCreated_at() {
			return created_at;
		}

		public LocalDateTime getUpdated_at() {
			return updated_at;
		}

		public Date getJoiningDate() {
			return joiningDate;
		}

		public int getUser_id() {
			return user_id;
		}

		public int getDepartment_id() {
			return department_id;
		}

		public void setImage(String image) {
			this.image = image;
		}

		
	    
	    
}
