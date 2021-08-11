package com.revature.data;

import java.util.List;
import java.util.UUID;

import com.revature.beans.Form;

public interface ReimbursementDAO {

	UUID addForm(Form form);
	List<Form> getForm();
	Form getFormByUsername(String username);
	void addForms(Form form);
	//Form getFormById(UUID id);
	void updateForm(Form form);
	Form getFormById(String username, UUID id);
}