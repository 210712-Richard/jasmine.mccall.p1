package com.revature.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.beans.ApplicationStatus;
import com.revature.beans.EmailNotification;
import com.revature.beans.Form;
import com.revature.beans.User;
import com.revature.beans.UserType;
import com.revature.data.NotificationDAO;
import com.revature.data.NotificationDAOImpl;
import com.revature.data.ReimbursementDAO;
import com.revature.data.ReimbursementDAOImpl;
import com.revature.factory.Log;
import com.revature.services.ReimbursementService;
import com.revature.services.ReimbursementServiceImpl;
import com.revature.services.UserServiceImpl;

import io.javalin.http.Context;

@Log
public class UserControllerImpl implements UserController {
	private static Logger log = LogManager.getLogger(UserController.class);
	private UserServiceImpl us = new UserServiceImpl();
	private ReimbursementDAO rd = new ReimbursementDAOImpl();
	private NotificationDAO nd = new NotificationDAOImpl();
	private ReimbursementService rs = new ReimbursementServiceImpl();
	
	@Override
	public void login(Context ctx) {
		log.trace("Login method called");
		log.debug(ctx.body());
		User u = ctx.bodyAsClass(User.class);
		log.debug(u);
		u = us.login(u.getUsername());
		log.debug(u);
		
		if(u != null) {
			ctx.sessionAttribute("loggedUser", u);
		    ctx.json(u);
		    return;
		}
		
		ctx.status(401);
	}
	@Override
	
	public void logout(Context ctx) {
		ctx.req.getSession().invalidate();
		ctx.status(204);
	}

	@Override
	public void viewRequest(Context ctx) {
		User loggedUser = (User) ctx.sessionAttribute("loggedUser");
		String username = ctx.pathParam("username");
		if(loggedUser == null || !loggedUser.getUsername().equals(username)) {
			ctx.status(403);
			log.warn("User forbidden");
				ctx.status(403);
				return;
				}
		   rs.getForm(username);
		   ctx.json(rd);
		
		
	}

	@Override
	public void viewEmail(Context ctx) {
		User loggedUser = (User) ctx.sessionAttribute("loggedUser");
		String email= ctx.pathParam("email");
		if(loggedUser == null || !loggedUser.getEmail().equals(email)) {
			ctx.status(403);
			log.warn("User forbidden");
				ctx.status(403);
				return;
				}
		     EmailNotification e = new EmailNotification();
		     e.notifyUser(email, "hey");
		     nd.addEmail(e);
		     //nd.getEmail(email);
		     ctx.json(nd);
		
		
	}

	@Override
	public void viewRequestById(Context ctx) {
		User loggedUser = (User) ctx.sessionAttribute("loggedUser");
		String username = ctx.pathParam("username");
		Form form = (Form)ctx.bodyAsClass(Form.class);
		//if(loggedUser == null || !loggedUser.getType().equals(UserType.DirectSup)) {
			if(loggedUser == null || !loggedUser.getUsername().equals(username)) {	
			ctx.status(403);
			log.warn("User forbidden");
				ctx.status(403);
				return;
				}
		   rs.getForm(username, form.getId());
		   ctx.json(rd);
		
	}

	@Override
	public void approve(Context ctx) {
		User loggedUser = (User) ctx.sessionAttribute("loggedUser");
		String username = ctx.pathParam("username");
		//User user = ctx.bodyAsClass(User.class);
		Form f = ctx.bodyAsClass(Form.class);
		if(loggedUser == null || !loggedUser.getUsername().equals(username)) {
			ctx.status(403);
			log.warn("User forbidden");
				ctx.status(403);
				return;
				}
		
		rd.updateForm(f);
		ctx.json(us);
		
	}


	
	

}
