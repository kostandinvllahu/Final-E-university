package model;

import java.util.GregorianCalendar;

public class Rating {

	private int systemId;
	private Student student;
	private GregorianCalendar date;
	private String comment;
	private int ratingValue; // ex. 1-5 stars
	
	public Rating(Student student, GregorianCalendar date, 
			String comment, int ratingValue) {
		super();
		this.student = student;
		this.date = date;
		this.comment = comment;
		this.ratingValue = ratingValue;
	}

	public Rating(Student student, String comment, int ratingValue) {
		this(student, new GregorianCalendar(), comment, ratingValue);
	}

	public int getSystemId() {
		return systemId;
	}

	public void setSystemId(int systemId) {
		this.systemId = systemId;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public GregorianCalendar getDate() {
		return date;
	}

	public void setDate(GregorianCalendar date) {
		this.date = date;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getRatingValue() {
		return ratingValue;
	}

	public void setRatingValue(int ratingValue) {
		this.ratingValue = ratingValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ratingValue;
		result = prime * result + ((student == null) ? 0 : student.hashCode());
		result = prime * result + systemId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Rating)) {
			return false;
		}
		Rating other = (Rating) obj;
		if (comment == null) {
			if (other.comment != null) {
				return false;
			}
		} else if (!comment.equals(other.comment)) {
			return false;
		}
		if (date == null) {
			if (other.date != null) {
				return false;
			}
		} else if (!date.equals(other.date)) {
			return false;
		}
		if (ratingValue != other.ratingValue) {
			return false;
		}
		if (student == null) {
			if (other.student != null) {
				return false;
			}
		} else if (!student.equals(other.student)) {
			return false;
		}
		if (systemId != other.systemId) {
			return false;
		}
		return true;
	}
	
}
