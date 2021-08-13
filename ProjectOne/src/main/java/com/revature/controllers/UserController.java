package com.revature.controllers;

import io.javalin.http.Context;

public interface UserController {
	
	void login(Context ctx);
	void logout(Context ctx);
	void viewRequest(Context ctx);
	void viewRequestById(Context ctx);
	void supApproval(Context ctx);
	void deptHeadApproval(Context ctx);
	void benCoApproval(Context ctx);
	void benCoDenial(Context ctx);
	void deptHeadDenial(Context ctx);
	void supDenial(Context ctx);
	void moreInfoBenco(Context ctx);


}
