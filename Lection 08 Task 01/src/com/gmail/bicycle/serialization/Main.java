package com.gmail.bicycle.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.gmail.bicycle.GroupOutOfBoundsException;
import com.gmail.bicycle.serialization.model.AcademicDepartment;
import com.gmail.bicycle.serialization.model.Group;
import com.gmail.bicycle.serialization.model.Student;

public class Main {
	public static void main(String[] args) {
		System.out.println("Please provide your choice:");
		System.out.println("1. Save groups to the file");
		System.out.println("2. Load groups from the file");
		System.out.println("3. Exit");
		int choice = 3;
		Scanner sc = new Scanner(System.in);
		try {
			choice = sc.nextInt();
		} catch (NoSuchElementException e) {
			System.out.println("Wrong input. Try again");
			main(args);
		}
		if (choice == 1 || choice == 2 || choice == 3) {
			new Main().doSomething(choice, args, sc);
		} else {
			System.out.println("Wrong input. Try again");
			main(args);
		}
	}

	private void doSomething(int choice, String[] args, Scanner sc) {
		Main handler = new Main();
		AcademicDepartment academicDepartment = null;
		AcademicDepartment restoredDepartment = null;
		String fileName = "dep.dat";
		switch (choice) {
		case 1:
			academicDepartment = handler.createDefaultDepartment();
			handler.saveDepartment(academicDepartment, fileName);
			System.out.println("Done");
			System.out.println("Saved info is:");
			System.out.println(academicDepartment);
			main(args);
		case 2:
			restoredDepartment = handler.loadDepartment(fileName);
			System.out.println("Loaded info is:");
			System.out.println(restoredDepartment);
			main(args);
		case 3:
			sc.close();
			System.exit(1);
		}
	}

	private AcademicDepartment createDefaultDepartment() {
		AcademicDepartment academicDepartment = new AcademicDepartment("AI");
		academicDepartment.addGroup(createDefaultGroup());

		return academicDepartment;
	}

	private Group createDefaultGroup() {
		Group group = new Group("Ph 16");
		Student st1 = new Student("Jack", "Brown", new Date(), true, 23.9);
		Student st2 = new Student("Mike", "White", new Date(), true, 23.2);
		Student st3 = new Student("Bill", "Yellow", new Date(), true, 23);
		Student st4 = new Student("Ann", "Green", new Date(), false, 32);
		Student st5 = new Student("Maria", "Red", new Date(), false, 43);
		Student st6 = new Student("Mark", "Purple", new Date(), true, 4);
		Student st7 = new Student("John", "Borm", new Date(), true, 24);
		Student st8 = new Student("Jack", "Black", new Date(), true, 9);
		try {
			group.addStudent(st1);
			group.addStudent(st2);
			group.addStudent(st3);
			group.addStudent(st4);
			group.addStudent(st5);
			group.addStudent(st6);
			group.addStudent(st7);
			group.addStudent(st8);
		} catch (GroupOutOfBoundsException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		return group;
	}

	private AcademicDepartment loadDepartment(String fileName) {
		if (fileName.isEmpty()) {
			throw new IllegalArgumentException("wrong parameter");
		}
		AcademicDepartment loadedDepartment = null;
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
			loadedDepartment = (AcademicDepartment) ois.readObject();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return loadedDepartment;
	}

	private void saveDepartment(AcademicDepartment academicDepartment, String fileName) {
		if (academicDepartment == null || fileName.isEmpty()) {
			throw new IllegalArgumentException("wrong parameters");
		}
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
			oos.writeObject(academicDepartment);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
