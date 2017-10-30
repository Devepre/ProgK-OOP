package com.gmail.bicycle.dao.factories;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

import com.gmail.bicycle.dao.interfaces.GroupDAO;
import com.gmail.bicycle.dao.interfaces.StudentDAO;
import com.gmail.bicycle.dao.realization.PlainTextGroupDAO;
import com.gmail.bicycle.dao.realization.PlainTextStudentDAO;

public class PlainTextDAOFactory extends DAOFactory {
	public static final String FILE_NAME = "group.txt";

	public static RandomAccessFile createConnection() throws FileNotFoundException {
		RandomAccessFile raFile = new RandomAccessFile(FILE_NAME, "rw");

		return raFile;
	}

	public StudentDAO getStudentDAO() {
		// PlainTextStudentDAO implements StudentDAO
		return new PlainTextStudentDAO();
	}

	public GroupDAO getGroupDAO() {
		// PlainTextGroupDAO implements GroupDAO
		return new PlainTextGroupDAO();
	}

}