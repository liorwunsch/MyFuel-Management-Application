package entities;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SalesPattern implements Serializable {

	// primary keys
	private Integer salesPatternID;

	// fields
	private int durationInMinutes;

	public SalesPattern(int salesPatternID, int durationInMinutes) {
		super();
		this.salesPatternID = salesPatternID;
		this.durationInMinutes = durationInMinutes;
	}

	public int getSalesPatternID() {
		return salesPatternID;
	}

	public int getDurationInMinutes() {
		return durationInMinutes;
	}

	public void setDurationInMinutes(int durationInMinutes) {
		this.durationInMinutes = durationInMinutes;
	}

	@Override
	public String toString() {
		String str = "SalesPattern [";
		if (salesPatternID != null)
			str += "salesPatternID=" + salesPatternID + ", ";
		str += "durationInMinutes=" + durationInMinutes + "]";
		return str;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof SalesPattern))
			return false;
		SalesPattern other = (SalesPattern) obj;
		if (this.salesPatternID == null || other.salesPatternID == null)
			return false;
		return this.salesPatternID.equals(other.salesPatternID);
	}

}
