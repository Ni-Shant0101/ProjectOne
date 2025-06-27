package com.solutions.crm.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solutions.crm.beans.Role;
import com.solutions.crm.repository.RoleRepository;
import com.solutions.crm.service.RoleService;



@Service
public class RoleDao implements RoleService {

	@Autowired
	RoleRepository roleRepository;

	@Override
	public boolean saveRole(Role role) {
		
		try {
			roleRepository.save(role);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Role findByRollname(String rollname) {
		
		return roleRepository.findByRollname(rollname);
	}

	@Override
	public void saveAllRoles(List<Role> roles) {
		
		roleRepository.saveAll(roles);
	}

}
