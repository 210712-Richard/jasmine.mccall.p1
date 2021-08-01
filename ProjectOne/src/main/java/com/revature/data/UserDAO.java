package com.revature.data;

import java.util.List;

import com.revature.beans.User;

public interface UserDAO {
	
	List<User> getUsers(); 
	User getUser(String username, String password);
	void updateUser(User user);
		
	

}