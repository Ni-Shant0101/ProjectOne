package com.solutions.crm.service;
import java.util.List;
import java.util.Optional;

import com.solutions.crm.beans.Department;
import com.solutions.crm.request.DepartmentRequest;


public interface DepartmentService {

	List<Department> getAlldepartment();

	Department createdepartment(DepartmentRequest departmentRequest);

	Optional<Department> findById(int department_id);

	Department updateDepartment(DepartmentRequest departmentRequest, int department_id);

}
