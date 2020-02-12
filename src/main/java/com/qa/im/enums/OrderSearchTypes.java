package com.qa.im.enums;

/**
 * A list of Order fields
 * @author Admin
 *
 */
public enum OrderSearchTypes {
	ORDER ("id"),
	CUSTOMER ("customer_id"),
	TOTAL ("total");
	
	private String searchType;

	private OrderSearchTypes(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchType() {
		return searchType;
	}
}
