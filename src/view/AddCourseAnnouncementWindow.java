package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import controll.Settings;
import data.DBHandler;
import model.Announcement;
import model.Course;

public class AddCourseAnnouncementWindow extends Window {

	public AddCourseAnnouncementWindow(Course course) {
		
		setTitle(getTitle() + " - Add Course Announcement");

		JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
		mainPanel.setBackground(Settings.COLOR1);
		mainPanel.setBorder(new LineBorder(Settings.COLOR1, 10));

		JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 10));
		centerPanel.setBackground(Settings.COLOR1); 

		JPanel northPanel = new JPanel(new GridLayout(1, 2, 10, 10));
		northPanel.setBackground(Settings.COLOR1); 
		
		northPanel.add(new JLabel("Title"));
		
		JTextField titleTextField = new JTextField();
		northPanel.add(titleTextField);
		
		mainPanel.add(northPanel, BorderLayout.NORTH);
		
		centerPanel.add(new JLabel("Announcement"));
		
		JTextArea descriptionTextArea = new JTextArea();
		descriptionTextArea.setFont(new JLabel().getFont());
		descriptionTextArea.setLineWrap(true);
		descriptionTextArea.setWrapStyleWord(true);
		JScrollPane descriptionTextAreaScrollPane = new JScrollPane(descriptionTextArea);
		centerPanel.add(descriptionTextAreaScrollPane);
		
		mainPanel.add(centerPanel);
		
		JPanel buttonsPanel = new JPanel(new GridLayout(1, 2, 10, 10));
		buttonsPanel.setBackground(Settings.COLOR1); 


		JButton confirm = new JButton("Confirm");
		JButton cancel = new JButton("Cancel");
		

		confirm.addActionListener((e)->{ 
			String title = titleTextField.getText();
			String description = descriptionTextArea.getText();
			Announcement announcement = new Announcement(new GregorianCalendar(), title, description);
			course.getAnnouncements().add(announcement);
			DBHandler.db.addAnnouncementToCourse(announcement, course);
			setVisible(false);
		});

		cancel.addActionListener((e)->{
			setVisible(false);
		});

		buttonsPanel.add(confirm);
		buttonsPanel.add(cancel);
		
		mainPanel.add(buttonsPanel, BorderLayout.SOUTH);
		
		add(mainPanel);
		
		setMinimumSize(new Dimension(600, 400));
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		revalidate();
		setResizable(false);
		setVisible(true);

	}

	private static final long serialVersionUID = 694720828179145875L;

}
