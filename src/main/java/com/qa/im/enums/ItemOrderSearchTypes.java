package com.qa.im.enums;

/**
 * A list of Item Order fields
 * @author Admin
 *
 */
public enum ItemOrderSearchTypes {
	ITEM_ORDER ("id"),
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
