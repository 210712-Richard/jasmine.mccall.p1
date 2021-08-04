package com.revature.main;

import com.revature.beans.User;
import com.revature.beans.UserType;
import com.revature.data.UserDAO;
import com.revature.data.UserDAOImpl;
import com.revature.util.CassandraUtil;

public class DataBaseCreator {
	private static UserDAO ud = new UserDAOImpl();
	
	public static void dropTables() {
		StringBuilder sb = new StringBuilder("DROP TABLE IF EXISTS User;");
		CassandraUtil.getInstance().getSession().execute(sb.toString());
	}

	public static void createTables() {
		StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS USER (")
				.append("username text PRIMARY KEY, type text, firstName text, lastName text, ")
				.append("password text, email text);");
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
		u.setType(UserType.DirectSup);
		ud.addUser(s);
		ud.addUser(new User("employee1", "Jane", "J", "1111", "jane@employee.net"));
		ud.addUser(new User("employee2", "Chris", "C", "2222", "chris@employee.net"));
	}
}
