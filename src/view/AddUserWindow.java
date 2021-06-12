package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Properties;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import controll.Settings;
import data.DBHandler;
import model.Admin;
import model.Person.Gender;
import model.Student;
import util.Util;
import view.jdatepicker.DateLabelFormatter;

public class AddUserWindow extends Window {

	private static final long serialVersionUID = 4353306195629229491L;
	private String password = "", repeatPassword = "";
	
	public AddUserWindow() {
		
		setTitle(getTitle() + " - Add User");
		
		JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
		mainPanel.setBackground(Settings.COLOR1);
		mainPanel.setBorder(new LineBorder(Settings.COLOR1, 10));

		JPanel centerPanel = new JPanel(new GridLayout(0, 2, 10, 10));
		centerPanel.setBackground(Settings.COLOR1);

		JPanel eastPanel = new JPanel(new GridLayout(0, 1, 10, 10));
		eastPanel.setBackground(Settings.COLOR1);
		
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(eastPanel, BorderLayout.EAST);
		
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
		
		studentRadioButton.setSelected(true);
		
		userRolePanel.add(studentRadioButton);
		userRolePanel.add(adminRadioButton);
		
		centerPanel.add(new JLabel("National Id"));
		JTextField nationalIdTextField = new JTextField();
		centerPanel.add(nationalIdTextField); 
		
		centerPanel.add(new JLabel("Name"));
		JTextField nameTextField = new JTextField();
		centerPanel.add(nameTextField);
		 
		centerPanel.add(new JLabel("Surname"));
		JTextField surnameTextField = new JTextField();
		centerPanel.add(surnameTextField);

		centerPanel.add(new JLabel("Birthday"));
		UtilDateModel model = new UtilDateModel();
		Properties dateProperties = new Properties(); 
		dateProperties.put("text.today", "Today");
		dateProperties.put("text.month", "Month");
		dateProperties.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, dateProperties);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		//https://www.codejava.net/java-se/swing/how-to-use-jdatepicker-to-display-calendar-component
		datePicker.setPreferredSize(new Dimension(300, 25));
		centerPanel.add(datePicker);
		 
		centerPanel.add(new JLabel("Gender"));
		JComboBox<String> genderComboBox = new JComboBox<String>(new String[] {
				"Male", "Female", "Other"
		});
		centerPanel.add(genderComboBox);

		centerPanel.add(new JLabel("Address"));
		JTextArea addressTextArea = new JTextArea();
		addressTextArea.getDocument().putProperty("filterNewlines", Boolean.TRUE);
		addressTextArea.setFont(nameTextField.getFont());
		JScrollPane addressTextAreaScrollPane = new JScrollPane(addressTextArea);
		addressTextAreaScrollPane.getHorizontalScrollBar().setPreferredSize(
				   new Dimension(Integer.MAX_VALUE, 5));
		centerPanel.add(addressTextAreaScrollPane);

		centerPanel.add(new JLabel("Emails"));
		JTextArea emailsTextArea = new JTextArea();
		emailsTextArea.getDocument().putProperty("filterNewlines", Boolean.TRUE);
		emailsTextArea.setFont(nameTextField.getFont());
		JScrollPane emailsTextAreaScrollPane = new JScrollPane(emailsTextArea);
		emailsTextAreaScrollPane.getHorizontalScrollBar().setPreferredSize(
				   new Dimension(Integer.MAX_VALUE, 5));
		centerPanel.add(emailsTextAreaScrollPane);


		centerPanel.add(new JLabel("Phone Numbers"));
		JTextArea phoneNumbersTextArea = new JTextArea();
		phoneNumbersTextArea.getDocument().putProperty("filterNewlines", Boolean.TRUE);
		phoneNumbersTextArea.setFont(nameTextField.getFont());
		JScrollPane phoneNumbersTextAreaScrollPane = new JScrollPane(phoneNumbersTextArea);
		phoneNumbersTextAreaScrollPane.getHorizontalScrollBar().setPreferredSize(
				   new Dimension(Integer.MAX_VALUE, 5));
		centerPanel.add(phoneNumbersTextAreaScrollPane);

		centerPanel.add(new JLabel("Username"));
		JTextField usernameTextField = new JTextField();
		centerPanel.add(usernameTextField);
		
		KeyAdapter usernameListener = new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) { 
				usernameTextField.setText((nameTextField.getText() + "." + 
										   surnameTextField.getText()).toLowerCase());
			}
		}; 
		
		nameTextField.addKeyListener(usernameListener);
		surnameTextField.addKeyListener(usernameListener);
		
		centerPanel.add(new JLabel("Password"));
		JTextField passwordTextField = new JTextField();
		centerPanel.add(passwordTextField);
		
		centerPanel.add(new JLabel("Repeat Password"));
		JTextField repeatPasswordTextField = new JTextField();
		centerPanel.add(repeatPasswordTextField);

		KeyAdapter hidePasswordListener = new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) { 
				if(passwordTextField.getText().length() < password.length()) {
					String text = "";
					for (int i = 0; i < passwordTextField.getText().length(); i++) {
						text = text + password.charAt(i);
					}
					password = text;
				}
				else { 
					password = password + e.getKeyChar(); 
					passwordTextField.setText(Util.getHiddenText(password));
					repeatPasswordTextField.setText(Util.getHiddenText(repeatPassword));
				}
				passwordTextField.revalidate();
				repeatPasswordTextField.revalidate();
				super.keyPressed(e);
			}
		};
		
		JButton showPassword = new JButton(new ImageIcon("img/closed-eye.png")); 
		 
		showPassword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(passwordTextField.getKeyListeners().length != 0) {
					showPassword.setIcon(new ImageIcon("img/eye-open.png"));
					passwordTextField.setText(password); 
					repeatPasswordTextField.setText(repeatPassword);
					passwordTextField.removeKeyListener(hidePasswordListener);
				}
				else {
					showPassword.setIcon(new ImageIcon("img/closed-eye.png"));
					password = passwordTextField.getText();
					passwordTextField.setText(Util.getHiddenText(password));
					repeatPasswordTextField.setText(Util.getHiddenText(repeatPassword));
					passwordTextField.addKeyListener(hidePasswordListener);
				}
				passwordTextField.revalidate();
				repeatPasswordTextField.revalidate();
			}
		});
		
		repeatPasswordTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(repeatPasswordTextField.getText().length() < repeatPassword.length()) {
					String text = "";
					for (int i = 0; i < repeatPasswordTextField.getText().length(); i++) {
						text = text + repeatPassword.charAt(i);
					}
					repeatPassword = text;
				}
				else { 
					repeatPassword = repeatPassword + e.getKeyChar(); 
				} 
				if(passwordTextField.getKeyListeners().length != 0) {
					repeatPasswordTextField.setText(Util.getHiddenText(repeatPassword));
				}
				super.keyPressed(e);
			}
		});

		passwordTextField.addKeyListener(hidePasswordListener);
		
		eastPanel.add(new JLabel());
		eastPanel.add(new JLabel());
		eastPanel.add(new JLabel());
		eastPanel.add(new JLabel());
		eastPanel.add(new JLabel());
		eastPanel.add(new JLabel());
		eastPanel.add(new JLabel());
		eastPanel.add(new JLabel());
		eastPanel.add(new JLabel());
		eastPanel.add(new JLabel());
		eastPanel.add(showPassword);
		eastPanel.add(new JLabel());
		eastPanel.add(new JLabel());
		eastPanel.add(new JLabel());
		eastPanel.add(new JLabel());
		eastPanel.add(new JLabel());
		eastPanel.add(new JLabel());
		

		centerPanel.add(new JLabel("Active"));
		JCheckBox activeUserCheckBox = new JCheckBox();
		activeUserCheckBox.setSelected(true);
		activeUserCheckBox.setBackground(Settings.COLOR1);
		activeUserCheckBox.setBorder(null);
		JPanel activeUserCheckBoxOrientationPanel = new JPanel();
		activeUserCheckBoxOrientationPanel.setBackground(Settings.COLOR1);
		activeUserCheckBoxOrientationPanel.setBorder(null);
		activeUserCheckBoxOrientationPanel.add(activeUserCheckBox);
		centerPanel.add(activeUserCheckBoxOrientationPanel);
		
		JLabel subjectLabel = new JLabel("Subject");
		centerPanel.add(subjectLabel);

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
		
		centerPanel.add(subjectLevelPanel);
		centerPanel.add(new JLabel());
		
		JPanel subjectPanel = new JPanel(new GridLayout(1, 1, 10, 10));
		subjectPanel.setBackground(Settings.COLOR1);
		
		JComboBox<String> subjectComboBox =
				new JComboBox<String>(DBHandler.db.getMinorSubjects());
		subjectPanel.add(subjectComboBox);
		centerPanel.add(subjectPanel);

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
		
		JLabel semesterNumberLabel = new JLabel("Semester Number");
		centerPanel.add(semesterNumberLabel);
		JSpinner semesterNumberSpinner = new JSpinner();
		semesterNumberSpinner.setModel(new SpinnerNumberModel(1, 1, 10, 1));
		centerPanel.add(semesterNumberSpinner);

		studentRadioButton.addActionListener((e)->{
			subjectLabel.setVisible(true);
			subjectLevelPanel.setVisible(true);
			subjectPanel.setVisible(true);
			semesterNumberLabel.setVisible(true);
			semesterNumberSpinner.setVisible(true);
			revalidate();
		});

		adminRadioButton.addActionListener((e)->{
			subjectLabel.setVisible(false);
			subjectLevelPanel.setVisible(false);
			subjectPanel.setVisible(false);
			semesterNumberLabel.setVisible(false);
			semesterNumberSpinner.setVisible(false);
			revalidate();
		});
		
		JButton confirm = new JButton("Confirm");
		JButton cancel = new JButton("Cancel");
		
		confirm.addActionListener((e)->{
			String nationalId = nationalIdTextField.getText();
			String name = nameTextField.getText();
			String surname = surnameTextField.getText();
			GregorianCalendar birthday = new GregorianCalendar(datePicker.getModel().getYear(), 
															   datePicker.getModel().getMonth(), 
															   datePicker.getModel().getDay());
			Gender gender = Gender.OTHER;
			if(genderComboBox.getSelectedIndex() == 0) {
				gender = Gender.MALE;
			}
			else if(genderComboBox.getSelectedIndex() == 1) {
				gender = Gender.FEMALE;
			}
			String address = addressTextArea.getText();
			String phoneNumbersText = phoneNumbersTextArea.getText();
			String emailsText = emailsTextArea.getText();
			String username = usernameTextField.getText(); 
			boolean isActive = activeUserCheckBox.isSelected(); 
			ArrayList<String> phoneNumbers = Util.splitList(phoneNumbersText, ",");
			ArrayList<String> emails = Util.splitList(emailsText, ","); 
			if(studentRadioButton.isSelected()) {  
				DBHandler.db.addStudent(new Student(nationalId, name, surname, birthday, 
						gender, address, phoneNumbers, emails, username, password,
						isActive, subjectComboBox.getSelectedItem().toString(), 
						minorSubjectRadioButton.isSelected(), subjectComboBox.getSelectedItem().toString(), 
						majorSubjectRadioButton.isSelected(), Integer.parseInt("" + semesterNumberSpinner.getModel().getValue())));
			}
			else {
				DBHandler.db.addAdmin(new Admin(nationalId, name, surname, 
						birthday, gender, username, phoneNumbers, emails, username, 
						password, isActive));
			}
			setVisible(false);
			JOptionPane.showMessageDialog(null, "Success!");
		});
		
		cancel.addActionListener((e)->{
			setVisible(false);
		});

		centerPanel.add(confirm);
		centerPanel.add(cancel);
		 
		setMinimumSize(new Dimension(600, 200));
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		revalidate();
		setResizable(false);

	}
	
//	public static void main(String[] args) {
//		new AddUserWindow().setVisible(true);
//	}

}
