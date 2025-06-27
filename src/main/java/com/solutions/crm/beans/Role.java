package com.solutions.crm.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String rollname;
	
	private String roleDescription;

	public Role() {
		super();
	}

	public Role(String rollname) {
		super();
		this.rollname = rollname;
	}
	
	public Role(String rollname, String roleDescription) {
		super();
		this.rollname = rollname;
		this.roleDescription = roleDescription;
	}

	public Role(int id, String rollname, String roleDescription) {
		super();
		this.id = id;
		this.rollname = rollname;
		this.roleDescription = roleDescription;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRollname() {
		return rollname;
	}

	public void setRollname(String rollname) {
		this.rollname = rollname;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	@Override
	public String toString() {
		return rollname;
	}

	
}
