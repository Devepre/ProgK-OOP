package com.gmail.bicycle;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Letter {
	private Map<Integer, String> body = new HashMap<>();
	private int height;

	public Letter() {
		super();
	}

	public Letter(String[] data) {
		super();
		for (int key = 0; key < data.length; key++) {
			body.put(key, data[key]);
		}
		this.height = data.length;
	}

	public String getString(int key) {
		Optional<String> result = Optional.ofNullable(body.get(key));
		return result.orElse("");
	}
	
	public int getHeight() {
		return height;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + height;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Letter other = (Letter) obj;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		if (height != other.height)
			return false;
		return true;
	}
	
	
}
