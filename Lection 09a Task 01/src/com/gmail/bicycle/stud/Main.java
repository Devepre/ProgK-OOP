package com.gmail.bicycle.stud;

import com.gmail.bicycle.stud.model.Group;

public class Main {

	public static void main(String[] args) {
		Group someGroup = Group.createDefaultGroup();
		System.out.println("Input group:");
		someGroup.printGroup();
		String firstChars = "B";
		someGroup.setStudents(someGroup.getListBySurnameFirstLetter(firstChars));
		System.out.println();
		System.out.println("Studends with surname starts with " + firstChars);
		someGroup.printGroup();
	}

}
