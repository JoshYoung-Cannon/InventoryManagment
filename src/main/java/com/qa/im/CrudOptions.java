package com.qa.im;

public enum CrudOptions {
	CREATE ("CREATE"),
	READALL ("READ ALL"),
	UPDATE ("UPDATE"),
	DELETE ("DELETE"),
	BACK ("BACK");
	
	private String crudOption;

	private CrudOptions(String crudOption) {
		this.crudOption = crudOption;
	}

	public String getCrudOption() {
		return crudOption;
	}
}
