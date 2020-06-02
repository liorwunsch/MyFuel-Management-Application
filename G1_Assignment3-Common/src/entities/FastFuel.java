package entities;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Elroy, Vlad, Lior
 */
@SuppressWarnings("serial")
public class FastFuel implements Serializable {

	// primary keys
	private Integer fastFuelID;

	// foreign keys
	private String registrationPlate;
	private String customerID;
	private int productInStaionID;

	// fields
	private Date fastFuelTime;
	private double amountBought;
	private double finalPrice;

	/**
	 * without fastFuelID auto-inc
	 * @param registrationPlate
	 * @param customerID
	 * @param productInStaionID
	 * @param fastFuelTime
	 * @param amountBought
	 * @param finalPrice
	 */
	public FastFuel(String registrationPlate, String customerID, int productInStaionID, Date fastFuelTime,
			double amountBought, double finalPrice) {
		super();
		this.registrationPlate = registrationPlate;
		this.customerID = customerID;
		this.productInStaionID = productInStaionID;
		this.fastFuelTime = fastFuelTime;
		this.amountBought = amountBought;
		this.finalPrice = finalPrice;
	}

	/**
	 * with fastFuelID
	 * @param registrationPlate
	 * @param fastFuelID
	 * @param customerID
	 * @param productInStaionID
	 * @param fastFuelTime
	 * @param amountBought
	 * @param finalPrice
	 */
	public FastFuel(String registrationPlate, int fastFuelID, String customerID, int productInStaionID,
			Date fastFuelTime, double amountBought, double finalPrice) {
		super();
		this.registrationPlate = registrationPlate;
		this.fastFuelID = fastFuelID;
		this.customerID = customerID;
		this.productInStaionID = productInStaionID;
		this.fastFuelTime = fastFuelTime;
		this.amountBought = amountBought;
		this.finalPrice = finalPrice;
	}

	public int getFastFuelID() {
		return fastFuelID;
	}

	public String getRegistrationPlate() {
		return registrationPlate;
	}

	public void setRegistrationPlate(String registrationPlate) {
		this.registrationPlate = registrationPlate;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public int getProductInStaionID() {
		return productInStaionID;
	}

	public void setProductInStaionID(int productInStaionID) {
		this.productInStaionID = productInStaionID;
	}

	public Date getFastFuelTime() {
		return fastFuelTime;
	}

	public void setFastFuelTime(Date fastFuelTime) {
		this.fastFuelTime = fastFuelTime;
	}

	public double getAmountBought() {
		return amountBought;
	}

	public void setAmountBought(double amountBought) {
		this.amountBought = amountBought;
	}

	public double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}

	@Override
	public String toString() {
		String str = "FastFuel [";
		if (fastFuelID != null)
			str += "fastFuelID=" + fastFuelID + ", ";
		str += "registrationPlate=" + registrationPlate + ", customerID=" + customerID + ", productInStaionID="
				+ productInStaionID + ", fastFuelTime=" + fastFuelTime + ", amountBought=" + amountBought
				+ ", finalPrice=" + finalPrice + "]";
		return str;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof FastFuel))
			return false;
		FastFuel other = (FastFuel) obj;
		if (this.fastFuelID == null || other.fastFuelID == null)
			return false;
		return this.fastFuelID.equals(other.fastFuelID);
	}

}
