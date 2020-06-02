package entities;

import enums.ProductName;

/**
 * @author Elroy, Vlad, Lior
 */
@SuppressWarnings("serial")
public class ProductInStation extends Product {

	// primary keys
	private Integer productInStationID;

	// foreign keys
	/* private ProductName productName; */
	private int fuelStationID;

	// fields
	private int capacity;
	private int thresholdLevel;

	/**
	 * w/0 productInStationID auto-inc
	 * 
	 * @param productName
	 * @param maxPrice
	 * @param currentPrice
	 * @param fuelStationID
	 * @param capacity
	 * @param thresholdLevel
	 */
	public ProductInStation(ProductName productName, double maxPrice, double currentPrice, int fuelStationID,
			int capacity, int thresholdLevel) {
		super(productName, maxPrice, currentPrice);
		this.fuelStationID = fuelStationID;
		this.capacity = capacity;
		this.thresholdLevel = thresholdLevel;
	}

	/**
	 * 
	 * @param productName
	 * @param maxPrice
	 * @param currentPrice
	 * @param productInStationID
	 * @param fuelStationID
	 * @param capacity
	 * @param thresholdLevel
	 */
	public ProductInStation(int productInStationID, ProductName productName, double maxPrice, double currentPrice,
			int fuelStationID, int capacity, int thresholdLevel) {
		super(productName, maxPrice, currentPrice);
		this.productInStationID = productInStationID;
		this.fuelStationID = fuelStationID;
		this.capacity = capacity;
		this.thresholdLevel = thresholdLevel;
	}

	public int getProductInStationID() {
		return productInStationID;
	}

	public int getFuelStationID() {
		return fuelStationID;
	}

	public void setFuelStationID(int fuelStationID) {
		this.fuelStationID = fuelStationID;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getThresholdLevel() {
		return thresholdLevel;
	}

	public void setThresholdLevel(int thresholdLevel) {
		this.thresholdLevel = thresholdLevel;
	}

	@Override
	public String toString() {
		String str = "ProductInStation [" + super.toString() + ", ";
		if (productInStationID != null)
			str += "productInStationID=" + productInStationID + ", ";
		str += ", fuelStationID=" + fuelStationID + ", capacity=" + capacity + ", thresholdLevel=" + thresholdLevel
				+ "]";
		return str;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ProductInStation))
			return false;
		ProductInStation other = (ProductInStation) obj;
		if (this.productInStationID == null || other.productInStationID == null)
			return false;
		return this.productInStationID.equals(other.productInStationID);
	}

}
