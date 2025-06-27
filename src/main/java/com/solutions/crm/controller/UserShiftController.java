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

import com.solutions.crm.beans.UserShift;
import com.solutions.crm.commom.responses.JsonResponses;
import com.solutions.crm.request.UserShiftRequest;
import com.solutions.crm.service.UserShiftService;

@RestController
@RequestMapping("/usershift")
public class UserShiftController {
	
	@Autowired 
	UserShiftService userShiftService;
	
	@GetMapping
	public Map<String, Object> getProject() {

		List<UserShift> allUserShift = userShiftService.getallUserShift();

		if (allUserShift.isEmpty()) {
			return JsonResponses.generateResponse1(false, allUserShift, "List is empty");
		} else {
			return JsonResponses.generateResponse1(true, allUserShift, "UserShift Details Get Successfully");
		}
	}

	// Add Project
	@PostMapping
	public Map<String, Object> addUserShift( @RequestBody UserShiftRequest userShift) {

		UserShift details = userShiftService.saveUserShift(userShift);
	

		if (details != null) {
			return JsonResponses.generateResponse1(true, details, "UserShift Added Successfully");
		} else {
			return JsonResponses.generateResponse1(false, details, "Some Data is Null or Invalid");
		}


	}

	// Edit Project
	@GetMapping("/edit/{shift_id}")
	public Map<String, Object> findUserById(@PathVariable int shift_id) {
		Optional<UserShift> OneUser = userShiftService.findById(shift_id);
		if (OneUser.isPresent()) {
			return JsonResponses.generateResponse1(true, OneUser, "UserShift Data Fetched Successfully");
		} else {
			return JsonResponses.generateResponse1(false, shift_id, "UserShift Not Found for Id " + shift_id);
		}
	}

	// Update Project
	@PutMapping("/update/{shift_id}")
	public Map<String, Object> updateUserById(@PathVariable int shift_id, @RequestBody UserShiftRequest userShift) {

		Optional<UserShift> UserShift1 = userShiftService.findById(shift_id);

		if (UserShift1 != null) {

			UserShift updatedUserShift = userShiftService.updateUserShift(userShift, shift_id);
			if (updatedUserShift != null) {
				return JsonResponses.generateResponse1(true, updatedUserShift, "UserShift Updated Successfully");
			} else {
				return JsonResponses.generateResponse1(false, shift_id, "UserShift Not Found fot this ID");
			}
		} else {
			return JsonResponses.generateResponse1(false, shift_id, "UserShift Not Found fot this ID");
		}
	}
}
