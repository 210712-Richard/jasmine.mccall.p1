package com.revature.data;

import java.util.List;
import java.util.UUID;

import com.revature.beans.Form;

public interface ReimbursementDAO {

	List<Form> getForm();
	Form getFormByUsername(String username);
	void addForm(Form form);
	Form getFormById(String username, UUID id);
	void updateDirectSupApproval(Form form);
	void updateDeptHeadApproval(Form form);
	void updateBenCoApproval(Form form);
	void updateStatus(Form form);
	void reasonForDenial(Form form);
	void updateDocuments(Form form);

}