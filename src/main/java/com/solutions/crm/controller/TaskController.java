package com.solutions.crm.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solutions.crm.beans.Task;
import com.solutions.crm.beans.TaskTimeInfo;
import com.solutions.crm.commom.responses.JsonResponses;
import com.solutions.crm.request.TaskRequest;
import com.solutions.crm.service.TaskService;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping
    public Map<String, Object> getTask() {
        List<Task> allTask = taskService.getallTask();

        if (allTask.isEmpty()) {
            return JsonResponses.generateResponse1(false, allTask, "List is empty");
        } else {
            return JsonResponses.generateResponse1(true, allTask, "Task Details Get Successfully");
        }
    }

    @PostMapping
    public ResponseEntity<?> addTask(@RequestBody TaskRequest task) {
        try {
            Task savedTask = taskService.saveTask(task);

            if (savedTask != null) {
                return ResponseEntity.ok().body(JsonResponses.generateResponse1(true, savedTask, "Task Added Successfully"));
            } else {
                return ResponseEntity.badRequest().body(JsonResponses.generateResponse1(false, savedTask, "Some Data is Null or Invalid"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request");
        }
    }

    @GetMapping("/edit/{task_id}")
    public Map<String, Object> findUserById(@PathVariable int task_id) {
        Optional<Task> OneUser = taskService.findById(task_id);
        if (OneUser.isPresent()) {
            return JsonResponses.generateResponse1(true, OneUser, "Project Data Fetched Successfully");
        } else {
            return JsonResponses.generateResponse1(false, task_id, "Project Not Found for Id " + task_id);
        }
    }

    @PutMapping("/update/{task_id}")
    public Map<String, Object> updateUserById(@PathVariable int task_id, @RequestBody TaskRequest task) {
        Optional<Task> task1 = taskService.findById(task_id);

        if (task1 != null) {
            Task updatedProject = taskService.updateTask(task, task_id);
            if (updatedProject != null) {
                return JsonResponses.generateResponse1(true, updatedProject, "Task Updated Successfully");
            } else {
                return JsonResponses.generateResponse1(false, task_id, "Task Not Found for this ID");
            }
        } else {
            return JsonResponses.generateResponse1(false, task_id, "Task Not Found for this ID");
        }
    }

    @PutMapping("/{task_id}/start")
    public Map<String, Object> startTask(@PathVariable int task_id) {
//        taskService.startTask(task_id);
//        return ResponseEntity.ok("Task started");
    	
    	 try {
    		 taskService.startTask(task_id);
    	        return JsonResponses.generateResponse1(true, task_id, "Task started Successfully");
    	    } catch (Exception e) {
    	        // If an exception occurs, return an error response
    	        return JsonResponses.generateResponse1(false, task_id, "Task Not started for this ID");
    	    }
    }

    @PutMapping("/{task_id}/end")
    public Map<String, Object> stopTask(@PathVariable int task_id) {
    	try {
    		taskService.stopTask(task_id);
        
        return JsonResponses.generateResponse1(true, task_id, "Task stopped Successfully");
	    } catch (Exception e) {
	        // If an exception occurs, return an error response
	        return JsonResponses.generateResponse1(true, task_id, "Task Not stopped for this ID");
	    }    }

    @GetMapping("/{task_id}/times")
    public ResponseEntity<TaskTimeInfo> getTaskTimes(@PathVariable int task_id) {
        TaskTimeInfo taskTimeInfo = taskService.getTaskTimes(task_id);
        return ResponseEntity.ok(taskTimeInfo);
    }
}
