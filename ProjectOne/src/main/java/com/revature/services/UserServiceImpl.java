package com.revature.services;

import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

//import java.time.LocalDate;
//mport java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.beans.ApplicationStatus;
import com.revature.beans.Form;
import com.revature.beans.ReimbursementType;
import com.revature.beans.User;
import com.revature.beans.UserType;
import com.revature.data.ReimbursementDAO;
import com.revature.data.ReimbursementDAOImpl;
import com.revature.data.UserDAO;
import com.revature.data.UserDAOImpl;
//import com.revature.data.UserDao;
//import com.revature.data.UserDaoImpl;
import com.revature.factory.BeanFactory;

public class UserServiceImpl implements UserService {
	public UserDAO ud = (UserDAO)  BeanFactory.getFactory().get(UserDAO.class, UserDAOImpl.class);
	public ReimbursementDAO rd = (ReimbursementDAO)  BeanFactory.getFactory().get(ReimbursementDAO.class, ReimbursementDAOImpl.class);
	private Logger log = LogManager.getLogger(UserServiceImpl.class);
	Form f = new Form();

	

	@Override
	public User login(String username) {
		User u = ud.getUser(username);
		
		log.debug(u);
		return u;
		
		}
	

	





	
}