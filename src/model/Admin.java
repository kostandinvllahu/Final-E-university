package model;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Admin extends User { 

	public Admin(String username, String password) {
		super(username, password);
		// TODO Auto-generated constructor stub
	}

	public Admin(String username, String password, boolean active) {
		super(username, password, active);
		// TODO Auto-generated constructor stub
	}

	public Admin(String nationalId, String name, String surname, GregorianCalendar birthday, Gender gender,
			String address, ArrayList<String> phoneNumbers, ArrayList<String> emails, String username, String password,
			boolean active) {
		super(nationalId, name, surname, birthday, gender, address, phoneNumbers, emails, username, password, active);
		// TODO Auto-generated constructor stub
	}
}
