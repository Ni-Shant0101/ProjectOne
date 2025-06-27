package com.solutions.crm.beans;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tasks_count")
	@SequenceGenerator(name = "tasks_count", initialValue = 1, allocationSize = 1)
	private int task_id;

	@NotNull
	@Size(max = 50)
	@Column(length = 50)
	private String task_name;

	@Size(max = 65536)
	@Column(length = 65535, columnDefinition = "Text")
	private String task_description;

	@Column(nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime start_time;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime end_time;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date date;

	@ElementCollection
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private List<LocalDateTime> startTimes = new ArrayList<>();

	@ElementCollection
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private List<LocalDateTime> endTimes = new ArrayList<>();

//	@JsonBackReference
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

	@NotNull
	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "project_id")
	private Project project_id;

//	@JsonManagedReference
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

	@NotNull
	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "user_id")
	private Users user_id;

	private int status;

	public Task() {
		super();
	}

	public Task(int task_id, @NotNull @Size(max = 50) String task_name, @Size(max = 65536) String task_description,
			LocalDateTime start_time, LocalDateTime end_time, Date date, List<LocalDateTime> startTimes,
			List<LocalDateTime> endTimes, @NotNull Project project_id, @NotNull Users user_id, int status) {
		super();
		this.task_id = task_id;
		this.task_name = task_name;
		this.task_description = task_description;
		this.start_time = start_time;
		this.end_time = end_time;
		this.date = date;
		this.startTimes = startTimes;
		this.endTimes = endTimes;
		this.project_id = project_id;
		this.user_id = user_id;
		this.status = status;
	}

	public int getTask_id() {
		return task_id;
	}

	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}

	public String getTask_name() {
		return task_name;
	}

	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}

	public String getTask_description() {
		return task_description;
	}

	public void setTask_description(String task_description) {
		this.task_description = task_description;
	}

	public LocalDateTime getStart_time() {
		return start_time;
	}

	public void setStart_time(LocalDateTime start_time) {
		this.start_time = start_time;
	}

	public LocalDateTime getEnd_time() {
		return end_time;
	}

	public void setEnd_time(LocalDateTime end_time) {
		this.end_time = end_time;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setProject_id(Project project_id) {
		this.project_id = project_id;
	}

	public Project getProject_id() {
		return project_id;
	}

	public Users getUser_id() {
		return user_id;
	}

	public void setUser_id(Users user_id) {
		this.user_id = user_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<LocalDateTime> getStartTimes() {
		return startTimes;
	}

	public void setStartTimes(List<LocalDateTime> startTimes) {
		this.startTimes = startTimes;
	}

	public List<LocalDateTime> getEndTimes() {
		return endTimes;
	}

	public void setEndTimes(List<LocalDateTime> endTimes) {
		this.endTimes = endTimes;
	}

	@Override
	public String toString() {
		return "Task [task_id=" + task_id + ", task_name=" + task_name + ", task_description=" + task_description
				+ ", start_time=" + start_time + ", end_time=" + end_time + ", date=" + date + ", startTimes="
				+ startTimes + ", endTimes=" + endTimes + ", project_id=" + project_id + ", user_id=" + user_id
				+ ", status=" + status + "]";
	}

}
