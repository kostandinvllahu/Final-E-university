package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;

import controll.Settings;
import data.DBHandler;
import model.Course;

public class AddCourseWindow extends Window {

	private static final long serialVersionUID = -1350736257284983041L;

	public AddCourseWindow(){
		
		setTitle(getTitle() + " - Add Course");

		JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
		mainPanel.setBackground(Settings.COLOR1);
		mainPanel.setBorder(new LineBorder(Settings.COLOR1, 10));
		
		JPanel northPanel = new JPanel(new GridLayout(0, 2, 10, 10));
		northPanel.setBackground(Settings.COLOR1); 

		northPanel.add(new JLabel("Title"));
		
		JTextField titleTextField = new JTextField();
		northPanel.add(titleTextField);
		
		northPanel.add(new JLabel("Subject"));

		JRadioButton minorSubjectRadioButton = new JRadioButton("Minor");
		minorSubjectRadioButton.setBackground(Settings.COLOR1); 
		JRadioButton majorSubjectRadioButton = new JRadioButton("Major");
		majorSubjectRadioButton.setBackground(Settings.COLOR1); 
		
		ButtonGroup subjectButtonGroup = new ButtonGroup();
		subjectButtonGroup.add(minorSubjectRadioButton);
		subjectButtonGroup.add(majorSubjectRadioButton);
		
		minorSubjectRadioButton.setSelected(true);

		JPanel subjectLevelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
		subjectLevelPanel.setBackground(Settings.COLOR1);
		
		subjectLevelPanel.add(minorSubjectRadioButton);
		subjectLevelPanel.add(majorSubjectRadioButton);
		
		northPanel.add(subjectLevelPanel);
		northPanel.add(new JLabel());
		
		JComboBox<String> subjectComboBox =
				new JComboBox<String>(DBHandler.db.getMinorSubjects());
		northPanel.add(subjectComboBox);

		minorSubjectRadioButton.addActionListener((e)->{
			while(subjectComboBox.getItemCount() != 0) {
				subjectComboBox.removeItemAt(0);
			}
			String[] subjects = DBHandler.db.getMinorSubjects();
			for(String subject : subjects) {
				subjectComboBox.addItem(subject);
			}
			subjectComboBox.revalidate();
		});

		majorSubjectRadioButton.addActionListener((e)->{
			while(subjectComboBox.getItemCount() != 0) {
				subjectComboBox.removeItemAt(0);
			}
			String[] subjects = DBHandler.db.getMajorSubjects();
			for(String subject : subjects) {
				subjectComboBox.addItem(subject);
			}
			subjectComboBox.revalidate();
		});
		 
		northPanel.add(new JLabel("Credits"));
		JSpinner creditsNumberSpinner = new JSpinner();
		creditsNumberSpinner.setModel(new SpinnerNumberModel(1, 1, 12, 1));
		northPanel.add(creditsNumberSpinner);
		 
		northPanel.add(new JLabel("Semester Number"));
		JSpinner semesterNumberSpinner = new JSpinner();
		semesterNumberSpinner.setModel(new SpinnerNumberModel(1, 1, 10, 1));
		northPanel.add(semesterNumberSpinner);
		
		mainPanel.add(northPanel, BorderLayout.NORTH);
		
		JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 10));
		centerPanel.setBackground(Settings.COLOR1); 
		
		centerPanel.add(new JLabel("Description"));
		JTextArea descriptionTextArea = new JTextArea();
		descriptionTextArea.setFont(titleTextField.getFont());
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
			String subject = subjectComboBox.getSelectedItem().toString();
			int numberOfCredits = Integer.parseInt(creditsNumberSpinner.getModel().getValue() + "");
			int semesterNumber = Integer.parseInt(creditsNumberSpinner.getModel().getValue() + "");
			DBHandler.db.addCourse(new Course(title, description, subject,
					numberOfCredits, semesterNumber));
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

	}
	
}
