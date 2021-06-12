package model;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Student extends User {
	
	private String minorSubject, majorSubject;
	private boolean minorDegreeGraduated, majorDegreeGraduated;
	private int currentSemesterNumber;
	private ArrayList<Student> connectedStudents;
	private ArrayList<Course> coursesList;

	public Student(String nationalId, String name, String surname, 
			GregorianCalendar birthday, Gender gender,
			String address, ArrayList<String> phoneNumbers, 
			ArrayList<String> emails, String username, String password,
			boolean active, String minorSubject, boolean minorDegreeGraduated,
			String majorSubject, boolean majorDegreeGraduated, 
			int currentSemesterNumber) {
		super(nationalId, name, surname, birthday, gender, address, 
				phoneNumbers, emails, username, password, active);
		this.minorSubject = minorSubject;
		this.majorSubject = majorSubject;
		this.minorDegreeGraduated = minorDegreeGraduated;
		this.majorDegreeGraduated = majorDegreeGraduated;
		this.currentSemesterNumber = currentSemesterNumber;
		this.connectedStudents = new ArrayList<Student>();
		this.coursesList = new ArrayList<Course>();
	}

	public String getMinorSubject() {
		return minorSubject;
	}

	public void setMinorSubject(String minorSubject) {
		this.minorSubject = minorSubject;
	}

	public String getMajorSubject() {
		return majorSubject;
	}

	public void setMajorSubject(String majorSubject) {
		this.majorSubject = majorSubject;
	}

	public boolean isMinorDegreeGraduated() {
		return minorDegreeGraduated;
	}

	public void setMinorDegreeGraduated(boolean minorDegreeGraduated) {
		this.minorDegreeGraduated = minorDegreeGraduated;
	}

	public boolean isMajorDegreeGraduated() {
		return majorDegreeGraduated;
	}

	public void setMajorDegreeGraduated(boolean majorDegreeGraduated) {
		this.majorDegreeGraduated = majorDegreeGraduated;
	}

	public int getCurrentSemesterNumber() {
		return currentSemesterNumber;
	}

	public void setCurrentSemesterNumber(int currentSemesterNumber) {
		this.currentSemesterNumber = currentSemesterNumber;
	}

	public ArrayList<Student> getConnectedStudents() {
		return connectedStudents;
	}

	public ArrayList<Course> getCoursesList() {
		return coursesList;
	}

	@Override
	public String toString() {
		return "Student [minorSubject=" + minorSubject + ", majorSubject=" + 
				majorSubject + ", minorDegreeGraduated="
				+ minorDegreeGraduated + ", majorDegreeGraduated=" + 
				majorDegreeGraduated + ", currentSemesterNumber="
				+ currentSemesterNumber + ", connectedStudents=" + 
				connectedStudents + ", coursesList=" + coursesList
				+ ", toString()=" + super.toString() + "]";
	}

}
