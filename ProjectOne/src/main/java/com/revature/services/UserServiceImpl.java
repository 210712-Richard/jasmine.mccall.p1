package com.revature.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.beans.User;
import com.revature.data.UserDAOImpl;

public class UserServiceImpl implements UserService {
	UserDAOImpl ud = new UserDAOImpl();
	private Logger log = LogManager.getLogger(UserServiceImpl.class);

	
	public User login(String name, String password) {
		User u = ud.getUser(name , password);
		return u;
	

}
}