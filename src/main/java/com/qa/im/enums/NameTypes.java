package com.qa.im.enums;

/**
 * A list of Name fields
 * @author Admin
 *
 */
public enum NameTypes {
	FORENAME ("Forename"),
	SURNAME ("Surname"),
	ITEM_NAME ("Item Name");
	
	private String nameType;

	private NameTypes(String nameType) {
		this.nameType = nameType;
	}

	public String getNameType() {
		return nameType;
	}
	
}
