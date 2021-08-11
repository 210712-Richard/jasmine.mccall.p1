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
import com.datastax.oss.driver.api.core.data.TupleValue;
import com.revature.beans.ApplicationStatus;
import com.revature.beans.Form;
import com.revature.beans.GradeFormat;
import com.revature.beans.ReimbursementType;
import com.revature.factory.Log;
import com.revature.util.CassandraUtil;

@Log
public class ReimbursementDAOImpl implements ReimbursementDAO{
	private CqlSession session = CassandraUtil.getInstance().getSession();
	

	@Override
	public UUID addForm(Form form) {
		String query = "Insert into form (id, username, firstName, lastName, email, reimbursementAmount, date, format, gradeReceived, type, status) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		UUID id = UUID.randomUUID();
		SimpleStatement s = new SimpleStatementBuilder(query).setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM)
				.build();
		BoundStatement bound = session.prepare(s)
				.bind(id, form.getUsername(), form.getFirstName(), form.getLastName(), form.getEmail(), form.getReimbursementAmount(), form.getDate(), form.getFormat().toString(), form.getGradeReceived(), form.getType().toString(), form.getStatus().toString());
		session.execute(bound);
		return id;
	}
	@Override
	public void addForms(Form form) {
		String query = "Insert into form (id, username, firstName, lastName, email, reimbursementAmount, date, format, gradeReceived, type, status) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?;";
		SimpleStatement s = new SimpleStatementBuilder(query).setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM)
				.build();
		BoundStatement bound = session.prepare(s)
				.bind(form.getUsername(), form.getFirstName(), form.getLastName(), form.getEmail(), form.getReimbursementAmount(), form.getDate(), form.getFormat().toString(), form.getGradeReceived(), form.getType().toString(), form.getStatus().toString());
		session.execute(bound);
	}
	
	@Override
	public List<Form> getForm() {
		List<Form> forms = new ArrayList<Form>();
		String query = "Select id, username, firstName, lastName, email, reimbursementAmount, date, format, gradeReceived, type, status from form";
		SimpleStatement s = new SimpleStatementBuilder(query).build();
		
		ResultSet rs = session.execute(s);

		rs.forEach(row -> {
			Form form = new Form();
			form.setId(row.getUuid("id"));
			form.setUsername(row.getString("username"));
			form.setFirstName(row.getString("firstName"));
			form.setLastName(row.getString("lastName"));
			form.setEmail(row.getString("email"));
			form.setReimbursementAmount(row.getLong("reimbursementAmount"));
			form.setDate(row.getLocalDate("date"));
			form.setFormat(GradeFormat.valueOf(row.getString("format")));
			form.setGradeReceived(row.getDouble("gradeReceived"));
			form.setType(ReimbursementType.valueOf(row.getString("type")));
			form.setStatus(ApplicationStatus.valueOf(row.getString("status")));
			forms.add(form);
		});

		return forms;
	}
	@Override
	public Form getFormByUsername(String username) {
		String query = "Select id, username, firstName, lastName, email, reimbursementAmount, date, format, gradeReceived, type, status from form where username=?";
		SimpleStatement s = new SimpleStatementBuilder(query).build();
		BoundStatement bound = session.prepare(s).bind(username);
		ResultSet rs = session.execute(bound);

		Row row = rs.one();
		if (row == null) {
			return null;
		}
		Form f = new Form();
		f.setId(row.getUuid("id"));
		f.setUsername(row.getString("username"));
		f.setFirstName(row.getString("firstName"));
		f.setLastName(row.getString("lastName"));
		f.setEmail(row.getString("email"));
		f.setReimbursementAmount(row.getLong("reimbursementAmount"));
		f.setDate(row.getLocalDate("date"));
		f.setFormat(GradeFormat.valueOf(row.getString("format")));
		f.setGradeReceived(row.getDouble("gradeReceived"));
		f.setType(ReimbursementType.valueOf(row.getString("type")));
		f.setStatus(ApplicationStatus.valueOf(row.getString("status")));

		return f;
	}

	@Override
	public Form getFormById(String username, UUID id) {
		String query = "Select id, username, firstName, lastName, email, reimbursementAmount, date, format, gradeReceived, type, status from form where username = ? and id = ? Allow Filtering";
		BoundStatement bound = session.prepare(new SimpleStatementBuilder(query).build()).bind(username, id);
		ResultSet rs = session.execute(bound);

		Row row = rs.one();
		if (row == null) {
			return null;
		}
		Form f1 = new Form();
		f1.setId(row.getUuid("id"));
		f1.setUsername(row.getString("username"));
		f1.setFirstName(row.getString("firstName"));
		f1.setLastName(row.getString("lastName"));
		f1.setEmail(row.getString("email"));
		f1.setReimbursementAmount(row.getLong("reimbursementAmount"));
		f1.setDate(row.getLocalDate("date"));
		f1.setFormat(GradeFormat.valueOf(row.getString("format")));
		f1.setGradeReceived(row.getDouble("gradeReceived"));
		f1.setType(ReimbursementType.valueOf(row.getString("type")));
		f1.setStatus(ApplicationStatus.valueOf(row.getString("status")));
		return f1;
}
	@Override
	public void updateForm(Form form) {
		String query = "update form set id=?, username=?, firstName=?, lastName=?, email=?, reimbursementAmount=?, date=?, format=?, gradeReceived=?, type=?, status=? where username = ?;";

		SimpleStatement s = new SimpleStatementBuilder(query).setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM)
				.build();
		BoundStatement bound = session.prepare(s).bind(form.getId(), form.getUsername(), form.getFirstName(), form.getLastName(), form.getEmail(), form.getReimbursementAmount(), form.getDate(), form.getFormat().toString(), form.getGradeReceived(), form.getType().toString(), form.getStatus().toString());
				
		session.execute(bound);
	}
}