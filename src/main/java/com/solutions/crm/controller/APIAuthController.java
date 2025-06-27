package com.solutions.crm.controller;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.solutions.crm.Jwt.AuthRequest;
import com.solutions.crm.Jwt.AuthResponse;
import com.solutions.crm.Jwt.JwtTokenUtil;
import com.solutions.crm.beans.ResetPasswordOtp;
import com.solutions.crm.beans.Role;
import com.solutions.crm.beans.Users;
import com.solutions.crm.commom.responses.JsonResponses;
import com.solutions.crm.repository.RoleRepository;
import com.solutions.crm.repository.UsersRepository;
import com.solutions.crm.request.UserRequest;
import com.solutions.crm.request.updatePasswordRequest;
import com.solutions.crm.request.verifyOtpRequest;
import com.solutions.crm.service.EmailSenderService;
import com.solutions.crm.service.PasswordResetOtpService;
import com.solutions.crm.service.RoleService;
import com.solutions.crm.service.UsersService;

@RestController
public class APIAuthController {

	@Autowired
	AuthenticationManager authManager;

	@Autowired
	JwtTokenUtil jwtUtil;

	@Autowired
	PasswordResetOtpService passwordResetOtpService;

	@Autowired
	private EmailSenderService senderService;

	@Autowired
	UsersService usersService;

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	RoleService roleService;

	@Autowired
	RoleRepository roleRepository;

	Random random = new Random(1000);

	LocalDateTime today = LocalDateTime.now();

	String today1 = today.toString();

	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	// Login
	@Transactional
	@PostMapping("auth/login")
	public Map<String, Object> login(@RequestBody AuthRequest request) {
		try {
			Authentication authentication = authManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

			Users users = (Users) authentication.getPrincipal();

			String accessToken = jwtUtil.generateAccessToken(users);
			AuthResponse response = new AuthResponse(users.getUsername(), accessToken);
			// return ResponseEntity.ok(response);
			return JsonResponses.generateResponse1(true, response, "User Logged in Successfully");

		} catch (BadCredentialsException e) {
			return JsonResponses.generateResponse1(false, request, "Bad Credentials");
		}
	}

	// User register
	@PostMapping("auth/register")
	public Map<String, Object> registerUser(@RequestBody UserRequest users) {
		String userEmail = users.getEmail();
		String role = users.getRole();
		Users u2 = usersService.findByEmail(userEmail);
		Users username = usersService.findByUsername(users.getUsername());
		
		if (u2 == null && username == null) {

			Users mainUser = new Users();
			
			if(role.equals("user")) {
				Role roleUser1 = roleService.findByRollname("ROLE_USER");
				if (roleUser1 == null) {
					Role r2 = new Role();
					r2.setRollname("ROLE_USER");
					r2.setRoleDescription("This is Default Role for Newly Created User");
					Role rol = roleRepository.save(r2);			
					mainUser.addRole(rol);
				} else {
					mainUser.addRole(roleUser1);
				}
			}
						
			mainUser.setPassword(passwordEncoder.encode(users.getPassword()));
			mainUser.setFirst_name(users.getFirst_name());
			mainUser.setLast_name(users.getLast_name());
			mainUser.setEmail(userEmail);
			mainUser.setUsername(users.getUsername());
			mainUser.setStatus(1);
			mainUser.setCreated_at(today);

			Role roleUser = roleService.findByRollname("ROLE_USER");

			Users details = usersRepository.save(mainUser);

			return JsonResponses.generateResponse1(true, details, "User Registered Successfully");
		} else {
			return JsonResponses.generateResponse1(false, userEmail, "Email Or Username Already Exists");
		}

	}

	@Transactional
	@PostMapping("send-otp")
	public Map<String, Object> SendOtp(@RequestParam String email) {
		Users user = usersService.findByEmail(email);

		int otp = random.nextInt(999999);

		if (user != null) {

			ResetPasswordOtp user1 = new ResetPasswordOtp();
			user1.setOtp(otp);
			user1.setUser(user);
			user1.setUser_email(email);
			user1.setStatus(1);
			user1.setOtpRequestedTime(today);

			String subject = "Password Reset OTP - Box Calculaion Portal";

			String message = "Hello <b>" + user.getFirst_name() + "</b>,<br>"
					+ "&emsp; You recently requested to reset the password for your Box Calculation account. Enter the below One time password to proceed."
					+ "<h3> Your OTP : " + otp + " </h3>"
					+ "&ensp; If you did not request a password reset, please ignore this email or reply to let us know. This password reset OTP is only valid for the next 24 Hours."
					+ "<br>" + "<br>" + "Thanks & Regards," + "<br>" + "<b>Box Calculaion team</b>" + "<br>"
					+ "<h4>Please do not reply to this e-mail, this is a system generated email sent from an unattended mail box.</h4>";

			try {
				senderService.sendHtmlEmail(subject, message, email);
			} catch (Exception e) {
				return JsonResponses.generateResponse1(false, email, "Invalid Email");
			}

			ResetPasswordOtp user2 = passwordResetOtpService.findByEmail(email);

			if (user2 == null) {
				passwordResetOtpService.savepasswordResetOtp(user2);
			} else {
				passwordResetOtpService.updateStatusByEmail(email);
				passwordResetOtpService.savepasswordResetOtp(user1);
			}

			return JsonResponses.generateResponse1(true, email, "Email Send Successfully");
		} else {
			return JsonResponses.generateResponse1(false, email, "Email Not Found");
		}
	}

	// Generating Random String
	public String generatePasswordResetToken(int length) {
		// create a string of all characters
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz";

		// create random string builder
		StringBuilder sb = new StringBuilder();

		// create an object of Random class
		Random random = new Random();

		// specify length of random string
		// int length = 15;

		for (int i = 0; i < length; i++) {

			// generate random index number
			int index = random.nextInt(alphabet.length());

			// get character specified by index
			// from the string
			char randomChar = alphabet.charAt(index);

			// append the character to string builder
			sb.append(randomChar);
		}

		String randomString = sb.toString();

		return randomString;

	}

	// Verify Otp and update status of that email Id
	@Transactional
	@PostMapping("verify-otp")
	public Map<String, Object> verifyOtp(@RequestBody verifyOtpRequest otpRequest) {
		String email = otpRequest.getEmail();

		try {
			ResetPasswordOtp user = passwordResetOtpService.findByEmail(email);

			// Otp Validity Check
			boolean timecheck = user.isOTPRequired();

			// DB Otp
			int dbotp = user.getOtp();

			// User Otp
			int otp = otpRequest.getOtp();

			if (user != null && dbotp == otp) {
				if (timecheck == false) {
					return JsonResponses.generateResponse1(false, otp,
							"Otp Expired... Please generate a new One Time Password...!!!");
				}

				String tokenValue = this.generatePasswordResetToken(16);

				passwordResetOtpService.updateStatusByEmailAndResetToken(email, tokenValue);

				return JsonResponses.generateResponse1(true, tokenValue, "Otp Verified Successfully");
			} else {
				return JsonResponses.generateResponse1(false, otp, "Otp Not Matched");
			}
		} catch (Exception e) {
			return JsonResponses.generateResponse1(false, email, "Invalid Email");
		}
	}

	@Transactional
	@PutMapping("update-password/{token}")
	public Map<String, Object> updatePassword(@PathVariable String token,
			@RequestBody updatePasswordRequest passwordRequest) {

		try {

			ResetPasswordOtp Dbuser = passwordResetOtpService.findByResetToken(token);

			String resetToken = Dbuser.getReset_token();

			if (token.equals(resetToken)) {

				String email = passwordRequest.getEmail();

				String pwd = passwordRequest.getPassword();
				String password = passwordEncoder.encode(pwd);

				Users user1 = usersService.findByEmail(email);

				int user_id = user1.getId();

				if (user1 != null && password != null) {

					usersService.updatePasswordByEmailAndId(password, email, user_id);

					return JsonResponses.generateResponse1(true, email, "Password Changed Successfully");
				} else {
					return JsonResponses.generateResponse1(false, email, "Password Not Changed");
				}
			} else {
				return JsonResponses.generateResponse1(false, Dbuser, "Token Not Found");
			}
		} catch (Exception e) {
			return JsonResponses.generateResponse1(false, token, "Token is Invalid");
		}
	}

}
