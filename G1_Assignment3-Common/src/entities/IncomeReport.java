package entities;

import java.util.Date;

@SuppressWarnings("serial")
public class IncomeReport extends QuarterlyReport {

	// primary keys
	/* private int repQuarter; */
	/* private String repYear; */
	/* private int fuelStationID; */

	// fields
	private double totalIncome;

	public IncomeReport(int repQuarter, String repYear, int fuelStationID, Date dateCreated, double totalIncome) {
		super(repQuarter, repYear, fuelStationID, dateCreated);
		this.totalIncome = totalIncome;
	}

	public double getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(double totalIncome) {
		this.totalIncome = totalIncome;
	}

	@Override
	public String toString() {
		return "IncomeReport [" + super.toString() + ", totalIncome=" + totalIncome + "]";
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

}
