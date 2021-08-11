package com.revature.factory;

import com.revature.beans.EmailNotification;
import com.revature.beans.Notification;

public class NotificationFactory {
	public Notification createNotification(String email, String content ) {
		if(email == null || email.isEmpty())
			return null;
		
		else if ("EMAIL".equals(email)) {
			return new EmailNotification();
		}
		return null;
	}

}
