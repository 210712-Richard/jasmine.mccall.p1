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
import com.revature.beans.EmailNotification;
import com.revature.beans.User;
import com.revature.beans.UserType;
import com.revature.factory.Log;
import com.revature.util.CassandraUtil;

@Log
public class NotificationDAOImpl implements NotificationDAO {
	private CqlSession session = CassandraUtil.getInstance().getSession();
	
	
	@Override
	public void addEmail(EmailNotification email) {
		String query = "Insert into email (email, content) values (?, ?);";
		SimpleStatement s = new SimpleStatementBuilder(query).setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM)
				.build();
		BoundStatement bound = session.prepare(s)
	
				.bind(email.getEmail(), email.getContent());
		session.execute(bound);	
	}

	@Override
	public List<EmailNotification> getEmail() {
		String query = "Select email, content from user";
		SimpleStatement s = new SimpleStatementBuilder(query).build();
		ResultSet rs = session.execute(s);
		List<EmailNotification> emails = new ArrayList<>();
		rs.forEach(row -> {
			EmailNotification email = new EmailNotification();
			email.setEmail(row.getString("email"));
			email.setContent(row.getString("content"));
		    emails.add(email);
			
		});
		return emails;

}
	
	@Override
	public EmailNotification getEmail(String email) {

		String query = "Select email, content from user where email=?";
		SimpleStatement s = new SimpleStatementBuilder(query).build();
		BoundStatement bound = session.prepare(s).bind(email);
		ResultSet rs = session.execute(bound);
		Row row = rs.one();
		if(row == null) {
			return null;
		}
		EmailNotification e = new EmailNotification();
		e.setEmail(row.getString("email"));
		e.setContent(row.getString("content"));
		row = rs.one();
		if(row != null) {
			throw new RuntimeException("More than one user with same email");
		}
		return e;
}
	}
