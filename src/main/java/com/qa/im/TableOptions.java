package com.qa.im;

public enum TableOptions {
	CUSTOMERS ("CUSTOMERS"),
	ITEMS ("ITEMS"),
	ORDERS ("ORDERS"),
	ORDERITEMS ("ITEMS IN ORDERS"),
	QUIT ("QUIT");
	
	private String tableOption;

	private TableOptions(String tableOption) {
		this.tableOption = tableOption;
	}

	public String getTableOption() {
		return tableOption;
	}
}
