package com.qa.im;

public enum NameTypes {
	FORENAME ("Forename"),
	SURNAME ("Surname"),
	ITEMNAME ("Item Name");
	
	private String nameType;

	private NameTypes(String nameType) {
		this.nameType = nameType;
	}

	public String getNameType() {
		return nameType;
	}
	
}
