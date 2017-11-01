package com.gmail.bicycle.dao.factories;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

import com.gmail.bicycle.dao.interfaces.GroupDAO;
import com.gmail.bicycle.dao.interfaces.StudentDAO;
import com.gmail.bicycle.dao.realization.JSONGroupDAO;
import com.gmail.bicycle.dao.realization.JSONStudentDAO;
import com.gmail.bicycle.json.JsonParser;

public class JSONDAOFactory extends DAOFactory {
	public static final String FILE_NAME = "group.json";
	
	public static JsonParser createConnection() throws FileNotFoundException {
		JsonParser jsonParser = JsonParser.getParser();

		return jsonParser;
	}

	@Override
	public StudentDAO getStudentDAO() {
		return new JSONStudentDAO();
	}

	@Override
	public GroupDAO getGroupDAO() {		
		return new JSONGroupDAO();
	}

}
