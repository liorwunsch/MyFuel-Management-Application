package entities;

import java.io.Serializable;
import java.util.Date;
/**
 * The SupplierItemInTable Class is the basic that in the table in one
 * of the components it SupplierWindow.
 * <p>
 * This class isnt part of the class diagram because its simplify version fuel station order.
 * with more data
 * @author Leptop-Pc
 */
@SuppressWarnings("serial")
public class SupplierItemInTable implements Serializable{
	public Integer fuelStationID; 
	public Integer orderID;
	public Date orderTime;
	public String productName;
	public Double amount;
	public String address;
	
	public SupplierItemInTable(Integer fuelStationID, Integer orderID, Date orderTime, String productName,
			Double amount, String address) {
		super();
		this.fuelStationID = fuelStationID;
		this.orderID = orderID;
		this.orderTime = orderTime;
		this.productName = productName;
		this.amount = amount;
		this.address = address;
	}

	public Integer getFuelStationID() {
		return fuelStationID;
	}

	public void setFuelStationID(Integer fuelStationID) {
		this.fuelStationID = fuelStationID;
	}

	public Integer getOrderID() {
		return orderID;
	}

	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
