package com.solutions.crm.beans;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int department_id;
    private String departmentName;
    private Integer departmentStatus;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "user_id")
    private Users user_id;
	public Department(int department_id, String departmentName, Integer departmentStatus, Users user_id) {
		super();
		this.department_id = department_id;
		this.departmentName = departmentName;
		this.departmentStatus = departmentStatus;
		this.user_id = user_id;
	}
	public Department() {
		super();
	}
	
	
	public int getDepartment_id() {
		return department_id;
	}
	
	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}
	
	public String getDepartmentName() {
		return departmentName;
	}
	
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	public Integer getDepartmentStatus() {
		return departmentStatus;
	}
	
	public void setDepartmentStatus(Integer departmentStatus) {
		this.departmentStatus = departmentStatus;
	}
	
	public Users getUser_id() {
		return user_id;
	}
	
	public void setUser_id(Users user_id) {
		this.user_id = user_id;
	}

    
}

