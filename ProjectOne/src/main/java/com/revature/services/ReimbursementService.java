package com.revature.services;

import com.revature.beans.Form;
import com.revature.beans.User;

public interface ReimbursementService {

	Form createNewForm(Form form);
	
	public Form getForm(User username);

}
