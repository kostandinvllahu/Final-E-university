package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import controll.Settings;
import model.Announcement;
import model.Course;
import util.Util;

public class ViewCourseAnnouncementsWindow extends Window{

	private static final long serialVersionUID = -4708647139193626444L;

	public ViewCourseAnnouncementsWindow(Course course) {
		
		setTitle(Settings.SYSTEM_TITLE + " - Course Announcements");

		JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
		mainPanel.setBackground(Settings.COLOR1);
		mainPanel.setBorder(new LineBorder(Settings.COLOR1, 10));
		
		JPanel coursePanel = new JPanel(new BorderLayout(10, 10));
		coursePanel.setBackground(Settings.COLOR1);
		
		JPanel northPanel = new JPanel(new BorderLayout(10, 10));
		northPanel.setBackground(Settings.COLOR1);
		
		coursePanel.add(northPanel, BorderLayout.NORTH);
		
		JTextField courseTitleTextField = new JTextField(course.getTitle());
		courseTitleTextField.setEditable(false);
		courseTitleTextField.setBorder(null);
		courseTitleTextField.setFont(new Font(courseTitleTextField.getFont().getFontName(),
				Font.BOLD, 14));
		courseTitleTextField.setBackground(Settings.COLOR1);
		northPanel.add(courseTitleTextField);
		
		JPanel centerPanel = new JPanel(new GridLayout(0, 1, 10, 10));
		centerPanel.setBackground(Settings.COLOR1);
		
		JScrollPane centerPanelScrollPane = new JScrollPane(centerPanel);
		centerPanelScrollPane.setBackground(Settings.COLOR1);
		centerPanelScrollPane.setBorder(new TitledBorder("Announcements"));
		coursePanel.add(centerPanelScrollPane);
		
		int cn = 1;
		for(Announcement announcement : course.getAnnouncements()) {
			JPanel announcementPanel = new JPanel(new BorderLayout(10, 10));
			announcementPanel.setBackground(Settings.COLOR1);
			JPanel announcementNorthPanel = new JPanel(new GridLayout(1, 2, 10, 10));
			announcementNorthPanel.setBackground(Settings.COLOR1);
			announcementPanel.add(announcementNorthPanel, BorderLayout.NORTH);
			announcementNorthPanel.add(new JLabel(Util.dateToString(announcement.getDate()), 
					SwingConstants.LEFT));
			announcementNorthPanel.add(new JLabel(cn + "", SwingConstants.RIGHT));
			JTextArea announcementTextArea = new JTextArea(announcement.getTitle() + "\n\n" + 
															announcement.getDescription());
			announcementTextArea.setLineWrap(true);
			announcementTextArea.setWrapStyleWord(true);
			announcementTextArea.setBorder(null);
			announcementTextArea.setFont(new JTextField().getFont());
			JScrollPane announcementTextAreaScrollPane = new JScrollPane(announcementTextArea);
			announcementTextAreaScrollPane.setBackground(Settings.COLOR1);
			announcementTextAreaScrollPane.setBorder(null);
			mainPanel.add(announcementTextAreaScrollPane);
			announcementPanel.add(announcementTextAreaScrollPane);
			centerPanel.add(announcementPanel);
			cn++;
		}
		
		JScrollPane coursePanelScrollPane = new JScrollPane(coursePanel);
		coursePanelScrollPane.setBackground(Settings.COLOR1);
		coursePanelScrollPane.setBorder(null);
		coursePanelScrollPane.setPreferredSize(new Dimension(800, 300));
		mainPanel.add(coursePanelScrollPane);
		
		add(mainPanel);

		setMinimumSize(new Dimension(960, 600));
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);		
		
	}

}