package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import controll.Settings;
import data.DBHandler;
import model.Course;

public class AddCourseToListWindow extends Window {

	private static final long serialVersionUID = -26744006892523878L;

	public AddCourseToListWindow() {

		setTitle(getTitle() + " - Add Course");
		
		JPanel mainPanel = new JPanel(new GridLayout(0, 2, 10, 10));
		mainPanel.setBackground(Settings.COLOR1);
		mainPanel.setBorder(new LineBorder(Settings.COLOR1, 10));

		ArrayList<Course> availableCourses = DBHandler.db.getAvailableCourses();
		
		if(availableCourses.size() == 0) {
			JOptionPane.showMessageDialog(null, "0 available courses!");
			throw new IllegalArgumentException(); 
		}
		
		String[] courses = new String[availableCourses.size()];
		
		for (int i = 0; i < courses.length; i++) {
			courses[i] = availableCourses.get(i).getTitle();
		}
		
		mainPanel.add(new JLabel("Course"));

		JComboBox<String> coursesComboBox = new JComboBox<>(courses);
		mainPanel.add(coursesComboBox);
		
		JButton confirm = new JButton("Confirm");
		JButton cancel = new JButton("Cancel");
		
		confirm.addActionListener((e)->{
			DBHandler.db.addCourseToMyList(courses[coursesComboBox.getSelectedIndex()]);
			setVisible(false);
		});

		cancel.addActionListener((e)->{
			setVisible(false);
		});

		mainPanel.add(confirm);
		mainPanel.add(cancel);
		
		add(mainPanel);
		
		setMinimumSize(new Dimension(400, 100));
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		revalidate();
		setResizable(false);
		
	}
	
}
