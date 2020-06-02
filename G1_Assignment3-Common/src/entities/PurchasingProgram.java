package entities;

import java.io.Serializable;
import enums.FuelCompanyName;
import enums.PurchasingProgramName;

@SuppressWarnings("serial")
public class PurchasingProgram implements Serializable {

	// primary keys
	private String customerID;

	// foreign keys
	private PurchasingProgramName purchasingProgramName;
	private FuelCompanyName fuelCompanyName1;
	private FuelCompanyName fuelCompanyName2; // can be NULL
	private FuelCompanyName fuelCompanyName3; // can be NULL

	public PurchasingProgram(String customerID, PurchasingProgramName purchasingProgramName,
			FuelCompanyName fuelCompanyName1) {
		super();
		this.customerID = customerID;
		this.purchasingProgramName = purchasingProgramName;
		this.fuelCompanyName1 = fuelCompanyName1;
	}

	public PurchasingProgram(String customerID, PurchasingProgramName purchasingProgramName,
			FuelCompanyName fuelCompanyName1, FuelCompanyName fuelCompanyName2) {
		super();
		this.customerID = customerID;
		this.purchasingProgramName = purchasingProgramName;
		this.fuelCompanyName1 = fuelCompanyName1;
		this.fuelCompanyName2 = fuelCompanyName2;
	}

	public PurchasingProgram(String customerID, PurchasingProgramName purchasingProgramName,
			FuelCompanyName fuelCompanyName1, FuelCompanyName fuelCompanyName2, FuelCompanyName fuelCompanyName3) {
		super();
		this.customerID = customerID;
		this.purchasingProgramName = purchasingProgramName;
		this.fuelCompanyName1 = fuelCompanyName1;
		this.fuelCompanyName2 = fuelCompanyName2;
		this.fuelCompanyName3 = fuelCompanyName3;
	}

	public String getCustomerID() {
		return customerID;
	}

	public PurchasingProgramName getPurchasingProgramName() {
		return purchasingProgramName;
	}

	public void setPurchasingProgramName(PurchasingProgramName purchasingProgramName) {
		this.purchasingProgramName = purchasingProgramName;
	}

	public FuelCompanyName getFuelCompanyName1() {
		return fuelCompanyName1;
	}

	public void setFuelCompanyName1(FuelCompanyName fuelCompanyName1) {
		this.fuelCompanyName1 = fuelCompanyName1;
	}

	public FuelCompanyName getFuelCompanyName2() {
		return fuelCompanyName2;
	}

	public void setFuelCompanyName2(FuelCompanyName fuelCompanyName2) {
		this.fuelCompanyName2 = fuelCompanyName2;
	}

	public FuelCompanyName getFuelCompanyName3() {
		return fuelCompanyName3;
	}

	public void setFuelCompanyName3(FuelCompanyName fuelCompanyName3) {
		this.fuelCompanyName3 = fuelCompanyName3;
	}

	@Override
	public String toString() {
		String str = "PurchasingProgram [customerID=" + customerID + ", purchasingProgramName=" + purchasingProgramName
				+ ", fuelCompanyName1=" + fuelCompanyName1;
		if (fuelCompanyName2 != null)
			str += ", fuelCompanyName2=" + fuelCompanyName2;
		if (fuelCompanyName3 != null)
			str += ", fuelCompanyName3=" + fuelCompanyName3;
		return str + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof PurchasingProgram))
			return false;
		PurchasingProgram other = (PurchasingProgram) obj;
		return this.customerID.equals(other.customerID);
	}

}