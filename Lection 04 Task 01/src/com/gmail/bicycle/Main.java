package com.gmail.bicycle;

import java.util.Date;

import com.gmail.bicycle.GroupOutOfBoundsException;

public class Main {

	public static void main(String[] args) {
		Group group = new Group("Math17");

		System.out.println("Adding 8 new members");
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

		System.out.println(group);

		try {
			group.addStudent();
			group.addStudent();
		} catch (GroupOutOfBoundsException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		System.out.println();
		System.out.println("Group after adding students from keyboard");
		System.out.println(group);

		System.out.println("Sorted descending by Stipend");
		group.sort(SortCriterion.STIPEND, false);
		System.out.println(group);

		System.out.println("Sorted ascending by Name");
		group.sort(SortCriterion.NAME, true);
		System.out.println(group);

		System.out.println("Sorted ascending by Sex");
		group.sort(SortCriterion.SEX, true);
		System.out.println(group);

		System.out.println("These students need to visit Military house:");
		Student[] conscripters = group.getConscripters();
		for (Student student : conscripters) {
			System.out.println(student);
		}
	}

}
