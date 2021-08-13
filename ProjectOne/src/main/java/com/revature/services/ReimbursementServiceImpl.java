package com.revature.services;


import java.time.LocalDate;

import java.util.UUID;


import com.revature.beans.Form;
import com.revature.beans.GradeFormat;

import com.revature.beans.ReimbursementType;
import com.revature.data.ReimbursementDAO;
import com.revature.data.ReimbursementDAOImpl;
import com.revature.factory.BeanFactory;
import com.revature.factory.Log;



@Log
public class ReimbursementServiceImpl implements ReimbursementService {
	private ReimbursementDAO rd = (ReimbursementDAO) BeanFactory.getFactory().get(ReimbursementDAO.class, ReimbursementDAOImpl.class);
	Form form = new Form();
	
	


	@Override
	public Form createNewForm(String username, String firstName, String lastName, String email, Long reimbursementAmount, LocalDate date, String gradeReceived, GradeFormat format, ReimbursementType type) {
		Form f = new Form();
		f.setUsername(username);
		f.setFirstName(firstName);
		f.setLastName(lastName);
		f.setEmail(email);
		f.setReimbursementAmount(reimbursementAmount);
		f.setDate(date);
		f.setGradeReceived(gradeReceived);
		f.setFormat(format);
		f.setType(type);
		rd.addForm(f);
		return f;
		
}
	@Override
	public Form getForm(String username) {
		
		return rd.getFormByUsername(username);
	}

	@Override
	public Form sendForm(String username) {
		
		return rd.getFormByUsername(username);
	}
	@Override
	public Form getForm(String username, UUID id) {
		return rd.getFormById(username, id);
		
	}


@Override
public void updateDocuments(Form form) {
	rd.updateDocuments(form);
}


	
}