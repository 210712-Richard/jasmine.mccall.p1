package com.revature.services;


import com.revature.beans.Form;
import com.revature.beans.User;
import com.revature.data.ReimbursementDAO;
import com.revature.data.ReimbursementDAOImpl;
import com.revature.factory.BeanFactory;
import com.revature.factory.Log;

@Log
public class ReimbursementServiceImpl implements ReimbursementService {
	private ReimbursementDAO rd = (ReimbursementDAO) BeanFactory.getFactory().get(ReimbursementDAO.class, ReimbursementDAOImpl.class);
	
	
	@Override
	public Form createNewForm(Form form) {
		rd.addForm(form);
		return form;
		
}
	@Override
	public Form getForm(User username) {
		return rd.getFormByUsername(username);
	}
	
}