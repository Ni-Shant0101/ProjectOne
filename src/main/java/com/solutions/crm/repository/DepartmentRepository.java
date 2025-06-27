package com.solutions.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solutions.crm.beans.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
