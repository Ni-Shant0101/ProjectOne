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

import com.solutions.crm.beans.Attendance;
import com.solutions.crm.commom.responses.JsonResponses;
import com.solutions.crm.request.AttendanceRequest;
import com.solutions.crm.service.AttendanceService;

@RestController
@RequestMapping("/attendances")
public class AttendanceController {

	@Autowired
	private AttendanceService attendanceService;

	@GetMapping
	public Map<String, Object> getAttendance() {

		List<Attendance> allAttendance = attendanceService.getAllAttendances();

		if (allAttendance.isEmpty()) {
			return JsonResponses.generateResponse1(false, allAttendance, "List is empty");
		} else {
			return JsonResponses.generateResponse1(true, allAttendance, "Attendance Details Get Successfully");
		}
	}

	// Add Project
	@PostMapping
	public Map<String, Object> addAttendance(@RequestBody AttendanceRequest attendance) {

		Attendance details = attendanceService.createAttendance(attendance);

		if (details != null) {
			return JsonResponses.generateResponse1(true, details, "attendance Added Successfully");
		} else {
			return JsonResponses.generateResponse1(false, details, "attendance Data is Null or Invalid");
		}

	}

	// Edit Project
	@GetMapping("/edit/{attendance_id}")
	public Map<String, Object> findUserById(@PathVariable int attendance_id) {
		Optional<Attendance> OneUser = attendanceService.findById(attendance_id);
		if (OneUser.isPresent()) {
			return JsonResponses.generateResponse1(true, OneUser, "attendance Data Fetched Successfully");
		} else {
			return JsonResponses.generateResponse1(false, attendance_id,
					"attendance Not Found for Id " + attendance_id);
		}
	}

	// Update Project
	@PutMapping("/update/{attendance_id}")
	public Map<String, Object> updateUserById(@PathVariable int attendance_id,
			@RequestBody AttendanceRequest attendance) {

		Optional<Attendance> attendance1 = attendanceService.findById(attendance_id);

		if (attendance1 != null) {

			Attendance updatedAttendance = attendanceService.updateProject(attendance, attendance_id);
			if (updatedAttendance != null) {
				return JsonResponses.generateResponse1(true, updatedAttendance, "attendance Updated Successfully");
			} else {
				return JsonResponses.generateResponse1(false, attendance_id, "attendance Not Found fot this ID");
			}
		} else {
			return JsonResponses.generateResponse1(false, attendance_id, "attendance Not Found fot this ID");
		}
	}
}
