package com.gmail.bicycle.letters;

/**
 * Service class for letter counting {@link #value} represents letter
 * {@link #count} describes how much times this letter present somewhere
 */
public class Letter implements Comparable<Letter> {
	private char value;
	private int count;

	public Letter() {
		super();
	}

	public Letter(char value) {
		super();
		this.value = value;
	}

	public char getValue() {
		return value;
	}

	public void setValue(char value) {
		this.value = value;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + value;
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
		Letter other = (Letter) obj;
		if (value != other.value)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return value + "->" + count + " ";
	}

	@Override
	public int compareTo(Letter o) {
		int compareResult = 0;
		if (this.getCount() < o.getCount()) {
			compareResult = -1;
		}
		if (this.getCount() > o.getCount()) {
			compareResult = 1;
		}
		return compareResult;
	}

}
