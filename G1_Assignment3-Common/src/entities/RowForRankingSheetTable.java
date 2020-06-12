package entities;

import java.io.Serializable;

/**
 * 
 * @author Elroy
 */
@SuppressWarnings("serial")
public class RowForRankingSheetTable implements Serializable {
	private String customerID;
	private double customerTypeRank;
	private double fuelingHoursRank;
	private double fuelTypesRank;

	public RowForRankingSheetTable(String customerID, double customerTypeRank, double fuelingHoursRank,
			double fuelTypesRank) {
		super();
		this.customerID = customerID;
		this.customerTypeRank = customerTypeRank;
		this.fuelingHoursRank = fuelingHoursRank;
		this.fuelTypesRank = fuelTypesRank;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public double getCustomerTypeRank() {
		return customerTypeRank;
	}

	public void setCustomerTypeRank(double customerTypeRank) {
		this.customerTypeRank = customerTypeRank;
	}

	public double getFuelingHoursRank() {
		return fuelingHoursRank;
	}

	public void setFuelingHoursRank(double fuelingHoursRank) {
		this.fuelingHoursRank = fuelingHoursRank;
	}

	public double getFuelTypesRank() {
		return fuelTypesRank;
	}

	public void setFuelTypesRank(double fuelTypesRank) {
		this.fuelTypesRank = fuelTypesRank;
	}

	@Override
	public String toString() {
		return "RowForRankingSheetTable [customerID=" + customerID + ", customerTypeRank=" + customerTypeRank
				+ ", fuelingHoursRank=" + fuelingHoursRank + ", fuelTypesRank=" + fuelTypesRank + "]";
	}

}
