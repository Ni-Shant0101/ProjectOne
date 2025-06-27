package com.solutions.crm.service;

import java.util.List;
import java.util.Optional;

import com.solutions.crm.beans.LeaveMaster;

public interface LeaveMasterService {

	List<LeaveMaster> getAllLeaveMasters();

	LeaveMaster createLeaveMaster(LeaveMaster leaveMaster);

	Optional<LeaveMaster> getLeaveMasterById(int leave_id);

	LeaveMaster updateLeaveMaster(int leave_id, LeaveMaster leaveMaster);

}
