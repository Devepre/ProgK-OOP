package com.gmail.bicycle;

import java.util.HashMap;
import java.util.Map;

public class Letter {
	private Map<Integer, String> body = new HashMap<>();
	private int height;
	

	public Letter(String[] data) {
		super();
		for (int key = 0; key < data.length; key++) {
			body.put(key, data[key]);
		}
		this.height = data.length;
	}

	public String getString(int key) {
		return body.get(key);
	}
	
	public int getHeight() {
		return height;
	}
	
}
