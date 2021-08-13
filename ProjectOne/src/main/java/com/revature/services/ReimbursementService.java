package com.revature.services;

import java.time.LocalDate;
import java.util.UUID;

import com.revature.beans.Form;
import com.revature.beans.GradeFormat;
import com.revature.beans.ReimbursementType;

public interface ReimbursementService {



	 Form getForm(String username);


	Form sendForm(String username);



	Form getForm(String username, UUID id);




	void updateDocuments(Form form);


	Form createNewForm(String username, String firstName, String lastName, String email, Long reimbursementAmount,
			LocalDate date, String gradeReceived, GradeFormat format, ReimbursementType type);



}
