package com.gmail.bicycle.stack;

public class Main {
	public static void main(String[] args) {

		try {
			Stack stack = new Stack();
			stack.setSelfPrinted(true);
			stack.push(5);
			stack.push(7);
			stack.push("Hi");
			stack.push("World");

			Object oo = stack.get();
			System.out.println("<Get> Object recieved: " + oo);

			stack.push(0.3);
			Object obj = stack.pop();
			System.out.println("<Pop> result: " + obj);
		} catch (StackCapaictyException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		System.out.println("the end");
	}

}
