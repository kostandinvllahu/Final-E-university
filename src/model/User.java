package model;

import java.util.ArrayList;
import java.util.GregorianCalendar; 

public class User extends Person  {
	
    private String username;
    private String password;
    private boolean active;

    public User(String username, String password){
        this(username, password, true);
    }

    public User(String username, String password, boolean active){
        this("", "", "", new GregorianCalendar(1970, 0, 1), 
        		Gender.OTHER, null, new ArrayList<String>(),
        		new ArrayList<String>(), username, password, active);
    }
     
	public User(String nationalId, String name, 
			String surname, GregorianCalendar birthday,
			Gender gender, String address, 
			ArrayList<String> phoneNumbers, 
			ArrayList<String> emails,
			String username, String password, boolean active) {
		super(nationalId, name, surname, birthday, 
				gender, address, phoneNumbers, emails);
        this.username = username;
        this.password = password;
        this.active = active;
	}

	public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "model.User [username=" + username + ", password=" + 
				password + ", active=" + active + ", toString()="
				+ super.toString() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

}
