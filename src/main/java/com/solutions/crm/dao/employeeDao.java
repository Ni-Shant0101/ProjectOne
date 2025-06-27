package com.solutions.crm.dao;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solutions.crm.beans.Department;
import com.solutions.crm.beans.Employee;
import com.solutions.crm.beans.Users;
import com.solutions.crm.repository.DepartmentRepository;
import com.solutions.crm.repository.EmployeeRepository;
import com.solutions.crm.repository.UsersRepository;
import com.solutions.crm.request.EmployeeRequest;
import com.solutions.crm.service.EmployeeSerivce;

@Service
public class employeeDao implements EmployeeSerivce{
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	DepartmentRepository departmentRepository;

	@Override
	public List<Employee> getAllEmployees() {
		 return employeeRepository.findAll();
	}

	@Override
	public Employee saveEmployee(EmployeeRequest employeeRequest) {
		LocalDateTime now = LocalDateTime.now(); // Get the current date and time
		
		Users user = usersRepository.findById(employeeRequest.getUser_id())
	            .orElseThrow(() -> new EntityNotFoundException("user not found"));
		
		Department department = departmentRepository.findById(employeeRequest.getDepartment_id())
	            .orElseThrow(() -> new EntityNotFoundException("department not found"));

			Employee employee = new Employee();
			employee.setCreated_at(now);
			employee.setName(employeeRequest.getName());
			employee.setEmail(employeeRequest.getEmail());
			employee.setImage(employeeRequest.getImage());
			employee.setGender(employeeRequest.getGender());
			employee.setMobile(employeeRequest.getMobile());
			employee.setAlternateMobile(employeeRequest.getAlternateMobile());
	       	employee.setAddress(employeeRequest.getAddress());
	       	employee.setJoiningDate(employeeRequest.getJoiningDate());
	        employee.setDesignation(employeeRequest.getDesignation());
	        employee.setAadharNo(employeeRequest.getAadharNo());
	        employee.setPanNo(employeeRequest.getPanNo());
	        employee.setStatus(1);
	        employee.setUser_id(user);
	        employee.setDepartment_id(department);
	        return employeeRepository.save(employee);
	}

	@Override
	public Optional<Employee> findEmployeeById(int employee_id) {
		return employeeRepository.findById(employee_id);
	}

	@Override
	public Employee updateEmployee(EmployeeRequest employeeRequest, int employee_id) {
		
		
		Users user = usersRepository.findById(employeeRequest.getUser_id())
	            .orElseThrow(() -> new EntityNotFoundException("user not found"));
		
		Department department = departmentRepository.findById(employeeRequest.getDepartment_id())
	            .orElseThrow(() -> new EntityNotFoundException("department not found"));
		
	    Optional<Employee> existingEmployeeOptional = employeeRepository.findById(employee_id);
	    LocalDateTime now = LocalDateTime.now();
	    if (existingEmployeeOptional.isPresent()) {
	        Employee existingEmployee = existingEmployeeOptional.get();

	        // Preserve the original created_at timestamp
	        LocalDateTime originalCreatedAt = existingEmployee.getCreated_at();

	        // Update existingEmployee with updatedEmployee's fields
	        existingEmployee.setName(employeeRequest.getName());
	        existingEmployee.setEmail(employeeRequest.getEmail());
	        existingEmployee.setImage(employeeRequest.getImage());
	        existingEmployee.setGender(employeeRequest.getGender());
	        existingEmployee.setMobile(employeeRequest.getMobile());
	        existingEmployee.setAlternateMobile(employeeRequest.getAlternateMobile());
	        existingEmployee.setAddress(employeeRequest.getAddress());
	        existingEmployee.setJoiningDate(employeeRequest.getJoiningDate());
	        existingEmployee.setDepartment_id(department);
	        existingEmployee.setUser_id(user);
	        existingEmployee.setDesignation(employeeRequest.getDesignation());
	        existingEmployee.setAadharNo(employeeRequest.getAadharNo());
	        existingEmployee.setPanNo(employeeRequest.getPanNo());
	        existingEmployee.setStatus(employeeRequest.getStatus());

	        // Set the preserved created_at value
	        existingEmployee.setCreated_at(originalCreatedAt);

	        // Set the updated_at field
	       
	        existingEmployee.setUpdated_at(now);

	        // Save and return the updated employee
	        Employee updateEmployee= employeeRepository.save(existingEmployee);
	        return updateEmployee;
	    } else {
	        // If employee with given ID is not found
	        return null;
	    }
	}


//	@Override
//	public Employee getEmployeeById(Employee employee,int employee_id) {
//		Optional<Employee> existingEmployeeOptional = employeeRepository.findById(employee_id);
//
//	    if (existingEmployeeOptional.isPresent()) {
//	        Employee existingEmployee = existingEmployeeOptional.get();
//
//	        // Preserve the original created_at timestamp
//	        LocalDateTime originalCreatedAt = existingEmployee.getCreated_at();
//
//	        // Update existingEmployee with newEmployee's fields
//	        existingEmployee.setName(employee.getName());
//	        existingEmployee.setEmail(employee.getEmail());
//	        existingEmployee.setImage(employee.getImage());
//	        existingEmployee.setGender(employee.getGender());
//	        existingEmployee.setMobile(employee.getMobile());
//	        existingEmployee.setAlternateMobile(employee.getAlternateMobile());
//	        existingEmployee.setAddress(employee.getAddress());
//	        existingEmployee.setJoiningDate(employee.getJoiningDate());
//	        existingEmployee.setDepartment_id(employee.getDepartment_id());
//	        existingEmployee.setUser_id(employee.getUser_id());
//	        existingEmployee.setDesignation(employee.getDesignation());
//	        existingEmployee.setAadharNo(employee.getAadharNo());
//	        existingEmployee.setPanNo(employee.getPanNo());
//	        existingEmployee.setStatus(employee.getStatus());
//
//	        // Set the preserved created_at value
//	        existingEmployee.setCreated_at(originalCreatedAt);
//
//	        // Set the updated_at field
//	        LocalDateTime now = LocalDateTime.now();
//	        existingEmployee.setUpdated_at(now);
//
//	        // Save and return the updated employee
//	        return employeeRepository.save(existingEmployee);
//	    } else {
//	        // If employee with given ID is not found
//	        return null;
//	    }
//	}
	
//	public Employee updateEmployee(Employee updatedEmployee, int employee_id) {
//	    Optional<Employee> existingEmployeeOptional = employeeRepository.findById(employee_id);
//
//	    if (existingEmployeeOptional.isPresent()) {
//	        Employee existingEmployee = existingEmployeeOptional.get();
//
//	        // Preserve the original created_at timestamp
//	        LocalDateTime originalCreatedAt = existingEmployee.getCreated_at();
//
//	        // Update existingEmployee with updatedEmployee's fields
//	        existingEmployee.setName(updatedEmployee.getName());
//	        existingEmployee.setEmail(updatedEmployee.getEmail());
//	        existingEmployee.setImage(updatedEmployee.getImage());
//	        existingEmployee.setGender(updatedEmployee.getGender());
//	        existingEmployee.setMobile(updatedEmployee.getMobile());
//	        existingEmployee.setAlternateMobile(updatedEmployee.getAlternateMobile());
//	        existingEmployee.setAddress(updatedEmployee.getAddress());
//	        existingEmployee.setJoiningDate(updatedEmployee.getJoiningDate());
//	        existingEmployee.setDepartment_id(updatedEmployee.getDepartment_id());
//	        existingEmployee.setUser_id(updatedEmployee.getUser_id());
//	        existingEmployee.setDesignation(updatedEmployee.getDesignation());
//	        existingEmployee.setAadharNo(updatedEmployee.getAadharNo());
//	        existingEmployee.setPanNo(updatedEmployee.getPanNo());
//	        existingEmployee.setStatus(updatedEmployee.getStatus());
//
//	        // Set the preserved created_at value
//	        existingEmployee.setCreated_at(originalCreatedAt);
//
//	        // Set the updated_at field
//	        LocalDateTime now = LocalDateTime.now();
//	        existingEmployee.setUpdated_at(now);
//
//	        // Save and return the updated employee
//	        return employeeRepository.save(existingEmployee);
//	    } else {
//	        // If employee with given ID is not found
//	        return null;
//	    }
//	}

}
