package com.gmail.bicycle;

import java.io.IOException;
import java.util.Date;

import com.gmail.bicycle.dao.factories.DAOFactory;
import com.gmail.bicycle.dao.interfaces.GroupDAO;
import com.gmail.bicycle.json.JsonRealization;
import com.gmail.bicycle.json.JsonParser;
import com.gmail.bicycle.model.Group;
import com.gmail.bicycle.model.Student;

public class Main {

	public static void main(String[] args) {
		System.out.println("Hi");

		Group group = new Group("Chemistry17");
		Student st1 = new Student("Jack", "Brown", new Date(0, 1, 23, 23, 1), true, 23.9);
		Student st2 = new Student("Mike", "White", new Date(2, 11, 23, 3, 11), true, 23.2);
		Student st3 = new Student("Bill", "Yellow", new Date(), true, 23);
		Student st4 = new Student("Anna", "Smith", new Date(), false, 213.34);
		try {
			//group.addStudent(st1);
			group.addStudent(st2);
			group.addStudent(st3);
			group.addStudent(st4);

			System.out.println("Created group is:");
			System.out.println(group);
			System.out.println();
		} catch (GroupOutOfBoundsException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

/*		DAOFactory plainTextFactory = DAOFactory.getDAOFactory(DAOFactory.PLAIN_TEXT);
		GroupDAO groupDAO = plainTextFactory.getGroupDAO();
		try {
			System.out.println("Saving group usint Plain Text method");
			groupDAO.saveGroup(group);
			System.out.println("Loading group using Plain Text method");
			Group loadedGroup = groupDAO.loadGroup();
			System.out.println(loadedGroup);

		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		Group loadedGroup = new Group();
		String fileName = "data.json";										
		try {
			JsonParser jsonParser = JsonParser.getParser(group);
			jsonParser.serializeTo(fileName);
			loadedGroup = (Group) jsonParser.deserializeFrom(fileName, loadedGroup.getClass());
			System.out.println("Loaded group is:");
			System.out.println(loadedGroup);
			System.out.println();
		} catch (SecurityException | IOException | ReflectiveOperationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
						
		System.out.println("End of program");

	}

}
