package com.solutions.crm.dao;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solutions.crm.beans.UserShift;
import com.solutions.crm.beans.Users;
import com.solutions.crm.repository.UserShiftRepository;
import com.solutions.crm.repository.UsersRepository;
import com.solutions.crm.request.UserShiftRequest;
import com.solutions.crm.service.UserShiftService;
@Service
public class UserShiftDao implements UserShiftService{
	
	@Autowired
	UserShiftRepository userShiftRepository;
	
	@Autowired
	UsersRepository usersRepository;

	@Override
	public List<UserShift> getallUserShift() {
		return userShiftRepository.findAll();
	}

	@Override
	public UserShift saveUserShift(UserShiftRequest userShiftRequest) {
		Users user = usersRepository.findById(userShiftRequest.getUser_id())
	            .orElseThrow(() -> new EntityNotFoundException("user not found"));

		UserShift userShift = new UserShift();
		userShift.setStartDay(userShiftRequest.getStartDay());
		userShift.setEndDay(userShiftRequest.getEndDay());
		userShift.setUser_id(user);
		userShift.setStartTime(userShiftRequest.getStartTime());
		userShift.setEndTime(userShiftRequest.getEndTime());
        userShift.setStatus(1);
		return userShiftRepository.save(userShift);
	}

	@Override
	public Optional<UserShift> findById(int shift_id) {
		return userShiftRepository.findById(shift_id);
	}

	@Override
	public UserShift updateUserShift(UserShiftRequest userShiftRequest, int shift_id) {
		
		Users user = usersRepository.findById(userShiftRequest.getUser_id())
	            .orElseThrow(() -> new EntityNotFoundException("user not found"));
		
		 Optional<UserShift> optionalUserShift = userShiftRepository.findById(shift_id);

		    if (optionalUserShift.isPresent()) {
		        UserShift existingUserShift = optionalUserShift.get();

		        // Update the attributes of existingUserShift with the values from updatedUserShift
		        existingUserShift.setStartDay(userShiftRequest.getStartDay());
		        existingUserShift.setEndDay(userShiftRequest.getEndDay());
		        existingUserShift.setUser_id(user);
		        existingUserShift.setStartTime(userShiftRequest.getStartTime());
		        existingUserShift.setEndTime(userShiftRequest.getEndTime());
		        existingUserShift.setStatus(1);

		        return userShiftRepository.save(existingUserShift);
		    } else {
		        
		        return null;
		    }
		}
}
