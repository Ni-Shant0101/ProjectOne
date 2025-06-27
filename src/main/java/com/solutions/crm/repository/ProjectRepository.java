package com.solutions.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solutions.crm.beans.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer>{

	

}
