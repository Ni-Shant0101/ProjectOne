package com.solutions.crm.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.solutions.crm.beans.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

	@Query("SELECT a FROM Attendance a WHERE a.user_id.id = :user_id AND a.attendanceDate = :attendanceDate")
	Optional<Attendance> findByUserIdAndAttendanceDate(@Param("user_id") int user_id,
			@Param("attendanceDate") LocalDateTime attendanceDate);

//	@Query("SELECT a FROM Attendance a WHERE a.user_id.id = :user_id AND a.attendanceDate = :attendanceDate")
//    Optional<Attendance> findByUser_idAndAttendanceDate(
//            @Param("userId") int userId,
//            @Param("attendanceDate") LocalDateTime attendanceDate);
//	Optional<Attendance> findByUserIdAndAttendanceDate1(int user_id, LocalDateTime attendanceDate);

	@Query("SELECT COUNT(a) FROM Attendance a WHERE a.user_id = :userId AND a.attendanceDate = :startOfDay")
	int countByUser_idAndAttendanceDate(int userId, LocalDateTime startOfDay);
}
