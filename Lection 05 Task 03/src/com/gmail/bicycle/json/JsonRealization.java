package com.gmail.bicycle.json;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonRealization extends JsonParser {
	private Writer writer;
	private Reader reader;

	public JsonRealization() {
		super();
	}

	@Override
	public void serialize(Object obj, String fileName) throws NoSuchFieldException, SecurityException,
			IllegalArgumentException, IllegalAccessException, IOException {
		if (obj == null) {
			throw new IllegalArgumentException("Nothing to serialize");
		}
		OutputStream outputStream = new FileOutputStream(fileName);
		writer = new PrintWriter(outputStream);
		writeObject(obj);
		writer.flush();
		writer.close();
	}

	private void writeArray(Object[] array) throws IOException, NoSuchFieldException, SecurityException,
			IllegalArgumentException, IllegalAccessException {
		array = deleteNullObjects(array);

		writeString(JsonData.ARRAY_BEGIN.getValue());

		for (int i = 0; i < array.length - 1; i++) {
			writeObject(array[i]);
			writeString(JsonData.COMMA.getValue());
		}
		Object lastObject = array[array.length - 1];
		writeObject(lastObject);

		writeString(JsonData.ARRAY_END.getValue());

	}

	private Object[] deleteNullObjects(Object[] array) {
		int count = 0;
		for (Object object : array) {
			if (object != null) {
				count++;
			}
		}
		Object[] nonNullOjbects = new Object[count];
		count = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null) {
				nonNullOjbects[count++] = array[i];
			}
		}
		return nonNullOjbects;
	}

	private void writeObject(Object object) throws IOException, NoSuchFieldException, SecurityException,
			IllegalArgumentException, IllegalAccessException, IllegalArgumentException {
		writeString(JsonData.BEGIN.getValue());

		ArrayList<String> fieldNames = ReflectionHandler.getListOfSerializableFields(object);
		ArrayList<String> fieldTypes = ReflectionHandler.getListOfSerializableTypes(object);

		if (fieldNames.isEmpty() || fieldTypes.isEmpty()) {
			throw new IllegalArgumentException(
					"This class can't be serialized - any of field doesn't marked to be serialized");
		}

		for (int i = 0; i < fieldNames.size(); i++) {
			String name = fieldNames.get(i);
			String type = fieldTypes.get(i);

			boolean isLastObject = i < fieldNames.size() - 1;
			if (type.equals("java.lang.String") || type.equals("java.util.Date")) {
				writeStringObject(object, name, type, isLastObject);
			} else if (type.equals("double")) {
				writeDoubleObject(object, name, type, isLastObject);
			} else if (type.equals("boolean")) {
				writeBooleanObject(object, name, type, isLastObject);
			} else if (type.equals("long")) {
				writeLongObject(object, name, type, isLastObject);
			} else if (type.startsWith("[")) {
				Object[] array = (Object[]) ReflectionHandler.getObjectFromObject(object, name);

				writeBeginningOfStringValue(name);
				writeArray(array);
			}

		}

		writeString(JsonData.END.getValue());
	}

	private void writeStringObject(Object object, String name, String type, boolean lastObject) throws IOException,
			NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		writeBeginningOfStringValue(name);

		String value = "";

		Field field = ReflectionHandler.getField(object, name);
		if (!field.isAccessible()) {
			field.setAccessible(true);
		}

		if (type.equals("java.util.Date")) {
			Date date = (Date) field.get(object);
			value = date.toString();
			writeStringValue(value);
		} else if (type.equals("java.lang.String")) {
			value = (String) field.get(object);
			writeStringValue(value);
		}

		if (lastObject) {
			writeString(JsonData.COMMA.getValue());
		}

	}

	private void writeString(String text) throws IOException {
		writer.write(text);
	}

	private void writeLongObject(Object object, String name, String type, boolean isLastObject) throws IOException,
			NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		writeBeginningOfStringValue(name);

		Field field = ReflectionHandler.getField(object, name);
		if (!field.isAccessible()) {
			field.setAccessible(true);
		}

		long value = (long) field.get(object);
		writer.write(String.valueOf(value));

		if (isLastObject) {
			writeString(JsonData.COMMA.getValue());
		}

	}

	private void writeBooleanObject(Object object, String name, String type, boolean isLastObject) throws IOException,
			NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		writeBeginningOfStringValue(name);

		Field field = ReflectionHandler.getField(object, name);
		if (!field.isAccessible()) {
			field.setAccessible(true);
		}

		boolean value = (boolean) field.get(object);
		writer.write(String.valueOf(value));

		if (isLastObject) {
			writeString(JsonData.COMMA.getValue());
		}

	}

	private void writeDoubleObject(Object object, String name, String type, boolean isLastObject) throws IOException,
			NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		writeBeginningOfStringValue(name);

		Field field = ReflectionHandler.getField(object, name);
		if (!field.isAccessible()) {
			field.setAccessible(true);
		}

		double value = (double) field.get(object);
		writer.write(String.valueOf(value));

		if (isLastObject) {
			writeString(JsonData.COMMA.getValue());
		}
	}

	private void writeStringValue(String text) throws IOException {
		writer.write(JsonData.QUOUTES.getValue());
		writer.write(text);
		writer.write(JsonData.QUOUTES.getValue());
	}

	private void writeBeginningOfStringValue(String name) throws IOException {
		writeStringValue(name);
		writeString(JsonData.SEPARATOR.getValue());
	}

	@Override
	public Object deserialize(Class<?> objectClass, String fileName) throws Exception {
		if (fileName == null || fileName.isEmpty()) {
			throw new IllegalArgumentException("Can't create unknown file");
		}
		if (objectClass == null) {
			throw new IllegalArgumentException("Need to specify Class type");
		}
		Object obj = null;
		reader = new BufferedReader(new FileReader(fileName));
		obj = readObject(objectClass);
		reader.close();
		return obj;
	}

	private Object readObject(Class<?> object) throws Exception {
		Object obj = null;
		char ch = ' ';
		String str;
		int digit;
		StringBuilder currentObject = new StringBuilder();
		boolean grabField = false;
		ArrayList<String> stack = new ArrayList<String>();
		while ((digit = reader.read()) != -1) {
			ch = (char) digit;
			str = String.valueOf(ch);

			if (str.equals(JsonData.BEGIN.getValue())) {
				stack.add(JsonData.BEGIN.getValue());
				grabField = true;
			} else if (str.equals(JsonData.END.getValue())) {
				if (stack.get(stack.size() - 1).equals(JsonData.BEGIN.getValue())) {
					stack.remove(stack.size() - 1);
					if (stack.size() == 0) {
						grabField = false;
						break;
					}
				} else {
					stack.add(JsonData.END.getValue());
				}
			}
			if (grabField) {
				currentObject.append(ch);
			}
		}

		if (currentObject.length() > 0) {
			obj = createObject(currentObject.toString(), object);
		} else {
			throw new Exception("Can't read object from stream");
		}

		return obj;
	}

	private Object createObject(String currentObject, Class<?> currentClass)
			throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchFieldException, ParseException {
		Object obj = null;

		Constructor<?> constructor = currentClass.getConstructor();
		obj = constructor.newInstance();

		ArrayList<String> fieldNames = getListOfSerializableFieldNames(currentObject);
		ArrayList<String> fieldValues = getListOfSerializableFieldValues(currentObject);

		int index = 0;
		for (String fieldName : fieldNames) {
			Object value = fieldValues.get(index++);
			Field field = ReflectionHandler.getField(currentClass, fieldName);
			Class<?> typeValue = field.getType();
			
			if (typeValue.getName().equals("double")) {
				value = Double.parseDouble((String) value);
			} else if (typeValue.getName().equals("long")) {
				value = Long.parseLong((String) value);
			} else if (typeValue.getName().equals("boolean")) {
				value = Boolean.parseBoolean((String) value);
			} else if (typeValue.getName().equals("java.util.Date")) {
				SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss 'EET' yyyy", Locale.ENGLISH);
				value = sdf.parse((String) value);
			} else if (typeValue.getName().startsWith("[")) {
				ArrayList<String> values = parseArray(value);
				Class<?> arrayObjectClass = typeValue.getComponentType();
				Constructor<?> arrayConstructor = arrayObjectClass.getConstructor();
				ArrayList<Object> array = new ArrayList<Object>();
				
				for (String string : values) {
					Object objOfArray = arrayConstructor.newInstance();
					objOfArray = createObject(string, objOfArray.getClass());
					array.add(objOfArray);
				}
				value = (Object[]) Array.newInstance(typeValue.getComponentType(), array.size());
				for (int i = 0; i < ((Object[]) value).length; i++) {
					((Object[]) value)[i] = array.get(i);
				}
			}
			
			field.setAccessible(true);
			field.set(obj, value);
		}
		return obj;
	}

	private ArrayList<String> parseArray(Object value) {
		ArrayList<String> itmes = new ArrayList<String>();
		char[] input = ((String) value).toCharArray();
		StringBuilder currentObject = new StringBuilder();
		boolean grabField = false;
		String str;
		for (char ch : input) {
			str = String.valueOf(ch);
			if (str.equals(JsonData.BEGIN.getValue())) {
				grabField = true;
			} else {
				if (str.equals(JsonData.END.getValue())) {
					grabField = false;
					currentObject.append(ch);
					itmes.add(currentObject.toString());
					currentObject = new StringBuilder();
				}
			}
			if (grabField) {
				currentObject.append(ch);
			}
		}

		return itmes;
	}

	// TODO following 2 methods should be optimized
	// getListOfSerializableFieldNames and getListOfSerializableFieldValues has almost the same code
	private static ArrayList<String> getListOfSerializableFieldNames(String currentObject) {
		ArrayList<String> fieldNames = new ArrayList<String>();
		ArrayList<String> fieldValues = new ArrayList<String>();

		// everything between " and ":
		String regExp = JsonData.QUOUTES.getValue() + "([^" + JsonData.QUOUTES.getValue() + "]*)"
				+ JsonData.QUOUTES.getValue() + ":";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(currentObject);
		while (m.find()) {
			fieldNames.add(m.group(1));
			int beginIndex = currentObject.indexOf(m.group(0)) + m.group(0).length();
			String cutted = currentObject.substring(beginIndex, currentObject.length());

			int endIndex;
			if (cutted.startsWith(JsonData.ARRAY_BEGIN.getValue())) {
				endIndex = cutted.length();
				String str = cutted.substring(0, endIndex);
				fieldValues.add(str);
				break;
			} else {
				endIndex = cutted.indexOf(JsonData.COMMA.getValue());
				endIndex = endIndex < 0 ? cutted.length() : endIndex;
				String str = cutted.substring(0, endIndex);
				str = str.replace(JsonData.END.getValue(), "");
				fieldValues.add(str.replace(JsonData.QUOUTES.getValue(), ""));
			}

		}

		return fieldNames;
	}

	private static ArrayList<String> getListOfSerializableFieldValues(String currentObject) {
		ArrayList<String> fieldValues = new ArrayList<String>();

		// everything between " and ":
		String regExp = JsonData.QUOUTES.getValue() + "([^" + JsonData.QUOUTES.getValue() + "]*)"
				+ JsonData.QUOUTES.getValue() + ":";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(currentObject);
		while (m.find()) {
			int beginIndex = currentObject.indexOf(m.group(0)) + m.group(0).length();
			String cutted = currentObject.substring(beginIndex, currentObject.length());

			int endIndex;
			if (cutted.startsWith(JsonData.ARRAY_BEGIN.getValue())) {
				endIndex = cutted.length();
				String str = cutted.substring(0, endIndex);
				fieldValues.add(str);
				break;
			} else {
				endIndex = cutted.indexOf(JsonData.COMMA.getValue());
				endIndex = endIndex < 0 ? cutted.length() : endIndex;
				String str = cutted.substring(0, endIndex);
				str = str.replace(JsonData.END.getValue(), "");
				fieldValues.add(str.replace(JsonData.QUOUTES.getValue(), ""));
			}

		}
		return fieldValues;
	}

}
