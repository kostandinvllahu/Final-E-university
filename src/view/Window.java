package view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import controll.Settings;

public class Window extends JFrame {

	private static final long serialVersionUID = -3739008754324139579L;
	
	public Window() {
		super(Settings.SYSTEM_TITLE);
		setSize(960, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setIconImage(new ImageIcon("img/logo.png").getImage());
	}

}
