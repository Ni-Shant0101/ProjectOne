package com.solutions.crm.service;

import java.util.List;
import java.util.Optional;

import com.solutions.crm.beans.Attendance;
import com.solutions.crm.request.AttendanceRequest;


public interface AttendanceService {

	List<Attendance> getAllAttendances();

	Attendance createAttendance(AttendanceRequest attendanceRequest);

	Optional<Attendance> findById(int attendance_id);

	Attendance updateProject(AttendanceRequest attendanceRequest, int attendance_id);

//	public List<Attendance> getAllAttendances() ;
//
//	public Optional<Attendance> getAttendanceById(Long attendance_id);
//
//	public Attendance createAttendance(Attendance attendance);
//
//	public Attendance updateAttendance(Long attendance_id, Attendance attendance);
//
//	public void deleteAttendance(Long attendance_id);
	
	

}
