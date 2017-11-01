package com.gmail.bicycle.json;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class ReflectionHandler {
	private static ArrayList<String> listOfSerializableFields;
	private static ArrayList<String> listOfSerializableTypes;
	
	public ReflectionHandler() {
		super();		
	}
	
	public static ArrayList<String> getListOfSerializableFields(Object obj) {
		processFields(obj);
		return listOfSerializableFields;
	}

	public static ArrayList<String> getListOfSerializableTypes(Object obj) {
		processFields(obj);
		return listOfSerializableTypes;
	}
	
	public static Object getObjectFromObject(Object obj, String name) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Class<?> objClass = obj.getClass();
		Field field = objClass.getDeclaredField(name);
		if (!field.isAccessible()) {
			field.setAccessible(true);
		}
		Object object = field.get(obj);
		
		return object;
	}
	
	public static Field getField(Object obj, String name) throws NoSuchFieldException, SecurityException {
		Class<?> objClass = obj.getClass();
		
		return getField(objClass, name);
	}
	
	public static Field getField(Class<?> objClass, String name) throws NoSuchFieldException, SecurityException {
		boolean fieldFound = true;
		try {
			Field currentField = objClass.getDeclaredField(name);
		} catch (NoSuchFieldException e) {
			fieldFound = false;
		}

		while (!fieldFound && objClass.getSuperclass() != null) {
			Field[] fields = objClass.getDeclaredFields();
			for (Field field : fields) {
				if (field.isAnnotationPresent(SerializedParameter.class)) {
					SerializedParameter annotaion = field.getAnnotation(SerializedParameter.class);
					if (annotaion.value().equals(name)) {
						fieldFound = true;
						break;
					}
				}
			}
			if (!fieldFound) {
				objClass = objClass.getSuperclass();
			}
		}
		Field field = objClass.getDeclaredField(name);

		return field;
	}
	
	private static void processFields(Object obj) {
		Class<?> objClass = obj.getClass();
		
		listOfSerializableFields = new ArrayList<String>();
		listOfSerializableTypes = new ArrayList<String>();
		
		while (objClass.getSuperclass() != null) {
			Field[] fields = objClass.getDeclaredFields();
			for (Field field : fields) {
				Class<?> fieldClass = field.getType();
				if (field.isAnnotationPresent(SerializedParameter.class)) {
					SerializedParameter annotaion = field.getAnnotation(SerializedParameter.class);
					listOfSerializableFields.add(annotaion.value());
					listOfSerializableTypes.add(fieldClass.getName());
				}
			}
			objClass = objClass.getSuperclass();
		}
	}

}
