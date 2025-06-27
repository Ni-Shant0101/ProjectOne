package com.solutions.crm.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solutions.crm.beans.Department;
import com.solutions.crm.commom.responses.JsonResponses;
import com.solutions.crm.request.DepartmentRequest;
import com.solutions.crm.service.DepartmentService;
@RestController
@RequestMapping("/departments")
public class DepartmentController {
	
	@Autowired
	DepartmentService departmentService;
	
	@GetMapping
	public Map<String, Object> getDepartment() {

		List<Department> allDepartment = departmentService.getAlldepartment();

		if (allDepartment.isEmpty()) {
			return JsonResponses.generateResponse1(false, allDepartment, "List is empty");
		} else {
			return JsonResponses.generateResponse1(true, allDepartment, "department Details Get Successfully");
		}
	}

	// Add Project
	@PostMapping
	public Map<String, Object> addDepartment( @RequestBody DepartmentRequest department) {

		Department details = departmentService.createdepartment(department);
	

		if (details != null) {
			return JsonResponses.generateResponse1(true, details, "attendance Added Successfully");
		} else {
			return JsonResponses.generateResponse1(false, details, "attendance Data is Null or Invalid");
		}


	}

	// Edit Project
	@GetMapping("/edit/{department_id}")
	public Map<String, Object> findDepartmentById(@PathVariable int department_id) {
		Optional<Department> OneUser = departmentService.findById(department_id);
		if (OneUser.isPresent()) {
			return JsonResponses.generateResponse1(true, OneUser, "attendance Data Fetched Successfully");
		} else {
			return JsonResponses.generateResponse1(false, department_id, "attendance Not Found for Id " + department_id);
		}
	}

	// Update Project
	@PutMapping("/update/{department_id}")
	public Map<String, Object> updateUserById(@PathVariable int department_id, @RequestBody DepartmentRequest department) {

		Optional<Department> department1 = departmentService.findById(department_id);

		if (department1 != null) {

			Department updateDepartment = departmentService.updateDepartment(department, department_id);
			if (updateDepartment != null) {
				return JsonResponses.generateResponse1(true, updateDepartment, "department Updated Successfully");
			} else {
				return JsonResponses.generateResponse1(false, department_id, "department Not Found fot this ID");
			}
		} else {
			return JsonResponses.generateResponse1(false, department_id, "department Not Found fot this ID");
		}
	}
}


