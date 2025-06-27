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

import com.solutions.crm.beans.LeaveMaster;
import com.solutions.crm.commom.responses.JsonResponses;
import com.solutions.crm.service.LeaveMasterService;

@RestController
@RequestMapping("/masters")
public class LeaveMasterController {

	@Autowired
	private LeaveMasterService leaveMasterService;

	@GetMapping
	public Map<String, Object> getAllLeaveMasters() {
		List<LeaveMaster> allLeaveMasters = leaveMasterService.getAllLeaveMasters();
		if (allLeaveMasters.isEmpty()) {
			return JsonResponses.generateResponse1(false, null, "List is empty");
		} else {
			return JsonResponses.generateResponse1(true, allLeaveMasters, "Leave masters retrieved successfully");
		}
	}

	@PostMapping
	public Map<String, Object> addLeaveMaster(@RequestBody LeaveMaster leaveMaster) {
		LeaveMaster createdLeaveMaster = leaveMasterService.createLeaveMaster(leaveMaster);
		if (createdLeaveMaster != null) {
			return JsonResponses.generateResponse1(true, createdLeaveMaster, "Leave master added successfully");
		} else {
			return JsonResponses.generateResponse1(false, null, "Failed to add leave master");
		}
	}

	@GetMapping("/{leave_id}")
	public Map<String, Object> getLeaveMasterById(@PathVariable int leave_id) {
		Optional<LeaveMaster> leaveMasterOptional = leaveMasterService.getLeaveMasterById(leave_id);
		if (leaveMasterOptional.isPresent()) {
			return JsonResponses.generateResponse1(true, leaveMasterOptional.get(),
					"Leave master retrieved successfully");
		} else {
			return JsonResponses.generateResponse1(false, null, "Leave master not found for ID " + leave_id);
		}
	}

	@PutMapping("/update/{leaveId}")
	public Map<String, Object> updateLeaveMaster(@PathVariable int leave_id, @RequestBody LeaveMaster leaveMaster) {
		Optional<LeaveMaster> existingLeaveMasterOptional = leaveMasterService.getLeaveMasterById(leave_id);
		if (existingLeaveMasterOptional.isPresent()) {
			LeaveMaster updatedLeaveMaster = leaveMasterService.updateLeaveMaster(leave_id, leaveMaster);
			if (updatedLeaveMaster != null) {
				return JsonResponses.generateResponse1(true, updatedLeaveMaster, "Leave master updated successfully");
			} else {
				return JsonResponses.generateResponse1(false, null, "Failed to update leave master");
			}
		} else {
			return JsonResponses.generateResponse1(false, null, "Leave master not found for ID " + leave_id);
		}
	}
}
