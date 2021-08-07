	package com.revature.services;

import java.time.LocalDate;

import com.revature.beans.Form;
import com.revature.beans.ReimbursementType;
import com.revature.beans.User;

public interface UserService {   

	User login(String name);

//	Form fillOutForm(String firstName, String lastName, String email, long reimbursementAmount,
//			String reimbursementType, LocalDate date, Double gradeReceive);

	Form fillOutForm(String firstName, String lastName, String email, long reimbursementAmount, ReimbursementType type,
			LocalDate date, Double gradeReceive);

	
	
	
	
	
	
	
}			
				