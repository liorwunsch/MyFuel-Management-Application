package entities;

import java.util.Date;

/**
 * @author Elroy, Vlad, Lior
 */
@SuppressWarnings("serial")
public class OutcomeReport extends QuarterlyReport {

	// primary keys
	/* private int repQuarter; */
	/* private String repYear; */
	/* private int fuelStationID; */

	// fields
	private double totalAmountBoughtFromSupplier;

	/**
	 * 
	 * @param repQuarter
	 * @param repYear
	 * @param fuelStationID
	 * @param dateCreated
	 */
	public OutcomeReport(int repQuarter, String repYear, int fuelStationID, Date dateCreated) {
		super(repQuarter, repYear, fuelStationID, dateCreated);
	}

	/**
	 * everything
	 * 
	 * @param repQuarter
	 * @param repYear
	 * @param fuelStationID
	 * @param dateCreated
	 * @param totalAmountBoughtFromSupplier
	 */
	public OutcomeReport(int repQuarter, String repYear, int fuelStationID, Date dateCreated,
			double totalAmountBoughtFromSupplier) {
		super(repQuarter, repYear, fuelStationID, dateCreated);
		this.totalAmountBoughtFromSupplier = totalAmountBoughtFromSupplier;
	}

	public double getTotalAmountBoughtFromSupplier() {
		return totalAmountBoughtFromSupplier;
	}

	public void setTotalAmountBoughtFromSupplier(double totalAmountBoughtFromSupplier) {
		this.totalAmountBoughtFromSupplier = totalAmountBoughtFromSupplier;
	}

	@Override
	public String toString() {
		return "OutcomeReport [" + super.toString() + "]";
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

}
