package com.revature.data;

import java.util.List;
import java.util.UUID;

import com.revature.beans.Form;
import com.revature.beans.User;

public interface ReimbursementDAO {

	void addForm(Form form);

	List<Form> getForm();

	Form getFormByUsername(User username);

}
