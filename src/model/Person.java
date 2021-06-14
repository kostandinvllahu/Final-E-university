package model;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
 
public class Person  { 

	//Deklarohen gender
	public enum Gender {
		
		MALE, 
		FEMALE, 
		OTHER;
		
	}
//deklarohen te dhenat e studentit qe do marrin vlerat
	private int systemId;
	private String nationalId;
	private String name;
	private String surname;
	private GregorianCalendar birthday;
	private Gender gender; 
	private ArrayList<String> phoneNumbers;
	private ArrayList<String> emails;
	private String address;
	private File profileImg;
	/*Vlerat futen te string dhe int perkatese, dhe rregjistrohen ne databaze
	 * */
	public Person(String nationalId) {
		this(nationalId, "", "", 
			 new GregorianCalendar(1970, 0, 1),
			 Gender.OTHER, "", new ArrayList<String>(),
			 new ArrayList<String>());
	}
	
	public Person(String nationalId, String name,
				  String surname, GregorianCalendar birthday,
				  Gender gender) {
		this(nationalId, name, surname, birthday, gender, "", 
			 new ArrayList<String>(), new ArrayList<String>());
	}

	public Person(String nationalId, String name,
			  String surname, GregorianCalendar birthday,
			  Gender gender, String address,
			  ArrayList<String> phoneNumbers,
			  ArrayList<String> emails) { 
		this.nationalId = nationalId;
		this.name = name;
		this.surname = surname;
		this.birthday = birthday;
		this.gender = gender;
		this.address = address;
		if(phoneNumbers == null) {
			phoneNumbers = new ArrayList<String>();
		}
		this.phoneNumbers = phoneNumbers;
		if(emails == null) {
			emails = new ArrayList<String>();
		}
		this.emails = emails; 
	}

	public int getSystemId() {
		return systemId;
	}

	public void setSystemId(int systemId) {
		this.systemId = systemId;
	}
 
	public String getNationalId() {
		return nationalId;
	}

	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public GregorianCalendar getBirthday() {
		return birthday;
	}

	public void setBirthday(GregorianCalendar birthday) {
		this.birthday = birthday;
	}

	public File getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(File profileImg) {
		this.profileImg = profileImg;
	}
	
	public int getPhoneNumbers() {
		return phoneNumbers.size();
	}

	public int getEmails() {
		return emails.size();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Gender getGender() {
		return gender;
	}

	public String getPhoneNumber(int index) {
		if(index >= 0 && index < phoneNumbers.size()) {
			return phoneNumbers.get(index);
		}
		return null;
	}
	
	public void addPhoneNumber(String phoneNumber) {
		if(!phoneNumbers.contains(phoneNumber)) {
			phoneNumbers.add(phoneNumber);
		}
	}

	public void removePhoneNumber(int index) {
		if(index >= 0 && index < phoneNumbers.size()) {
			phoneNumbers.remove(index);
		}
	}

	public void removePhoneNumber(String phoneNumber) { 
		phoneNumbers.remove(phoneNumber); 
	}

	public String getEmail(int index) {
		if(index >= 0 && index < emails.size()) {
			return emails.get(index);
		}
		return null;
	}
	
	
	public void addEmail(String email) {
		email = email.toLowerCase();
		if(!emails.contains(email)) {
			emails.add(email);
		}
	}

	public void removeEmail(int index) {
		if(index >= 0 && index < emails.size()) {
			emails.remove(index);
		}
	}

	public void removeEmail(String email) { 
		emails.remove(email);
	}
	
	public boolean existsPhoneNumber(String phoneNumber) {
		return phoneNumbers.contains(phoneNumber);
	}

	public boolean existsEmail(String email) {
		return emails.contains(email);
	}

	@Override
	public String toString() {
		return "Person [systemId=" + systemId + 
				", nationalId=" + nationalId + 
				", name=" + name + 
				", surname=" + surname
				+ ", birthday=" + birthday.get(Calendar.YEAR) + "/" 
								+ (birthday.get(Calendar.MONTH) + 1) + "/"
								+ birthday.get(Calendar.DAY_OF_MONTH) +
				", gender=" + gender + 
				", phoneNumbers=" + phoneNumbers + 
				", emails=" + emails + 
				", address=" + address + 
				", profileImg=" + profileImg + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + ((emails == null) ? 0 : emails.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((nationalId == null) ? 0 : nationalId.hashCode());
		result = prime * result + ((phoneNumbers == null) ? 0 : phoneNumbers.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + ((profileImg == null) ? 0 : profileImg.hashCode());
		result = prime * result + systemId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(!(obj instanceof Person)) {
			return false;
		}
		return systemId == ((Person)obj).systemId;
	}
	
}
