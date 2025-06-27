package com.solutions.crm.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solutions.crm.beans.LeaveMaster;
import com.solutions.crm.repository.LeaveMasterRepository;
import com.solutions.crm.service.LeaveMasterService;

@Service
public class LeaveMasterDao implements LeaveMasterService {

	@Autowired
	LeaveMasterRepository leaveMasterRepository;

	@Override
	public List<LeaveMaster> getAllLeaveMasters() {
		// TODO Auto-generated method stub
		return leaveMasterRepository.findAll();
	}

	@Override
	public LeaveMaster createLeaveMaster(LeaveMaster leaveMaster) {
		return leaveMasterRepository.save(leaveMaster);
	}

	@Override
	public Optional<LeaveMaster> getLeaveMasterById(int leave_id) {
		return leaveMasterRepository.findById(leave_id);
	}

	@Override
	public LeaveMaster updateLeaveMaster(int leave_id, LeaveMaster leaveMaster) {
		Optional<LeaveMaster> existingLeaveMasterOptional = leaveMasterRepository.findById(leave_id);
		if (existingLeaveMasterOptional.isPresent()) {
			LeaveMaster existingLeaveMaster = existingLeaveMasterOptional.get();
			existingLeaveMaster.setLeaveName(leaveMaster.getLeaveName());
			existingLeaveMaster.setAvailable(leaveMaster.getAvailable());
			return leaveMasterRepository.save(existingLeaveMaster);
		}
		return null;
	}
}
