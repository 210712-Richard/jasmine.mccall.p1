package com.revature.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.javalin.http.Context;

import com.revature.beans.User;
import com.revature.services.UserServiceImpl;

public class UserController {
	private static Logger log = LogManager.getLogger(UserController.class);
	private UserServiceImpl us = new UserServiceImpl();
	
	public void login(Context ctx) {
		log.trace("Login method called");
		log.debug(ctx.body());
		User u = ctx.bodyAsClass(User.class);
		log.debug(u);
		u = us.login(u.getUsername(), u.getPassword());
		log.debug(u);
		
		if(u != null) {
			ctx.sessionAttribute("loggedUser", u);
		    ctx.json(u);
		    return;
		}
		
		ctx.status(401);
	}
	

}
