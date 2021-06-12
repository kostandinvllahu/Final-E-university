package model;

public class Schedule {

	private int systemId;
	private String dayOfWeek;
	private String timePeriod;
	private String location;

	public Schedule(String dayOfWeek, String timePeriod, String location) {
		super();
		this.dayOfWeek = dayOfWeek;
		this.timePeriod = timePeriod;
		this.location = location;
	}

	public int getSystemId() {
		return systemId;
	}

	public void setSystemId(int systemId) {
		this.systemId = systemId;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public String getTimePeriod() {
		return timePeriod;
	}

	public void setTimePeriod(String timePeriod) {
		this.timePeriod = timePeriod;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dayOfWeek == null) ? 0 : dayOfWeek.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + systemId;
		result = prime * result + ((timePeriod == null) ? 0 : timePeriod.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Schedule)) {
			return false;
		}
		Schedule other = (Schedule) obj;
		if (dayOfWeek == null) {
			if (other.dayOfWeek != null) {
				return false;
			}
		} else if (!dayOfWeek.equals(other.dayOfWeek)) {
			return false;
		}
		if (location == null) {
			if (other.location != null) {
				return false;
			}
		} else if (!location.equals(other.location)) {
			return false;
		}
		if (systemId != other.systemId) {
			return false;
		}
		if (timePeriod == null) {
			if (other.timePeriod != null) {
				return false;
			}
		} else if (!timePeriod.equals(other.timePeriod)) {
			return false;
		}
		return true;
	}

}
