package junit.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import data.DBHandler;

class TestTimeTable {

	@Test
	void test() {
		int id = DBHandler.db.getTimeTable("Into to Java");
		assertEquals(id, 10);
	}
}
