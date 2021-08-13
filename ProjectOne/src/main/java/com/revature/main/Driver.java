package com.revature.main;








import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.revature.controllers.ReimbursementController;
import com.revature.controllers.ReimbursementControllerImpl;
import com.revature.controllers.UserController;
import com.revature.controllers.UserControllerImpl;
import com.revature.factory.BeanFactory;
import io.javalin.Javalin;
import io.javalin.plugin.json.JavalinJackson;
public class Driver {
	public static void main(String[] args) {
		//instantiateDatabase();
		//javalin();
		
	}

	public static void instantiateDatabase() {
		DataBaseCreator.dropTables();
		try {
			Thread.sleep(40000);
		} catch(Exception e) {
			e.printStackTrace();
		}
		DataBaseCreator.createTables();
		try {
			Thread.sleep(30000);
		} catch(Exception e) {
			e.printStackTrace();
		}
		DataBaseCreator.populateUserTable();
		DataBaseCreator.populateFormTable();

		System.exit(0);
	}

	
	public static void javalin() {
		
		ObjectMapper jackson = new ObjectMapper();
		jackson.registerModule(new JavaTimeModule());
		jackson.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		JavalinJackson.configure(jackson);
		
		Javalin app = Javalin.create().start(8080);
		
		UserController uc = (UserController) BeanFactory.getFactory().get(UserController.class, UserControllerImpl.class);
		ReimbursementController rc = (ReimbursementController) BeanFactory.getFactory().get(ReimbursementController.class, ReimbursementControllerImpl.class);
		
		app.post("/users", uc::login);
		app.delete("/users", uc::logout);
		app.post("/form/:username", rc::addForm);
		app.get("/request/:username", uc::viewRequestById);
		app.put("/requests/:username/supervisor/", uc::supApproval);
		app.put("/requests/:username/departmentHead", uc::deptHeadApproval);
		app.put("/requests/:username/benCo", uc::benCoApproval);
		app.put("/requests/:username/supervisor/denial", uc::supDenial);
		app.put("/requests/:username/departmentHead/denial", uc::deptHeadDenial);
		app.put("/requests/:username/benCo/denial", uc::benCoDenial);
		app.put("/uploads/:username/documents", rc::uploadDocument);
		app.get("/documents/:username/", rc::getUpload);
		app.put("/requests/:username/benCo/moreInfo", uc::moreInfoBenco);
		
		
		
	}
	

}
