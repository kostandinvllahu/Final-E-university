package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import controll.Settings;
import data.DBHandler;
import util.Util;
import view.jdatepicker.DateLabelFormatter;

public class EditPersonalDataWindow extends Window {

	private static final long serialVersionUID = 6530958043100186854L;

	public EditPersonalDataWindow() {
		
		setTitle(getTitle() + " - Edit Personal Data");
		
		JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
		mainPanel.setBackground(Settings.COLOR1);
		mainPanel.setBorder(new LineBorder(Settings.COLOR1, 10));

		JPanel centerPanel = new JPanel(new GridLayout(0, 2, 10, 10));
		centerPanel.setBackground(Settings.COLOR1);
		
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		
		add(mainPanel);
 
		centerPanel.add(new JLabel("National Id"));
		JTextField nationalIdTextField = new JTextField(Settings.loggedinUser.getNationalId());
		centerPanel.add(nationalIdTextField); 
		
		centerPanel.add(new JLabel("Name"));
		JTextField nameTextField = new JTextField(Settings.loggedinUser.getName());
		centerPanel.add(nameTextField);
		 
		centerPanel.add(new JLabel("Surname"));
		JTextField surnameTextField = new JTextField(Settings.loggedinUser.getSurname());
		centerPanel.add(surnameTextField);

		centerPanel.add(new JLabel("Birthday"));
		UtilDateModel model = new UtilDateModel();
		model.setDate(Settings.loggedinUser.getBirthday().get(Calendar.YEAR),
					  Settings.loggedinUser.getBirthday().get(Calendar.MONTH), 
					  Settings.loggedinUser.getBirthday().get(Calendar.DAY_OF_MONTH));
		Properties dateProperties = new Properties(); 
		dateProperties.put("text.today", "Today");
		dateProperties.put("text.month", "Month");
		dateProperties.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, dateProperties);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setPreferredSize(new Dimension(300, 25));
		centerPanel.add(datePicker);
		datePicker.setTextEditable(true);
		
		centerPanel.add(new JLabel("Address"));
		JTextArea addressTextArea = new JTextArea(Settings.loggedinUser.getAddress());
		addressTextArea.getDocument().putProperty("filterNewlines", Boolean.TRUE);
		addressTextArea.setFont(nameTextField.getFont());
		JScrollPane addressTextAreaScrollPane = new JScrollPane(addressTextArea);
		addressTextAreaScrollPane.getHorizontalScrollBar().setPreferredSize(
				   new Dimension(Integer.MAX_VALUE, 5));
		centerPanel.add(addressTextAreaScrollPane);

		ArrayList<String> emailsList = new ArrayList<String>();
		for (int i = 0; i < Settings.loggedinUser.getEmails(); i++) {
			emailsList.add(Settings.loggedinUser.getEmail(i));
		}
		
		centerPanel.add(new JLabel("Emails"));
		JTextArea emailsTextArea = new JTextArea(Util.listToString(emailsList));
		emailsTextArea.getDocument().putProperty("filterNewlines", Boolean.TRUE);
		emailsTextArea.setFont(nameTextField.getFont());
		JScrollPane emailsTextAreaScrollPane = new JScrollPane(emailsTextArea);
		emailsTextAreaScrollPane.getHorizontalScrollBar().setPreferredSize(
				   new Dimension(Integer.MAX_VALUE, 5));
		centerPanel.add(emailsTextAreaScrollPane);

		ArrayList<String> phoneNumbersList = new ArrayList<String>();
		for (int i = 0; i < Settings.loggedinUser.getPhoneNumbers(); i++) {
			phoneNumbersList.add(Settings.loggedinUser.getPhoneNumber(i));
		}
		
		centerPanel.add(new JLabel("Phone Numbers"));
		JTextArea phoneNumbersTextArea = new JTextArea(Util.listToString(phoneNumbersList));
		phoneNumbersTextArea.getDocument().putProperty("filterNewlines", Boolean.TRUE);
		phoneNumbersTextArea.setFont(nameTextField.getFont());
		JScrollPane phoneNumbersTextAreaScrollPane = new JScrollPane(phoneNumbersTextArea);
		phoneNumbersTextAreaScrollPane.getHorizontalScrollBar().setPreferredSize(
				   new Dimension(Integer.MAX_VALUE, 5));
		centerPanel.add(phoneNumbersTextAreaScrollPane);
		
		JButton confirm = new JButton("Confirm");
		JButton cancel = new JButton("Cancel");
		
		confirm.addActionListener((e)->{
			String nationalId = nationalIdTextField.getText();
			String name = nameTextField.getText();
			String surname = surnameTextField.getText();
			GregorianCalendar birthday = new GregorianCalendar(datePicker.getModel().getYear(), 
															   datePicker.getModel().getMonth(), 
															   datePicker.getModel().getDay());
			String address = addressTextArea.getText();
			String phoneNumbersText = phoneNumbersTextArea.getText();
			String emailsText = emailsTextArea.getText();
			DBHandler.db.deletePhoneNumbers();
			DBHandler.db.deleteEmails();
			ArrayList<String> phoneNumbers = Util.splitList(phoneNumbersText, ",");
			ArrayList<String> emails = Util.splitList(emailsText, ",");
			for(String phoneNumber : phoneNumbers) {
				Settings.loggedinUser.addPhoneNumber(phoneNumber);
			}
			for(String email : emails) {
				Settings.loggedinUser.addEmail(email);
			}
			Settings.loggedinUser.setNationalId(nationalId);
			Settings.loggedinUser.setName(name);
			Settings.loggedinUser.setSurname(surname);
			Settings.loggedinUser.setBirthday(birthday);
			Settings.loggedinUser.setAddress(address);
			DBHandler.db.addPhoneNumbers();
			DBHandler.db.addEmails();
			DBHandler.db.updateNationalId();
			DBHandler.db.updateName();
			DBHandler.db.updateSurname();
			DBHandler.db.updateBirthday();
			DBHandler.db.updateAddress();
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

}
