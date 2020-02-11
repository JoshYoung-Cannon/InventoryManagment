package com.qa.im.dao;

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
