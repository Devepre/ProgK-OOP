package com.gmail.bicycle;

import java.io.IOException;
import java.util.Date;

import com.gmail.bicycle.dao.factories.DAOFactory;
import com.gmail.bicycle.dao.factories.JSONDAOFactory;
import com.gmail.bicycle.dao.factories.PlainTextDAOFactory;
import com.gmail.bicycle.dao.interfaces.GroupDAO;
import com.gmail.bicycle.json.JsonParser;
import com.gmail.bicycle.model.Group;
import com.gmail.bicycle.model.Student;

public class Main {

	public static void main(String[] args) {
		System.out.println("Creating some default group...");

		Group group = new Group("Chemistry17");
		Student st1 = new Student("Jack", "Brown", new Date(0, 1, 23, 23, 1), true, 23.9);
		Student st2 = new Student("Mike", "White", new Date(2, 11, 23, 3, 11), true, 23.2);
		Student st3 = new Student("Bill", "Yellow", new Date(), true, 23);
		Student st4 = new Student("Anna", "Smith", new Date(), false, 213.34);
		try {
			group.addStudent(st1);
			group.addStudent(st2);
			group.addStudent(st3);
			group.addStudent(st4);

			System.out.println("Group created:");
			System.out.println(group);
		} catch (GroupOutOfBoundsException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		//Block of code using DAO model
		{
			System.out.println("<<<Demo using DAO>>>");
			System.out.println("<<<File name used from PlainTextDAOFactory and JSONDAOFactory configuration>>>");
			System.out.println();

			DAOFactory groupDAOFactory = DAOFactory.getDAOFactory(DAOFactory.PLAIN_TEXT);
			GroupDAO groupDAO = groupDAOFactory.getGroupDAO();
			try {
				System.out.println("Saving group using Plain Text DAO method. Mode: adding to file if exist");
				System.out.println("File name is: " + PlainTextDAOFactory.FILE_NAME);
				groupDAO.saveGroup(group);
				System.out.println("Loading group using Plain Text DAO method");
				Group loadedGroup = groupDAO.loadGroup();
				System.out.println(loadedGroup);

			} catch (IOException e) {
				e.printStackTrace();
			}

			//it's the only code to change provider from Plain text to JSON
			groupDAOFactory = DAOFactory.getDAOFactory(DAOFactory.JSON);
			groupDAO = groupDAOFactory.getGroupDAO();

			try {
				System.out.println("Saving group using JSON method");
				System.out.println("File name is: " + JSONDAOFactory.FILE_NAME);
				groupDAO.saveGroup(group);
				System.out.println("Loading group using JSON method");
				Group loadededJsonGroup = groupDAO.loadGroup();
				System.out.println(loadededJsonGroup);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("<<<End Demo using DAO>>>");			
		}	//End block of code using DAO model

		Group loadedGroup = new Group();
		String fileName = "data.json";
		System.out.printf("<<<Demo using JSON parser directly. Using '%s' file>>>", fileName);
		System.out.println();
		try {
			JsonParser jsonParser = JsonParser.getParser();
			System.out.println("Saving group");
			jsonParser.serialize(group, fileName);
			loadedGroup = (Group) jsonParser.deserialize(Group.class, fileName);
			System.out.println("Loaded group is:");
			System.out.println(loadedGroup);
		} catch (SecurityException | IOException | ReflectiveOperationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("<<<End of program>>>");

	}

}
