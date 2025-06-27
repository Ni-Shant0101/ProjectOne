package com.solutions.crm.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solutions.crm.beans.Employee;
import com.solutions.crm.commom.responses.JsonResponses;
import com.solutions.crm.request.EmployeeRequest;
import com.solutions.crm.service.EmployeeSerivce;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	EmployeeSerivce employeeService;

	@Value("E://SpringBootWorkspace//CRM.zip_expanded//CRM//uploads")
	private String uploadDir;

	// Get all employees
	@GetMapping
	public Map<String, Object> getAllEmployees() {
		List<Employee> allEmployees = employeeService.getAllEmployees();
		if (allEmployees.isEmpty()) {
			return JsonResponses.generateResponse1(false, null, "List is empty");
		} else {
			return JsonResponses.generateResponse1(true, allEmployees, "Employee Details Get Successfully");
		}
	}

	// Register new employee
	@PostMapping
	public Map<String, Object> registerEmployee(@RequestParam("image") MultipartFile imageFile,
			@RequestParam("data") String jsonData) {
		try {
			// Convert JSON data to Employee object
			ObjectMapper objectMapper = new ObjectMapper();
			EmployeeRequest employeeRequest = objectMapper.readValue(jsonData, EmployeeRequest.class);

			// Normalize the file name
			String fileName = StringUtils.cleanPath(Objects.requireNonNull(imageFile.getOriginalFilename()));

			// Save the file to the server's filesystem
			String filePath = uploadDir + File.separator + fileName;
			File destFile = new File(filePath);
			imageFile.transferTo(destFile);

			// Set the image path in employee
			employeeRequest.setImage(filePath);

			// Save employee and handle errors
			Employee savedEmployee = employeeService.saveEmployee(employeeRequest);
			if (savedEmployee == null) {
				return JsonResponses.generateResponse1(false, null, "Failed to save employee");
			} else {
				return JsonResponses.generateResponse1(true, savedEmployee, "Employee registered successfully");
			}
		} catch (IOException ex) {
			return JsonResponses.generateResponse1(false, null, "Failed to save image file: " + ex.getMessage());
		}
	}

	// Edit employee
	@GetMapping("/edit/{employee_id}")
	public Map<String, Object> findEmployeeById(@PathVariable int employee_id) {
		Optional<Employee> employee = employeeService.findEmployeeById(employee_id);
		if (employee.isPresent()) {
			return JsonResponses.generateResponse1(true, employee.get(), "Employee Data Fetched Successfully");
		} else {
			return JsonResponses.generateResponse1(false, null, "Employee Not Found for Id " + employee_id);
		}
	}

	// Update employee
	@PutMapping("/update/{employee_id}")
	public Map<String, Object> updateEmployee(@PathVariable("employee_id") int employee_id,
			@RequestParam("image") MultipartFile imageFile, @RequestParam("data") String jsonData) {
		try {
			// Convert JSON data to Employee object
			ObjectMapper objectMapper = new ObjectMapper();
			EmployeeRequest employeeRequest = objectMapper.readValue(jsonData, EmployeeRequest.class);

			// Retrieve the existing employee by ID
			Employee updatedEmployee = employeeService.updateEmployee(employeeRequest, employee_id);
			if (updatedEmployee == null) {
				return JsonResponses.generateResponse1(false, null, "Employee with ID " + employee_id + " not found");
			}

			// Check if a new image file is provided
			if (!imageFile.isEmpty()) {
				// Normalize the file name
				String fileName = StringUtils.cleanPath(Objects.requireNonNull(imageFile.getOriginalFilename()));

				// Save the file to the server's filesystem
				String filePath = uploadDir + File.separator + fileName;
				File destFile = new File(filePath);
				imageFile.transferTo(destFile);

				// Set the new image path in employee
				updatedEmployee.setImage(filePath);
			}

			// Save updated employee
//			Employee updatedEmployee = employeeService.saveEmployee(existingEmployee);

			return JsonResponses.generateResponse1(true, updatedEmployee, "Employee updated successfully");
		} catch (IOException ex) {
			return JsonResponses.generateResponse1(false, null, "Failed to update employee: " + ex.getMessage());
		}
	}

}
