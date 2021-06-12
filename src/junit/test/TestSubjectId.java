package junit.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import data.DBHandler;

class TestSubjectId {

	@Test
	void test() {
		int id = DBHandler.db.getSubjectId("Informatics");
		assertEquals(id, 1);
	}
}
