package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import controll.Settings;
import model.Student;
import model.User;
import util.Util;

public class ViewUserWindow extends Window {

	private static final long serialVersionUID = 1821899731308972665L;

	public ViewUserWindow(User user) {
		
		setTitle(getTitle() + " - View User");
		
		JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
		mainPanel.setBackground(Settings.COLOR1);
		mainPanel.setBorder(new LineBorder(Settings.COLOR1, 10));

		JPanel centerPanel = new JPanel(new GridLayout(0, 2, 10, 10));
		centerPanel.setBackground(Settings.COLOR1);
 
		mainPanel.add(centerPanel, BorderLayout.CENTER); 
		
		add(mainPanel);

		centerPanel.add(new JLabel("User Role")); 
		
		JPanel userRolePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0)); 
		userRolePanel.setBackground(Settings.COLOR1);
		centerPanel.add(userRolePanel); 

		JRadioButton studentRadioButton = new JRadioButton("Student");
		studentRadioButton.setBackground(Settings.COLOR1); 
		JRadioButton adminRadioButton = new JRadioButton("Admin");
		adminRadioButton.setBackground(Settings.COLOR1); 

		ButtonGroup userRolesGroup = new ButtonGroup();
		userRolesGroup.add(studentRadioButton);
		userRolesGroup.add(adminRadioButton);
		
		if(user instanceof Student) {
			studentRadioButton.setSelected(true);
		}
		else {
			adminRadioButton.setSelected(true);
		}

		studentRadioButton.setEnabled(false);
		adminRadioButton.setEnabled(false);
		
		userRolePanel.add(studentRadioButton);
		userRolePanel.add(adminRadioButton);
		
		centerPanel.add(new JLabel("National Id"));
		JTextField nationalIdTextField = new JTextField(user.getNationalId());
		nationalIdTextField.setEditable(false);
		centerPanel.add(nationalIdTextField); 
		
		centerPanel.add(new JLabel("Name"));
		JTextField nameTextField = new JTextField(user.getName());
		nameTextField.setEditable(false);
		centerPanel.add(nameTextField);
		 
		centerPanel.add(new JLabel("Surname"));
		JTextField surnameTextField = new JTextField(user.getSurname());
		surnameTextField.setEditable(false);
		centerPanel.add(surnameTextField);

		centerPanel.add(new JLabel("Birthday"));
		JTextField birthdayTextField = new JTextField(Util.dateToString(user.getBirthday()));
		birthdayTextField.setEditable(false);
		centerPanel.add(birthdayTextField);
		 
		centerPanel.add(new JLabel("Gender"));
		JTextField genderTextField = new JTextField(user.getGender() + "");
		genderTextField.setEditable(false);
		centerPanel.add(genderTextField);

		centerPanel.add(new JLabel("Address"));
		JTextArea addressTextArea = new JTextArea(user.getAddress());
		addressTextArea.setEditable(false);
		addressTextArea.getDocument().putProperty("filterNewlines", Boolean.TRUE);
		addressTextArea.setFont(nameTextField.getFont());
		JScrollPane addressTextAreaScrollPane = new JScrollPane(addressTextArea);
		addressTextAreaScrollPane.getHorizontalScrollBar().setPreferredSize(
				   new Dimension(Integer.MAX_VALUE, 5));
		centerPanel.add(addressTextAreaScrollPane);

		ArrayList<String> emails = new ArrayList<String>();
		for (int j = 0; j < user.getEmails(); j++) {
			emails.add(user.getEmail(j));
		}
		
		centerPanel.add(new JLabel("Emails"));
		JTextArea emailsTextArea = new JTextArea(Util.listToString(emails));
		emailsTextArea.setEditable(false);
		emailsTextArea.getDocument().putProperty("filterNewlines", Boolean.TRUE);
		emailsTextArea.setFont(nameTextField.getFont());
		JScrollPane emailsTextAreaScrollPane = new JScrollPane(emailsTextArea);
		emailsTextAreaScrollPane.getHorizontalScrollBar().setPreferredSize(
				   new Dimension(Integer.MAX_VALUE, 5));
		centerPanel.add(emailsTextAreaScrollPane);

		ArrayList<String> phoneNumbers = new ArrayList<String>();
		for (int j = 0; j < user.getPhoneNumbers(); j++) {
			phoneNumbers.add(user.getPhoneNumber(j));
		}
		
		centerPanel.add(new JLabel("Phone Numbers"));
		JTextArea phoneNumbersTextArea = new JTextArea(Util.listToString(phoneNumbers));
		phoneNumbersTextArea.setEditable(false);
		phoneNumbersTextArea.getDocument().putProperty("filterNewlines", Boolean.TRUE);
		phoneNumbersTextArea.setFont(nameTextField.getFont());
		JScrollPane phoneNumbersTextAreaScrollPane = new JScrollPane(phoneNumbersTextArea);
		phoneNumbersTextAreaScrollPane.getHorizontalScrollBar().setPreferredSize(
				   new Dimension(Integer.MAX_VALUE, 5));
		centerPanel.add(phoneNumbersTextAreaScrollPane);

		centerPanel.add(new JLabel("Username"));
		JTextField usernameTextField = new JTextField(user.getUsername());
		usernameTextField.setEditable(false);
		centerPanel.add(usernameTextField); 
		
		centerPanel.add(new JLabel("Active"));
		JCheckBox activeUserCheckBox = new JCheckBox();
		activeUserCheckBox.setSelected(user.isActive());
		activeUserCheckBox.setEnabled(false);
		activeUserCheckBox.setBackground(Settings.COLOR1);
		activeUserCheckBox.setBorder(null);
		JPanel activeUserCheckBoxOrientationPanel = new JPanel();
		activeUserCheckBoxOrientationPanel.setBackground(Settings.COLOR1);
		activeUserCheckBoxOrientationPanel.setBorder(null);
		activeUserCheckBoxOrientationPanel.add(activeUserCheckBox);
		centerPanel.add(activeUserCheckBoxOrientationPanel);
		
		if(user instanceof Student) {
			
			Student std = (Student)user;
			
			JLabel subjectLabel = new JLabel("Subject");
			centerPanel.add(subjectLabel);

			JRadioButton minorSubjectRadioButton = new JRadioButton("Minor");
			minorSubjectRadioButton.setBackground(Settings.COLOR1); 
			JRadioButton majorSubjectRadioButton = new JRadioButton("Major");
			majorSubjectRadioButton.setBackground(Settings.COLOR1); 
			
			ButtonGroup subjectButtonGroup = new ButtonGroup();
			subjectButtonGroup.add(minorSubjectRadioButton);
			subjectButtonGroup.add(majorSubjectRadioButton);
			
			if(!std.isMinorDegreeGraduated()) {
				minorSubjectRadioButton.setSelected(true);
			}
			else {
				majorSubjectRadioButton.setSelected(true);
			}

			minorSubjectRadioButton.setEnabled(false);
			majorSubjectRadioButton.setEnabled(false);
			
			JPanel subjectLevelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
			subjectLevelPanel.setBackground(Settings.COLOR1);
			
			subjectLevelPanel.add(minorSubjectRadioButton);
			subjectLevelPanel.add(majorSubjectRadioButton);
			
			centerPanel.add(subjectLevelPanel);
			centerPanel.add(new JLabel());
			
			String subject = std.getMinorSubject();
			if(std.isMinorDegreeGraduated()) {
				subject = std.getMajorSubject();
			} 
			JTextField subjectTextField = new JTextField(subject);
			subjectTextField.setEditable(false);
			centerPanel.add(subjectTextField);
			
			JLabel semesterNumberLabel = new JLabel("Semester Number");
			centerPanel.add(semesterNumberLabel);
			JTextField semesterTextField = new JTextField(std.getCurrentSemesterNumber() + "");
			semesterTextField.setEditable(false);
			centerPanel.add(semesterTextField);
			
		}

		setMinimumSize(new Dimension(600, 200));
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		revalidate();
		setResizable(false);

	}

}
