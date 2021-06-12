package model;

import java.util.ArrayList;

public class Course {

	private int systemId;
	private int numberOfCredits;
	private int semesterNumber;
	private String title, description, subject;
	private ArrayList<Announcement> announcements;
	private ArrayList<Rating> ratings;

	public Course(String title, String description, String subject, 
			int numberOfCredits, int semesterNumber) {
		super();
		this.numberOfCredits = numberOfCredits;
		this.semesterNumber = semesterNumber;
		this.title = title;
		this.description = description;
		this.subject = subject;
		this.announcements = new ArrayList<Announcement>();
		this.ratings = new ArrayList<Rating>();
	}

	public int getSystemId() {
		return systemId;
	}

	public void setSystemId(int systemId) {
		this.systemId = systemId;
	}

	public int getNumberOfCredits() {
		return numberOfCredits;
	}

	public void setNumberOfCredits(int numberOfCredits) {
		this.numberOfCredits = numberOfCredits;
	}

	public int getSemesterNumber() {
		return semesterNumber;
	}

	public void setSemesterNumber(int semesterNumber) {
		this.semesterNumber = semesterNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public ArrayList<Announcement> getAnnouncements() {
		return announcements;
	}

	public void setAnnouncements(ArrayList<Announcement> announcements) {
		this.announcements = announcements;
	}

	public ArrayList<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(ArrayList<Rating> ratings) {
		this.ratings = ratings;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((announcements == null) ? 0 : announcements.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + numberOfCredits;
		result = prime * result + ((ratings == null) ? 0 : ratings.hashCode());
		result = prime * result + semesterNumber;
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result + systemId;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

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
