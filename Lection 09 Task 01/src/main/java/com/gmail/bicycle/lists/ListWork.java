package com.gmail.bicycle.lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListWork {
	
	/**
	 * метод, который создаст список,
	 * положит в него 10 элементов, затем удалит первые два и последний,
	 * а затем выведет результат на экран
	 */
	public void doWork() {
		List<String> result = createList(createRandomString(10).split(""));
		removeFirst(result);
		removeFirst(result);
		removeLast(result);
		print(result);
	}

	protected void print(List<?> result) {
		System.out.println(result);
		
	}

	protected String createRandomString(int size) {
		StringBuilder sb = new StringBuilder();
		Random rnd = new Random(System.currentTimeMillis());
		for (int i = 0; i < size; i++) {
			int num = rnd.nextInt(26);
			sb.append((char) ('A' + num));
		}
		return sb.toString();
	}

	protected void removeLast(List<?> result) {
		result.remove(result.size() - 1);
	}

	protected void removeFirst(List<?> result) {
		result.remove(0);
	}

	protected <T> List<T> createList(T... args) {
		List<T> result = new ArrayList<T>();
		for (T item : args) {
			result.add(item);
		}
		return result;
	}

}
