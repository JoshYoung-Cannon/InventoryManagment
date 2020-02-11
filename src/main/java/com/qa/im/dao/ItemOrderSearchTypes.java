package com.qa.im.dao;

public enum ItemOrderSearchTypes {
	ITEMORDER ("id"),
	ITEM ("item_id"),
	ORDER ("order_id"),
	QUANTITY ("quantity");
	
	private String searchType;

	private ItemOrderSearchTypes(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchType() {
		return searchType;
	}
}
