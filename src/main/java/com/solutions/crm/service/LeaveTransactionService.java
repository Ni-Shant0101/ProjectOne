package com.solutions.crm.service;

import java.util.List;
import java.util.Optional;

import com.solutions.crm.beans.LeaveTransaction;
import com.solutions.crm.request.LeaveTransactionRequest;

public interface LeaveTransactionService {

	List<LeaveTransaction> getAllLeaveTransactions();

	LeaveTransaction createLeaveTransaction(LeaveTransactionRequest leaveTransactionRequest);

	LeaveTransaction updateLeaveTransaction(int transaction_id, LeaveTransactionRequest leaveTransactionRequest);

	Optional<LeaveTransaction> findById(int transaction_id);

}
