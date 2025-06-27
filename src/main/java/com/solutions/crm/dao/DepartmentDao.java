package com.solutions.crm.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solutions.crm.beans.Department;
import com.solutions.crm.beans.Users;
import com.solutions.crm.repository.DepartmentRepository;
import com.solutions.crm.repository.UsersRepository;
import com.solutions.crm.request.DepartmentRequest;
import com.solutions.crm.service.DepartmentService;

@Service
public class DepartmentDao implements DepartmentService{
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Autowired
	UsersRepository usersRepository;

	@Override
	public List<Department> getAlldepartment() {
		return departmentRepository.findAll();
	}

	@Override
	public Department createdepartment(DepartmentRequest departmentRequest) {
		
		Users user = usersRepository.findById(departmentRequest.getUser_id())
	            .orElseThrow(() -> new EntityNotFoundException("user not found"));

		Department department = new Department();
		department.setDepartmentName(departmentRequest.getDepartmentName());
		department.setDepartmentStatus(1);
		department.setUser_id(user);
		 return departmentRepository.save(department);
	}

	@Override
	public Optional<Department> findById(int department_id) {
		 return departmentRepository.findById(department_id);
	}

	@Override
	public Department updateDepartment(DepartmentRequest departmentRequest, int department_id) {
		Users user = usersRepository.findById(departmentRequest.getUser_id())
	            .orElseThrow(() -> new EntityNotFoundException("user not found"));
		
		 Optional<Department> existingDepartmentOptional = departmentRepository.findById(department_id);
	        if (existingDepartmentOptional.isPresent()) {
	            Department department = existingDepartmentOptional.get();
	            department.setDepartmentName(departmentRequest.getDepartmentName());
	            department.setDepartmentStatus(1);
	            department.setUser_id(user);
	            return departmentRepository.save(department);
	        } else {
	            // Department not found
	            return null;
	        }
	    }
	}
