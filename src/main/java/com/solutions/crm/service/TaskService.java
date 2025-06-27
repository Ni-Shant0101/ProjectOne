package com.solutions.crm.service;

import java.util.List;
import java.util.Optional;

import com.solutions.crm.beans.Task;
import com.solutions.crm.beans.TaskTimeInfo;
import com.solutions.crm.request.TaskRequest;

public interface TaskService {

	Task saveTask(TaskRequest taskrequest);

	Task updateTask(TaskRequest taskrequest, int task_id);

	List<Task> getallTask();

	Optional<Task> findById(int task_id);

	void startTask(int task_id);

	void stopTask(int task_id);

	TaskTimeInfo getTaskTimes(int task_id);
}
