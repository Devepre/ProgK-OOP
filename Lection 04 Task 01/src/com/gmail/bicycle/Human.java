package com.gmail.bicycle;

import java.util.Date;

public class Human {
	public static long count = 31245800;
	private String name = "";
	private String surname = "";
	private long id = ++count;
	private Date dateOfBirth;
	private boolean male;

	public Human() {
		super();
	}

	public Human(String name, String surname, Date dateOfBirth, boolean male) {
		super();
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
		this.male = male;
	}

	public void printInfo() {
		StringBuilder sb = new StringBuilder();
		sb.append("I'm a Human ID:" + id);
		sb.append(System.lineSeparator());
		sb.append("My name is " + name);
		sb.append(System.lineSeparator());
		sb.append("My Surname is " + surname);
		sb.append(System.lineSeparator());
		System.out.println(sb.toString());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isMale() {
		return male;
	}

	public void setMale(boolean male) {
		this.male = male;
	}

	@Override
	public String toString() {
		return "Human [name=" + name + ", surname=" + surname + ", id=" + id + ", dateOfBirth=" + dateOfBirth + ", "
				+ (male ? "man" : "woman") + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Human other = (Human) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
