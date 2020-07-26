package com.spring.board.vo;

import java.util.Arrays;

public class SearchCriteria extends PageVo {
	
	private String[] searchKeys;
	
	
	public String[] getSearchKeys() {
		return searchKeys;
	}
	public void setSearchKeys(String[] searchKeys) {

		this.searchKeys = searchKeys;
	}
	@Override
	public String toString() {
		return "SearchCriteria [searchKeys=" + Arrays.toString(searchKeys) + "]";
	}


	
}
