	package com.revature.services;

import java.time.LocalDate;
import java.util.UUID;

import com.revature.beans.Form;
import com.revature.beans.ReimbursementType;
import com.revature.beans.User;

public interface UserService {   

	User login(String name);

	//User approveRequest(String username);

	//User approveRequest(User user);

	//User approveRequest(String username);

	//User approveRequest(String username, UUID id);

	User approveRequest(User user, UUID id);

	//Form approveRequest(Form f);

	//void approveRequest();

//	Form fillOutForm(String firstName, String lastName, String email, long reimbursementAmount,
//			String reimbursementType, LocalDate date, Double gradeReceive);

//	Form fillOutForm(String firstName, String lastName, String email, long reimbursementAmount, ReimbursementType type,
//			LocalDate date, Double gradeReceive);

	
	
	
	
	
	
	
}			
				