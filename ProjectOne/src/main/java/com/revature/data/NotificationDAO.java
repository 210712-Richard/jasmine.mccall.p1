package com.revature.data;

import java.util.List;

import com.revature.beans.EmailNotification;

public interface NotificationDAO {

	void addEmail(EmailNotification email);

	EmailNotification getEmail(String email);

	List<EmailNotification> getEmail();

}
