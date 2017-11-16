/**
 * 
 */
package com.gmail.bicycle.lists;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ListWorkTest {
	private ListWork listWork;

	@BeforeEach
	void setUp() throws Exception {
		listWork = new ListWork();
	}

	@AfterEach
	void tearDown() throws Exception {
		listWork = null;
	}

	/**
	 * Test method for {@link com.gmail.bicycle.lists.ListWork#doWork()}.
	 */
	@Test
	@Disabled
	void testDoWork() {
		 fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.gmail.bicycle.lists.ListWork#print(java.util.List)}.
	 */
	@Test
	void testPrint() {
		PrintStream defaultOut = System.out;
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		List<String> list = new ArrayList<>();
		list.add("one");
		listWork.print(list);
		String expectedOutput = "[one]" + System.lineSeparator();
		assertEquals(expectedOutput, outContent.toString());
		
		System.setOut(defaultOut);
	}

	/**
	 * Test method for
	 * {@link com.gmail.bicycle.lists.ListWork#createRandomString(int)}.
	 */
	@Test
	@DisplayName("Test for creating random string")
	void testCreateRandomString() {
		int size = 7;
		String actual = listWork.createRandomString(size);
		assertNotNull(actual);
		assertEquals(String.class, actual.getClass());
		assertEquals(size, actual.length());
	}

	/**
	 * Test method for
	 * {@link com.gmail.bicycle.lists.ListWork#removeLast(java.util.List)}.
	 */
	@Test
	void testRemoveLast() {
		List<String> list = new ArrayList<>();
		list.add("one");
		listWork.removeLast(list);
		assertTrue(list.isEmpty());

		list.add("one");
		list.add("two");
		listWork.removeLast(list);
		assertEquals(1, list.size());

		List<String> testList = new ArrayList<>();
		testList.add("one");
		assertEquals(testList, list);

		assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
			listWork.removeLast(list);
			listWork.removeLast(list);
		});
	}

	/**
	 * Test method for
	 * {@link com.gmail.bicycle.lists.ListWork#removeFirst(java.util.List)}.
	 */
	@Test
	void testRemoveFirst() {
		List<String> list = new ArrayList<>();
		list.add("one");
		listWork.removeFirst(list);
		assertTrue(list.isEmpty());

		list.add("one");
		list.add("two");
		listWork.removeFirst(list);
		assertEquals(1, list.size());
		assertEquals("two", list.get(0));

		assertThrows(IndexOutOfBoundsException.class, () -> {
			listWork.removeFirst(list);
			listWork.removeFirst(list);
		});
	}

	/**
	 * Test method for {@link com.gmail.bicycle.lists.ListWork#createList(T[])}.
	 */
	@Test
	void testCreateList() {
		String[] arr = new String[] { "one", "two", "three" };
		List<String> created = listWork.createList(arr);
		List<String> expected = new ArrayList<>(Arrays.asList(arr));

		assertEquals(expected, created);
	}

}
