package com.solutions.crm.service;

import java.util.List;
import java.util.Optional;

import com.solutions.crm.beans.Project;

public interface ProjectService {
	Project saveProject(Project project);

	Project updateProject(Project project, int project_id);

	List<Project> getallProject();

	Optional<Project> findById(int project_id);
}
