package com.solutions.crm.controller;

import java.io.File;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solutions.crm.beans.Attendance;
import com.solutions.crm.beans.AttendanceTransaction;
import com.solutions.crm.beans.Users;
import com.solutions.crm.repository.AttendanceRepository;
import com.solutions.crm.repository.AttendanceTransactionRepository;
import com.solutions.crm.repository.UsersRepository;
import com.solutions.crm.request.AttendanceTransactionRequest;
import com.solutions.crm.service.UsersService;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceTransactionController {

	@Value("E://SpringBootWorkspace//CRM.zip_expanded//CRM//uploads")
	private String uploadDir;

	@Autowired
	private AttendanceTransactionRepository attendanceTransactionRepository;

	@Autowired
	private AttendanceRepository attendanceRepository;

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private UsersService userService;

	@PostMapping("/transaction")
	public ResponseEntity<?> createAttendanceTransaction(@RequestParam("image") MultipartFile imageFile,
			@RequestParam("data") String jsonData) {

		try {
			// Convert JSON data to AttendanceTransactionRequest object
			ObjectMapper objectMapper = new ObjectMapper();
			AttendanceTransactionRequest request = objectMapper.readValue(jsonData, AttendanceTransactionRequest.class);

			Users user = usersRepository.findById(request.getUser_id())
					.orElseThrow(() -> new EntityNotFoundException("user not found"));

			// Create AttendanceTransaction entry
			AttendanceTransaction attendanceTransaction = new AttendanceTransaction();
//            Users user = new Users();
//            user.setId(request.getUser_id()); // Assuming you have a setter method for user_id
			attendanceTransaction.setUser_id(user);
			LocalDateTime now = LocalDateTime.now();
			attendanceTransaction.setAttendance_date(now);
			attendanceTransaction.setLogin_time(request.getLogin_time()); // Login time provided
			attendanceTransaction.setLogout_time(request.getLogout_time()); // Initially set as null
			attendanceTransaction.setLocation(request.getLocation());

			// Save the file to the server's filesystem and set the path in photoUrl
			String fileName = imageFile.getOriginalFilename();
			String filePath = uploadDir + fileName;
			imageFile.transferTo(new File(filePath));
			attendanceTransaction.setPhotoUrl(filePath);

			attendanceTransaction.setStatus(null); // Initially set as null
			attendanceTransaction = attendanceTransactionRepository.save(attendanceTransaction);

			// Extract month and year from attendanceDate
//            LocalDateTime attendanceDate = request.getAttendance_date();
//            if (attendanceDate == null) {
//                attendanceDate = now; // Use the current time if attendanceDate is null
//            }
//           
//            ZonedDateTime zonedDateTime = attendanceDate.atZone(ZoneId.systemDefault());
//            Date date = Date.from(zonedDateTime.toInstant());
//
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(date);
//            String monthId = String.format("%02d", calendar.get(Calendar.MONTH) + 1); // Month is 0-based
//            int financialYear = calendar.get(Calendar.YEAR);

			return ResponseEntity.ok("Attendance transaction created and attendance record updated.");
		} catch (Exception ex) {
			return ResponseEntity.status(400).body("Failed to create attendance transaction: " + ex.getMessage());
		}
	}

//    @PostMapping("/createOrUpdate")
//    public ResponseEntity<?> createOrUpdateAttendance(@RequestBody AttendanceTransactionRequest request) {
//        try {
//            // Extract user id and attendance date from the request object
//            int userId = request.getUser_id();
//           
//            LocalDateTime attendanceDate = request.getAttendance_date();
//            
//            // Set the start of the day for the provided attendance date
//            LocalDateTime startOfDay = attendanceDate.toLocalDate().atStartOfDay();            
//            // Retrieve the user from the repository
//            Users user = usersRepository.findById(userId)
//                    .orElseThrow(() -> new EntityNotFoundException("User not found"));
//            
//            // Check if attendance record already exists for the user on the given date
//            Optional<Attendance> attendanceOptional = attendanceRepository.findByUserIdAndAttendanceDate(userId, startOfDay);
//            
//            Attendance attendance;
//            if (attendanceOptional.isPresent()) {
//                attendance = attendanceOptional.get();
//            } else {
//                // Create a new attendance record if not found
//                attendance = new Attendance();
//                attendance.setUser_id(user);
//                // Set the attendance date with provided year and month, but keep day as the first day of the month
//                attendance.setAttendanceDate(startOfDay.withDayOfMonth(1));
//                // Set financial year and month id based on the attendance date
//                int year = attendanceDate.getYear();
//                String monthId = attendanceDate.getMonth().name(); // or use other method to get month id
//                attendance.setFinancialYear(year);
//                attendance.setMonthId(monthId);
//            }
//
//            // Check the number of attendance entries for the user on the given date
//            int attendanceCount = attendanceRepository.countByUser_idAndAttendanceDate(userId, startOfDay);
//
//            // Determine the status based on the number of entries
//            String status;
//            if (attendanceCount == 2) {
//                status = "Present";
//            } else if (attendanceCount == 1) {
//                status = "Half day";
//            } else {
//                status = "Absent";
//            }
//            
//            attendance.setStatus(status);
//
//            // Set other fields of attendance...
//            
//            attendanceRepository.save(attendance);
//
//            return ResponseEntity.ok("Attendance record created/updated successfully.");
//        } catch (Exception ex) {
//            return ResponseEntity.status(400).body("Failed to create/update attendance record: " + ex.getMessage());
//        }
//    }

	@PostMapping("/updateAttendanceStatus")
	public ResponseEntity<?> updateAttendanceStatus(@RequestParam("user_id") int userId,
			@RequestParam("attendance_date") String attendanceDateStr) {

		try {
			// Parse the provided date-time string to LocalDateTime
			LocalDateTime attendanceDate = LocalDateTime.parse(attendanceDateStr);
			LocalDateTime startOfDay = attendanceDate.toLocalDate().atStartOfDay();
			LocalDateTime endOfDay = attendanceDate.toLocalDate().atTime(LocalTime.MAX);

			// Retrieve the user from the repository
			Users user = usersRepository.findById(userId)
					.orElseThrow(() -> new EntityNotFoundException("User not found"));

			// Retrieve the number of attendance transactions for the user on the given date
			List<AttendanceTransaction> transactions = attendanceTransactionRepository
					.findByUserIdAndAttendanceDateBetween(userId, startOfDay, endOfDay);

			int attendanceCount = transactions.size();

			// Determine the status based on the number of entries
			String status;
			if (attendanceCount == 2) {
				status = "Present";
			} else if (attendanceCount == 1) {
				status = "Half day";
			} else {
				status = "Absent";
			}

			// Retrieve or create the attendance record
			Optional<Attendance> attendanceOptional = attendanceRepository.findByUserIdAndAttendanceDate(userId,
					startOfDay);
			Attendance attendance;
			if (attendanceOptional.isPresent()) {
				attendance = attendanceOptional.get();
			} else {
				attendance = new Attendance();
				attendance.setUser_id(user);
				attendance.setAttendanceDate(startOfDay); // Set the attendance date as startOfDay
			}

			// Set the status, month ID, and financial year
			attendance.setStatus(status);
			int year = attendanceDate.getYear();
			String monthId = String.format("%02d", attendanceDate.getMonthValue());

			attendance.setFinancialYear(year);
			attendance.setMonthId(monthId);

			// Set the attendance transaction
			if (!transactions.isEmpty()) {
				// Assuming you want to associate the latest transaction
				AttendanceTransaction latestTransaction = transactions.get(transactions.size() - 1);
				attendance.setAttendanceTransaction(latestTransaction);
			} else {
				attendance.setAttendanceTransaction(null); // No transactions found
			}

			// Save the attendance record
			attendanceRepository.save(attendance);

			System.out.print(attendance);

			return ResponseEntity.ok("Attendance status updated successfully.");
		} catch (Exception ex) {
			return ResponseEntity.status(400).body("Failed to update attendance status: " + ex.getMessage());
		}
	}

}
