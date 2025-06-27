package com.solutions.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solutions.crm.beans.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
