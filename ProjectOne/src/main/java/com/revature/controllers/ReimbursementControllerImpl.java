package com.revature.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.beans.Form;

import com.revature.beans.User;
import com.revature.beans.UserType;
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
		log.debug(f);

		// Add the gacha to the database
		rs.createNewForm(f);
		// send back the newly saved gacha object with appropriate status code
		ctx.json(f);
	}
}