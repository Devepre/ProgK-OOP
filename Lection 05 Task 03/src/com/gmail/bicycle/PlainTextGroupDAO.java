package com.gmail.bicycle;

import java.io.IOException;
import java.io.RandomAccessFile;

//PlainTextGroupDAO implementation of the 
//GroupDAO interface. This class can contain all
//PlainText specific code. 
//The client is thus shielded from knowing 
//these implementation details.
public class PlainTextGroupDAO implements GroupDAO {

	public PlainTextGroupDAO() {
		super();
	}

	@Override
	public void saveGroup(Group group) throws IOException {
		try (RandomAccessFile raf = PlainTextDAOFactory.createConnection()) {
			Student[] students = group.getStorage();
			for (Student student : students) {
				if (student != null) {
					DAOFactory plainTextFactory = DAOFactory.getDAOFactory(DAOFactory.PLAIN_TEXT);
					StudentDAO studentDAO = plainTextFactory.getStudentDAO();
					studentDAO.saveStudent(student);
				}
			}
		} catch (IOException e) {
			throw e;
		}
	}

	@Override
	public Group loadGroup() throws IOException {
		Group group = new Group();
		Student student;

		int position = 0;
		DAOFactory plainTextFactory = DAOFactory.getDAOFactory(DAOFactory.PLAIN_TEXT);
		StudentDAO studentDAO = plainTextFactory.getStudentDAO();
		while ((student = studentDAO.loadStudent(position)) != null) {
			try {
				group.addStudent(student);
			} catch (IllegalArgumentException | GroupOutOfBoundsException e) {
				e.printStackTrace();
			}
			position++;
		}

		return group;
	}

}
