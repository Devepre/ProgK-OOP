package com.gmail.bicycle;

public class GroupOutOfBoundsException extends Exception {
	private static final long serialVersionUID = -2238584377898509365L;

	@Override
	public String getMessage() {
		return "Wrong index number for Group";
	}

}
