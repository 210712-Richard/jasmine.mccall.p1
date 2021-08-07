package com.revature.beans;

public enum UserType {
	
	Employee(1), DirectSup(2), DeptHead(3), BenCo(4);
	
	private Integer level;
	
	
	UserType(Integer level) {
		this.level = level;
	}


	public Integer getLevel() {
		return level;
	}


	

}
