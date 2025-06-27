package com.solutions.crm.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.solutions.crm.beans.AttendanceTransaction;

public interface AttendanceTransactionRepository extends JpaRepository<AttendanceTransaction, Integer> {

	@Query("SELECT a FROM AttendanceTransaction a WHERE a.user_id.id = :userId AND a.attendance_date = :attendanceDate")
	List<AttendanceTransaction> findByUserIdAndAttendanceDate(@Param("userId") int userId,
			@Param("attendanceDate") LocalDateTime attendanceDate);

	@Query("SELECT at FROM AttendanceTransaction at WHERE at.user_id.id = :userId AND at.attendance_date BETWEEN :startOfDay AND :endOfDay")
	List<AttendanceTransaction> findByUserIdAndAttendanceDateBetween(@Param("userId") int userId,
			@Param("startOfDay") LocalDateTime startOfDay, @Param("endOfDay") LocalDateTime endOfDay);

//	@Query("SELECT at FROM AttendanceTransaction at WHERE at.user.id = :userId AND at.attendance_date BETWEEN :startOfDay AND :endOfDay")
//    List<AttendanceTransaction> findByUser_idAndAttendanceDate(
//            @Param("userId") int userId,
//            @Param("startOfDay") LocalDateTime startOfDay,
//            @Param("endOfDay") LocalDateTime endOfDay);

//	List<AttendanceTransaction> findByUserIdAndAttendanceDate(int user_id, LocalDateTime atStartOfDay,
//			LocalDateTime atStartOfDay2);

}
