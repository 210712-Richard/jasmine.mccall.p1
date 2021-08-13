package com.revature.data;

import java.util.ArrayList;

import java.util.List;
import java.util.UUID;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.DefaultConsistencyLevel;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.core.cql.SimpleStatementBuilder;
import com.revature.beans.User;
import com.revature.beans.UserType;
import com.revature.factory.Log;
import com.revature.util.CassandraUtil;


@Log
public class UserDAOImpl implements UserDAO{
	private CqlSession session = CassandraUtil.getInstance().getSession();
	
	@Override
	public void addUser(User u) {
		String query = "Insert into user (username, firstName, lastName, password, email, type, funds) values (?, ?, ?, ?, ?, ?, ?);";
		SimpleStatement s = new SimpleStatementBuilder(query).setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM)
				.build();
		BoundStatement bound = session.prepare(s)
				.bind(u.getUsername(), u.getFirstName(), u.getLastName(), u.getPassword(), u.getEmail(), u.getType().toString(), u.getFunds());
		session.execute(bound);
		
	}
	
	@Override
	public List<User> getUsers() {
		String query = "Select username, firstName, lastName, password, email, type, funds from user";
		SimpleStatement s = new SimpleStatementBuilder(query).build();
		ResultSet rs = session.execute(s);
		List<User> users = new ArrayList<>();
		rs.forEach(row -> {
			User u = new User();
			u.setUsername(row.getString("username"));
			u.setFirstName(row.getString("firstName"));
			u.setLastName(row.getString("lastName"));
			u.setPassword(row.getString("password"));
			u.setEmail(row.getString("email"));
		    u.setType(UserType.valueOf(row.getString("type")));
		    u.setFunds(row.getLong("funds"));
			users.add(u);
		});
		return users;
	}
	



	@Override
	public User getUser(String username) {

		String query = "Select username, firstName, lastName, password, email, type, funds from user where username=?";
		SimpleStatement s = new SimpleStatementBuilder(query).build();
		BoundStatement bound = session.prepare(s).bind(username);
		ResultSet rs = session.execute(bound);
		Row row = rs.one();
		if(row == null) {
			return null;
		}
		User u = new User();
		u.setUsername(row.getString("username"));
		u.setFirstName(row.getString("firstName"));
		u.setLastName(row.getString("lastName"));
		u.setPassword(row.getString("password"));
		u.setEmail(row.getString("email"));
		u.setType(UserType.valueOf(row.getString("type")));
		u.setFunds(row.getLong("funds"));

		row = rs.one();
		if(row != null) {
			throw new RuntimeException("More than one user with same username");
		}
		return u;
	}
	@Override
	public void updateUser(User u) {
		String query = "Update user set firstName = ?, lastName = ?, password = ?, email = ?, type = ?, funds = ? where username = ?;";
		SimpleStatement s = new SimpleStatementBuilder(query).setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM).build();
		BoundStatement bound = session.prepare(s)
				.bind(u.getFirstName(), u.getLastName(), u.getPassword(), u.getEmail(), u.getUsername(), u.getType().toString(), u.getFunds());
		session.execute(bound);
	}


	@Override
	public List<UUID> getUserForms(String username) {
		String query = "Select forms from user where username=?";
		SimpleStatement s = new SimpleStatementBuilder(query).build();
		BoundStatement bound = session.prepare(s).bind(username);
		// ResultSet is the values returned by my query.
		ResultSet rs = session.execute(bound);
		Row row = rs.one();
		if(row == null) {
			// if there is no return values
			return null;
		}
		List<UUID> forms = row.getList("forms", UUID.class);
		return forms;
	}
	
	@Override
	public void updateFunds(User u) {
		String query = "Update user set funds = ? where username = ?;";
		SimpleStatement s = new SimpleStatementBuilder(query).setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM).build();
		BoundStatement bound = session.prepare(s)
				.bind(u.getUsername(), u.getFunds());
		session.execute(bound);



	}

	@Override
	public User getUserbyEmail(String email) {

		String query = "Select username, firstName, lastName, password, email, type, funds from user where email=?";
		SimpleStatement s = new SimpleStatementBuilder(query).build();
		BoundStatement bound = session.prepare(s).bind(email);
		ResultSet rs = session.execute(bound);
		Row row = rs.one();
		if(row == null) {
			return null;
		}
		User u = new User();
		u.setUsername(row.getString("username"));
		u.setFirstName(row.getString("firstName"));
		u.setLastName(row.getString("lastName"));
		u.setPassword(row.getString("password"));
		u.setEmail(row.getString("email"));
		u.setType(UserType.valueOf(row.getString("type")));
		u.setFunds(row.getLong("funds"));

		row = rs.one();
		if(row != null) {
			throw new RuntimeException("More than one user with same username");
		}
		return u;
	}



}
