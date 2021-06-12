package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import controll.Settings;
import data.DBHandler;
import util.Util;

public class LoginWindow extends Window {

	private static final long serialVersionUID = -5433548214205850844L;

	public LoginWindow() {
		
		JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
		mainPanel.setBackground(Settings.COLOR1);
		mainPanel.setBorder(new LineBorder(Settings.COLOR1, 10));
		
		add(mainPanel);
	
		JLabel imageLabel = new JLabel(new ImageIcon("img/login.png"));
		mainPanel.add(imageLabel, BorderLayout.NORTH);
		
		JPanel centerPanel = new JPanel(new GridLayout(3, 2, 10, 10));
		centerPanel.setBackground(Settings.COLOR1);
		centerPanel.setBorder(new LineBorder(Settings.COLOR1, 10));

		mainPanel.add(centerPanel);

		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setForeground(Settings.COLOR2);
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setForeground(Settings.COLOR2);

		JTextField usernameTextField = new JTextField();
		JPasswordField passwordField = new JPasswordField();
		
		usernameTextField.setPreferredSize(new Dimension(150, 25));

		usernameTextField.setForeground(Settings.COLOR2);
		passwordField.setForeground(Settings.COLOR2);

		centerPanel.add(usernameLabel);
		centerPanel.add(usernameTextField);
		centerPanel.add(passwordLabel);
		centerPanel.add(passwordField);

		JButton loginButton = new JButton("Login"); 

		loginButton.addActionListener((e) -> {
			String username = usernameTextField.getText();
			String password = Util.charArrayToString(passwordField.getPassword());
			if(DBHandler.db.existsUser(username, password)) {
				Settings.loggedinUser = DBHandler.db.getUserData(username);
				setVisible(false);
				new MainWindow().setVisible(true);
			}
			else {
				JOptionPane.showMessageDialog(null, "Incorrect Username or Password", 
						"Login message", JOptionPane.ERROR_MESSAGE);
			}
		});
		 

		centerPanel.add(new JLabel());
		centerPanel.add(loginButton);
		
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}

}
