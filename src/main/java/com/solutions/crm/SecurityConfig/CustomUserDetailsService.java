package com.solutions.crm.SecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.solutions.crm.beans.Users;
import com.solutions.crm.repository.UsersRepository;



@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	UsersRepository usersRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = usersRepository.findByUsername(username);
		if(user != null) {
			return user;
		}else {
			throw new UsernameNotFoundException("User Not Found");
		}
	}

}
