package com.revature.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;import com.revature.beans.Form;

import com.revature.beans.User;
import com.revature.beans.UserType;
import com.revature.data.ReimbursementDAO;
import com.revature.data.ReimbursementDAOImpl;
import com.revature.factory.BeanFactory;
import com.revature.factory.Log;
import com.revature.services.ReimbursementService;
import com.revature.services.ReimbursementServiceImpl;

import io.javalin.http.Context;

@Log
public class ReimbursementControllerImpl implements ReimbursementController {
	private static Logger log = LogManager.getLogger(ReimbursementControllerImpl.class);
	private ReimbursementService rs = (ReimbursementService) BeanFactory.getFactory().get(ReimbursementService.class,
			ReimbursementServiceImpl.class);
	private ReimbursementDAO rd = (ReimbursementDAO) BeanFactory.getFactory().get(ReimbursementDAO.class,
			ReimbursementDAOImpl.class);

	public void addForm(Context ctx) {

		User loggedUser = ctx.sessionAttribute("loggedUser");
		if (loggedUser == null) {
			ctx.status(401);
			return;
		}
		if (!loggedUser.getType().equals(UserType.Employee)) {
			ctx.status(403);
			return;
		}
		Form f = ctx.bodyAsClass(Form.class);
		Form newForm = rs.createNewForm(f.getUsername(), f.getFirstName(), f.getLastName(), f.getEmail(), f.getReimbursementAmount(), f.getDate(), f.getGradeReceived(), f.getFormat(), f.getType());
		ctx.status(201);
		log.debug(newForm);
		
		
        ctx.json(newForm);		
	}
	
}