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
import model.Course;
import model.Rating;
import util.Util;

public class ViewCourseRatingsWindow extends Window{

	private static final long serialVersionUID = 8882940141973576626L;

	public ViewCourseRatingsWindow(Course course) {

		setTitle(Settings.SYSTEM_TITLE + " - Course Ratings");

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
		centerPanelScrollPane.setBorder(new TitledBorder("ratings"));
		coursePanel.add(centerPanelScrollPane);
		
		int cn = 1;
		
		for(Rating rating : course.getRatings()) {
			JPanel ratingPanel = new JPanel(new BorderLayout(10, 10));
			ratingPanel.setBackground(Settings.COLOR1);
			
			JPanel ratingNorthPanel = new JPanel(new GridLayout(1, 2, 10, 10));
			ratingNorthPanel.setBackground(Settings.COLOR1);
			ratingPanel.add(ratingNorthPanel, BorderLayout.NORTH);
			ratingNorthPanel.add(new JLabel(Util.dateToString(rating.getDate()), 
					SwingConstants.LEFT));
			ratingNorthPanel.add(new JLabel(cn + "", SwingConstants.RIGHT));
			JTextArea ratingTextArea = new JTextArea("Student:       " + rating.getStudent().getName() + " " +
													rating.getStudent().getSurname() + "\n" + 
													"Rating value: " + rating.getRatingValue() + "\n" + 
													"Comment:      " + rating.getComment());
			ratingTextArea.setLineWrap(true);
			ratingTextArea.setWrapStyleWord(true);
			ratingTextArea.setBorder(null);
			ratingTextArea.setFont(new JTextField().getFont());
			JScrollPane ratingTextAreaScrollPane = new JScrollPane(ratingTextArea);
			ratingTextAreaScrollPane.setBackground(Settings.COLOR1);
			ratingTextAreaScrollPane.setBorder(null);
			mainPanel.add(ratingTextAreaScrollPane);
			ratingPanel.add(ratingTextAreaScrollPane);
			centerPanel.add(ratingPanel);
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
