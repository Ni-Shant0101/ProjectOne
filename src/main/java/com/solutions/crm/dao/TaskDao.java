package com.solutions.crm.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solutions.crm.beans.Project;
import com.solutions.crm.beans.Task;
import com.solutions.crm.beans.TaskTimeInfo;
import com.solutions.crm.beans.Users;
import com.solutions.crm.repository.ProjectRepository;
import com.solutions.crm.repository.TaskRepository;
import com.solutions.crm.repository.UsersRepository;
import com.solutions.crm.request.TaskRequest;
import com.solutions.crm.service.TaskService;

@Service
public class TaskDao implements TaskService {

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	ProjectRepository projectRepository;

	@Override
	public Task saveTask(TaskRequest taskrequest) {
		try {
			LocalDateTime now = LocalDateTime.now();
			Users user = usersRepository.findById(taskrequest.getUser_id())
					.orElseThrow(() -> new EntityNotFoundException("user not found"));

			Project project = projectRepository.findById(taskrequest.getProject_id())
					.orElseThrow(() -> new EntityNotFoundException("user not found"));

			Task task = new Task();
			task.setTask_name(taskrequest.getTask_name());
			task.setTask_description(taskrequest.getTask_description());
			task.setStatus(1);
			task.setDate(taskrequest.getDate());
			task.setUser_id(user);
			task.setProject_id(project);
//            task.getStartTimes().add(task.getStart_time()); 
//    		task.setStart_time(taskrequest.getStart_time());
			task.setStart_time(now);
			task.setEnd_time(taskrequest.getEnd_time());
			task.getStartTimes().add(task.getStart_time());
			return taskRepository.save(task);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Task updateTask(TaskRequest taskrequest, int task_id) {
		try {
//            task.setTask_id(task_id);
			LocalDateTime now = LocalDateTime.now();
			Users user = usersRepository.findById(taskrequest.getUser_id())
					.orElseThrow(() -> new EntityNotFoundException("user not found"));

			Project project = projectRepository.findById(taskrequest.getProject_id())
					.orElseThrow(() -> new EntityNotFoundException("user not found"));

			Task task = new Task();
			task.setTask_name(taskrequest.getTask_name());
			task.setTask_description(taskrequest.getTask_description());
			task.setStatus(1);
			task.setDate(taskrequest.getDate());
			task.setUser_id(user);
			task.setProject_id(project);
			task.setStart_time(taskrequest.getStart_time());
			task.setEnd_time(taskrequest.getEnd_time());
			task.getStartTimes().add(task.getStart_time());
			task.setStatus(1);
			return taskRepository.save(task);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Task> getallTask() {
		return taskRepository.findAll();
	}

	@Override
	public Optional<Task> findById(int task_id) {
		return taskRepository.findById(task_id);
	}

	@Override
	public void startTask(int task_id) {
		Optional<Task> optionalTask = taskRepository.findById(task_id);
		if (optionalTask.isPresent()) {
			Task task = optionalTask.get();
			task.getStartTimes().add(LocalDateTime.now()); // Add current time to start times
			taskRepository.save(task);
		}
	}

	@Override
	public void stopTask(int task_id) {
		Optional<Task> optionalTask = taskRepository.findById(task_id);
		if (optionalTask.isPresent()) {
			Task task = optionalTask.get();
			task.getEndTimes().add(LocalDateTime.now()); // Add current time to end times
			taskRepository.save(task);
		}
	}

	@Override
	public TaskTimeInfo getTaskTimes(int task_id) {
		Optional<Task> optionalTask = taskRepository.findById(task_id);
		TaskTimeInfo taskTimeInfo = new TaskTimeInfo();
		if (optionalTask.isPresent()) {
			Task task = optionalTask.get();
			taskTimeInfo.setStartTimes(task.getStartTimes());
			taskTimeInfo.setEndTimes(task.getEndTimes());
			taskTimeInfo.calculateTotalDurationAndBreakTime();
		}
		return taskTimeInfo;
	}
}
