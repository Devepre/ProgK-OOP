package com.gmail.bicycle;

import java.io.IOException;

public interface StudentDAO {
	public void saveStudent(Student student) throws IOException;

	public Student loadStudent(int position) throws IOException;

}
