package com.revature.beans;

import java.time.LocalDate;

public class Form {
	
	private String firstName;
	private String lastName;
	private String email;
	private Long reimbursementAmount;
	private String reimbursementType;
	private LocalDate date;
	private Double gradeReceived;
	
	public Form(String firstName, String lastName, String email, long reimbursementAmount, String reimbursementType,
			LocalDate date, Double gradeReceived) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.reimbursementAmount = reimbursementAmount;
		this.reimbursementType = reimbursementType;
		this.date = date;
		this.gradeReceived = gradeReceived;
	}

	public Form() {
		super();

	} 

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getReimbursementAmount() {
		return reimbursementAmount;
	}

	public void setReimbursementAmount(long reimbursementAmount) {
		this.reimbursementAmount = reimbursementAmount;
	}

	public String getReimbursementType() {
		return reimbursementType;
	}

	public void setReimbursementType(String reimbursementType) {
		this.reimbursementType = reimbursementType;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	

	public Double getGradeReceived() {
		return gradeReceived;
	}

	public void setGradeReceived(Double gradeReceived) {
		this.gradeReceived = gradeReceived;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((gradeReceived == null) ? 0 : gradeReceived.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((reimbursementAmount == null) ? 0 : reimbursementAmount.hashCode());
		result = prime * result + ((reimbursementType == null) ? 0 : reimbursementType.hashCode());
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
		Form other = (Form) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (gradeReceived == null) {
			if (other.gradeReceived != null)
				return false;
		} else if (!gradeReceived.equals(other.gradeReceived))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (reimbursementAmount == null) {
			if (other.reimbursementAmount != null)
				return false;
		} else if (!reimbursementAmount.equals(other.reimbursementAmount))
			return false;
		if (reimbursementType == null) {
			if (other.reimbursementType != null)
				return false;
		} else if (!reimbursementType.equals(other.reimbursementType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Form [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", reimbursementAmount="
				+ reimbursementAmount + ", reimbursementType=" + reimbursementType + ", date=" + date
				+ ", gradeReceived=" + gradeReceived + "]";
	}
	
	

}
