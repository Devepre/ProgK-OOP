package com.gmail.bicycle.stack;

import java.util.Arrays;

public class Stack {
	private static final int RESIZE_COEF = 2;
	private int capacity;
	private int length;
	private Object[] storage;
	private boolean selfPrinted;
	private boolean blackListEnabled;
	private BlackList blackList;

	public Stack() {
		super();
		this.capacity = 2;
		this.storage = new Object[this.capacity];
	}

	public Stack(int capacity) {
		super();
		if (capacity < 1 || capacity > Integer.MAX_VALUE) {
			throw new IllegalArgumentException("Wrong parameter");
		}
		this.capacity = capacity;
		this.storage = new Object[capacity];
	}

	public void push(Object obj) throws StackCapaictyException {
		boolean allowedTransaction = blackListEnabled ? !blackList.checkClass(obj.getClass()) : true;
		if (allowedTransaction) {
			checkStorage();
			this.storage[this.length++] = obj;
		} else {
			throw new IllegalArgumentException("Object class \"" + obj.getClass() + "\" is in " + this.blackList);
		}

		if (selfPrinted) {
			System.out.printf("Push operation for \"%s\" -> ", obj);
			System.out.println(this);
		}
	}

	public Object pop() throws StackCapaictyException {
		Object obj = get();
		this.storage[--length] = null;

		if (selfPrinted) {
			System.out.printf("Pop operation result is \"%s\" -> ", obj);
			System.out.println(this);
		}
		return obj;
	}

	public Object get() throws StackCapaictyException {
		if (this.length < 1) {
			throw new StackCapaictyException("End of Stack is reached");
		}
		Object obj = null;
		obj = this.storage[this.length - 1];

		return obj;
	}

	private void checkStorage() throws StackCapaictyException {
		if (getCapacity() == Integer.MAX_VALUE) {
			throw new StackCapaictyException("Max capacity of Stack already reached");
		}
		if (getLength() == getCapacity()) {
			expandCapacity();
		}

	}

	/**
	 * not good enough for max size reaching. Can be optimized
	 */
	private void expandCapacity() throws StackCapaictyException {
		int newSize = getCapacity() * RESIZE_COEF;
		newSize = getCapacity() > newSize ? Integer.MAX_VALUE - 8 : newSize;
		Object[] newStorage = null;
		try {
			newStorage = new Object[newSize];
			this.capacity = newSize;
			System.arraycopy(this.storage, 0, newStorage, 0, this.storage.length);
			this.storage = newStorage;
		} catch (OutOfMemoryError e) {
			throw new StackCapaictyException("OutOfMemoryError: Java heap space max capacity reached");
		}
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) throws StackCapaictyException {
		if (capacity < 1 || capacity > Integer.MAX_VALUE) {
			throw new IllegalArgumentException("wrong parameter");
		}
		if (this.storage.length > capacity) {
			throw new StackCapaictyException(
					"Unable to set " + capacity + " for Stack with " + getLength() + " elements");
		}
		this.capacity = capacity;
	}

	public int getLength() {
		return length;
	}

	public BlackList getBlackList() {
		return blackList;
	}

	public void setBlackList(BlackList blackList) {
		this.blackList = blackList;
		this.blackListEnabled = !(blackList == null);
	}

	public boolean isSelfPrinted() {
		return selfPrinted;
	}

	public void setSelfPrinted(boolean selfPrinted) {
		this.selfPrinted = selfPrinted;
	}

	public boolean isBlackListEnabled() {
		return blackListEnabled;
	}

	public void setBlackListEnabled(boolean blackListed) {
		this.blackListEnabled = blackListed;
	}

	@Override
	public String toString() {
		return "Stack capacity = " + capacity + ", length = " + length + ", storage:" + System.lineSeparator()
				+ Arrays.toString(storage)
				// + ", pointer=" + pointer + "]";
				+ System.lineSeparator();
	}

}
