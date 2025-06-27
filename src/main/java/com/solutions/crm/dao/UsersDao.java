package com.solutions.crm.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.solutions.crm.beans.Role;
import com.solutions.crm.beans.Users;
import com.solutions.crm.repository.RoleRepository;
import com.solutions.crm.repository.UsersRepository;
import com.solutions.crm.request.UserRequest;
import com.solutions.crm.service.UsersService;



@Service
public class UsersDao implements UsersService {

	@Autowired
	UsersRepository usersRepo;

	@Autowired
	RoleRepository roleRepository;

	LocalDateTime today = LocalDateTime.now();

	String today1 = today.toString();

	public UsersDao(UsersRepository usersRepo) {
		super();
		this.usersRepo = usersRepo;
	}

	@Override
	public boolean saveUser(UserRequest user) {

		
		try {
			Users user1 = new Users();
			user1.setFirst_name(user.getFirst_name());
			user1.setLast_name(user.getLast_name());
			user1.setEmail(user.getEmail());
			user1.setUsername(user.getUsername());

			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

			user1.setPassword(passwordEncoder.encode(user.getPassword()));

			user1.setStatus(1);
			user1.setCreated_at(today);

			String role = user.getRole();

			if (role.equals("super_admin")) {
				Role super_admin = roleRepository.findByRollname("ROLE_SUPER_ADMIN");
				user1.addRole(super_admin);
			} else if (role.equals("admin")) {
				Role admin = roleRepository.findByRollname("ROLE_ADMIN");

				user1.addRole(admin);
			} else if (role.equals("employee")) {
				Role manager = roleRepository.findByRollname("ROLE_EMPLOYEE");

				user1.addRole(manager);
			} else if (role.equals("user")) {
				Role staff = roleRepository.findByRollname("ROLE_USER");

				user1.addRole(staff);
			}

			usersRepo.save(user1);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Users> getallUsers() {


		return usersRepo.findAllUsers();
	}

	@Override
	public Optional<Users> findById(int id) {
		Optional<Users> user1 = usersRepo.findUserById(id);
		return user1;
	}

	@Override
	public boolean updateUser(UserRequest user, int id) {

		try {
			String role = user.getRole();

			Users user1 = new Users();
			user1.setFirst_name(user.getFirst_name());
			user1.setLast_name(user.getLast_name());
			user1.setEmail(user.getEmail());
			user1.setUsername(user.getUsername());

			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

			user1.setPassword(passwordEncoder.encode(user.getPassword()));

			user1.setStatus(1);
			user1.setId(id);
			user1.setUpdated_at(today);

			if (role.equals("super_admin")) {
				Role super_admin = roleRepository.findByRollname("ROLE_SUPER_ADMIN");
				// System.out.println("Role "+super_admin);
				user1.addRole(super_admin);
			} else if (role.equals("admin")) {
				Role admin = roleRepository.findByRollname("ROLE_ADMIN");

				user1.addRole(admin);
			} else if (role.equals("employee")) {
				Role manager = roleRepository.findByRollname("ROLE_EMPLOYEE");

				user1.addRole(manager);
			} else if (role.equals("user")) {
				Role staff = roleRepository.findByRollname("ROLE_USER");

				user1.addRole(staff);
			}

			user1.setCreated_at(today);

			usersRepo.save(user1);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Users> getdeletedUsers() {
	
		return usersRepo.deletedUsers();
	}

	@Override
	public int deleteUserById(String updated, int id ) {

		int deleted = usersRepo.deleteByUserId(updated, id);
		if (deleted == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int activeUserById(String updated, int id) {

		int activated = usersRepo.activeByUserId(updated, id);
		if (activated == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Users addRoleToUser(String username, String roleName) {
		Users user = usersRepo.findByUsername(username);
		// System.out.println("User " + user);
		if (user != null) {
			if (roleName.equals("super_admin")) {
				Role super_admin = roleRepository.findByRollname("ROLE_SUPER_ADMIN");
				user.getRoles().add(super_admin);
			} else if (roleName.toString().equals("admin")) {
				Role admin = roleRepository.findByRollname("ROLE_ADMIN");
				System.out.println("Role " + admin);
				user.getRoles().add(admin);
			} else if (roleName.toString().equals("manager")) {
				Role manager = roleRepository.findByRollname("ROLE_MANAGER");

				user.getRoles().add(manager);
			} else if (roleName.toString().equals("staff")) {
				Role staff = roleRepository.findByRollname("ROLE_STAFF");

				user.getRoles().add(staff);
			}

			return usersRepo.save(user);
		} else {
			return user;
		}
	}

	@Override
	public Users removeRoleFromUser(String username, String roleName) {
		Users user = usersRepo.findByUsername(username);
		// System.out.println("User " + user);
		if (user != null) {
			if (roleName.equals("super_admin")) {
				Role super_admin = roleRepository.findByRollname("ROLE_SUPER_ADMIN");
				user.getRoles().remove(super_admin);
			} else if (roleName.toString().equals("admin")) {
				Role admin = roleRepository.findByRollname("ROLE_ADMIN");
				// System.out.println("Role " + admin);
				user.getRoles().remove(admin);
			} else if (roleName.toString().equals("manager")) {
				Role manager = roleRepository.findByRollname("ROLE_MANAGER");

				user.getRoles().remove(manager);
			} else if (roleName.toString().equals("staff")) {
				Role staff = roleRepository.findByRollname("ROLE_STAFF");

				user.getRoles().remove(staff);
			}

			return usersRepo.save(user);
		} else {
			return user;
		}
	}

	@Override
	public Users findByEmail(String email) {

		return usersRepo.findByEmail(email);
	}

	@Override
	public Users findByUsername(String username) {

		return usersRepo.findByUsername(username);
	}

	@Override
	public boolean saveUser1(Users user) {
		try {
			usersRepo.save(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public int updatePasswordByEmailAndId(String password, String email, int user_id) {

		int updated = usersRepo.updatePasswordByEmailAndId(password, today1, email, user_id);
		if (updated == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Optional<Users> getByUsername(String username) {
		
		return usersRepo.getByUsername(username);
	}

}
