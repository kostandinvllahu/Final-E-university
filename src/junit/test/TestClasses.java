package junit.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import data.DBHandler;

class TestClasses {

	@Test
	void test() {
		int id = DBHandler.db.getCourseId("Into to C#");
		assertEquals(id, 2);
	}
	
	@Test
	void test1() {
		int id = DBHandler.db.getCourseId("Into to programming");
		assertEquals(id, 1);
	}
	
	@Test
	void test2() {
		int id=DBHandler.db.getCourseId("Software analysis and design");
		assertEquals(id,3);
	}
}
