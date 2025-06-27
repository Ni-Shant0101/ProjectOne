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

import com.solutions.crm.beans.LeaveTransaction;
import com.solutions.crm.commom.responses.JsonResponses;
import com.solutions.crm.request.LeaveTransactionRequest;
import com.solutions.crm.service.LeaveTransactionService;

@RestController
@RequestMapping("/transactions")
public class LeaveTransactionController {

	@Autowired
	private LeaveTransactionService leaveTransactionService;

	// Get all leave transactions
	@GetMapping
	public Map<String, Object> getAllLeaveTransactions() {
		List<LeaveTransaction> allLeaveTransactions = leaveTransactionService.getAllLeaveTransactions();
		if (allLeaveTransactions.isEmpty()) {
			return JsonResponses.generateResponse1(false, null, "Leave transactions list is empty");
		} else {
			return JsonResponses.generateResponse1(true, allLeaveTransactions,
					"Leave transactions retrieved successfully");
		}
	}

	// Create a new leave transaction
	@PostMapping
	public Map<String, Object> createLeaveTransaction(@RequestBody LeaveTransactionRequest leaveTransaction) {
		LeaveTransaction createdLeaveTransaction = leaveTransactionService.createLeaveTransaction(leaveTransaction);
		if (createdLeaveTransaction != null) {
			return JsonResponses.generateResponse1(true, createdLeaveTransaction,
					"Leave transaction added successfully");
		} else {
			return JsonResponses.generateResponse1(false, null, "Failed to add leave transaction");
		}
	}

	@GetMapping("/edit/{transaction_id}")
	public Map<String, Object> findLeaveTransactionById(@PathVariable int transaction_id) {
		Optional<LeaveTransaction> OneUser = leaveTransactionService.findById(transaction_id);
		if (OneUser.isPresent()) {
			return JsonResponses.generateResponse1(true, OneUser, "attendance Data Fetched Successfully");
		} else {
			return JsonResponses.generateResponse1(false, transaction_id,
					"attendance Not Found for Id " + transaction_id);
		}
	}

	// Update leave transaction by ID
	@PutMapping("/update/{transaction_id}")
	public Map<String, Object> updateLeaveTransactionById(@PathVariable int transaction_id,
			@RequestBody LeaveTransactionRequest leaveTransaction) {
		LeaveTransaction updatedLeaveTransaction = leaveTransactionService.updateLeaveTransaction(transaction_id,
				leaveTransaction);
		if (updatedLeaveTransaction != null) {
			return JsonResponses.generateResponse1(true, updatedLeaveTransaction,
					"Leave transaction updated successfully");
		} else {
			return JsonResponses.generateResponse1(false, null, "Failed to update leave transaction");
		}
	}

}
