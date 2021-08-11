package com.revature.beans;

public class EmailNotification implements Notification {
	private String content;
	
	User u = new User();
	private String email = u.getEmail();
	
	
	public EmailNotification(String email, String content) {
		super();
		
		this.email = email;
		this.content = content;
	}

	public EmailNotification() {
		super();
	}

	@Override
	public void notifyUser(String email, String content) {
		this.setEmail(email);
		this.setContent(content);
		
	}
    public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmailNotification other = (EmailNotification) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "EmailNotification [content=" + content + ", email=" + email + "]";
	}

	
	
}
