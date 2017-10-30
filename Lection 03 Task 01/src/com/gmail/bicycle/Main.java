package com.gmail.bicycle;

import java.util.Date;

public class Main {

	public static void main(String[] args) {

		System.out.println("Creating Humans + toString(): ");
		Human humanOne = new Human("Lisa", "Brown", new Date(), false);
		Human humanTwo = new Human();
		humanOne.printInfo();
		humanTwo.printInfo();
		System.out.println();

		System.out.println("Creating group of 15 students:");
		Group group = new Group("Math17");

		try {
			for (int i = 0; i < 15; i++) {
				group.addStudent(new Student());
			}
		} catch (GroupOutOfBoundsException e) {
			e.printStackTrace();
		}
		System.out.println(group);
		System.out.println();

		System.out.println("Remove student #2");
		try {
			group.remove(1);
		} catch (GroupOutOfBoundsException e1) {
			e1.printStackTrace();
		}
		System.out.println("Remove student #12");
		try {
			group.remove(11);
		} catch (GroupOutOfBoundsException e1) {
			e1.printStackTrace();
		}

		System.out.println("Trying to add Student which is already added");
		Student studentNew = new Student();
		studentNew.setId(31245803);

		try {
			group.addStudent(studentNew);
		} catch (GroupOutOfBoundsException e) {
			e.printStackTrace();
		}
		System.out.println(group);
		System.out.println("Total places left: " + (group.capacity() - group.size()));
		System.out.println();

		System.out.println("Adding Student with stipend:");
		Student studBill = new Student("Bill", "Forester", new Date(), true, 3.0d);
		try {
			group.addStudent(studBill);
		} catch (GroupOutOfBoundsException e) {
			e.printStackTrace();
		}
		System.out.println(group);
		System.out.println("Total places left: " + (group.capacity() - group.size()));

		System.out.println("Triyng to find Student null");
		Student found = group.findStudent(null);
		System.out.println("Found:" + found);
		String surname = "Forester";
		System.out.println("Trying to find Student " + surname);
		found = group.findStudent(surname);
		System.out.println("Found:" + found);

		System.out.println("Make group empty:");
		group.clear();
		System.out.println(group);
		System.out.println();

		System.out.println("Adding new members with Surnames to sort to the group");
		Student st1 = new Student("Jack", "Brown", new Date(), true, 23);
		Student st2 = new Student("Mike", "White", new Date(), true, 23);
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
			group.addStudent(new Student(null, null, new Date(), true, 23));
			group.addStudent(new Student(null, null, new Date(), false, 13));
		} catch (GroupOutOfBoundsException e) {
			e.printStackTrace();
		}

		System.out.println(group);
		System.out.println("Total places left: " + (group.capacity() - group.size()));

	}

}
