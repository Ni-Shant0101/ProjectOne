package com.solutions.crm.service;

import java.util.List;
import java.util.Optional;

import com.solutions.crm.beans.UserShift;
import com.solutions.crm.request.UserShiftRequest;

public interface UserShiftService {

	List<UserShift> getallUserShift();

	UserShift saveUserShift(UserShiftRequest userShiftRequest);

	Optional<UserShift> findById(int shift_id);

	UserShift updateUserShift(UserShiftRequest userShiftRequest, int shift_id);

}
