package junit.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import data.DBHandler;

class TestUser1Password {

	@Test
	void test() {
		assertEquals("1234", DBHandler.db.getUserData("Kostandin").getPassword());
	}

	@Test
	void test1() {
		assertEquals("1234", DBHandler.db.getUserData("Marin").getPassword());
	}
	
	@Test
	void test2() {
		assertEquals("1994", DBHandler.db.getUserData("Vasil").getPassword());
	}
	
	@Test
	void test3() {
		assertEquals("1991", DBHandler.db.getUserData("Dori").getPassword());
	}
	
	@Test
	void test4() {
		assertEquals("admin", DBHandler.db.getUserData("Admin").getPassword());
	}
}
