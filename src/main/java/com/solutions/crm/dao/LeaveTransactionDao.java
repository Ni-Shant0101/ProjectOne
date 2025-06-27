package com.solutions.crm.dao;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solutions.crm.beans.LeaveTransaction;
import com.solutions.crm.beans.Users;
import com.solutions.crm.repository.LeaveTransactionRepository;
import com.solutions.crm.repository.UsersRepository;
import com.solutions.crm.request.LeaveTransactionRequest;
import com.solutions.crm.service.LeaveTransactionService;

@Service
public class LeaveTransactionDao implements LeaveTransactionService{
	
	@Autowired
	LeaveTransactionRepository leaveTransactionRepository;

	@Autowired
	UsersRepository usersRepository;
	
	@Override
	public List<LeaveTransaction> getAllLeaveTransactions() {
		return leaveTransactionRepository.findAll();
	}

	@Override
	public LeaveTransaction createLeaveTransaction(LeaveTransactionRequest leaveTransactionRequest) {

		Users user = usersRepository.findById(leaveTransactionRequest.getUser_id())
	            .orElseThrow(() -> new EntityNotFoundException("user not found"));

		LeaveTransaction leaveTransaction = new LeaveTransaction();
		leaveTransaction.setLeaveSubject(leaveTransactionRequest.getLeaveSubject());
		leaveTransaction.setLeaveReason(leaveTransactionRequest.getLeaveReason());
		leaveTransaction.setFromDate(leaveTransactionRequest.getFromDate());
		leaveTransaction.setLeaveType(leaveTransactionRequest.getLeaveType());
		leaveTransaction.setTotalDays(leaveTransactionRequest.getTotalDays());
		leaveTransaction.setToDate(leaveTransactionRequest.getToDate());
		leaveTransaction.setStatus(1);
		leaveTransaction.setUser_id(user);
		 
		return leaveTransactionRepository.save(leaveTransaction);
	}

	
	@Override
	public LeaveTransaction updateLeaveTransaction(int transaction_id, LeaveTransactionRequest leaveTransactionRequest) {
		Users user = usersRepository.findById(leaveTransactionRequest.getUser_id())
	            .orElseThrow(() -> new EntityNotFoundException("user not found"));
		
		 Optional<LeaveTransaction> optionalLeaveTransaction = leaveTransactionRepository.findById(transaction_id);
		    if (optionalLeaveTransaction.isPresent()) {
		        LeaveTransaction existingLeaveTransaction = optionalLeaveTransaction.get();
		        existingLeaveTransaction.setUser_id(user);
		        existingLeaveTransaction.setLeaveType(leaveTransactionRequest.getLeaveType());
		        existingLeaveTransaction.setLeaveSubject(leaveTransactionRequest.getLeaveSubject());
		        existingLeaveTransaction.setFromDate(leaveTransactionRequest.getFromDate());
		        existingLeaveTransaction.setToDate(leaveTransactionRequest.getToDate());
		        existingLeaveTransaction.setLeaveReason(leaveTransactionRequest.getLeaveReason());
		        existingLeaveTransaction.setTotalDays(leaveTransactionRequest.getTotalDays());
		        existingLeaveTransaction.setStatus(1);
		        return leaveTransactionRepository.save(existingLeaveTransaction);
		    } else {
		        return null; // Or throw an exception
		    }
    }
	

	@Override
	public Optional<LeaveTransaction> findById(int transaction_id) {
		return leaveTransactionRepository.findById(transaction_id);
	}

}
