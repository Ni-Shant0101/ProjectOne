package com.solutions.crm.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solutions.crm.beans.Project;
import com.solutions.crm.commom.responses.JsonResponses;
import com.solutions.crm.service.ProjectService;


@RestController
@RequestMapping("/project")
public class ProjectController {
		@Autowired
		ProjectService projectService;
		

		@GetMapping
		public Map<String, Object> getProject() {

			List<Project> allProject = projectService.getallProject();

			if (allProject.isEmpty()) {
				return JsonResponses.generateResponse1(false, allProject, "List is empty");
			} else {
				return JsonResponses.generateResponse1(true, allProject, "Project Details Get Successfully");
			}
		}

		// Add Project
		@PostMapping
		public Map<String, Object> addProduct( @RequestBody Project project) {

			Project details = projectService.saveProject(project);
		

			if (details != null) {
				return JsonResponses.generateResponse1(true, project, "project Added Successfully");
			} else {
				return JsonResponses.generateResponse1(false, project, "Some Data is Null or Invalid");
			}


		}

		// Edit Project
		@GetMapping("/edit/{project_id}")
		public Map<String, Object> findUserById(@PathVariable int project_id) {
			Optional<Project> OneUser = projectService.findById(project_id);
			if (OneUser.isPresent()) {
				return JsonResponses.generateResponse1(true, OneUser, "Project Data Fetched Successfully");
			} else {
				return JsonResponses.generateResponse1(false, project_id, "Project Not Found for Id " + project_id);
			}
		}

		// Update Project
		@PutMapping("/update/{project_id}")
		public Map<String, Object> updateUserById(@PathVariable int project_id, @RequestBody Project project) {

			Optional<Project> project1 = projectService.findById(project_id);

			if (project1 != null) {

				Project updatedProject = projectService.updateProject(project, project_id);
				if (updatedProject != null) {
					return JsonResponses.generateResponse1(true, updatedProject, "Project Updated Successfully");
				} else {
					return JsonResponses.generateResponse1(false, project_id, "Project Not Found fot this ID");
				}
			} else {
				return JsonResponses.generateResponse1(false, project_id, "Project Not Found fot this ID");
			}
		}
}
