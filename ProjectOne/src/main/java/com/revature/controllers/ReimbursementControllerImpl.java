package com.revature.controllers;

import java.io.InputStream;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.revature.beans.Form;


import com.revature.beans.User;
import com.revature.beans.UserType;

import com.revature.factory.BeanFactory;
import com.revature.factory.Log;
import com.revature.services.ReimbursementService;
import com.revature.services.ReimbursementServiceImpl;
import com.revature.util.S3Util;

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
		Form newForm = rs.createNewForm(f.getUsername(), f.getFirstName(), f.getLastName(), f.getEmail(), f.getReimbursementAmount(), f.getDate(), f.getGradeReceived(), f.getFormat(), f.getType());
		ctx.status(201);
		log.debug(newForm);
		
		
        ctx.json(newForm);		
	}
	

	@Override
	public void uploadDocument(Context ctx) {
		User loggedUser = ctx.sessionAttribute("loggedUser");
		// Checking if logged in
		if (loggedUser == null) {
			ctx.status(401);
			return;
		}
		// Check that we're an admin
		if (!loggedUser.getType().equals(UserType.Employee)) {
			ctx.status(403);
			return;
		}

		
		String username = ctx.pathParam("username");
		Form f = (Form)
				rs.getForm(username);
		if(f == null) {
			ctx.status(404);
			return;
		}
		

		// How are we going to get the filetype?;
		String filetype = ctx.header("extension");
		if(filetype == null) {
			ctx.status(400); // bad request, expected the filetype
			return;
		}
		String key = username+"."+filetype;
		S3Util.getInstance().uploadToBucket(key, ctx.bodyAsBytes());
		f.setDocument(key);
		rs.updateDocuments(f);
		ctx.json(f);
	}
	
	@Override
	public void getUpload(Context ctx) {
		User loggedUser = ctx.sessionAttribute("loggedUser");
		// Checking if logged in
		if (loggedUser == null) {
			ctx.status(401);
			return;
		}
		String username = ctx.pathParam("username");
		Form f =
				rs.getForm(username);
		if(f == null) {
			ctx.status(404);
			return;
		}
		

		
		try {
			InputStream document = S3Util.getInstance().getObject(f.getDocument());
			ctx.result(document);
		} catch (Exception e) {
			ctx.status(500);
		}
}
}