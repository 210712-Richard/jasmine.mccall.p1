package com.revature.data;

import java.time.LocalDate;
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
import com.revature.beans.Form;
import com.revature.beans.GradeFormat;
import com.revature.beans.ReimbursementType;
import com.revature.beans.User;
import com.revature.factory.Log;
import com.revature.util.CassandraUtil;

@Log
public class ReimbursementDAOImpl implements ReimbursementDAO{
	private CqlSession session = CassandraUtil.getInstance().getSession();
	

	@Override
	public void addForm(Form form) {
		String query = "Insert into remimbursement (username, firstName, lastName, email, reimbursementAmount, localDate, format, gradeReceived, type) values (?, ?, ?, ?, ?, ?, ?, ?, ?);";
		SimpleStatement s = new SimpleStatementBuilder(query).setConsistencyLevel(DefaultConsistencyLevel.LOCAL_QUORUM)
				.build();
		BoundStatement bound = session.prepare(s).bind(form.getUsername(), form.getFirstName(), form.getLastName()
			, form.getEmail(), form.getReimbursementAmount().toString(), form.getDate(), form.getFormat(), form.getGradeReceived(), form.getFormat());
		session.execute(bound);
	}
	
	@Override
	public List<Form> getForm() {
		List<Form> forms = new ArrayList<Form>();
		String query = "Select username, firstName, lastName, email, reimbursementAmount, localDate, format, gradeReceived, type from reimbursement";
		ResultSet rs = session.execute(new SimpleStatementBuilder(query).build());

		rs.forEach(row -> {
			Form form = new Form();
			form.setUsername(row.getString("username"));
			form.setFirstName(row.getString("firstName"));
			form.setLastName(row.getString("lastName"));
			form.setEmail(row.getString("email"));
			form.setReimbursementAmount(row.getLong("reimbursementAmount"));
			form.setDate(row.getLocalDate("localDate"));
			form.setFormat(GradeFormat.valueOf(row.getString("format")));
			form.setGradeReceived(row.getDouble("gradeReceived"));
			form.setType(ReimbursementType.valueOf(row.getString("type")));
			forms.add(form);
		});

		return forms;
	}
	@Override
	public Form getFormByUsername(User username) {
		String query = "Select username, firstName, lastName, email, reimbursementAmount, localDate, format, gradeReceived, type  from reimbursement where username = ?";
		BoundStatement bound = session.prepare(new SimpleStatementBuilder(query).build()).bind(username);
		ResultSet rs = session.execute(bound);

		Row row = rs.one();
		if (row == null) {
			return null;
		}
		Form f = new Form();
		f.setUsername(row.getString("username"));
		f.setFirstName(row.getString("firstName"));
		f.setLastName(row.getString("lastName"));
		f.setEmail(row.getString("email"));
		f.setReimbursementAmount(row.getLong("reimbursementAmount"));
		f.setDate(row.getLocalDate("localDate"));
		f.setFormat(GradeFormat.valueOf(row.getString("format")));
		f.setGradeReceived(row.getDouble("gradeReceived"));
		f.setType(ReimbursementType.valueOf(row.getString("type")));

		return f;
	}
}