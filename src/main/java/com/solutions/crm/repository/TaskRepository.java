package com.solutions.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solutions.crm.beans.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {

}
