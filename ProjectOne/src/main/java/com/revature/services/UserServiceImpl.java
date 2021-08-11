package com.revature.services;

import java.time.LocalDate;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.beans.ApplicationStatus;
import com.revature.beans.Form;
import com.revature.beans.ReimbursementType;
import com.revature.beans.User;
import com.revature.beans.UserType;
import com.revature.data.ReimbursementDAOImpl;
import com.revature.data.UserDAOImpl;

public class UserServiceImpl implements UserService {
	UserDAOImpl ud = new UserDAOImpl();
	ReimbursementDAOImpl rd = new ReimbursementDAOImpl();
	private Logger log = LogManager.getLogger(UserServiceImpl.class);

	

	@Override
	public User login(String username) {
		User u = ud.getUser(username);
		log.debug(u);
		return u;
		
		}




	@Override
     public User approveRequest(User user, UUID id) {

    	 
     }

	
}