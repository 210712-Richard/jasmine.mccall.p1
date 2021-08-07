package com.revature.main;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.revature.controllers.ReimbursementController;
import com.revature.controllers.ReimbursementControllerImpl;
import com.revature.controllers.UserController;
import com.revature.controllers.UserControllerImpl;
import com.revature.factory.BeanFactory;
//import com.revature.factory.Notification;
//import com.revature.factory.NotificationFactory;

import io.javalin.Javalin;
import io.javalin.plugin.json.JavalinJackson;

public class Driver {
	public static void main(String[] args) {
		instantiateDatabase();
		//javalin();
		
//		NotificationFactory notificationFactory = new NotificationFactory();
//		Notification notification = notificationFactory.createNotification("EMAIL");
//		notification.notifyUser();
//	}
	}
	public static void instantiateDatabase() {
//		DataBaseCreator.dropTables();
//		try {
//			Thread.sleep(30000); // wait 20 seconds
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		DataBaseCreator.createTables();
//		try {
//			Thread.sleep(10000); // wait 10 seconds
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
		DataBaseCreator.populateUserTable();
		System.exit(0);
	}

	public static void javalin() {
		
		// Set up Jackson to serialize LocalDates and LocalDateTimes
		ObjectMapper jackson = new ObjectMapper();
		jackson.registerModule(new JavaTimeModule());
		jackson.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		JavalinJackson.configure(jackson);
		
		// Starts the Javalin Framework
		Javalin app = Javalin.create().start(8080);
		
		UserController uc = (UserController) BeanFactory.getFactory().get(UserController.class, UserControllerImpl.class);
		ReimbursementController rc = (ReimbursementController) BeanFactory.getFactory().get(ReimbursementController.class, ReimbursementControllerImpl.class);
		
		app.post("/users", uc::login);
		app.delete("/users", uc::logout);
		app.post("/form/:username", rc::addForm);
		
		}
	

}
