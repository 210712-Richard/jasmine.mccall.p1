//package com.revature.factory;
//
//public class EmailNotification implements Notification {
//	private String content;
//
//	@Override
//	public void notifyUser(String content) {
//		this.setContent(content);
//		
//	}
//
//	@Override
//	public void notifyUser() {
//		
//		
//	}
//
//	public String getContent() {
//		return content;
//	}
//
//	public void setContent(String content) {
//		this.content = content;
//	}
//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((content == null) ? 0 : content.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		EmailNotification other = (EmailNotification) obj;
//		if (content == null) {
//			if (other.content != null)
//				return false;
//		} else if (!content.equals(other.content))
//			return false;
//		return true;
//	}
//
//	@Override
//	public String toString() {
//		return "EmailNotification [content=" + content + "]";
//	}
//
//	
//	
//}
