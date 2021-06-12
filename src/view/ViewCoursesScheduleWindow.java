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
import data.DBHandler;
import model.Course;
import model.Schedule;
import model.Timetable;
import util.Util;

public class ViewCoursesScheduleWindow extends Window {

	private static final long serialVersionUID = 1535132337187269046L;

	public ViewCoursesScheduleWindow() {

		setTitle(getTitle() + " - Timetable");
		
		JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
		mainPanel.setBackground(Settings.COLOR1);
		mainPanel.setBorder(new LineBorder(Settings.COLOR1, 10));
		
		JPanel northPanel = new JPanel(new GridLayout(0, 2, 10, 10));
		northPanel.setBackground(Settings.COLOR1);

		northPanel.add(new JLabel("Timetable Title"));
		
		Timetable currentTimetable = DBHandler.db.getCurrentTimetable();
		
		JTextField timetableTitleTextField = new JTextField(currentTimetable.getTitle());
		timetableTitleTextField.setEditable(false);
		northPanel.add(timetableTitleTextField);

		northPanel.add(new JLabel("Timetable Start Date"));
		
		JTextField timetableStartDateTextField = new JTextField(Util.dateToString(currentTimetable.getStartDate()));
		timetableStartDateTextField.setEditable(false);
		northPanel.add(timetableStartDateTextField);
		
		northPanel.add(new JLabel("Timetable End Date"));
		
		JTextField timetableEndDateTextField = new JTextField(Util.dateToString(currentTimetable.getEndDate()));
		timetableEndDateTextField.setEditable(false);
		northPanel.add(timetableEndDateTextField);

		mainPanel.add(northPanel, BorderLayout.NORTH);
		
		HashMap<Course, Schedule> info = currentTimetable.getInfo();

		Object[][] data = new Object[info.size()][5];
	
		int i = 0;
		
		for(Course course : info.keySet()) {
			Schedule schedule = info.get(course);
			data[i][0] = " " + (i + 1);
			data[i][1] = " " + course.getTitle();
			data[i][2] = " " + schedule.getDayOfWeek();
			data[i][3] = " " + schedule.getTimePeriod();
			data[i][4] = " " + schedule.getLocation();
			i++;
		}

		Object[] tableHeader = new Object[] {"#", "Course Title", 
				"Day of Week", "Time Period", "Location"};

		DefaultTableModel model = new DefaultTableModel();
		model.setDataVector(data, tableHeader);

		JTable table = new JTable(model);
		
		JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
		centerPanel.setBackground(Settings.COLOR1);

		JScrollPane tableScrollPane = new JScrollPane(table);

		centerPanel.add(tableScrollPane);
		
		mainPanel.add(centerPanel);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(50); 
		table.getColumnModel().getColumn(1).setPreferredWidth(250);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(3).setPreferredWidth(200); 
		table.getColumnModel().getColumn(4).setPreferredWidth(200);
		
		table.setRowHeight(25);
		
		table.getTableHeader().setFont(new Font(table.getTableHeader().getFont().getFontName(), Font.BOLD, 12));

		
		add(mainPanel);
		
		setMinimumSize(new Dimension(900, 500));
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
}
