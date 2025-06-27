package com.solutions.crm.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solutions.crm.beans.Project;
import com.solutions.crm.repository.ProjectRepository;
import com.solutions.crm.service.ProjectService;

@Service
public class ProjectDao implements ProjectService {

	@Autowired
	ProjectRepository projectRepository;

	@Override
	public Project saveProject(Project project) {
		try {
			Project savedProject = projectRepository.save(project); // Assuming you're using JPA EntityManager
			return savedProject;
		} catch (Exception e) {
			e.printStackTrace(); // It's better to log the exception for debugging purposes
			return null;
		}
	}

	@Override
	public Project updateProject(Project project, int project_id) {
		try {
			project.setProject_id(project_id);
			Project Project1 = projectRepository.save(project);
			return Project1;
		} catch (Exception e) {
//			System.out.println(e);
			return null;
		}
	}

	@Override
	public List<Project> getallProject() {
		return projectRepository.findAll();
	}

	@Override
	public Optional<Project> findById(int project_id) {
		return projectRepository.findById(project_id);
	}

}
