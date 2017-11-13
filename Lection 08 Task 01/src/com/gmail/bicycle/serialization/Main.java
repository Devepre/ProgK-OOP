package com.gmail.bicycle.serialization;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Main {
	public static void main(String[] args) {	
		Main handler = new Main();	
		String fileName = "dep.dat";			
		AcademicDepartment academicDepartment = handler.createDefaultDepartment();
		handler.saveDepartment(academicDepartment, fileName);
	}
	
	private AcademicDepartment createDefaultDepartment() {
		AcademicDepartment academicDepartment = new AcademicDepartment("AI");
		
		return academicDepartment;
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
