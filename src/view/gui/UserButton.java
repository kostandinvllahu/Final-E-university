package view.gui;

import javax.swing.JButton;

import model.User;

public class UserButton extends JButton {

	private static final long serialVersionUID = -551678787441350038L;
	private User user;

	public UserButton(String txt, User user) {
		super(txt);
		this.setUser(user);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}