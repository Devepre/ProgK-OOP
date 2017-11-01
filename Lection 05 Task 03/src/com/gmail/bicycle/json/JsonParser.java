package com.gmail.bicycle.json;

import java.io.IOException;

public abstract class JsonParser {

	public abstract void serializeTo(String fileName) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, IOException;

	public abstract Object deserializeFrom(String fileName, Class<?> object) throws IOException, Exception;

	public static JsonParser getParser(Object obj) throws SecurityException, IOException, ReflectiveOperationException  {
		return new JsonRealization(obj);
	}

}
