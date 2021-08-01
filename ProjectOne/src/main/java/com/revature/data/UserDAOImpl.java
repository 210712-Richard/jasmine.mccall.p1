package com.revature.data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.datastax.oss.driver.api.core.DefaultConsistencyLevel;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.core.cql.SimpleStatementBuilder;
import com.revature.beans.User;
import com.revature.beans.UserType;

public class UserDAOImpl implements UserDAO{
	
	public List<User> getUsers() {
		String query = "Select firstName, lastName, username, email, userID, type from user";
		//SimpleStatement s = new SimpleStatementBuilder(query).build();
		//ResultSet rs = session.execute(s);
		List<User> users = new ArrayList<>();
		rs.forEach(row -> {
			User u = new User();
			u.setFirstName(row.getString("firstName"));
			u.setLastName(row.getString("lastName"));
			u.setUsername(row.getString("username"));
			u.setEmail(row.getString("email"));
			u.setUserID(row.getLong("userID"));
			u.setType(UserType.valueOf(row.getString("type")));
			
			users.add(u);
		});
		return users;
	}
	
	@Override
	public User getUser(String username, String password) {

		String query = "Select firstName, lastName, username ,email, userID, type from user where username=?";
		//SimpleStatement s = new SimpleStatementBuilder(query).build();
		//BoundStatement bound = session.prepare(s).bind(username);
		// ResultSet is the values returned by my query.
		//ResultSet rs = session.execute(bound);
		//Row row = rs.one();
		if(row == null) {
			// if there is no return values
			return null;
		}
		User u = new User();
		u.setFirstName(row.getString("firstName"));
		u.setLastName(row.getString("lastName"));
		u.setUsername(row.getString("username"));
		u.setEmail(row.getString("email"));
		u.setUserID(row.getLong("userID"));
		u.setType(UserType.valueOf(row.getString("type")));

//		row = rs.one();
//		if(row != null) {
//			throw new RuntimeException("More than one user with same username");
//		}
		return u;
	}
	@Override
	public void updateUser(User u) {
		String query = "Update user set type = ?, firstName = ?, lastName = ?,  email = ?, where username = ?;";
		SimpleStatement s = new SimpleStatementBuilder(query).setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM).build();
		BoundStatement bound = session.prepare(s)
				.bind(u.getType().toString(),u.getFirstName(), u.getLastName(), u.getEmail(), u.getUsername());
		session.execute(bound);
	}

}
