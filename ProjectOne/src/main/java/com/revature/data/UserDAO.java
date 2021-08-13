package com.revature.data;

import java.util.List;
import java.util.UUID;
import com.revature.beans.User;

public interface UserDAO {
	
	void addUser(User u);
	List<User> getUsers(); 
	void updateUser(User user);
	User getUser(String username);
	List<UUID> getUserForms(String username);
	void updateFunds(User u);
	User getUserbyEmail(String email);
		
	

}