package com.solutions.crm.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solutions.crm.beans.Attendance;
import com.solutions.crm.beans.Users;
import com.solutions.crm.repository.AttendanceRepository;
import com.solutions.crm.repository.UsersRepository;
import com.solutions.crm.request.AttendanceRequest;
import com.solutions.crm.service.AttendanceService;

@Service
public class AttendanceDao implements AttendanceService {

	@Autowired
	AttendanceRepository attendanceRepository;

	@Autowired
	UsersRepository usersRepository;

	@Override
	public List<Attendance> getAllAttendances() {
		return attendanceRepository.findAll();
	}

	@Override
	public Attendance createAttendance(AttendanceRequest attendanceRequest) {
		Users user = usersRepository.findById(attendanceRequest.getUser_id())
				.orElseThrow(() -> new EntityNotFoundException("user not found"));

		Attendance attendance = new Attendance();
		attendance.setAttendanceDate(attendanceRequest.getAttendanceDate());
		attendance.setFinancialYear(attendanceRequest.getFinancialYear());

		attendance.setMonthId(attendanceRequest.getMonthId());
		attendance.setUser_id(user);
		return attendanceRepository.save(attendance);
	}

	@Override
	public Optional<Attendance> findById(int attendance_id) {
		return attendanceRepository.findById(attendance_id);
	}

	@Override
	public Attendance updateProject(AttendanceRequest attendanceRequest, int attendance_id) {
		Users user = usersRepository.findById(attendanceRequest.getUser_id())
				.orElseThrow(() -> new EntityNotFoundException("user not found"));

		Optional<Attendance> optionalAttendance = attendanceRepository.findById(attendance_id);
		if (optionalAttendance.isPresent()) {
			Attendance existingAttendance = optionalAttendance.get();

			existingAttendance.setAttendanceDate(attendanceRequest.getAttendanceDate());

			existingAttendance.setUser_id(user); // Set user entity instead of userId
			existingAttendance.setMonthId(attendanceRequest.getMonthId());
			existingAttendance.setFinancialYear(attendanceRequest.getFinancialYear());

			return attendanceRepository.save(existingAttendance);
		} else {
			return null; // Or throw an exception
		}
	}
}
