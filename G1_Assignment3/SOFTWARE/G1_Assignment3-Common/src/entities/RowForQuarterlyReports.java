package entities;

import java.io.Serializable;

/**
 * 
 * @author Liad
 *
 */
@SuppressWarnings("serial")
public class RowForQuarterlyReports implements Serializable {
	private int productID;
	private String productName;
	private Double x;

	public RowForQuarterlyReports(int productID, String productName, Double x) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.x = x;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	@Override
	public String toString() {
		return "RowForQuarterlyReports [productID=" + productID + ", productName=" + productName + ", x=" + x + "]";
	}

}
