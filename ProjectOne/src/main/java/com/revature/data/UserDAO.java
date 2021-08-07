package com.revature.data;

import java.util.List;

import com.revature.beans.User;

public interface UserDAO {
	
	void addUser(User u);
	List<User> getUsers(); 
	void updateUser(User user);
	User getUser(String username);
		
	

}