package entities;

import java.util.Date;

/**
 * @author Elroy, Vlad, Lior
 */
@SuppressWarnings("serial")
public class InventoryReport extends QuarterlyReport {

	// primary keys
	/* private int repQuarter; */
	/* private String repYear; */
	/* private int fuelStationID; */

	// fields
	private double totalAmountSold;

	/**
	 * 
	 * @param repQuarter
	 * @param repYear
	 * @param fuelStationID
	 * @param dateCreated
	 */
	public InventoryReport(int repQuarter, String repYear, int fuelStationID, Date dateCreated) {
		super(repQuarter, repYear, fuelStationID, dateCreated);
	}

	/**
	 * everything
	 * 
	 * @param repQuarter
	 * @param repYear
	 * @param fuelStationID
	 * @param dateCreated
	 * @param totalAmountSold
	 */
	public InventoryReport(int repQuarter, String repYear, int fuelStationID, Date dateCreated,
			double totalAmountSold) {
		super(repQuarter, repYear, fuelStationID, dateCreated);
		this.totalAmountSold = totalAmountSold;
	}

	public double getTotalAmountSold() {
		return totalAmountSold;
	}

	public void setTotalAmountSold(double totalAmountSold) {
		this.totalAmountSold = totalAmountSold;
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
