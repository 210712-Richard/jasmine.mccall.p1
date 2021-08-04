package com.revature.data;

import java.util.ArrayList;
import java.util.List;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.DefaultConsistencyLevel;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.core.cql.SimpleStatementBuilder;
import com.revature.beans.User;
import com.revature.beans.UserType;
import com.revature.util.CassandraUtil;

public class UserDAOImpl implements UserDAO{
	private CqlSession session = CassandraUtil.getInstance().getSession();
	
	@Override
	public void addUser(User u) {
		// SimpleStatement leaves us vulnerable to CQL injection and also it's hard to read and write.
//	StringBuilder sb = new StringBuilder("Insert into user ")
//			.append("(username, type, firstName, lastName, password, email)")
//			.append(" values ('"+u.getUsername()+"', '"+u.getType()+"', '"
//			+u.getEmail()+"', "+u.getFirstName()+", '"+u.getLastName()+
//			"', "+ u.getEmail()+", '"+u.getPassword()+"');");
//	    System.out.println(sb.toString());
//     	SimpleStatement s = new SimpleStatementBuilder(sb.toString())
//			.setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM).build();
		
		// Bound Statement will guard against CQL injection and is just a lot nicer
		String query = "Insert into user (username, type, firstName, lastName, email, password values (?, ?, ?, ?, ?, ?);";
		SimpleStatement s1 = new SimpleStatementBuilder(query).setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM).build();
		BoundStatement bound = session.prepare(s1)
				.bind(u.getUsername(), u.getType().toString(), u.getFirstName(), u.getLastName(), u.getEmail(), u.getPassword());
		session.execute(bound);
		
	}
	
	public List<User> getUsers() {
		String query = "Select username ,firstName, lastName, email, password, type from user";
		SimpleStatement s = new SimpleStatementBuilder(query).build();
		ResultSet rs = session.execute(s);
		List<User> users = new ArrayList<>();
		rs.forEach(row -> {
			User u = new User();
			u.setUsername(row.getString("username"));
			u.setFirstName(row.getString("firstName"));
			u.setLastName(row.getString("lastName"));
			u.setEmail(row.getString("email"));
			u.setPassword(row.getString("password"));
			u.setType(UserType.valueOf(row.getString("type")));
			
			users.add(u);
		});
		return users;
	}
	
	@Override
	public User getUser(String username, String password) {

		String query = "Select username, firstName, lastName, email, password, type from user where username=?";
		SimpleStatement s = new SimpleStatementBuilder(query).build();
		BoundStatement bound = session.prepare(s).bind(username);
		// ResultSet is the values returned by my query.
		ResultSet rs = session.execute(bound);
		Row row = rs.one();
		if(row == null) {
			// if there is no return values
			return null;
		}
		User u = new User();
		u.setUsername(row.getString("username"));
		u.setFirstName(row.getString("firstName"));
		u.setLastName(row.getString("lastName"));
		u.setPassword("password");
		u.setEmail(row.getString("email"));
		u.setType(UserType.valueOf(row.getString("type")));

//		row = rs.one();
//		if(row != null) {
//			throw new RuntimeException("More than one user with same username");
//		}
		return u;
	}
	@Override
	public void updateUser(User u) {
		String query = "Update user set type = ?, firstName = ?, lastName = ?,  email = ?, password = ?, where username = ?;";
		SimpleStatement s = new SimpleStatementBuilder(query).setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM).build();
		BoundStatement bound = session.prepare(s)
				.bind(u.getType().toString(),u.getFirstName(), u.getLastName(), u.getEmail(), u.getUsername());
		session.execute(bound);
	}



}
