package entities;

import java.io.Serializable;
import enums.PricingModelName;

@SuppressWarnings("serial")
public class PricingModel implements Serializable {

	// primary keys
	private String customerID;

	// foreign keys
	private PricingModelName pricingModelName;

	// fields
	private double currentDiscount;
	private double lastMonthUtilization; // can be NULL

	public PricingModel(String customerID, PricingModelName pricingModelName, double currentDiscount) {
		super();
		this.customerID = customerID;
		this.pricingModelName = pricingModelName;
		this.currentDiscount = currentDiscount;
	}

	public PricingModel(String customerID, PricingModelName pricingModelName, double currentDiscount,
			double lastMonthUtilization) {
		super();
		this.customerID = customerID;
		this.pricingModelName = pricingModelName;
		this.currentDiscount = currentDiscount;
		this.lastMonthUtilization = lastMonthUtilization;
	}

	public String getCustomerID() {
		return customerID;
	}

	public PricingModelName getPricingModelName() {
		return pricingModelName;
	}

	public void setPricingModelName(PricingModelName pricingModelName) {
		this.pricingModelName = pricingModelName;
	}

	public double getCurrentDiscount() {
		return currentDiscount;
	}

	public void setCurrentDiscount(double currentDiscount) {
		this.currentDiscount = currentDiscount;
	}

	public double getLastMonthUtilization() {
		return lastMonthUtilization;
	}

	public void setLastMonthUtilization(double lastMonthUtilization) {
		this.lastMonthUtilization = lastMonthUtilization;
	}

	@Override
	public String toString() {
		String str = "PricingModel [customerID=" + customerID + ", pricingModelName=" + pricingModelName
				+ ", currentDiscount=" + currentDiscount;
		if (this.pricingModelName.equals(PricingModelName.FullProgramSingleCar))
			str += ", lastMonthUtilization=" + lastMonthUtilization + "]";
		return str + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof PricingModel))
			return false;
		PricingModel other = (PricingModel) obj;
		return this.customerID.equals(other.customerID);
	}

}
