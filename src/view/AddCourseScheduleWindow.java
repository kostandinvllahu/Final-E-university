package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import controll.Settings;
import data.DBHandler;
import model.Course;
import model.Schedule;
import model.Timetable;

public class AddCourseScheduleWindow extends Window{

	private static final long serialVersionUID = -5412117534661938568L;

	public AddCourseScheduleWindow(Timetable timetable) {

		setTitle(getTitle() + " - Add Course Schedule");
		
		JPanel mainPanel = new JPanel(new GridLayout(0, 2, 10, 10));
		mainPanel.setBackground(Settings.COLOR1);
		mainPanel.setBorder(new LineBorder(Settings.COLOR1, 10));
		
		mainPanel.add(new JLabel("Course"));

		ArrayList<Course> list = DBHandler.db.getCourses();
		if(list.size() == 0) { 
			JOptionPane.showMessageDialog(null, "There is no data on courses!");
			throw new IllegalArgumentException(); 
		}
		String[] courses = new String[list.size()];
		for(int i = 0; i < courses.length; i++) {
			courses[i] = list.get(i).getTitle();
		}
		
		JComboBox<String> coursesComboBox = new JComboBox<>(courses);
		mainPanel.add(coursesComboBox);
		
		mainPanel.add(new JLabel("Day of Week"));

		String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", 
				"Thursday", "Friday"};
		JComboBox<String> daysOfWeekComboBox = new JComboBox<>(daysOfWeek);
		mainPanel.add(daysOfWeekComboBox);

		mainPanel.add(new JLabel("Period"));
		JTextField periodTextField = new JTextField();
		mainPanel.add(periodTextField);

		mainPanel.add(new JLabel("Location"));
		JTextField locationTextField = new JTextField();
		mainPanel.add(locationTextField);
		
		JButton confirm = new JButton("Confirm");
		JButton cancel = new JButton("Cancel");

		mainPanel.add(confirm);
		mainPanel.add(cancel);
		
		confirm.addActionListener((e)->{
			timetable.getInfo().put(list.get(coursesComboBox.getSelectedIndex()), 
					new Schedule(daysOfWeek[daysOfWeekComboBox.getSelectedIndex()], 
							periodTextField.getText(), locationTextField.getText()));
			setVisible(false);
		});

		cancel.addActionListener((e)->{
			setVisible(false);
		});
		
		add(mainPanel);

		setMinimumSize(new Dimension(600, 200));
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		revalidate();
		setResizable(false);

	}
	
}
