package com.revature.factory;

public class NotificationFactory {
	public Notification createNotification(String channel) {
		if(channel == null || channel.isEmpty())
			return null;
		
		else if ("EMAIL".equals(channel)) {
			return new EmailNotification();
		}
		return null;
	}

}
