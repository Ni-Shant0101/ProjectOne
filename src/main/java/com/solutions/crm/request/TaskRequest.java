package com.solutions.crm.request;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;


public class TaskRequest {

	@NotNull
	@Size(max = 50)
	@Column(length = 50)
	private String task_name;

	@Size(max = 65536)
	@Column(length = 65535, columnDefinition = "Text")
	private String task_description;

	@Column(nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime start_time;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime end_time;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date date;
	
	@ElementCollection
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private List<LocalDateTime> startTimes = new ArrayList<>();

    @ElementCollection
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private List<LocalDateTime> endTimes = new ArrayList<>();

//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//    @JsonBackReference
	@NotNull
	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "project_id")
	private int project_id;

//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//    @JsonManagedReference
	@NotNull
	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "user_id")
	private int user_id;

	private int status;

	public String getTask_name() {
		return task_name;
	}

	public String getTask_description() {
		return task_description;
	}

	public LocalDateTime getStart_time() {
		return start_time;
	}

	public LocalDateTime getEnd_time() {
		return end_time;
	}

	public Date getDate() {
		return date;
	}

	public List<LocalDateTime> getStartTimes() {
		return startTimes;
	}

	public List<LocalDateTime> getEndTimes() {
		return endTimes;
	}

	

	public int getProject_id() {
		return project_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public int getStatus() {
		return status;
	}
	
	
	
}
