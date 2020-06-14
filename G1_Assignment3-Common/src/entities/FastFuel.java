package entities;

import java.io.Serializable;
import java.util.Date;

import enums.FuelCompanyName;
import enums.ProductName;

/**
 * @author Elroy, Vlad, Lior
 */
@SuppressWarnings("serial")
public class FastFuel implements Serializable {

	// primary keys
	private Integer fastFuelID; // auto-inc

	// foreign keys
	private String registrationPlate;
	private String customerID;
	private int productInStationID; // was renamed

	// fields
	private Date fastFuelTime;
	private double amountBought;
	private Double finalPrice;

	// added in java - can be null
	private ProductName fuelType; // == productName
	private String fuelStationName;
	private int fuelStationID;
	private FuelCompanyName fuelCompanyName;
	private Integer saleID;
	private String function;

	/**
	 * empty constructor but regPlate
	 */
	public FastFuel(String registrationPlate) {
		super();
		this.registrationPlate = registrationPlate;
	}

	/**
	 * without fastFuelID auto-inc
	 * 
	 * @param registrationPlate
	 * @param customerID
	 * @param productInStationID
	 * @param fastFuelTime
	 * @param amountBought
	 * @param finalPrice
	 */
	public FastFuel(String registrationPlate, String customerID, int productInStationID, Date fastFuelTime,
			double amountBought, double finalPrice) {
		super();
		this.registrationPlate = registrationPlate;
		this.customerID = customerID;
		this.productInStationID = productInStationID;
		this.fastFuelTime = fastFuelTime;
		this.amountBought = amountBought;
		this.finalPrice = finalPrice;
	}

	/**
	 * with fastFuelID
	 * 
	 * @param registrationPlate
	 * @param fastFuelID
	 * @param customerID
	 * @param productInStationID
	 * @param fastFuelTime
	 * @param amountBought
	 * @param finalPrice
	 */
	public FastFuel(String registrationPlate, int fastFuelID, String customerID, int productInStationID,
			Date fastFuelTime, double amountBought, double finalPrice) {
		super();
		this.registrationPlate = registrationPlate;
		this.fastFuelID = fastFuelID;
		this.customerID = customerID;
		this.productInStationID = productInStationID;
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

	public int getProductInStationID() {
		return productInStationID;
	}

	public void setProductInStationID(int productInStationID) {
		this.productInStationID = productInStationID;
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

	public Double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public ProductName getFuelType() {
		return fuelType;
	}

	public void setFuelType(ProductName fuelType) {
		this.fuelType = fuelType;
	}

	public String getFuelStationName() {
		return fuelStationName;
	}

	public void setFuelStationName(String fuelStationName) {
		this.fuelStationName = fuelStationName;
	}

	public int getFuelStationID() {
		return fuelStationID;
	}

	public void setFuelStationID(int fuelStationID) {
		this.fuelStationID = fuelStationID;
	}

	public FuelCompanyName getFuelCompanyName() {
		return fuelCompanyName;
	}

	public void setFuelCompanyName(FuelCompanyName fuelCompanyName) {
		this.fuelCompanyName = fuelCompanyName;
	}

	public Integer getSaleID() {
		return saleID;
	}

	public void setSaleID(int saleID) {
		this.saleID = saleID;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	@Override
	public String toString() {
		String str = "FastFuel [";
		if (fastFuelID != null)
			str += "fastFuelID=" + fastFuelID + ", ";
		str += "registrationPlate=" + registrationPlate + ", customerID=" + customerID + ", productInStaionID="
				+ productInStationID + ", fastFuelTime=" + fastFuelTime + ", amountBought=" + amountBought
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
