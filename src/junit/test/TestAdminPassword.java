package junit.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import data.DBHandler;

class TestAdminPassword {

	@Test
	void test() {
		assertEquals("admin", DBHandler.db.getUserData("admin").getPassword());
	}

}
