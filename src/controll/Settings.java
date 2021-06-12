package controll;

import java.awt.Color;

import data.DBHandler;
import model.User;

public class Settings {

	public static String SYSTEM_TITLE = DBHandler.db.getSystemTitle(),
						 SYSTEM_EMAIL = DBHandler.db.getSystemEmail(),
						 SYSTEM_EMAIL_PASSWORD = DBHandler.db.getSystemEmailPassword();
	public static final Color COLOR1 = new Color(230, 230, 230);
	public static final Color COLOR2 = Color.BLACK;
	public static User loggedinUser;
	
}
