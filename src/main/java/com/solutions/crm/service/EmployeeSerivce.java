package com.solutions.crm.service;

import java.util.List;
import java.util.Optional;

import com.solutions.crm.beans.Employee;
import com.solutions.crm.request.EmployeeRequest;

public interface EmployeeSerivce {

	List<Employee> getAllEmployees();

	Employee saveEmployee(EmployeeRequest employeeRequest);

	Optional<Employee> findEmployeeById(int employee_id);

	Employee updateEmployee(EmployeeRequest employeeRequest, int employee_id);

//	Employee getEmployeeById(Employee employee,int employee_id);

}
