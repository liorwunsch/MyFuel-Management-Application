package entities;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class QuarterlyReport implements Serializable {

	// primary keys
	private int repQuarter;
	private String repYear;
	private int fuelStationID;

	// fields
	private Date dateCreated;

	public QuarterlyReport(int repQuarter, String repYear, int fuelStationID, Date dateCreated) {
		super();
		this.repQuarter = repQuarter;
		this.repYear = repYear;
		this.fuelStationID = fuelStationID;
		this.dateCreated = dateCreated;
	}

	public int getRepQuarter() {
		return repQuarter;
	}

	public String getRepYear() {
		return repYear;
	}

	public int getFuelStationID() {
		return fuelStationID;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Override
	public String toString() {
		return "QuarterlyReport [repQuarter=" + repQuarter + ", repYear=" + repYear + ", fuelStationID=" + fuelStationID
				+ ", dateCreated=" + dateCreated + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof QuarterlyReport))
			return false;
		QuarterlyReport other = (QuarterlyReport) obj;
		return this.repQuarter == other.repQuarter && this.repYear.equals(other.repYear);
	}

}
