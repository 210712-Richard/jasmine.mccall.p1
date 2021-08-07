package com.revature.services;

import java.time.LocalDate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.beans.Form;
import com.revature.beans.ReimbursementType;
import com.revature.beans.User;
import com.revature.data.UserDAOImpl;

public class UserServiceImpl implements UserService {
	UserDAOImpl ud = new UserDAOImpl();
	private Logger log = LogManager.getLogger(UserServiceImpl.class);

	

	@Override
	public User login(String username) {
		User u = ud.getUser(username);
		log.debug(u);
		return u;
		
		}
	

	@Override
	public Form fillOutForm(String firstName, String lastName, String email, long reimbursementAmount, ReimbursementType type,
			LocalDate date, Double gradeReceive) {
		Form f = new Form();
		f.setFirstName(firstName);
		f.setLastName(lastName);
		f.setEmail(email);
		f.setReimbursementAmount(reimbursementAmount);
		f.setType(type);
		f.setDate(date);
		f.setGradeReceived(gradeReceive);
		ud.addForm(f);
		return f;
	}

	
}