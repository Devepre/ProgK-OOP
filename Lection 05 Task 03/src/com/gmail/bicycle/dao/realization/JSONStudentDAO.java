package com.gmail.bicycle.dao.realization;

import java.io.IOException;

import com.gmail.bicycle.dao.factories.JSONDAOFactory;
import com.gmail.bicycle.dao.interfaces.StudentDAO;
import com.gmail.bicycle.json.JsonParser;
import com.gmail.bicycle.model.Student;

public class JSONStudentDAO implements StudentDAO {

	@Override
	public void saveStudent(Student student) throws IOException {
		JsonParser jsonParser = JSONDAOFactory.createConnection();
		try {
			jsonParser.serialize(student, JSONDAOFactory.FILE_NAME);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Student loadStudent(int position) throws IOException {	
		JsonParser jsonParser = JSONDAOFactory.createConnection();
		Student student = null;
		try {
			student = (Student) jsonParser.deserialize(Student.class, JSONDAOFactory.FILE_NAME);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return student;
	}

}
