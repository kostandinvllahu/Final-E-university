package controll;

import javax.swing.UIManager;

import view.LoginWindow;

public class MainClass {
	
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		new LoginWindow().setVisible(true);  
		
		
	}
	
	

}
