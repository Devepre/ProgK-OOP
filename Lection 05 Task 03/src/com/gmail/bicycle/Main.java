package com.gmail.bicycle;

import java.io.IOException;
import java.util.Date;

import dao.factories.DAOFactory;
import dao.interfaces.GroupDAO;

public class Main {

	public static void main(String[] args) {
		System.out.println("Hi");

		Group group = new Group("Chemistry17");
		Student st1 = new Student("Jack", "Brown", new Date(0, 1, 23, 23, 1), true, 23.9);
		Student st2 = new Student("Mike", "White", new Date(2, 11, 23, 3, 11), true, 23.2);
		Student st3 = new Student("Bill", "Yellow", new Date(), true, 23);
		try {
			group.addStudent(st1);
			group.addStudent(st2);
			group.addStudent(st3);

			System.out.println("Created group is:");
			System.out.println(group);
			System.out.println();
		} catch (GroupOutOfBoundsException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		DAOFactory plainTextFactory = DAOFactory.getDAOFactory(DAOFactory.PLAIN_TEXT);
		GroupDAO groupDAO = plainTextFactory.getGroupDAO();
		try {
			System.out.println("Saving group usint Plain Text method");
			groupDAO.saveGroup(group);
			System.out.println("Loading group using Plain Text method");
			Group loadedGroup = groupDAO.loadGroup();
			System.out.println(loadedGroup);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
