package com.solutions.crm.service;

import java.util.List;

import com.solutions.crm.beans.Role;



public interface RoleService {
	
	boolean saveRole(Role role);
	
	public Role findByRollname(String rollname);
	
	void saveAllRoles(List<Role> roles);

}
