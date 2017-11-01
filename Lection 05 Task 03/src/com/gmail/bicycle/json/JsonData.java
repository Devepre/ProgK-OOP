package com.gmail.bicycle.json;

public enum JsonData {
	BEGIN ("{"),
	DELIMITER (" "),
	SEPARATOR (":"),
	QUOUTES ("\""),
	COMMA(","),
	ARRAY_BEGIN("["),
	ARRAY_END("]"),
	END ("}");
		
	private final String value;
	JsonData(String str){
		this.value = str;
	}
		
	public String getValue() {
		return value;
	}
}
