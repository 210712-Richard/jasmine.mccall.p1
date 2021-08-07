package com.revature.beans;

import org.w3c.dom.DocumentType;

public class Document {
	
	private DocumentType type;
	
    public Document(DocumentType type) {
    	this.type = type;
    }

	public DocumentType getType() {
		return type;
	}

	public void setType(DocumentType type) {
		this.type = type;
	}
	
	
	

}
