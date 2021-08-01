package com.revature.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import com.revature.beans.User;
import com.revature.data.UserDAOImpl;
import com.revature.main.UserService;

public class UserServiceImpl implements UserService {
	UserDAOImpl ud = new UserDAOImpl();

	
	public User login(String name, String password) {
		User u = ud.getUser(name , password);
		return u;
	

}
}