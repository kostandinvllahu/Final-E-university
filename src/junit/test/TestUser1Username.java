package junit.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import data.DBHandler;

class TestUser1Username {

	@Test
	void test() {
		assertEquals(DBHandler.db.existsUser("Kostandin"), true); 
	}

	@Test
	void test1() {
		assertEquals(DBHandler.db.existsUser("Admin"), true); 
	}
	
	@Test
	void test2() {
		assertEquals(DBHandler.db.existsUser("Dori"), true); 
	}
	
	@Test
	void test3() {
		assertEquals(DBHandler.db.existsUser("Vasil"), true); 
	}
	
	@Test
	void test4() {
		assertEquals(DBHandler.db.existsUser("Marin"), true); 
	}
}
