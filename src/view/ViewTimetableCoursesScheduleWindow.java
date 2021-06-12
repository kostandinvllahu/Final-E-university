package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controll.Settings;
import model.Course;
import model.Schedule;
import model.Timetable;
import util.Util;

public class ViewTimetableCoursesScheduleWindow extends Window {

	public ViewTimetableCoursesScheduleWindow(Timetable timetable) {
		
		setTitle(Settings.SYSTEM_TITLE + " - Timetable");
		
		JPanel mainPanel = new JPanel(new GridLayout(1, 1, 10, 10));
		mainPanel.setBackground(Settings.COLOR1);
		 
		JPanel coursesScheduleMainPanel = new JPanel(new BorderLayout(10, 10));
		coursesScheduleMainPanel.setBackground(Settings.COLOR1);
		coursesScheduleMainPanel.setBorder(new LineBorder(Settings.COLOR1, 10));
		
		JPanel coursesScheduleNorthPanel = new JPanel(new GridLayout(0, 2, 10, 10));
		coursesScheduleNorthPanel.setBackground(Settings.COLOR1);

		coursesScheduleNorthPanel.add(new JLabel("Timetable Title"));
		
		JTextField timetableTitleTextField = new JTextField(timetable.getTitle());
		timetableTitleTextField.setEditable(false);
		coursesScheduleNorthPanel.add(timetableTitleTextField);

		coursesScheduleNorthPanel.add(new JLabel("Timetable Start Date"));
		
		JTextField timeCoursesScheduleTableStartDateTextField = new JTextField(Util.dateToString(timetable.getStartDate()));
		timeCoursesScheduleTableStartDateTextField.setEditable(false);
		coursesScheduleNorthPanel.add(timeCoursesScheduleTableStartDateTextField);
		
		coursesScheduleNorthPanel.add(new JLabel("Timetable End Date"));
		
		JTextField timetableEndDateTextField = new JTextField(Util.dateToString(timetable.getEndDate()));
		timetableEndDateTextField.setEditable(false);
		coursesScheduleNorthPanel.add(timetableEndDateTextField);

		coursesScheduleMainPanel.add(coursesScheduleNorthPanel, BorderLayout.NORTH);
		
		HashMap<Course, Schedule> coursesScheduleInfo = timetable.getInfo();

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
		
		add(mainPanel);

		setMinimumSize(new Dimension(900, 400));
		pack();
		setResizable(false);
		setLocationRelativeTo(null);

	}

	private static final long serialVersionUID = -981546748725261000L;

}
