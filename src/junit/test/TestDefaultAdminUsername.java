package junit.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import data.DBHandler;

class TestDefaultAdminUsername {

	@Test
	void test() {
		assertEquals(DBHandler.db.existsUser("admin"), true); 
	}

}
