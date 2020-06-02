package entities;

import java.io.Serializable;
import enums.ProductName;

@SuppressWarnings("serial")
public class ProductInRequest implements Serializable {

	// primary keys
	private int productRatesUpdateRequestID;
	private ProductName productName;

	// fields
	private double requestedRate;

	public ProductInRequest(int productRatesUpdateRequestID, ProductName productName, double requestedRate) {
		super();
		this.productRatesUpdateRequestID = productRatesUpdateRequestID;
		this.productName = productName;

		this.requestedRate = requestedRate;
	}

	public int getProductRatesUpdateRequestID() {
		return productRatesUpdateRequestID;
	}

	public ProductName getProductName() {
		return productName;
	}

	public double getRequestedRate() {
		return requestedRate;
	}

	public void setRequestedRate(double requestedRate) {
		this.requestedRate = requestedRate;
	}

	@Override
	public String toString() {
		return "ProductInRequest [productRatesUpdateRequestID=" + productRatesUpdateRequestID + ", productName="
				+ productName + ", requestedRate=" + requestedRate + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ProductInRequest))
			return false;
		ProductInRequest other = (ProductInRequest) obj;
		return this.productName.equals(other.productName)
				&& this.productRatesUpdateRequestID == other.productRatesUpdateRequestID;
	}

}
