package junit.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controll.Settings;
import data.DBHandler;

class TestSystemEmailPassword {

	@Test
	void test() {
		assertEquals(Settings.SYSTEM_EMAIL_PASSWORD, 
					 DBHandler.db.getSystemEmailPassword());
	}

}
