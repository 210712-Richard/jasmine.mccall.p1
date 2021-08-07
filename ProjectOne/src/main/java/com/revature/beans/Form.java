package com.revature.beans;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Form {
	
	private UUID id;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private Long reimbursementAmount;
	private LocalDate date;
	private GradeFormat format;
	private Double gradeReceived;
	private ReimbursementType type;
	private List<Document> documents;
	
	
//	public Form(String username, String firstName, String lastName, String email, Long reimbursementAmount,
//			 LocalDate date, GradeFormat format, Double gradeReceived, ReimbursementType type, List<Document> document) {
//		
//		super();
//		this.username = username;
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.email = email;
//		this.reimbursementAmount = reimbursementAmount;
//		this.date = date;
//		this.format = format;
//		this.gradeReceived = gradeReceived;
//		this.type = type;
//		this.setDocuments(document);
//	}

	
	public Form(String username, String firstName, String lastName, String email, long reimbursementAmount, LocalDate date, double gradeReceived) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.reimbursementAmount = reimbursementAmount;
		this.date = date;
		this.gradeReceived = gradeReceived;
		
	}
	public Form() {
		super();
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public Long getReimbursementAmount() {
		return reimbursementAmount;
	}

	public void setReimbursementAmount(Long reimbursementAmount) {
		this.reimbursementAmount = reimbursementAmount;
	}

	public ReimbursementType getType() {
		return type;
	}

	public void setType(ReimbursementType type) {
		this.type = type;
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
	


	public GradeFormat getFormat() {
		return format;
	}

	public void setFormat(GradeFormat format) {
		this.format = format;
	}
	
	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((documents == null) ? 0 : documents.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((format == null) ? 0 : format.hashCode());
		result = prime * result + ((gradeReceived == null) ? 0 : gradeReceived.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((reimbursementAmount == null) ? 0 : reimbursementAmount.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		if (documents == null) {
			if (other.documents != null)
				return false;
		} else if (!documents.equals(other.documents))
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
		if (format != other.format)
			return false;
		if (gradeReceived == null) {
			if (other.gradeReceived != null)
				return false;
		} else if (!gradeReceived.equals(other.gradeReceived))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		if (type != other.type)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Form [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", reimbursementAmount=" + reimbursementAmount + ", date=" + date + ", format="
				+ format + ", gradeReceived=" + gradeReceived + ", type=" + type + ", documents=" + documents + "]";
	}

	

}
