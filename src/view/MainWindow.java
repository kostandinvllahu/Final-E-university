package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controll.Settings;
import data.DBHandler;
import model.Admin;
import model.Course;
import model.Schedule;
import model.Student;
import model.Timetable;
import util.Util;
import view.gui.ButtonRenderer;
import view.gui.StudentButton;
import view.gui.StudentButtonEditor;
import view.gui.UserButton;
import view.gui.UserButtonEditor;

public class MainWindow extends Window {

	private static final long serialVersionUID = -5433548214205850844L;

	public MainWindow() {

		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);
		
		JMenu menuMenu = new JMenu("Menu");
		menuMenu.setBorder(BorderFactory.createMatteBorder(0, 7, 0, 7, menubar.getBackground()));
		JMenu systemSettingsMenu = new JMenu("System");
		systemSettingsMenu.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 7, menubar.getBackground()));
		JMenu usersSettingsMenu = new JMenu("User");
		usersSettingsMenu.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 7, menubar.getBackground()));
		JMenu subjectsSettingsMenu = new JMenu("Subject");
		subjectsSettingsMenu.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 7, menubar.getBackground()));
		JMenu minorSubjectsSettingsMenu = new JMenu("Minor");
		minorSubjectsSettingsMenu.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 7, menubar.getBackground()));
		JMenu majorSubjectsSettingsMenu = new JMenu("Major");
		majorSubjectsSettingsMenu.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 7, menubar.getBackground()));
		JMenu courseSettingsMenu = new JMenu("Course");
		courseSettingsMenu.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 7, menubar.getBackground()));
		JMenu timetableSettingsMenu = new JMenu("Timetable");
		timetableSettingsMenu.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 7, menubar.getBackground()));
		JMenu profileMenu = new JMenu("Profile");
		profileMenu.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 7, menubar.getBackground()));
		JMenu profileAddMenu = new JMenu("Add");
		profileAddMenu.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 7, menubar.getBackground()));
		JMenu profileViewMenu = new JMenu("View");
		profileViewMenu.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 7, menubar.getBackground()));
		JMenu profileViewConnectionsMenu = new JMenu("Connections");
		profileViewConnectionsMenu.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 7, menubar.getBackground()));
		JMenu profileViewCoursesMenu = new JMenu("Courses");
		profileViewCoursesMenu.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 7, menubar.getBackground()));
		JMenu profileViewAnnouncementsMenu = new JMenu("Announcements");
		profileViewAnnouncementsMenu.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 7, menubar.getBackground()));
		JMenu profileEditMenu = new JMenu("Edit");
		profileEditMenu.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 7, menubar.getBackground())); 

		JMenuItem titleSystemSettingsMenuItem = new JMenuItem("Title");
		JMenuItem emailSystemSettingsMenuItem = new JMenuItem("Email");
		JMenuItem addUsersSettingsMenuItem = new JMenuItem("Add");
		JMenuItem listUsersSettingsMenuItem = new JMenuItem("List");
		JMenuItem addMinorSubjectsSettingsMenuItem = new JMenuItem("Add");
		JMenuItem listMinorSubjectsSettingsMenuItem = new JMenuItem("List");
		JMenuItem addMajorSubjectsSettingsMenuItem = new JMenuItem("Add");
		JMenuItem listMajorSubjectsSettingsMenuItem = new JMenuItem("List");
		JMenuItem addCourseSettingsMenuItem = new JMenuItem("Add");
		JMenuItem listCourseSettingsMenuItem = new JMenuItem("List");
		JMenuItem addTimetableMenuItem = new JMenuItem("Add");
		JMenuItem listTimetableMenuItem = new JMenuItem("List");
		JMenuItem logoutMenuItem = new JMenuItem("Logout");
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		
		JMenuItem courseAddProfileMenuItem = new JMenuItem("Course");
		JMenuItem connectionAddProfileMenuItem = new JMenuItem("Connection");
		JMenuItem ratingAddProfileMenuItem = new JMenuItem("Rating"); 
		JMenuItem listConnectionsViewProfileMenuItem = new JMenuItem("List");
		JMenuItem coursesConnectionsViewProfileMenuItem = new JMenuItem("Courses");
		JMenuItem listCoursesProfileMenuItem = new JMenuItem("List");
		JMenuItem timetableCoursesViewProfileMenuItem = new JMenuItem("Timetable");
		JMenuItem allAnnouncementsCoursesViewProfileMenuItem = new JMenuItem("Announcements"); 
		JMenuItem changePasswordEditProfileMenuItem = new JMenuItem("Change Password");
		JMenuItem personalDataEditProfileMenuItem = new JMenuItem("Personal Data");
		 
		menubar.add(menuMenu);
		menubar.add(profileMenu); 
		
		if(Settings.loggedinUser instanceof Admin) { 
			menubar.add(systemSettingsMenu);
			menubar.add(usersSettingsMenu);
			menubar.add(subjectsSettingsMenu);
			menubar.add(courseSettingsMenu);
			menubar.add(timetableSettingsMenu); 
		}
		menuMenu.add(logoutMenuItem);
		menuMenu.add(exitMenuItem);
		
		systemSettingsMenu.add(titleSystemSettingsMenuItem);
		systemSettingsMenu.add(emailSystemSettingsMenuItem);
		
		usersSettingsMenu.add(addUsersSettingsMenuItem);
		usersSettingsMenu.add(listUsersSettingsMenuItem);
		
		subjectsSettingsMenu.add(minorSubjectsSettingsMenu);
		subjectsSettingsMenu.add(majorSubjectsSettingsMenu);
		
		minorSubjectsSettingsMenu.add(addMinorSubjectsSettingsMenuItem);
		minorSubjectsSettingsMenu.add(listMinorSubjectsSettingsMenuItem);
		
		majorSubjectsSettingsMenu.add(addMajorSubjectsSettingsMenuItem);
		majorSubjectsSettingsMenu.add(listMajorSubjectsSettingsMenuItem);
		
		courseSettingsMenu.add(addCourseSettingsMenuItem);
		courseSettingsMenu.add(listCourseSettingsMenuItem);
		
		timetableSettingsMenu.add(addTimetableMenuItem);
		timetableSettingsMenu.add(listTimetableMenuItem);

		if(Settings.loggedinUser instanceof Student) {
			menubar.add(profileAddMenu);
			menubar.add(profileViewMenu);
			profileAddMenu.add(courseAddProfileMenuItem);
			profileAddMenu.add(connectionAddProfileMenuItem);
			profileAddMenu.add(ratingAddProfileMenuItem); 
		}
		profileMenu.add(profileEditMenu);

		profileViewMenu.add(profileViewConnectionsMenu); 
		profileViewMenu.add(profileViewCoursesMenu);

		profileViewConnectionsMenu.add(listConnectionsViewProfileMenuItem);
		profileViewConnectionsMenu.add(coursesConnectionsViewProfileMenuItem);

		profileViewCoursesMenu.add(listCoursesProfileMenuItem);
		profileViewCoursesMenu.add(timetableCoursesViewProfileMenuItem);
		profileViewCoursesMenu.add(allAnnouncementsCoursesViewProfileMenuItem);

		profileEditMenu.add(changePasswordEditProfileMenuItem);
		profileEditMenu.add(personalDataEditProfileMenuItem);
		 
		titleSystemSettingsMenuItem.addActionListener((e) -> {
			String title = JOptionPane.showInputDialog("System title");
			if (title == null || title.length() == 0) {
				return;
			}
			Settings.SYSTEM_TITLE = title;
			DBHandler.db.changeSystemTitle();
			setTitle(title);
			JOptionPane.showMessageDialog(null, "System title changed successfully!");
		});
		
		emailSystemSettingsMenuItem.addActionListener((e)->{ 
			try {
				String email = JOptionPane.showInputDialog("Insert gmail account").toString();
				String password = JOptionPane.showInputDialog("Insert gmail password").toString();
				Settings.SYSTEM_EMAIL = email;
				Settings.SYSTEM_EMAIL_PASSWORD = password;
				JOptionPane.showMessageDialog(null, "Success!", "Success", JOptionPane.INFORMATION_MESSAGE); 
			} catch (Exception ex) {
			}
		});

		addUsersSettingsMenuItem.addActionListener((e) -> {
			new AddUserWindow().setVisible(true);
		});
 
		listUsersSettingsMenuItem.addActionListener((e) -> {
			try {
				new ListUsersWindow().setVisible(true);
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		});
		
		addMinorSubjectsSettingsMenuItem.addActionListener((e)->{
			String subject = JOptionPane.showInputDialog("Insert minor subject title");
			if (subject == null || subject.length() == 0) {
				return;
			}
			if(!Util.contains(DBHandler.db.getMinorSubjects(), subject)) {
				DBHandler.db.addMinorSubject(subject);
				JOptionPane.showMessageDialog(null, "Success!");
			}
			else {
				JOptionPane.showMessageDialog(null, "Duplicated value! This subjects exists.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		});
		
		addMajorSubjectsSettingsMenuItem.addActionListener((e)->{
			String subject = JOptionPane.showInputDialog("Insert major subject title");
			if (subject == null || subject.length() == 0) {
				return;
			}
			if(!Util.contains(DBHandler.db.getMinorSubjects(), subject)) {
				DBHandler.db.addMajorSubject(subject);
				JOptionPane.showMessageDialog(null, "Success!");
			}
			else {
				JOptionPane.showMessageDialog(null, "Duplicated value! This subjects exists.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		});

		listMinorSubjectsSettingsMenuItem.addActionListener((e)->{
			try {
				new ListMinorSubjectsWindow().setVisible(true);
			}
			catch(Exception ex) {
			}
		});

		listMajorSubjectsSettingsMenuItem.addActionListener((e) -> {
			try {
				new ListMajorSubjectsWindow().setVisible(true);
			} catch (Exception ex) {
			}
		});
		
		addCourseSettingsMenuItem.addActionListener((e)->{
			new AddCourseWindow().setVisible(true);
		});
		
		listCourseSettingsMenuItem.addActionListener((e)->{
			try {
				new ListCoursesWindow().setVisible(true);
			} catch (Exception ex) {
			}
		});
		
		addTimetableMenuItem.addActionListener((e)->{
			new AddTimetableWindow().setVisible(true);
		});
		
		listTimetableMenuItem.addActionListener((e)->{
			new ListTimetablesWindow().setVisible(true);
		});
		
		courseAddProfileMenuItem.addActionListener((e)->{
			try {
				new AddCourseToListWindow().setVisible(true);
			}
			catch(Exception ex) {
			}
		});
		
		connectionAddProfileMenuItem.addActionListener((e)->{
			try {
				new ListStudentsToConnectWindow().setVisible(true);
			}
			catch(Exception ex) {
			}
		});
		
		ratingAddProfileMenuItem.addActionListener((e)->{
			new AddCourseRatingWindow().setVisible(true);
		});
		
		listConnectionsViewProfileMenuItem.addActionListener((e)->{
			new ListConnectionsWindow().setVisible(true);
		});
		
		coursesConnectionsViewProfileMenuItem.addActionListener((e)->{
			new ListConnectionsCoursesWindow().setVisible(true);
		});

		listCoursesProfileMenuItem.addActionListener((e)->{
			new ListMyCoursesWindow().setVisible(true);
		});

		timetableCoursesViewProfileMenuItem.addActionListener((e)->{
			new ViewCoursesScheduleWindow().setVisible(true);
		});

		allAnnouncementsCoursesViewProfileMenuItem.addActionListener((e)->{
			new ListAnnouncementsWindow().setVisible(true);
		});

		changePasswordEditProfileMenuItem.addActionListener((e) -> { 
			new ChangePasswordWindow().setVisible(true);
		});

		personalDataEditProfileMenuItem.addActionListener((e)->{
			new EditPersonalDataWindow().setVisible(true);
		});
		
		logoutMenuItem.addActionListener((e) -> {
			setVisible(false);
			Settings.loggedinUser = null;
			new LoginWindow().setVisible(true);
		});

		exitMenuItem.addActionListener((e) -> {
			System.exit(0);
		});
		 
		JPanel mainPanel = new JPanel(new GridLayout(2, 1, 10, 10));
		mainPanel.setBackground(Settings.COLOR1);
		 
		JPanel coursesScheduleMainPanel = new JPanel(new BorderLayout(10, 10));
		coursesScheduleMainPanel.setBackground(Settings.COLOR1);
		coursesScheduleMainPanel.setBorder(new LineBorder(Settings.COLOR1, 10));
		
		JPanel coursesScheduleNorthPanel = new JPanel(new GridLayout(0, 2, 10, 10));
		coursesScheduleNorthPanel.setBackground(Settings.COLOR1);

		coursesScheduleNorthPanel.add(new JLabel("Timetable Title"));
		
		Timetable currentTimetable = DBHandler.db.getCurrentTimetable();
		
		JTextField timetableTitleTextField = new JTextField(currentTimetable.getTitle());
		timetableTitleTextField.setEditable(false);
		coursesScheduleNorthPanel.add(timetableTitleTextField);

		coursesScheduleNorthPanel.add(new JLabel("Timetable Start Date"));
		
		JTextField timeCoursesScheduleTableStartDateTextField = new JTextField(Util.dateToString(currentTimetable.getStartDate()));
		timeCoursesScheduleTableStartDateTextField.setEditable(false);
		coursesScheduleNorthPanel.add(timeCoursesScheduleTableStartDateTextField);
		
		coursesScheduleNorthPanel.add(new JLabel("Timetable End Date"));
		
		JTextField timetableEndDateTextField = new JTextField(Util.dateToString(currentTimetable.getEndDate()));
		timetableEndDateTextField.setEditable(false);
		coursesScheduleNorthPanel.add(timetableEndDateTextField);

		coursesScheduleMainPanel.add(coursesScheduleNorthPanel, BorderLayout.NORTH);
		
		HashMap<Course, Schedule> coursesScheduleInfo = currentTimetable.getInfo();

		Object[][] data = new Object[coursesScheduleInfo.size()][5];
	
		int i = 0;
		
		for(Course course : coursesScheduleInfo.keySet()) {
			Schedule schedule = coursesScheduleInfo.get(course);
			data[i][0] = " " + (i + 1);
			data[i][1] = " " + course.getTitle();
			data[i][2] = " " + schedule.getDayOfWeek();
			data[i][3] = " " + schedule.getTimePeriod();
			data[i][4] = " " + schedule.getLocation();
			i++;
		}

		Object[] coursesScheduleTableHeader = new Object[] {"#", "Course Title", 
				"Day of Week", "Time Period", "Location"};

		DefaultTableModel model = new DefaultTableModel();
		model.setDataVector(data, coursesScheduleTableHeader);

		JTable coursesScheduleTable = new JTable(model);
		
		JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
		centerPanel.setBackground(Settings.COLOR1);

		JScrollPane coursesScheduleTableScrollPane = new JScrollPane(coursesScheduleTable);

		centerPanel.add(coursesScheduleTableScrollPane);
		
		coursesScheduleMainPanel.add(centerPanel);
		
		coursesScheduleTable.getColumnModel().getColumn(0).setPreferredWidth(50); 
		coursesScheduleTable.getColumnModel().getColumn(1).setPreferredWidth(250);
		coursesScheduleTable.getColumnModel().getColumn(2).setPreferredWidth(200);
		coursesScheduleTable.getColumnModel().getColumn(3).setPreferredWidth(200); 
		coursesScheduleTable.getColumnModel().getColumn(4).setPreferredWidth(200);
		
		coursesScheduleTable.setRowHeight(25);
		
		coursesScheduleTable.getTableHeader().setFont(new Font(coursesScheduleTable.getTableHeader().getFont().getFontName(), Font.BOLD, 12));

		mainPanel.add(coursesScheduleMainPanel);
		
		ArrayList<Student> usersList = DBHandler.db.getStudentsToConnect();
		
		Object[][] usersListData = new Object[usersList.size()][8];

		i = 0;
		for (Student student : usersList) {
			usersListData[i][0] = " " + (i + 1);
			usersListData[i][1] = " " + student.getName();
			usersListData[i][2] = " " + student.getSurname();
			usersListData[i][3] = " " + student.getUsername();
			usersListData[i][4] = " " + ((student.isMinorDegreeGraduated() == true) ? 
									student.getMajorSubject() : 
									student.getMinorSubject());
			usersListData[i][5] = " " + student.getCurrentSemesterNumber();
			usersListData[i][6] = new UserButton("", student) {
				private static final long serialVersionUID = -6252861590543651403L;
				@Override
				public String toString() {
					return "View";
				}
			};
			usersListData[i][7] = new StudentButton(" ", student) {
				private static final long serialVersionUID = 4117497482580748439L;
				@Override
				public String toString() {
					return "Connect";
				}
			};
			i++;
		}


		Object[] usersTableHeader = new Object[] {"#", "Name", "Surname",
				"Username", "Subject", "Semester", "", " "};

		DefaultTableModel usersModel = new DefaultTableModel();
		usersModel.setDataVector(usersListData, usersTableHeader);

		JTable usersListTable = new JTable(usersModel);
		
		usersListTable.getColumn("").setCellRenderer(new ButtonRenderer());
		usersListTable.getColumn("").setCellEditor(new UserButtonEditor(new JCheckBox()) {
			private static final long serialVersionUID = 1091385591443218428L;
			@Override
			public Object getCellEditorValue() {
				if (isPushed) {
					new ViewUserWindow(((UserButton) button).getUser()).setVisible(true);
				}
				isPushed = false;
				return new String(label);
			}
		});
		usersListTable.getColumn(" ").setCellRenderer(new ButtonRenderer());
		usersListTable.getColumn(" ").setCellEditor(new StudentButtonEditor(new JCheckBox()) {
			private static final long serialVersionUID = -1432410327572748479L;
			@Override
			public Object getCellEditorValue() {
				if (isPushed) {
					DBHandler.db.addStudentToConnections(((StudentButton) button).getStudent());
					setVisible(false);
					new MainWindow().setVisible(true);
				}
				isPushed = false;
				return new String(label);
			}
		});
   
		usersListTable.getColumnModel().getColumn(0).setPreferredWidth(50); 
		usersListTable.getColumnModel().getColumn(1).setPreferredWidth(150);
		usersListTable.getColumnModel().getColumn(2).setPreferredWidth(150);
		usersListTable.getColumnModel().getColumn(3).setPreferredWidth(150); 
		usersListTable.getColumnModel().getColumn(4).setPreferredWidth(300);
		usersListTable.getColumnModel().getColumn(5).setPreferredWidth(100);
		usersListTable.getColumnModel().getColumn(6).setPreferredWidth(100);
		usersListTable.getColumnModel().getColumn(7).setPreferredWidth(100);
		
		usersListTable.setRowHeight(25);
		
		usersListTable.getTableHeader().setFont(new Font(usersListTable.getTableHeader().getFont().getFontName(), Font.BOLD, 12));
		
		JScrollPane scrollPane = new JScrollPane(usersListTable);
		
		JPanel usersListMainPanel = new JPanel(new BorderLayout(10, 10));
		usersListMainPanel.setBackground(Settings.COLOR1);
		usersListMainPanel.setBorder(new LineBorder(Settings.COLOR1, 10));
		usersListMainPanel.add(scrollPane);
		
		mainPanel.add(usersListMainPanel);

		usersListMainPanel.setBorder(new TitledBorder("Students to connect"));
		
		add(mainPanel);
	
		setMinimumSize(new Dimension(960, 600));
		if (Toolkit.getDefaultToolkit().isFrameStateSupported(MAXIMIZED_BOTH)) {
			setExtendedState(MAXIMIZED_BOTH);
		}
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

}
