package com.gmail.bicycle.dao.interfaces;

import java.io.IOException;

import com.gmail.bicycle.model.Student;


public interface StudentDAO {
	public void saveStudent(Student student) throws IOException;

	public Student loadStudent(int position) throws IOException;

}
