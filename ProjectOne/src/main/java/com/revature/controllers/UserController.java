package com.revature.controllers;

import io.javalin.http.Context;

public interface UserController {
	
	void login(Context ctx);
	void logout(Context ctx);
	void viewRequest(Context ctx);
	void viewRequestById(Context ctx);
	void viewEmail(Context ctx);
	void approve(Context ctx);


}
