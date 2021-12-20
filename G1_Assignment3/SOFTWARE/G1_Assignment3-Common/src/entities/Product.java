package entities;

import java.io.Serializable;

import enums.ProductName;

/**
 * @author Elroy, Vlad, Lior
 */
@SuppressWarnings("serial")
public class Product implements Serializable {

	// primary keys
	private ProductName productName;

	// fields
	private double maxPrice;
	private double currentPrice;

	/**
	 * 
	 * @param productName
	 * @param maxPrice
	 * @param currentPrice
	 */
	public Product(ProductName productName, double maxPrice, double currentPrice) {
		super();
		this.productName = productName;
		this.maxPrice = maxPrice;
		this.currentPrice = currentPrice;
	}

	public ProductName getProductName() {
		return productName;
	}

	public double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public double getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}

	@Override
	public String toString() {
		return "Product [productName=" + productName + ", maxPrice=" + maxPrice + ", currentPrice=" + currentPrice
				+ "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Product))
			return false;
		Product other = (Product) obj;
		return this.productName.equals(other.getProductName());
	}

}
