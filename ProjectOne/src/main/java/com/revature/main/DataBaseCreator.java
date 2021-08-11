package com.revature.main;

import java.time.LocalDate;

import com.revature.beans.ApplicationStatus;
import com.revature.beans.EmailNotification;
import com.revature.beans.Form;
import com.revature.beans.GradeFormat;
import com.revature.beans.ReimbursementType;
import com.revature.beans.User;
import com.revature.beans.UserType;
import com.revature.data.NotificationDAO;
import com.revature.data.NotificationDAOImpl;
import com.revature.data.ReimbursementDAO;
import com.revature.data.ReimbursementDAOImpl;
import com.revature.data.UserDAO;
import com.revature.data.UserDAOImpl;
import com.revature.util.CassandraUtil;

public class DataBaseCreator {
	private static UserDAO ud = new UserDAOImpl();
	private static ReimbursementDAO rd = new ReimbursementDAOImpl();
	private static NotificationDAO nd = new NotificationDAOImpl();
	
	public static void dropTables() {
		StringBuilder sb = new StringBuilder("DROP TABLE IF EXISTS user;");
		CassandraUtil.getInstance().getSession().execute(sb.toString());
		
			sb = new StringBuilder("DROP TABLE IF EXISTS form;");
			CassandraUtil.getInstance().getSession().execute(sb.toString());
			
			sb = new StringBuilder("DROP TABLE IF EXISTS email;");
			CassandraUtil.getInstance().getSession().execute(sb.toString());	
	}

	public static void createTables() {
		StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS USER (")
				.append("username text PRIMARY KEY, firstName text, lastName text,")
				.append("password text, email text, type text, forms list<uuid>);");
		CassandraUtil.getInstance().getSession().execute(sb.toString());
		
		sb = new StringBuilder("CREATE TABLE IF NOT EXISTS FORM (")
				.append("username text PRIMARY KEY, id uuid ,firstName text, lastName text, ")
				.append("email text, reimbursementAmount bigint, date date, format text,")
		        .append("gradeReceived double, type text, status text);");
	    CassandraUtil.getInstance().getSession().execute(sb.toString());
	    
	    sb = new StringBuilder("CREATE TABLE IF NOT EXISTS EMAIL (")
				.append("email text PRIMARY KEY, content text);");
	    CassandraUtil.getInstance().getSession().execute(sb.toString());
		
		
	}
	public static void populateUserTable() {
		User u = new User("jmccall", "Jasmine", "McCall", "1234", "jasmine@jasmine.net");
		u.setType(UserType.BenCo);
		ud.addUser(u);
		User h = new User("tim", "Timothy", "T", "5678", "tim@tim.net");
		h.setType(UserType.DeptHead);
		ud.addUser(h);
		User s = new User("crystal", "Crystal", "C", "0000", "crystal@crystal.net");
		s.setType(UserType.DirectSup);
		ud.addUser(s);
		User e1 = new User("employee1", "Jane", "J", "1111", "jane@employee.net");
		e1.setType(UserType.Employee);
		ud.addUser(e1);
		User e2 = new User("employee2", "Chris", "C", "2222", "chris@employee.net");
		e2.setType(UserType.Employee);
		ud.addUser(e2);
	}

	public static void populateFormTable() {
		Form f = new Form("employee1", "Jane", "J", "jane@employee.net", 400l, LocalDate.of(2021, 1, 1), 99d);
		f.setType(ReimbursementType.TechTraining);
	    f.setFormat(GradeFormat.Percentage);
	    f.setStatus(ApplicationStatus.PENDING);
		//f.setDocuments(null);
		rd.addForm(f);
	}
	
	public static void populateEmailTable() {
		EmailNotification email = new EmailNotification("jane@employee.net", "You've been approved!");
		nd.addEmail(email);
	
	}
		
}
