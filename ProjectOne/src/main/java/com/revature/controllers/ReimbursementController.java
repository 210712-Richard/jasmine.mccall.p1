package com.revature.controllers;

import io.javalin.http.Context;

public interface ReimbursementController {
	void addForm(Context ctx);

	void uploadDocument(Context ctx);

	void getUpload(Context ctx);

}
