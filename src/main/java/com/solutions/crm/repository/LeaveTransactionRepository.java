package com.solutions.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solutions.crm.beans.LeaveTransaction;

public interface LeaveTransactionRepository extends JpaRepository<LeaveTransaction, Integer> {

}
