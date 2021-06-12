package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;

import controll.Settings;
import data.DBHandler;
import model.Course;
import model.Rating;
import model.Student;

public class AddRatingWindow extends Window {

	private static final long serialVersionUID = -2883944060011041511L;

	public AddRatingWindow(Course course) {
		
		setTitle(getTitle() + " - Evaluate");
		
		JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
		mainPanel.setBackground(Settings.COLOR1);
		mainPanel.setBorder(new LineBorder(Settings.COLOR1, 10));

		JPanel northPanel = new JPanel(new GridLayout(2, 2, 10, 10));
		northPanel.setBackground(Settings.COLOR1);
		
		mainPanel.add(northPanel, BorderLayout.NORTH);
		
		northPanel.add(new JLabel("Course"));

		JTextField courseTextField = new JTextField(course.getTitle());
		courseTextField.setEditable(false);
		northPanel.add(courseTextField);
		
		northPanel.add(new JLabel("Rating Value"));
		
		JSpinner ratingValueNumberSpinner = new JSpinner();
		ratingValueNumberSpinner.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		northPanel.add(ratingValueNumberSpinner);

		JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 10));
		centerPanel.setBackground(Settings.COLOR1);

		mainPanel.add(centerPanel, BorderLayout.CENTER);
		
		centerPanel.add(new JLabel("Comment"));

		JTextArea commentTextArea = new JTextArea();
		commentTextArea.setLineWrap(true);
		commentTextArea.setWrapStyleWord(true);
		JScrollPane commentScrollPane = new JScrollPane(commentTextArea);
		centerPanel.add(commentScrollPane);
		
		JPanel southPanel = new JPanel(new GridLayout(1, 2, 10, 10));
		southPanel.setBackground(Settings.COLOR1);

		mainPanel.add(southPanel, BorderLayout.SOUTH);
		
		JButton confirm = new JButton("Confirm");
		JButton cancel = new JButton("Cancel");

		southPanel.add(confirm);
		southPanel.add(cancel);

		confirm.addActionListener((e)->{
			Rating rating = new Rating((Student)(Settings.loggedinUser), "", 0);
			rating.setComment(commentTextArea.getText());
			rating.setRatingValue(Integer.parseInt(ratingValueNumberSpinner.getModel().getValue() + ""));
			course.getRatings().add(rating);
			DBHandler.db.addRatingToCourse(rating, course);
			setVisible(false);
		});

		cancel.addActionListener((e)->{
			setVisible(false);
		}); 
		
		add(mainPanel);
		
		setMinimumSize(new Dimension(400, 250));
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		revalidate();
		setResizable(false);
		
	}

}
