package entities;

import java.io.Serializable;
import enums.ProductName;

/**
 * @author Elroy, Vlad, Lior
 */
@SuppressWarnings("serial")
public class Car implements Serializable {

	// primary keys
	private String registrationPlate;

	// foreign keys
	private String customerID;
	private ProductName productName;

	// fields
	private String ownerName;
	private boolean deleted;

	/**
	 * 
	 * @param registrationPlate
	 * @param customerID
	 * @param productName
	 * @param ownerName
	 * @param deleted
	 */
	public Car(String registrationPlate, String customerID, ProductName productName, String ownerName,
			boolean deleted) {
		super();
		this.registrationPlate = registrationPlate;
		this.customerID = customerID;
		this.productName = productName;
		this.ownerName = ownerName;
		this.deleted = deleted;
	}

	public String getRegistrationPlate() {
		return registrationPlate;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public ProductName getProductName() {
		return productName;
	}

	public void setProductName(ProductName productName) {
		this.productName = productName;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "Car [registrationPlate=" + registrationPlate + ", customerID=" + customerID + ", productName="
				+ productName + ", ownerName=" + ownerName + ", deleted=" + deleted + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Car))
			return false;
		Car other = (Car) obj;
		return this.registrationPlate.equals(other.registrationPlate);
	}

}
