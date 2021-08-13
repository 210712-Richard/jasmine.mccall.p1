package com.revature.controllers;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.beans.ApplicationStatus;
import com.revature.beans.Form;
import com.revature.beans.ReasonForDenial;
import com.revature.beans.User;
import com.revature.beans.UserType;
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
	public void viewRequestById(Context ctx) {
		User loggedUser = (User) ctx.sessionAttribute("loggedUser");
		String username = ctx.pathParam("username");
		Form form = (Form)ctx.bodyAsClass(Form.class);
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
	public void supApproval(Context ctx) {
		User loggedUser = (User) ctx.sessionAttribute("loggedUser");
		Form f = ctx.bodyAsClass(Form.class);
		if(loggedUser == null || !loggedUser.getType().equals(UserType.DirectSup)) {
			ctx.status(403);
			log.warn("User forbidden");
				ctx.status(403);
				return;
		}

		f.setDirectSupApproval(true);
		rd.updateDirectSupApproval(f);
		//rs.getForm(username);
		//rd.getFormByUsername(username);
		
		   ctx.json(rd);
		



		
	}
	
	@Override
	public void deptHeadApproval(Context ctx) {
		User loggedUser = (User) ctx.sessionAttribute("loggedUser");

		Form f = ctx.bodyAsClass(Form.class);
		if(loggedUser == null || !loggedUser.getType().equals(UserType.DeptHead)) {
			ctx.status(403);
			log.warn("User forbidden");
				ctx.status(403);
				return;
		}
		
		f.setDeptHeadApproval(true);
		rd.updateDeptHeadApproval(f);
		
		
		ctx.json(rd);
		
	}
	
	@Override
	public void benCoApproval(Context ctx) {
		User loggedUser = (User) ctx.sessionAttribute("loggedUser");
		Form f = ctx.bodyAsClass(Form.class);
		if(loggedUser == null || !loggedUser.getType().equals(UserType.BenCo)) {
			ctx.status(403);
			log.warn("User forbidden");
				ctx.status(403);
				return;
		}
		
		f.setBenCoApproval(true);
		rd.updateBenCoApproval(f);
		f.setStatus(ApplicationStatus.APPROVED);
		rd.updateStatus(f);
		
		ctx.json(rd);

		
		
	}
	
	@Override
	public void supDenial(Context ctx) {
		User loggedUser = (User) ctx.sessionAttribute("loggedUser");
		Form f = ctx.bodyAsClass(Form.class);
		if(loggedUser == null || !loggedUser.getType().equals(UserType.DirectSup)) {
			ctx.status(403);
			log.warn("User forbidden");
				ctx.status(403);
				return;
		}
		
		f.setDirectSupApproval(false);
		rd.updateDirectSupApproval(f);
		f.setReasonForDenial(ReasonForDenial.INSUFFICIENT_GRADE);
		rd.reasonForDenial(f);

		
		
		ctx.json(rd);
		
	}
	
	@Override
	public void deptHeadDenial(Context ctx) {
		User loggedUser = (User) ctx.sessionAttribute("loggedUser");
		Form f = ctx.bodyAsClass(Form.class);
		if(loggedUser == null || !loggedUser.getType().equals(UserType.DeptHead)) {
			ctx.status(403);
			log.warn("User forbidden");
				ctx.status(403);
				return;
		}
		
		f.setDeptHeadApproval(false);
		rd.updateDeptHeadApproval(f);
	    f.setReasonForDenial(f.getReasonForDenial());
	    rd.reasonForDenial(f);
		
		ctx.json(rd);
		
	}

	@Override
	public void benCoDenial(Context ctx) {
		User loggedUser = (User) ctx.sessionAttribute("loggedUser");
		Form f = ctx.bodyAsClass(Form.class);
		if(loggedUser == null || !loggedUser.getType().equals(UserType.BenCo)) {
			ctx.status(403);
			log.warn("User forbidden");
				ctx.status(403);
				return;
		}
		
		f.setBenCoApproval(false);
		rd.updateBenCoApproval(f);
		f.setStatus(ApplicationStatus.DENIED);
		rd.updateStatus(f);
		rd.reasonForDenial(f);
//		f.setReasonForDenial("need more info");
//		rd.reasonForDenial(f);
		


		
		
		ctx.json(rd);
		
	}


@Override
public void moreInfoBenco(Context ctx) {
	User loggedUser = (User) ctx.sessionAttribute("loggedUser");
	Form f = ctx.bodyAsClass(Form.class);
	if(loggedUser == null || !loggedUser.getType().equals(UserType.BenCo)) {
		ctx.status(403);
		log.warn("User forbidden");
			ctx.status(403);
			return;
	}
	
	f.setBenCoApproval(false);
	rd.updateBenCoApproval(f);
	f.setStatus(ApplicationStatus.NEED_MORE_INFO);
	rd.updateStatus(f);
	
	ctx.json(rd);
	
}

	
	

}
