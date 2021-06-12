package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import controll.Settings;
import data.DBHandler;
import util.Util;

public class ChangePasswordWindow extends Window {

	private static final long serialVersionUID = 
			1959557355173887811L;
	 
	private String newPassword = "",
				   repeatNewPassword = "";

	public ChangePasswordWindow() {
		 
		setTitle(getTitle() + " - Change Password");
		
		JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBorder(new LineBorder(Color.WHITE, 10));

		JPanel centerPanel = new JPanel(new GridLayout(4, 2, 10, 10));
		centerPanel.setBackground(Color.WHITE);

		JPanel eastPanel = new JPanel(new GridLayout(4, 1, 10, 10));
		eastPanel.setBackground(Color.WHITE);
		
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(eastPanel, BorderLayout.EAST);
		
		add(mainPanel);
		  
		centerPanel.add(new JLabel("Password"));
		JTextField passwordTextField = new JTextField();
		centerPanel.add(passwordTextField);
		
		eastPanel.add(new JLabel());
		
		centerPanel.add(new JLabel("New Password"));
		JTextField newPasswordTextField = new JTextField();
		centerPanel.add(newPasswordTextField);

		KeyAdapter hideNewPasswordListener = new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) { 
				if(newPasswordTextField.getText().length() < newPassword.length()) {
					String text = "";
					for (int i = 0; i < newPasswordTextField.getText().length(); i++) {
						text = text + newPassword.charAt(i);
					}
					newPassword = text;
				}
				else { 
					newPassword = newPassword + e.getKeyChar(); 
					newPasswordTextField.setText(Util.getHiddenText(newPassword));
				}
				newPasswordTextField.revalidate();
				super.keyPressed(e);
			}
		};

		newPasswordTextField.addKeyListener(hideNewPasswordListener);
		
		JButton showNewPassword = new JButton(new ImageIcon("img/closed-eye.png")); 
		eastPanel.add(showNewPassword);
		
		showNewPassword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(newPasswordTextField.getKeyListeners().length != 0) {
					showNewPassword.setIcon(new ImageIcon("img/eye-open.png"));
					newPasswordTextField.setText(newPassword);
					newPasswordTextField.revalidate();
					newPasswordTextField.removeKeyListener(hideNewPasswordListener);
				}
				else {
					showNewPassword.setIcon(new ImageIcon("img/closed-eye.png"));
					newPassword = newPasswordTextField.getText();
					newPasswordTextField.setText(Util.getHiddenText(newPassword));
					newPasswordTextField.addKeyListener(hideNewPasswordListener);
				}
			}
		});

		centerPanel.add(new JLabel("Repeat New Password"));
		JTextField repeatNewPasswordTextField = new JTextField();
		centerPanel.add(repeatNewPasswordTextField);
		
		JButton showReapeatNewPassword = new JButton(new ImageIcon("img/closed-eye.png")); 
		eastPanel.add(showReapeatNewPassword);

		KeyAdapter hideRepeatNewPasswordListener = new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) { 
				if(repeatNewPasswordTextField.getText().length() < repeatNewPassword.length()) {
					String text = "";
					for (int i = 0; i < repeatNewPasswordTextField.getText().length(); i++) {
						text = text + repeatNewPassword.charAt(i);
					}
					repeatNewPassword = text;
				}
				else { 
					repeatNewPassword = repeatNewPassword + e.getKeyChar(); 
					repeatNewPasswordTextField.setText(Util.getHiddenText(repeatNewPassword));
				} 
				repeatNewPasswordTextField.revalidate();
				super.keyPressed(e);
			}
		};
		
		repeatNewPasswordTextField.addKeyListener(hideRepeatNewPasswordListener);
		
		showReapeatNewPassword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(repeatNewPasswordTextField.getKeyListeners().length != 0) {
					showReapeatNewPassword.setIcon(new ImageIcon("img/eye-open.png"));
					repeatNewPasswordTextField.setText(repeatNewPassword);
					repeatNewPasswordTextField.revalidate();
					repeatNewPasswordTextField.removeKeyListener(hideRepeatNewPasswordListener);
				}
				else {
					showReapeatNewPassword.setIcon(new ImageIcon("img/closed-eye.png"));
					repeatNewPassword = repeatNewPasswordTextField.getText();
					repeatNewPasswordTextField.setText(Util.getHiddenText(repeatNewPassword));
					repeatNewPasswordTextField.addKeyListener(hideRepeatNewPasswordListener);
				}
			}
		});
		  
		JButton confirm = new JButton("Confirm");
		JButton cancel = new JButton("Cancel");
		
		confirm.addActionListener((e)->{
			String password = passwordTextField.getText();  
			if(!password.equals(Settings.loggedinUser.getPassword())) {
				JOptionPane.showMessageDialog(null, 
						"Password is incorrect!", 
						"Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(!newPassword.equals(repeatNewPassword)) {
				JOptionPane.showMessageDialog(null, 
						"Password and New Password must be equal!", "Error", 
						JOptionPane.ERROR_MESSAGE);
			
			}
			else {
				Settings.loggedinUser.setPassword(newPassword);
				DBHandler.db.updatePassword();
				setVisible(false);
				JOptionPane.showMessageDialog(null, 
						"Password was changed successfully!", 
						"Success", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		cancel.addActionListener((e)->{
			setVisible(false);
		});

		centerPanel.add(confirm);
		centerPanel.add(cancel); 
		 
		eastPanel.add(new JLabel());
		
		setMinimumSize(new Dimension(500, 160));
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		revalidate();

	}
	
}
