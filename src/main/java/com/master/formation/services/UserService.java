package com.master.formation.services;

import java.util.Collection;


import com.master.formation.beans.User;


public interface UserService {

	public Collection<User> getAll();
    
	public User getUserById(Long id);
	
	public User getUserByUsername(String username);
	
	public User getUserByEmail(String email);
	
	public Collection<User> getUserByRole(String role);
    
	public User saveOrUpdateUser(User user); 
    
	public void deleteUser(Long id);
}