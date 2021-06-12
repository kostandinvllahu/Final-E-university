package data;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

import com.mysql.cj.jdbc.MysqlDataSource;

import controll.Settings;
import model.Admin;
import model.Announcement;
import model.Course;
import model.Person.Gender;
import model.Rating;
import model.Schedule;
import model.Student;
import model.Timetable;
import model.User;

public class DBHandler {

	private MysqlDataSource dataSource;
	private Connection connection;
	public static DBHandler db;
	private String dbName = "university";

	static {
		try {
			db = new DBHandler();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public DBHandler() throws SQLException {
		dataSource = new MysqlDataSource();
		dataSource.setUser("root");
		dataSource.setPassword("");
		dataSource.setServerName("localhost");
		dataSource.setUseSSL(false);
		dataSource.setDatabaseName(dbName);
		dataSource.setAllowPublicKeyRetrieval(true);
		connection = dataSource.getConnection();
	}

	public Connection getConnection() {
		return connection;
	}

	public void closeDBConnection() throws SQLException {
		connection.close();
	}

	public ArrayList<String> getTestValues() {
		ArrayList<String> list = new ArrayList<String>();
		try {
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * from " + dbName + ".test");
			while (rs.next()) {
				list.add(rs.getInt(1) + " " + rs.getString(2));
			}
			rs.clearWarnings();
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean existsUser(String username) {
		try {
			Statement stm = connection.createStatement();
			ResultSet rs = stm
					.executeQuery("SELECT * from " + dbName + ".user " + "where username = '" + username + "'");
			if (rs.next()) {
				return true;
			}
			rs.clearWarnings();
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean existsUser(String username, String password) {
		try {
			Statement stm = connection.createStatement();
			ResultSet rs = stm
					.executeQuery("SELECT * FROM " + dbName + ".user " + 
							"WHERE username = '" + username + 
							"' AND password = '" + password + "'");
			if (rs.next()) {
				return true;
			}
			rs.clearWarnings();
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public User getUserData(String username) {
		return Settings.loggedinUser = getUserByUsername(getUsers(), username);
	}

	private User getUserByUsername(ArrayList<User> users, String username) {
		for(User user : users) {
			if(user.getUsername().equalsIgnoreCase(username)) {
				return user;
			}
		}
		return null;
	}

	public String[] getMinorSubjects() {
		ArrayList<String> list = new ArrayList<String>();
		try {
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery("SELECT title FROM " + dbName + 
					".subject WHERE minor_subject = '1'");
			while (rs.next()) {
				list.add(rs.getString(1));
			}
			rs.clearWarnings();
			stm.close();
		}
		catch(Exception ex) {
		}
		list.add(0, "-");
		String[] result = new String[list.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = list.get(i);
		}
		return result;
	}

	public String[] getMajorSubjects() {
		ArrayList<String> list = new ArrayList<String>();
		try {
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery("SELECT title FROM " + dbName + 
					".subject WHERE minor_subject = '0'");
			while (rs.next()) {
				list.add(rs.getString(1));
			}
			rs.clearWarnings();
			stm.close();
		}
		catch(Exception ex) {
		}
		list.add(0, "-");
		String[] result = new String[list.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = list.get(i);
		}
		return result;
	}

	public ArrayList<User> getUsers() {
		ArrayList<Integer> adminIds = getAdminIds();
		//ArrayList<Integer> studentIds = getStudentIds();
		ArrayList<User> users = new ArrayList<User>();
		try {
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM " + dbName + ".user");
			while (rs.next()) {
				int systemId = rs.getInt(1);
				String nationalId = rs.getString(2);
				String name = rs.getString(3);
				String surname = rs.getString(4);
				GregorianCalendar birthday = new GregorianCalendar();
				birthday.setTimeInMillis(Long.parseLong(rs.getString(5)));
				String genderValue = rs.getString(6);
				Gender gender = Gender.OTHER;
				if(genderValue.equalsIgnoreCase("male")) {
					gender = Gender.MALE;
				}
				else if(genderValue.equalsIgnoreCase("female")) {
					gender = Gender.FEMALE;
				}
				String address = rs.getString(7);
				String profileImagePath = rs.getString(8);
				String username = rs.getString(9);
				String password = rs.getString(10);
				boolean isActive = rs.getInt(11) == 1;
				ArrayList<String> phoneNumbers = getUserPhoneNumbers(systemId);
				ArrayList<String> emails = getUserEmails(systemId);
				if(adminIds.contains(systemId)) {
					Admin admin = new Admin(nationalId, name, surname, birthday, gender, address, 
							phoneNumbers, emails, username, password, isActive);
					admin.setSystemId(systemId);
					//admin.setProfileImg(new File(profileImagePath));
					users.add(admin);
				}
				else {
					Statement stms = connection.createStatement();
					ResultSet rss = stms.executeQuery("SELECT * FROM " + dbName + ".student WHERE "
							+ "user_system_id=" + systemId);
					if(rss.next()) {
						Student student = new Student(nationalId, name, surname, birthday, gender, address, 
								phoneNumbers, emails, username, password, isActive, 
								rss.getString(2), rss.getInt(4) == 1, rss.getString(3), rss.getInt(5) == 1, 
								rss.getInt(6));
						student.setSystemId(systemId);
						student.setProfileImg(new File(profileImagePath));
						users.add(student);
					}
					rss.clearWarnings();
					stms.close();
				}
			}
			rs.clearWarnings();
			stm.close(); 
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return users;
	}

	private ArrayList<String> getUserEmails(int system_id) {
		ArrayList<String> emails = new ArrayList<String>();
		try {
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery("SELECT email FROM " +
					dbName + ".user_email WHERE user_system_id='" + 
					system_id + "'");
			while (rs.next()) {
				emails.add(rs.getString(1));
			}
			rs.clearWarnings();
			stm.close(); 
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return emails;
	}

	private ArrayList<String> getUserPhoneNumbers(int system_id) {
		ArrayList<String> phoneNumbers = new ArrayList<String>();
		try {
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery("SELECT phone_number FROM " +
					dbName + ".user_phone_number WHERE user_system_id='" + 
					system_id + "'");
			while (rs.next()) {
				phoneNumbers.add(rs.getString(1));
			}
			rs.clearWarnings();
			stm.close(); 
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return phoneNumbers;
	}
/**
	private ArrayList<Integer> getStudentIds() {
		try {
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery("SELECT user_system_id from " +
					dbName + ".student");
			ArrayList<Integer> ids = new ArrayList<Integer>();
			while (rs.next()) {
				ids.add(rs.getInt(1));
			}
			rs.clearWarnings();
			stm.close();
			return ids;
		}
		catch(Exception ex) {
			
		}
		return null;
	}
*/
	private ArrayList<Integer> getAdminIds() {
		try {
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * from " + dbName + ".admin");
			ArrayList<Integer> ids = new ArrayList<Integer>();
			while (rs.next()) {
				ids.add(rs.getInt(1));
			}
			rs.clearWarnings();
			stm.close();
			return ids;
		}
		catch(Exception ex) {
			
		}
		return null;
	}

	public void addMinorSubject(String subject) { 
		try {
			Statement stm = connection.createStatement();
			stm.executeUpdate("INSERT INTO " + dbName + ".subject "
					+ "(title, minor_subject) "
					+ "VALUES (\"" + subject + "\", \"" 
					+ 1 + "\")");
			stm.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	
	}

	public void addMajorSubject(String subject) { 
		try {
			Statement stm = connection.createStatement();
			stm.executeUpdate("INSERT INTO " + dbName + ".subject "
					+ "(title, minor_subject) "
					+ "VALUES (\"" + subject + "\", \"" 
					+ 0 + "\")");
			stm.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	
	}

	public void deleteSubject(String subject) {
		int id = getSubjectId(subject);
		try {
			PreparedStatement stm = connection.prepareStatement("DELETE FROM " + 
					 dbName + ".subject WHERE subject_id='" + id + "'");
			stm.executeUpdate();
			stm.close();
			deleteSubjectCourses(id);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	private void deleteSubjectCourses(int id) {
		try {
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery("SELECT course_id FROM " + 
					 dbName + ".course WHERE subject_id='" + id + "'");
			while(rs.next()) {
				deleteStudentCourse(rs.getInt(1));
			}
			rs.clearWarnings();
			stm.close();	
			PreparedStatement pstm = connection.prepareStatement("DELETE FROM " + 
					 dbName + ".course WHERE subject_id='" + id + "'");
			pstm.executeUpdate();
			pstm.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	private void deleteStudentCourse(int courseId) {
		try {
			PreparedStatement stm = connection.prepareStatement("DELETE FROM " + 
					 dbName + ".student_course WHERE course_id='" + courseId + "'");
			stm.executeUpdate();
			stm.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public ArrayList<Course> getCourses() {
		ArrayList<Course> list = new ArrayList<>();
		try {
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM " + 
					 dbName + ".course");
			while(rs.next()) {
				Course course = new Course(rs.getString(2), rs.getString(3), 
						getSubjectTitle(rs.getInt(4)), rs.getInt(5), rs.getInt(6));
				course.setSystemId(rs.getInt(1));
				course.setAnnouncements(getAnnouncements(course.getSystemId()));
				course.setRatings(getRatings(course.getSystemId()));
				list.add(course);
			}
			rs.clearWarnings();
			stm.close();		
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return list;
	}
	
	public Course getCourse(int courseId) { 
		try {
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM " + 
					 dbName + ".course WHERE course_id='" + courseId + "'");
			if(rs.next()) {
				Course course = new Course(rs.getString(2), rs.getString(3), 
						getSubjectTitle(rs.getInt(4)), rs.getInt(5), rs.getInt(6));
				course.setSystemId(rs.getInt(1));
				course.setAnnouncements(getAnnouncements(course.getSystemId()));
				course.setRatings(getRatings(course.getSystemId()));
				return course;
			}
			rs.clearWarnings();
			stm.close();		
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	private ArrayList<Rating> getRatings(int courseId) {
		ArrayList<Rating> list = new ArrayList<Rating>(); 
		try {
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM " + 
					 dbName + ".rating WHERE course_id='" + courseId + "'");
			while(rs.next()) {
				GregorianCalendar date = new GregorianCalendar();
				date.setTimeInMillis(Long.parseLong(rs.getString(3)));
				Rating rating = new Rating(getStudent(rs.getInt(2)), date, rs.getString(4), rs.getInt(5));
				rating.setSystemId(rs.getInt(1));
				list.add(rating);
			}
			rs.clearWarnings();
			stm.close();		
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return list;
	}

	private Student getStudent(int studentId) {
		Student student = null;
		System.out.println(studentId);
		try {
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM " + 
					 dbName + ".user WHERE user_system_id='" + studentId + "'");
			if(rs.next()) {
				GregorianCalendar birthday = new GregorianCalendar();
				birthday.setTimeInMillis(Long.parseLong(rs.getString(5)));
				Gender gender = Gender.MALE;
				if(rs.getString(6).equalsIgnoreCase("female")) {
					gender = Gender.FEMALE;
				}
				else if(rs.getString(6).equalsIgnoreCase("other")) {
					gender = Gender.OTHER;
				}
				Statement stms = connection.createStatement();
				ResultSet rss = stms.executeQuery("SELECT * FROM " + dbName + ".student WHERE "
						+ "user_system_id=" + studentId);
				if(rss.next()) { 
				student = new Student(rs.getString(2),
						rs.getString(3), rs.getString(4), 
						birthday, gender, rs.getString(7), 
						getUserPhoneNumbers(studentId), 
						getUserEmails(studentId), 
						rs.getString(9), rs.getString(10), 
						rs.getInt(11) == 1, rss.getString(2), 
						rss.getInt(4) == 1, rss.getString(3), 
						rss.getInt(5) == 1, studentId); 
				}
				rss.clearWarnings();
				stms.close();
				student.setSystemId(studentId);
				student.setProfileImg(new File(rs.getString(8)));
			}
			rs.clearWarnings();
			stm.close();		
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return student;
	}

	private ArrayList<Announcement> getAnnouncements(int courseId) {
		ArrayList<Announcement> list = new ArrayList<Announcement>();
		try {
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM " + 
					 dbName + ".announcement where course_id=" + courseId);
			while(rs.next()) {
				GregorianCalendar date = new GregorianCalendar();
				date.setTimeInMillis(Long.parseLong(rs.getString(2)));
				Announcement announcement = new Announcement(date, rs.getString(3), rs.getString(4));
				announcement.setSystemId(rs.getInt(1));
				list.add(announcement);
			}
			rs.clearWarnings();
			stm.close(); 
		}
		catch(Exception ex) {
			ex.printStackTrace();
		} 	
		return list;
	}

	private String getSubjectTitle(int id) {
		try {
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery("SELECT title FROM " + 
					 dbName + ".subject where subject_id=" + id);
			String title = "";
			if(rs.next()) {
				title = rs.getString(1);
			}
			rs.clearWarnings();
			stm.close();
			return title;
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public void deleteCourse(Course course) {
		try {
			PreparedStatement pstm = connection.prepareStatement("DELETE FROM " + 
					 dbName + ".course WHERE course_id='" + course.getSystemId() + "'");
			System.out.println(pstm.executeUpdate());
			pstm.close();
			pstm = connection.prepareStatement("DELETE FROM " + 
					 dbName + ".student_course WHERE course_id='" + course.getSystemId() + "'");
			System.out.println(pstm.executeUpdate());
			pstm.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public ArrayList<Timetable> getTimetables() {
		ArrayList<Timetable> list = new ArrayList<Timetable>();
		try {
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * from " + dbName + ".timetable");
			while (rs.next()) {
				GregorianCalendar startDate = new GregorianCalendar();
				startDate.setTimeInMillis(Long.parseLong(rs.getString(3)));
				GregorianCalendar endDate = new GregorianCalendar();
				endDate.setTimeInMillis(Long.parseLong(rs.getString(4)));
				Timetable timetable = new Timetable(rs.getString(2), 
						startDate, endDate);
				timetable.setSystemId(rs.getInt(1));
				timetable.setInfo(getTimetableSchedule(timetable.getSystemId()));
				list.add(timetable);
			}
			rs.clearWarnings();
			stm.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	private HashMap<Course, Schedule> getTimetableSchedule(int timetableId) {
		HashMap<Course, Schedule> info = new HashMap<Course, Schedule>();
		try {
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * from " + dbName +
					".schedule WHERE timetable_id='" + timetableId + "'");
			while(rs.next()) {
				info.put(getCourse(rs.getInt(5)), 
						new Schedule(rs.getString(2), 
									 rs.getString(3), 
								     rs.getString(4)));
			}
			rs.clearWarnings();
			stm.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return info;
	}

	public void deleteTimetable(Timetable timetable) {
		try {
			PreparedStatement stm = connection.prepareStatement("DELETE FROM " + 
					 dbName + ".timetable WHERE timetable_id='" + timetable.getSystemId() + "'");
			stm.executeUpdate();
			stm.close();
			deleteScheduleInfo(timetable);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	private void deleteScheduleInfo(Timetable timetable) {
		try {
			PreparedStatement stm = connection.prepareStatement("DELETE FROM " + 
					 dbName + ".schedule WHERE timetable_id='" + timetable.getSystemId() + "'");
			stm.executeUpdate();
			stm.close();
			deleteScheduleInfo(timetable);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}	
	}

	public ArrayList<Course> getAvailableCourses() {
		ArrayList<Course> list = getCourses();
		ArrayList<Course> result = new ArrayList<Course>();
		Student std = (Student)Settings.loggedinUser;
		for(Course course : list) {
			if(std.getMinorSubject().equalsIgnoreCase(course.getSubject()) || 
			   std.getMajorSubject().equalsIgnoreCase(course.getSubject())){
			   result.add(course);
			}
		}
		return result;
	}

	public void addCourseToMyList(String courseTitle) {
		int courseId = getCourseId(courseTitle);
		try {
			Statement stm = connection.createStatement();
			stm.executeUpdate("INSERT INTO " + dbName + ".student_course "
					+ "(user_system_id, course_id) "
					+ "VALUES (\"" + Settings.loggedinUser.getSystemId()
					+ "\", \"" + courseId + "\")");
			stm.close();
		}
		catch(Exception ex) {
			//ex.printStackTrace();
		}
	}
	
	private int getCourseId(String courseTitle) {
		try {
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery("SELECT course_id FROM " + dbName +
					".course WHERE title='" + courseTitle + "'");
			int id = 0;
			if(rs.next()) {
				id = rs.getInt(1);
			}
			rs.clearWarnings();
			stm.close();
			return id;
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	public ArrayList<Student> getStudentsToConnect() {
		ArrayList<Student> list = new ArrayList<Student>();
		try {
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery("SELECT user_system_id FROM " + 
					dbName + ".student WHERE user_system_id<>'" + 
					Settings.loggedinUser.getSystemId()+ "'");
			while(rs.next()) {
				list.add(getStudent(rs.getInt(1)));
			}
			rs.clearWarnings();
			stm.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return list;
	}

	public void addStudentToConnections(Student student) {
		try {
			Statement stm = connection.createStatement();
			stm.executeUpdate("INSERT INTO " + dbName + ".connected_students "
					+ "(user_system_id, connected_student_user_system_id) "
					+ "VALUES (\"" + Settings.loggedinUser.getSystemId() + "\"" + 
							  ", \"" + student.getSystemId() + "\")");
			stm.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public ArrayList<Course> getMyCourses() {
		ArrayList<Course> list = new ArrayList<>();
		int studentId = Settings.loggedinUser.getSystemId();
		try {
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery("SELECT course_id FROM " + dbName +
					".student_course WHERE user_system_id='" + studentId + "'");
			while(rs.next()) {
				list.add(getCourse(rs.getInt(1)));
			}
			rs.clearWarnings();
			stm.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	public ArrayList<Student> getMyConnections() {
		ArrayList<Student> list = new ArrayList<Student>();
		try {
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM " + 
					 dbName + ".connected_students"
					 		+ " WHERE user_system_id='" + Settings.loggedinUser.getSystemId() + "'"
					 				+ "OR connected_student_user_system_id='" + Settings.loggedinUser.getSystemId() + "'");
			while(rs.next()) {
				list.add(getStudent(Settings.loggedinUser.getSystemId() != rs.getInt(1)? 
							rs.getInt(1) : rs.getInt(2)));
			}
			rs.clearWarnings();
			stm.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	public void removeStudentConnection(Student student) {
		try {
			PreparedStatement stm = connection.prepareStatement("DELETE FROM " + 
					 dbName + ".connected_students WHERE (user_system_id='" + 
					 Settings.loggedinUser.getSystemId() + "' AND connected_student_system_id='"
					 		+ student.getSystemId() + "') OR (user_system_id='" +
					 		+ student.getSystemId() + "' AND connected_student_system_id='"
					 		+ Settings.loggedinUser.getSystemId() + "')" );
			stm.executeUpdate();
			stm.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public ArrayList<Course> getConnectionsCourses() {
		ArrayList<Course> list = new ArrayList<>();
		ArrayList<Student> connections = getMyConnections();
		for(Student student : connections) {
			for(Course course : student.getCoursesList()) {
				if(!list.contains(course)) {
					list.add(course);
				}
			}
		}
		return list;
	}

	public void deleteCourseFromMyList(Course course) {
		try {
			PreparedStatement stm = connection.prepareStatement("DELETE FROM " + 
					 dbName + ".student_course WHERE user_system_id='" + 
					 Settings.loggedinUser.getSystemId() + "'");
			stm.executeUpdate();
			stm.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void deletePhoneNumbers() {
		try {
			PreparedStatement stm = connection.prepareStatement("DELETE FROM " + 
					 dbName + ".user_phone_number WHERE user_system_id='" + 
					 Settings.loggedinUser.getSystemId() + "'");
			stm.executeUpdate();
			stm.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public void deleteEmails() {
		try {
			PreparedStatement stm = connection.prepareStatement("DELETE FROM " + 
					 dbName + ".user_email WHERE user_system_id='" + 
					 Settings.loggedinUser.getSystemId() + "'");
			stm.executeUpdate();
			stm.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public void addPhoneNumbers() {
		ArrayList<String> phoneNumbers = new ArrayList<String>();
		for (int j = 0; j < Settings.loggedinUser.getPhoneNumbers(); j++) {
			phoneNumbers.add(Settings.loggedinUser.getPhoneNumber(j));
		}
		addPhoneNumbers(Settings.loggedinUser.getSystemId(), phoneNumbers);
	}

	public void addEmails() {
		ArrayList<String> emails = new ArrayList<String>();
		for (int j = 0; j < Settings.loggedinUser.getEmails(); j++) {
			emails.add(Settings.loggedinUser.getEmail(j));
		}
		addUserEmails(Settings.loggedinUser.getSystemId(), emails);
	}

	public void updateNationalId() {
		try {
			Statement stm = connection.createStatement();
			stm.executeUpdate("UPDATE " + dbName + ".user "
					+ "SET national_id='" + Settings.loggedinUser.getNationalId() + "'"
							+ "WHERE user_system_id=" + Settings.loggedinUser.getSystemId());
			 stm.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public void updateAddress() {
		try {
			Statement stm = connection.createStatement();
			stm.executeUpdate("UPDATE " + dbName + ".user "
					+ "SET address='" + Settings.loggedinUser.getAddress() + "'"
							+ "WHERE user_system_id=" + Settings.loggedinUser.getSystemId());
			 stm.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public void updateBirthday() {
		try {
			Statement stm = connection.createStatement();
			stm.executeUpdate("UPDATE " + dbName + ".user "
					+ "SET birthday='" + Settings.loggedinUser.getBirthday().getTimeInMillis() + "'"
							+ "WHERE user_system_id=" + Settings.loggedinUser.getSystemId());
			 stm.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public void updateSurname() {
		try {
			Statement stm = connection.createStatement();
			stm.executeUpdate("UPDATE " + dbName + ".user "
					+ "SET surname='" + Settings.loggedinUser.getSurname() + "'"
							+ "WHERE user_system_id=" + Settings.loggedinUser.getSystemId());
			 stm.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public void updateName() {
		try {
			Statement stm = connection.createStatement();
			stm.executeUpdate("UPDATE " + dbName + ".user "
					+ "SET name='" + Settings.loggedinUser.getName() + "'"
							+ "WHERE user_system_id=" + Settings.loggedinUser.getSystemId());
			 stm.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public void changeSystemTitle() {
		try { 
			Statement stm = connection.createStatement();
			stm.executeUpdate("UPDATE " + dbName + ".system_settings "
							+ "SET system_title='" + Settings.SYSTEM_TITLE + "'"
							+ "WHERE system_id=1");
			stm.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void changeSystemEmail() {
		try { 
			Statement stm = connection.createStatement();
			stm.executeUpdate("UPDATE " + dbName + ".system_settings "
							+ "SET system_email='" + Settings.SYSTEM_EMAIL + "'"
							+ "WHERE system_id=1");
			stm.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void changeSystemEmailPassword() {
		try { 
			Statement stm = connection.createStatement();
			stm.executeUpdate("UPDATE " + dbName + ".system_settings "
							+ "SET system_email_password='" + Settings.SYSTEM_EMAIL_PASSWORD + "'"
							+ "WHERE system_id=1");
			stm.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public String getSystemTitle() {
		try {
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery("SELECT system_title FROM " + 
					 dbName + ".system_settings WHERE system_id=1");
			rs.next();
			String system_title = rs.getString(1);
			rs.clearWarnings();
			stm.close();
			return system_title;
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public String getSystemEmail() {
		try {
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery("SELECT system_email FROM " + 
					 dbName + ".system_settings WHERE system_id=1");
			rs.next();
			String system_title = rs.getString(1);
			rs.clearWarnings();
			stm.close();
			return system_title;
		
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public String getSystemEmailPassword() {
		try {
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery("SELECT system_email_password FROM " + 
					 dbName + ".system_settings WHERE system_id=1");
			rs.next();
			String system_title = rs.getString(1);
			rs.clearWarnings();
			stm.close();
			return system_title;
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public void addStudent(Student student) {
		try {
			addUser(student);
			int user_id = lastTableId("user_system_id", "user");
			Statement stm = connection.createStatement();
			stm.executeUpdate("INSERT INTO " + dbName + ".student "
					+ "(user_system_id, minor_subject, major_subject, "
					+ "minor_subject_gratuated, major_subject_gratuated, "
					+ "current_semester_number) "
					+ "VALUES (\"" + user_id + 
							  "\", \"" + student.getMinorSubject() + "\"" + 
							  ", \"" + student.getMajorSubject() + "\"" + 
							  ", \"" + ((student.isMinorDegreeGraduated() == true)? 1 : 0) + "\"" + 
							  ", \"" + ((student.isMajorDegreeGraduated() == true)? 1 : 0) + "\"" + 
							  ", \"" + student.getCurrentSemesterNumber() + "\")");
			stm.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	private void addUser(User user) {
		try {
			Statement stm = connection.createStatement();
			stm.executeUpdate("INSERT INTO " + dbName + ".user "
					+ "(national_id, name, surname, birthday, "
					+ "gender, address, profile_image_path, username, password, active) "
					+ "VALUES (\"" + user.getNationalId() + "\"" + 
							  ", \"" + user.getName() + "\"" + 
							  ", \"" + user.getSurname() + "\"" + 
							  ", \"" + user.getBirthday().getTimeInMillis() + "\"" + 
							  ", \"" + user.getGender() + "\"" + 
							  ", \"" + user.getAddress() + "\"" + 
							  ", \"" + user.getProfileImg() + "\"" + 
							  ", \"" + user.getUsername() + "\"" + 
							  ", \"" + user.getPassword() + "\"" + 
							  ", \"" + ((user.isActive() == true)? 1 : 0) + "\")");
			int user_id = lastTableId("user_system_id", "user");
			user.setSystemId(user_id);
			ArrayList<String> emails = new ArrayList<String>();
			for (int j = 0; j < user.getEmails(); j++) {
				emails.add(user.getEmail(j));
			}
			addUserEmails(user.getSystemId(), emails);
			ArrayList<String> phoneNumbers = new ArrayList<String>();
			for (int j = 0; j < user.getPhoneNumbers(); j++) {
				phoneNumbers.add(user.getPhoneNumber(j));
			}
			addPhoneNumbers(user.getSystemId(), phoneNumbers);
			stm.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	private void addPhoneNumbers(int systemId, ArrayList<String> phoneNumbers) {
		for(String phoneNumber : phoneNumbers) {
			try {
				Statement stm = connection.createStatement();
				stm.executeUpdate("INSERT INTO " + dbName + ".user_phone_number "
						+ "(user_system_id, phone_number) "
						+ "VALUES (\"" + systemId + "\"" + 
								  ", \"" + phoneNumber + "\")");
				stm.close();
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	private void addUserEmails(int systemId, ArrayList<String> emails) {
		for(String email : emails) {
			try {
				Statement stm = connection.createStatement();
				stm.executeUpdate("INSERT INTO " + dbName + ".user_email "
						+ "(user_system_id, email) "
						+ "VALUES (\"" + systemId + "\"" + 
								  ", \"" + email + "\")");
				stm.close();
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public void addAdmin(Admin admin) {
		try {
			addUser(admin);
			int user_id = lastTableId("user_system_id", "user");
			Statement stm = connection.createStatement();
			stm.executeUpdate("INSERT INTO " + dbName + ".admin "
					+ "(user_system_id) "
					+ "VALUES (\"" + user_id + "\")");
			stm.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	private int lastTableId(String idAttribut, String table) { 
		try {
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery("SELECT " + idAttribut + " FROM " + 
					 dbName + "." + table + " ORDER BY " + idAttribut + " DESC LIMIT 1");
			rs.next();
			int id = rs.getInt(1);
			rs.clearWarnings();
			stm.close();
			return id; 
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	public void addCourse(Course course) { 
		try {
			Statement stm = connection.createStatement();
			stm.executeUpdate("INSERT INTO " + dbName + ".course "
					+ "(title, description, subject_id, number_of_credits, semester_number) "
					+ "VALUES (\"" + course.getTitle() + "\"" + 
							  ", \"" + course.getDescription() + "\"" + 
							  ", \"" + getSubjectId(course.getSubject()) + "\"" + 
							  ", \"" + course.getNumberOfCredits() + "\"" + 
							  ", \"" + course.getSemesterNumber() + "\")");
			stm.close();
		
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public int getSubjectId(String subject) {
		try {
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery("SELECT subject_id FROM " + 
					 dbName + ".subject WHERE title='" + subject + "'");
			int id = 0;
			if(rs.next()) {
				id = rs.getInt(1);
			}
			rs.clearWarnings();
			stm.close();
			return id; 
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}
	
	public void deleteUser(User user) {
		try {
			PreparedStatement stm = connection.prepareStatement("DELETE FROM " + 
					 dbName + ".user WHERE user_system_id='" + user.getSystemId() + "'");
			stm.executeUpdate();
			stm.close();
			deleteUserEmails(user);
			deleteUserPhoneNumbers(user);
			if(user instanceof Admin) {
				deleteAdmin((Admin)user);
			}
			else {
				deleteStudent((Student)user);
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	private void deleteUserPhoneNumbers(User user) {
		try {
			PreparedStatement stm = connection.prepareStatement("DELETE FROM " + 
					 dbName + ".user_phone_number WHERE user_system_id='" + user.getSystemId() + "'");
			stm.executeUpdate();
			stm.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	private void deleteUserEmails(User user) {
		try {
			PreparedStatement stm = connection.prepareStatement("DELETE FROM " + 
					 dbName + ".user_email WHERE user_system_id='" + user.getSystemId() + "'");
			stm.executeUpdate();
			stm.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	private void deleteAdmin(Admin admin) {
		try {
			PreparedStatement stm = connection.prepareStatement("DELETE FROM " + 
					 dbName + ".admin WHERE user_system_id='" + 
					admin.getSystemId() + "'");
			stm.executeUpdate();
			stm.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	private void deleteStudent(Student std) {
		try {
			PreparedStatement stm = connection.prepareStatement("DELETE FROM " + 
					 dbName + ".student WHERE user_system_id='" + 
					std.getSystemId() + "'");
			deleteStudentConnections(std);
			deleteStudentCourse(std);
			stm.executeUpdate();
			stm.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	private void deleteStudentCourse(Student std) {
		try {
			PreparedStatement stm = connection.prepareStatement("DELETE FROM " + 
					 dbName + ".student_course WHERE user_system_id='" + std.getSystemId() + "'");
			stm.executeUpdate();
			stm.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	private void deleteStudentConnections(Student std) {
		try {
			PreparedStatement stm = connection.prepareStatement("DELETE FROM " + 
					 dbName + ".connected_students WHERE user_system_id='" + 
					std.getSystemId() + "' OR connected_student_user_system_id='" + 
					std.getSystemId() + "'");
			deleteStudentConnections(std);
			stm.executeUpdate();
			stm.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public void addTimetable(Timetable timetable) { 
		try {
			Statement stm = connection.createStatement();
			stm.executeUpdate("INSERT INTO " + dbName + ".timetable "
					+ "(title, start_date, end_date) "
					+ "VALUES (\"" + timetable.getTitle() + "\"" + 
							  ", \"" + timetable.getStartDate().getTimeInMillis() + "\"" + 
							  ", \"" + timetable.getEndDate().getTimeInMillis() + "\")");
			timetable.setSystemId(lastTableId("timetable_id", "timetable"));
			addScheduleInfo(timetable);
			stm.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	private void addScheduleInfo(Timetable timetable) { 
		try {
			for(Course course : timetable.getInfo().keySet()) {
				Statement stm = connection.createStatement();
				stm.executeUpdate("INSERT INTO " + dbName + ".schedule "
						+ "(timetable_id, course_id, day_of_week, time_period, location) "
						+ "VALUES (\"" + timetable.getSystemId() + "\"" + 
								  ", \"" + course.getSystemId() + "\"" + 
								  ", \"" + timetable.getInfo().get(course).getDayOfWeek() + "\"" + 
								  ", \"" + timetable.getInfo().get(course).getTimePeriod() + "\"" + 
								  ", \"" + timetable.getInfo().get(course).getLocation() + "\")");
				 stm.close();
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public Timetable getCurrentTimetable() {
		return getTimetables().get(0);
	}

	public void addRatingToCourse(Rating rating, Course course) {
		try {
			Statement stm = connection.createStatement();
			stm.executeUpdate("INSERT INTO " + dbName + ".rating "
					+ "(student_id, course_id, date, comment, value) "
					+ "VALUES (\"" + Settings.loggedinUser.getSystemId() + "\"" + 
							  ", \"" + course.getSystemId() + "\"" + 
							  ", \"" + rating.getDate().getTimeInMillis() + "\"" + 
							  ", \"" + rating.getComment() + "\"" + 
							  ", \"" + rating.getRatingValue() + "\")");
			 stm.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public void updatePassword() {
		try {
			Statement stm = connection.createStatement();
			stm.executeUpdate("UPDATE " + dbName + ".user "
					+ "SET password='" + Settings.loggedinUser.getPassword() + "'"
							+ "WHERE user_system_id=" + Settings.loggedinUser.getSystemId());
			 stm.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}