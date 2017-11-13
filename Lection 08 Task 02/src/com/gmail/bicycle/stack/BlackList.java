package com.gmail.bicycle.stack;

import java.util.ArrayList;
import java.util.List;

public class BlackList {
	private List<Class<?>> list = new ArrayList<Class<?>>();

	public BlackList() {
		super();
	}

	public void add(Class<?> clazz) {
		if (list.contains(clazz)) {
			throw new IllegalArgumentException(clazz + " is already in the " + this.getClass().getSimpleName());
		} else {
			list.add(clazz);
		}

	}

	public void remove(Class<?> clazz) {
		if (list.contains(clazz)) {
			list.remove(clazz);
		} else {
			throw new IllegalArgumentException("can't find such class in list");
		}
	}

	public boolean checkClass(Class<?> clazz) {
		return list.contains(clazz);
	}

	@Override
	public String toString() {
		return "BlackList [list=" + list + "]";
	}

}
