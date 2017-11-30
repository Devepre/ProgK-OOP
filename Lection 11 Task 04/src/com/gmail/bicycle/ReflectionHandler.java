package com.gmail.bicycle;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReflectionHandler {
	public static List<String> processFields(Object obj) {
		Class<?> objClass = obj.getClass();

		List<String> listOfMethods = new ArrayList<>();

		Method[] methods = objClass.getMethods();
		for (Method method : methods) {
			listOfMethods.add(method.getName());
		}

		return listOfMethods;
	}
	
	public static Map<Integer, String> processFields(Class<?> clazz) {
		Map<Integer, String> listOfMethods = new HashMap<>();

		Method[] methods = clazz.getMethods();
		for (int i = 0; i < methods.length; i++) {
			listOfMethods.put(i, methods[i].getName());
		}
//		for (Method method : methods) {
//			listOfMethods.put(counter, method.getName());
//		}

		return listOfMethods;
	}
}
