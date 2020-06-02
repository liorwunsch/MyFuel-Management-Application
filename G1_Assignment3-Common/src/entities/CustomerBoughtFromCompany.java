package entities;

import java.io.Serializable;
import java.util.Date;

import enums.FuelCompanyName;

@SuppressWarnings("serial")
public class CustomerBoughtFromCompany implements Serializable {

	// primary keys
	private String customerID;
	private FuelCompanyName fuelCompanyName;
	private Date dateOfPurchase;

	// fields
	private double amountBoughtFromCompany;
	private double amountPaidCompany;

	public CustomerBoughtFromCompany(String customerID, FuelCompanyName fuelCompanyName, Date dateOfPurchase,
			double amountBoughtFromCompany, double amountPaidCompany) {
		super();
		this.customerID = customerID;
		this.fuelCompanyName = fuelCompanyName;
		this.dateOfPurchase = dateOfPurchase;
		this.amountBoughtFromCompany = amountBoughtFromCompany;
		this.amountPaidCompany = amountPaidCompany;
	}

	public String getCustomerID() {
		return customerID;
	}

	public FuelCompanyName getFuelCompanyName() {
		return fuelCompanyName;
	}

	public Date getDateOfPurchase() {
		return dateOfPurchase;
	}

	public double getAmountBoughtFromCompany() {
		return amountBoughtFromCompany;
	}

	public void setAmountBoughtFromCompany(double amountBoughtFromCompany) {
		this.amountBoughtFromCompany = amountBoughtFromCompany;
	}

	public double getAmountPaidCompany() {
		return amountPaidCompany;
	}

	public void setAmountPaidCompany(double amountPaidCompany) {
		this.amountPaidCompany = amountPaidCompany;
	}

	@Override
	public String toString() {
		return "CustomerBoughtFromCompany [customerID=" + customerID + ", fuelCompanyName=" + fuelCompanyName
				+ ", dateOfPurchase=" + dateOfPurchase + ", amountBoughtFromCompany=" + amountBoughtFromCompany
				+ ", amountPaidCompany=" + amountPaidCompany + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof CustomerBoughtFromCompany))
			return false;
		CustomerBoughtFromCompany other = (CustomerBoughtFromCompany) obj;
		return this.customerID.equals(other.customerID) && this.fuelCompanyName.equals(other.fuelCompanyName)
				&& this.dateOfPurchase.equals(other.dateOfPurchase);
	}

}