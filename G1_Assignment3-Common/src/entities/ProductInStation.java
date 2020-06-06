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
	private double capacity;
	private double threshold; // was renamed

	/**
	 * w/0 productInStationID auto-inc
	 * 
	 * @param productName
	 * @param maxPrice
	 * @param currentPrice
	 * @param fuelStationID
	 * @param capacity
	 * @param threshold
	 */
	public ProductInStation(ProductName productName, double maxPrice, double currentPrice, int fuelStationID,
			double capacity, double threshold) {
		super(productName, maxPrice, currentPrice);
		this.fuelStationID = fuelStationID;
		this.capacity = capacity;
		this.threshold = threshold;
	}

	/**
	 * 
	 * @param productName
	 * @param maxPrice
	 * @param currentPrice
	 * @param productInStationID
	 * @param fuelStationID
	 * @param capacity
	 * @param threshold
	 */
	public ProductInStation(int productInStationID, ProductName productName, double maxPrice, double currentPrice,
			int fuelStationID, double capacity, double threshold) {
		super(productName, maxPrice, currentPrice);
		this.productInStationID = productInStationID;
		this.fuelStationID = fuelStationID;
		this.capacity = capacity;
		this.threshold = threshold;
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

	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

	public double getThreshold() {
		return threshold;
	}

	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}

	@Override
	public String toString() {
		String str = "ProductInStation [" + super.toString() + ", ";
		if (productInStationID != null)
			str += "productInStationID=" + productInStationID + ", ";
		str += ", fuelStationID=" + fuelStationID + ", capacity=" + capacity + ", thresholdLevel=" + threshold
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
