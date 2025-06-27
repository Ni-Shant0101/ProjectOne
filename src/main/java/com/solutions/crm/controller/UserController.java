package com.solutions.crm.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.solutions.crm.beans.Users;
import com.solutions.crm.commom.responses.JsonResponses;
import com.solutions.crm.request.UserRequest;
import com.solutions.crm.service.UsersService;



@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UsersService usersService;

	LocalDateTime today = LocalDateTime.now();

	@GetMapping("")
	public Map<String, Object> getUsers() {

		List<Users> allUsers = usersService.getallUsers();

		if (allUsers.isEmpty()) {
			return JsonResponses.generateResponse1(false, allUsers, "List is empty");
		} else {
			return JsonResponses.generateResponse1(true, allUsers, "User Details Get Successfully");
		}
	}

	// User register
	// @Secured("ROLE_ADMIN")
	@PostMapping("")
	public Map<String, Object> registerNewUser(@RequestBody UserRequest user) {
		String userEmail = user.getEmail();
		String username = user.getUsername();

		Users users = usersService.findByEmail(userEmail);
		Users validateUsername = usersService.findByUsername(username);

		if (users == null && validateUsername == null) {

			boolean details = usersService.saveUser(user);

			if (details) {
				return JsonResponses.generateResponse1(true, user, "User Registered Successfully");
			} else {
				return JsonResponses.generateResponse1(false, user, "Some Data is Null or Invalid");
			}

		} else {
			return JsonResponses.generateResponse1(false, userEmail, "Email Or Username Already Exists");
		}

	}

	// Edit User
	@GetMapping("/edit/{user_id}")
	public Map<String, Object> findUserById(@PathVariable int user_id) {
		Optional<Users> OneUser = usersService.findById(user_id);
		if (OneUser.isPresent()) {
			return JsonResponses.generateResponse1(true, OneUser, "User Data Fetched Successfully");
		} else {
			return JsonResponses.generateResponse1(false, user_id, "User Not Found for Id " + user_id);
		}
	}

	// Update User
	@PutMapping("/update/{user_id}")
	public Map<String, Object> updateUserById(@PathVariable int user_id,
			@RequestBody UserRequest user) {

		Optional<Users> users = usersService.findById(user_id);

		if (users != null) {

			boolean updatedUser = usersService.updateUser(user, user_id);
			if (updatedUser) {
				return JsonResponses.generateResponse1(true, updatedUser, "UserData Updated Successfully");
			} else {
				return JsonResponses.generateResponse1(false, user_id, "User Not Found fot this ID");
			}
		} else {
			return JsonResponses.generateResponse1(false, user_id, "User Not Found fot this ID");
		}
	}

	@Transactional
	@DeleteMapping("/{user_id}")
	public Map<String, Object> deleteUserById(@PathVariable int user_id)
			throws Exception {

		String today1 = today.toString();
		int deleted = usersService.deleteUserById(today1, user_id);

		if (deleted == 1) {
			return JsonResponses.generateResponse2(true, "User Deleted Successfully");
		} else {
			return JsonResponses.generateResponse2(false, "No User Found For this ID " + user_id);
		}

	}

	@GetMapping("/deactive_users")
	public Map<String, Object> getDeletedUsers() {

		List<Users> deletedUsers = usersService.getdeletedUsers();

		if (deletedUsers.isEmpty()) {
			return JsonResponses.generateResponse1(false, deletedUsers, "List is Empty");

		} else {
			return JsonResponses.generateResponse1(true, deletedUsers, "Deleted Users Get Successfully");
		}
	}

	@Transactional
	@PutMapping("/active/{user_id}")
	public Map<String, Object> activeUserById(@PathVariable int user_id)
			throws Exception {

		String today1 = today.toString();
		int activated = usersService.activeUserById(today1, user_id);

		if (activated == 1) {
			return JsonResponses.generateResponse2(true, "User Activated Successfully");
		} else {
			return JsonResponses.generateResponse2(false, "No User Found For this ID " + user_id);
		}

	}

	@PostMapping("/add_role/{username}")
	public Map<String, Object> addRoleToUser(@PathVariable String username, @RequestParam String roleName)
			throws Exception {
		Users userRole = usersService.addRoleToUser(username, roleName);
		if (userRole != null) {
			return JsonResponses.generateResponse1(true, userRole, "Role added to the user successfully");
		} else {
			return JsonResponses.generateResponse1(false, username, "User Not Found fot this Username");
		}

	}

	@PostMapping("/remove_role/{username}")
	public Map<String, Object> removeRoleFromUser(@PathVariable String username, @RequestParam String roleName)
			throws Exception {
		Users userRole = usersService.removeRoleFromUser(username, roleName);
		if (userRole != null) {
			return JsonResponses.generateResponse1(true, userRole, "Role removed from the user successfully");
		} else {
			return JsonResponses.generateResponse1(false, username, "User Not Found fot this Username");
		}

	}

}
