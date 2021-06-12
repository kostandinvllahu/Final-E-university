package model;

import java.util.ArrayList;

public class Course {

	/* Here are delcared the system ID, number of credits, semester number
	 * title, description, subject, announcement, and ratings for the classes
	 * */
	private int systemId;
	private int numberOfCredits;
	private int semesterNumber;
	private String title, description, subject;
	private ArrayList<Announcement> announcements;
	private ArrayList<Rating> ratings;

	/*In the public course are created the string and int for the data to be inserted from the database
	 * */
	public Course(String title, String description, String subject, 
			int numberOfCredits, int semesterNumber) {
		super();
		this.numberOfCredits = numberOfCredits; //this gets the number of credits
		this.semesterNumber = semesterNumber;//this gets the number of semester
		this.title = title;//this gets the title
		this.description = description; //this gets the description
		this.subject = subject;//this gets the subject
		this.announcements = new ArrayList<Announcement>(); //the announcement is placed in a new arraylist
		this.ratings = new ArrayList<Rating>();//the ratings are placed in a new arraylist
	}

	public int getSystemId() {
		return systemId; //the systemId is taken and passed to the systemId
	}
//and the value of systemId is passed to the other systemId
	public void setSystemId(int systemId) {
		this.systemId = systemId;
	}
//the number of credits is taken and passed to the number of credits
	public int getNumberOfCredits() {
		return numberOfCredits;
	}
//and is passed to this number of credits
	public void setNumberOfCredits(int numberOfCredits) {
		this.numberOfCredits = numberOfCredits;
	}
//the semester number is taken and passed to the semester number at the setSemesternumber
	public int getSemesterNumber() {
		return semesterNumber;
	}

	public void setSemesterNumber(int semesterNumber) {
		this.semesterNumber = semesterNumber;
	}
//title is taken from title and is passed to title
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
//description is taken from description and is passed at setdescription in the description
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
//subject is taken from getsubject and is passed at the setsubject
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
//announcemements are taken from the getAnnouncemenets and are passed at setAnnouncements
	public ArrayList<Announcement> getAnnouncements() {
		return announcements;
	}

	public void setAnnouncements(ArrayList<Announcement> announcements) {
		this.announcements = announcements;
	}
	//Ratings are taken from the geRatings and are passed at setRatings
	public ArrayList<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(ArrayList<Rating> ratings) {
		this.ratings = ratings;
	}

	@Override
	public int hashCode() {
		final int prime = 31;//final shows that this number can not be modified in the futre
		int result = 1;
		result = prime * result + ((announcements == null) ? 0 : announcements.hashCode());//if the announcements is null the hashcode generates a value
		result = prime * result + ((description == null) ? 0 : description.hashCode());//if the description is null the hashcode generates a value
		result = prime * result + numberOfCredits;//if the number of credits is null the hashcode generates a value
		result = prime * result + ((ratings == null) ? 0 : ratings.hashCode()); //if the ratings is null the hashcode generates a value
		result = prime * result + semesterNumber; //if the semesterNumber is null the hashcode generates a value
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());//if the subject is null the hashcode generates a value
		result = prime * result + systemId;//if the systemId is null the hashcode generates a value
		result = prime * result + ((title == null) ? 0 : title.hashCode());//if the title is null the hashcode generates a value
		return result;
	}
	/*A boolean is created  to check if any of the data is emtpy and if any of them is empty the
	 * boolean equals turns false else true
	 * */

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Course)) {
			return false;
		}
		Course other = (Course) obj;
		if (announcements == null) {
			if (other.announcements != null) {
				return false;
			}
		} else if (!announcements.equals(other.announcements)) {
			return false;
		}
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (numberOfCredits != other.numberOfCredits) {
			return false;
		}
		if (ratings == null) {
			if (other.ratings != null) {
				return false;
			}
		} else if (!ratings.equals(other.ratings)) {
			return false;
		}
		if (semesterNumber != other.semesterNumber) {
			return false;
		}
		if (subject == null) {
			if (other.subject != null) {
				return false;
			}
		} else if (!subject.equals(other.subject)) {
			return false;
		}
		if (systemId != other.systemId) {
			return false;
		}
		if (title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!title.equals(other.title)) {
			return false;
		}
		return true;
	}

}
