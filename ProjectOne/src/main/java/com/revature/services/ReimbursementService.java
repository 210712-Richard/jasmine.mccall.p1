package com.revature.services;

import java.time.LocalDate;
import java.util.UUID;

import com.revature.beans.ApplicationStatus;
import com.revature.beans.Form;
import com.revature.beans.GradeFormat;
import com.revature.beans.ReimbursementType;

public interface ReimbursementService {

	 //Form createNewForm(Form form);

	 Form getForm(String username);


	Form sendForm(String username);

	Form createNewForm(String username, String firstName, String lastName, String email, long reimbursementAmount,
			LocalDate date, double gradeReceived, GradeFormat format, ReimbursementType type);


	Form getForm(String username, UUID id);

}
