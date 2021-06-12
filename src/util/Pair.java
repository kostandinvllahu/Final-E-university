package util;

import java.io.Serializable;

public class Pair<T1, T2> implements Serializable {

	private static final long serialVersionUID = 2034595968431825331L;

	private T1 info1;
	private T2 info2;

	public T1 getInfo1() {
		return info1;
	}

	public void setInfo1(T1 info1) {
		this.info1 = info1;
	}

	public T2 getInfo2() {
		return info2;
	}

	public void setInfo2(T2 info2) {
		this.info2 = info2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((info1 == null) ? 0 : info1.hashCode());
		result = prime * result + ((info2 == null) ? 0 : info2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("rawtypes")
		Pair other = (Pair) obj;
		if (info1 == null) {
			if (other.info1 != null)
				return false;
		} else if (!info1.equals(other.info1))
			return false;
		if (info2 == null) {
			if (other.info2 != null)
				return false;
		} else if (!info2.equals(other.info2))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pair [info1=" + info1 + ", info2=" + info2 + "]";
	}

	public Pair(T1 info1, T2 info2) {
		super();
		this.info1 = info1;
		this.info2 = info2;
	}
	
	public Pair() {
	}

}
