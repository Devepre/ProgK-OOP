package com.gmail.bicycle.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import javax.swing.JOptionPane;

import com.gmail.bicycle.CheckNull;
import com.gmail.bicycle.GroupOutOfBoundsException;
import com.gmail.bicycle.SortCriterion;
import com.gmail.bicycle.json.SerializedParameter;

public class Group implements IMilitaryCommissariat {	
	private static final int CAPACITY = 10;
	@SerializedParameter("name")
	private String name;
	@SerializedParameter("storage")
	private Student[] storage = new Student[CAPACITY];

	private enum BooleanAnswer {
		YES, NO
	}

	public Group() {
		super();
	}

	public Group(String name) {
		super();
		this.name = name;
	}

	public void addStudent() throws GroupOutOfBoundsException {
		String stuSurname;
		String stuName;
		Date stuDate;
		Boolean isMale;
		Double stuStipend;

		stuSurname = getInputString("Please provide Surname", "Wrong Surname");
		if (stuSurname != null) {
			stuName = getInputString("Please provide Name", "Wrong Name");
			if (stuName != null) {
				stuDate = getInputDate("Please provide birth date", "Wrong birth date", "dd-MM-yyyy");
				if (stuDate != null) {
					isMale = getInputBoolean("Is it a man?", "Wrong sex input");
					if (isMale != null) {
						stuStipend = getInputDouble("Please provide stipend value", "Wrong number");
						if (stuStipend != null) {
							addStudent(new Student(stuName, stuSurname, stuDate, isMale, stuStipend));
							return;
						}
					}
				}
			}
		}

		System.out.println("User has canceled creating Student");
	}

	protected String getInputString(String displayMessage, String errorMessage) {
		String result = null;
		for (;;) {
			try {
				result = JOptionPane.showInputDialog(displayMessage);
				if (result != null && result.equals("")) {
					throw new IllegalArgumentException();
				}
				break;
			} catch (IllegalArgumentException e) {
				JOptionPane.showMessageDialog(null, errorMessage);
			}
		}
		return result;

	}

	protected Date getInputDate(String displayMessage, String errorMessage, String dateView) {
		Date result = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateView);
		for (;;) {
			try {
				result = dateFormat.parse((JOptionPane.showInputDialog(displayMessage + " " + dateView)));
				break;
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(null, errorMessage);
			} catch (NullPointerException e) {
				break;
			}
		}
		return result;

	}

	protected Boolean getInputBoolean(String displayMessage, String errorMessage) {
		Boolean result = null;
		BooleanAnswer[] answers = { BooleanAnswer.YES, BooleanAnswer.NO };

		for (;;) {
			try {
				BooleanAnswer answer = (BooleanAnswer) JOptionPane.showInputDialog(null, displayMessage, "Input",
						JOptionPane.QUESTION_MESSAGE, null, answers, answers[0]

				);
				switch (answer) {
				case YES:
					result = true;
					break;
				case NO:
					result = false;
					break;
				}
				break;
			} catch (NullPointerException e) {
				break;
			}
		}
		return result;

	}

	protected Double getInputDouble(String displayMessage, String errorMessage) {
		Double result = null;
		for (;;) {
			try {
				result = Double.parseDouble(JOptionPane.showInputDialog(displayMessage));
				break;
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, errorMessage);
			} catch (NullPointerException e) {
				break;
			}
		}
		return result;

	}

	public boolean addStudent(Student student) throws GroupOutOfBoundsException, IllegalArgumentException {
		if (student == null) {
			throw new IllegalArgumentException("Student can't be null");
		}
		for (int i = 0; i < storage.length; i++) {
			Student tempStudent = storage[i];
			if (tempStudent == null) {
				storage[i] = student;
				return true;
			} else {
				if (tempStudent.equals(student)) {
					System.err.print(tempStudent.getId() + " ID is already registered" + System.lineSeparator());
					return false;
				}
			}
		}
		throw new GroupOutOfBoundsException();
	}

	public Student remove(Student student) {
		for (int i = 0; i < storage.length; i++) {
			if (storage[i].equals(student)) {
				storage[i] = null;
				return student;
			}
		}
		return null;
	}

	public Student remove(int index) throws GroupOutOfBoundsException {
		checkIndex(index);
		Student student = storage[index];
		storage[index] = null;
		return student;
	}

	private void checkIndex(int index) throws GroupOutOfBoundsException {
		if (index >= storage.length || index < 0) {
			throw new GroupOutOfBoundsException();
		}
	}

	public Student findStudent(String surname) {
		Student studentFound = null;

		if (surname == null) {
			for (Student student : storage) {
				if (student != null && student.getSurname() == null) {
					studentFound = student;
					break;
				}
			}
		} else {
			for (Student student : storage) {
				if (student != null && student.getSurname() != null && student.getSurname().equals(surname)) {
					studentFound = student;
					break;
				}
			}
		}

		return studentFound;
	}

	public void sort(SortCriterion criterion, boolean ascending) {
		switch (criterion) {
		case SURNAME:
			Arrays.sort(storage, (a, b) -> CheckNull.checkNull(a, b) != CheckNull.NOT_NULL ? CheckNull.checkNull(a, b)
					: a.getSurname().compareToIgnoreCase(b.getSurname()));
			break;
		case NAME:
			Arrays.sort(storage, (a, b) -> CheckNull.checkNull(a, b) != CheckNull.NOT_NULL ? CheckNull.checkNull(a, b)
					: a.getName().compareToIgnoreCase(b.getName()));
			break;
		case DATE_OF_BIRTH:
			Arrays.sort(storage, (a, b) -> CheckNull.checkNull(a, b) != CheckNull.NOT_NULL ? CheckNull.checkNull(a, b)
					: a.getDateOfBirth().compareTo(b.getDateOfBirth()));
			break;
		case SEX:
			Arrays.sort(storage, (a, b) -> CheckNull.checkNull(a, b) != CheckNull.NOT_NULL ? CheckNull.checkNull(a, b)
					: ((Boolean) a.isMale()).compareTo((Boolean) b.isMale()));
			break;
		case STIPEND:
			Arrays.sort(storage, (a, b) -> {
				if (CheckNull.checkNull(a, b) != CheckNull.NOT_NULL) {
					return CheckNull.checkNull(a, b);
				}
				return a.getStipend() > b.getStipend() ? 1 : a.getStipend() < b.getStipend() ? -1 : 0;
			});
			break;
		}

		if (!ascending) {
			Collections.reverse(Arrays.asList(storage));
		}
	}

	@Override
	public Student[] getConscripters() {
		Student[] conscripters = new Student[CAPACITY];
		int index = 0;

		long ageMiliseconds = 0;
		int years = 0;
		Date dateNow = new Date();
		long timeNow = dateNow.getTime();
		Calendar calendar = Calendar.getInstance();
		for (Student student : storage) {
			if (student != null && student.isMale()) {
				ageMiliseconds = timeNow - student.getDateOfBirth().getTime();
				calendar.setTimeInMillis(ageMiliseconds);
				years = calendar.get(Calendar.YEAR) - 1970;
				if (years > 17 && years < 22) {
					conscripters[index++] = student;
				}
			}
		}
		conscripters = Arrays.copyOf(conscripters, index);

		return conscripters;
	}

	public void clear() {
		storage = new Student[CAPACITY];
	}

	public int size() {
		int size = 0;
		for (int i = 0; i < storage.length; i++) {
			if (storage[i] != null) {
				size++;
			}
		}
		return size;
	}

	public int capacity() {
		return CAPACITY;
	}

	public Student[] getStorage() {
		return storage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Student[] getStudents() {
		return storage;
	}

	public void setStudents(Student[] students) {
		this.storage = students;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("Name: ").append(this.getName()).append(System.lineSeparator());
		for (Student student : storage) {
			if (student != null) {
				sb.append(student + System.lineSeparator());
			}
		}

		return sb.toString();
	}

}
