package entities;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CustomerBoughtInSale implements Serializable {

	// primary keys
	private int saleID;
	private String customerID;

	// fields
	private double amountPaid;

	public CustomerBoughtInSale(int saleID, String customerID, double amountPaid) {
		super();
		this.saleID = saleID;
		this.customerID = customerID;
		this.amountPaid = amountPaid;
	}

	public int getSaleID() {
		return saleID;
	}

	public String getCustomerID() {
		return customerID;
	}

	public double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

	@Override
	public String toString() {
		return "CustomerBoughtInSale [saleID=" + saleID + ", customerID=" + customerID + ", amountPaid=" + amountPaid
				+ "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof CustomerBoughtInSale))
			return false;
		CustomerBoughtInSale other = (CustomerBoughtInSale) obj;
		return this.saleID == other.saleID && this.customerID.equals(other.customerID);
	}

}
