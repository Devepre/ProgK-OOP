package com.gmail.bicycle.json;

import java.io.IOException;

public abstract class JsonParser {

	public abstract void serialize(Object object, String fileName) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, IOException;

	public abstract Object deserialize(Class<?> object, String fileName) throws IOException, Exception;

	public static JsonParser getParser() {
		return new JsonRealization();
	}

}
