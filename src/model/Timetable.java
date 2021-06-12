package model;

import java.util.GregorianCalendar;
import java.util.HashMap;

public class Timetable {

	private int systemId;
	private String title;
	private GregorianCalendar startDate, endDate;
	private HashMap<Course, Schedule> info;
	
	public Timetable(String title, 
			GregorianCalendar startDate, 
			GregorianCalendar endDate) {
		super();
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.info = new HashMap<Course, Schedule>();
	}

	public int getSystemId() {
		return systemId;
	}

	public void setSystemId(int systemId) {
		this.systemId = systemId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public GregorianCalendar getStartDate() {
		return startDate;
	}

	public void setStartDate(GregorianCalendar startDate) {
		this.startDate = startDate;
	}

	public GregorianCalendar getEndDate() {
		return endDate;
	}

	public void setEndDate(GregorianCalendar endDate) {
		this.endDate = endDate;
	}

	public HashMap<Course, Schedule> getInfo() {
		return info;
	}

	public void setInfo(HashMap<Course, Schedule> info) {
		this.info = info;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((info == null) ? 0 : info.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + systemId;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Timetable)) {
			return false;
		}
		Timetable other = (Timetable) obj;
		if (endDate == null) {
			if (other.endDate != null) {
				return false;
			}
		} else if (!endDate.equals(other.endDate)) {
			return false;
		}
		if (info == null) {
			if (other.info != null) {
				return false;
			}
		} else if (!info.equals(other.info)) {
			return false;
		}
		if (startDate == null) {
			if (other.startDate != null) {
				return false;
			}
		} else if (!startDate.equals(other.startDate)) {
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
