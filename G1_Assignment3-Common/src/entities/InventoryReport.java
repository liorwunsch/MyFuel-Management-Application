package entities;

import java.util.Date;

@SuppressWarnings("serial")
public class InventoryReport extends QuarterlyReport {

	// primary keys
	/* private int repQuarter; */
	/* private String repYear; */
	/* private int fuelStationID; */

	public InventoryReport(int repQuarter, String repYear, int fuelStationID, Date dateCreated) {
		super(repQuarter, repYear, fuelStationID, dateCreated);
	}

	@Override
	public String toString() {
		return "InventoryReport [" + super.toString() + "]";
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

}
