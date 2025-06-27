package com.solutions.crm.service;

import java.util.List;
import java.util.Optional;

import com.solutions.crm.beans.Users;
import com.solutions.crm.request.UserRequest;


public interface UsersService {
	
	boolean saveUser(UserRequest user);
	
	boolean saveUser1(Users user);

	List<Users> getallUsers();

	Optional<Users> findById(int id);

	boolean updateUser(UserRequest user,int id);

	List<Users> getdeletedUsers();

	int deleteUserById(String updated, int id);

	int activeUserById(String updated, int id);
	
	Users addRoleToUser(String username, String roleName);
	
	Users removeRoleFromUser(String username, String roleName);
	
	Users findByEmail(String email);
	
	Users findByUsername(String username);
	
	int updatePasswordByEmailAndId(String password, String email, int user_id);

	Optional<Users> getByUsername(String username);

}
