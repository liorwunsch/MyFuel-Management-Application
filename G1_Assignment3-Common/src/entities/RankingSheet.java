package entities;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class RankingSheet implements Serializable {

	// primary keys
	private String customerID;

	// fields
	private double customerTypeRank;
	private double fuelingHoursRank;
	private double fuelTypesRank;
	private Date updatedForDate;

	public RankingSheet(String customerID, double customerTypeRank, double fuelingHoursRank, double fuelTypesRank,
			Date updatedForDate) {
		super();
		this.customerID = customerID;
		this.customerTypeRank = customerTypeRank;
		this.fuelingHoursRank = fuelingHoursRank;
		this.fuelTypesRank = fuelTypesRank;
		this.updatedForDate = updatedForDate;
	}

	public String getCustomerID() {
		return customerID;
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

	public Date getUpdatedForDate() {
		return updatedForDate;
	}

	public void setUpdatedForDate(Date updatedForDate) {
		this.updatedForDate = updatedForDate;
	}

	@Override
	public String toString() {
		return "RankingSheet [customerID=" + customerID + ", customerTypeRank=" + customerTypeRank
				+ ", fuelingHoursRank=" + fuelingHoursRank + ", fuelTypesRank=" + fuelTypesRank + ", updatedForDate="
				+ updatedForDate + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof RankingSheet))
			return false;
		RankingSheet rank = (RankingSheet) obj;
		return this.customerID.equals(rank.customerID);
	}

}
