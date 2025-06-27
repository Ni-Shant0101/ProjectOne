package com.solutions.crm.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "projects_count")
	@SequenceGenerator(name = "projects_count", initialValue = 1, allocationSize = 1)
	private int project_id;

	@NotNull
	@Size(max = 50)
	@Column(length = 50)
	private String project_name;

	@Size(max = 65536)
	@Column(length = 65535, columnDefinition = "Text")
	private String project_description;

	@Column(nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date start_date;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date end_date;

	@Column(length = 2)
	private int status;

	public Project() {
		super();
	}

	public Project(int project_id, String project_name, String project_description, Date start_date, Date end_date,
			int status) {
		super();
		this.project_id = project_id;
		this.project_name = project_name;
		this.project_description = project_description;
		this.start_date = start_date;
		this.end_date = end_date;
		this.status = status;
	}

	public int getProject_id() {
		return project_id;
	}

	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getProject_description() {
		return project_description;
	}

	public void setProject_description(String project_description) {
		this.project_description = project_description;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Project [project_id=" + project_id + ", project_name=" + project_name + ", project_description="
				+ project_description + ", start_date=" + start_date + ", end_date=" + end_date + ", status=" + status
				+ "]";
	}

}
