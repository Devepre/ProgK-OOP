package com.gmail.bicycle.serialization.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AcademicDepartment implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private List<Group> groups = new ArrayList<Group>();
	
	public AcademicDepartment() {
		super();
	}

	public AcademicDepartment(String name) {
		super();
		this.name = name;
	}
	
	public void addGroup(Group group) {
		this.groups.add(group);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Group> getGroups() {
		return groups;
	}

	@Override
	public String toString() {
		return "AcademicDepartment [name=" + name + ", groups=" + System.lineSeparator() + groups + "]";
	}
		
}
