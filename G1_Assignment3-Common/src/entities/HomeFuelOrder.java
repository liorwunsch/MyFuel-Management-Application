package entities;

import java.util.Date;

import enums.ProductName;
import enums.ShipmentType;

/**
 * @author Elroy, Vlad, Lior
 */
@SuppressWarnings("serial")
public class HomeFuelOrder extends Orders {

	// primary keys
	/* private Integer ordersID; */

	// foreign keys
	private String customerID;
	private ProductName productName;
	private ShipmentType shipmentMethod;

	// fields
	private Date dueTime;
	private double finalPrice;

	/**
	 * without odersID auto-inc
	 * @param orderTime
	 * @param amountBought
	 * @param address
	 * @param customerID
	 * @param productName
	 * @param shipmentMethod
	 * @param dueTime
	 * @param finalPrice
	 */
	public HomeFuelOrder(Date orderTime, double amountBought, String address, String customerID,
			ProductName productName, ShipmentType shipmentMethod, Date dueTime, double finalPrice) {
		super(orderTime, amountBought, address);
		this.customerID = customerID;
		this.productName = productName;
		this.shipmentMethod = shipmentMethod;
		this.dueTime = dueTime;
		this.finalPrice = finalPrice;
	}

	/**
	 * 
	 * @param ordersID
	 * @param orderTime
	 * @param amountBought
	 * @param address
	 * @param customerID
	 * @param productName
	 * @param shipmentMethod
	 * @param dueTime
	 * @param finalPrice
	 */
	public HomeFuelOrder(int ordersID, Date orderTime, double amountBought, String address, String customerID,
			ProductName productName, ShipmentType shipmentMethod, Date dueTime, double finalPrice) {
		super(ordersID, orderTime, amountBought, address);
		this.customerID = customerID;
		this.productName = productName;
		this.shipmentMethod = shipmentMethod;
		this.dueTime = dueTime;
		this.finalPrice = finalPrice;
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

	public ShipmentType getShipmentMethod() {
		return shipmentMethod;
	}

	public void setShipmentMethod(ShipmentType shipmentMethod) {
		this.shipmentMethod = shipmentMethod;
	}

	public Date getDueTime() {
		return dueTime;
	}

	public void setDueTime(Date dueTime) {
		this.dueTime = dueTime;
	}

	public double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}

	@Override
	public String toString() {
		return "HomeFuelOrder [" + super.toString() + ", customerID=" + customerID + ", productName=" + productName
				+ ", shipmentMethod=" + shipmentMethod + ", dueTime=" + dueTime + ", finalPrice=" + finalPrice + "]";
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

}
