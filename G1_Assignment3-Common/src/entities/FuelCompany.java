
package entities;

import java.io.Serializable;
import enums.FuelCompanyName;

@SuppressWarnings("serial")
public class FuelCompany implements Serializable {

	// primary keys
	private FuelCompanyName fuelCompanyName;

	// foreign keys
	private int employeeID; // supplier

	public FuelCompany(FuelCompanyName fuelCompanyName, int employeeID) {
		super();
		this.fuelCompanyName = fuelCompanyName;
		this.employeeID = employeeID;
	}

	public FuelCompanyName getFuelCompanyName() {
		return fuelCompanyName;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	@Override
	public String toString() {
		return "FuelCompany [fuelCompanyName=" + fuelCompanyName + ", employeeID=" + employeeID + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof FuelCompany))
			return false;
		FuelCompany other = (FuelCompany) obj;
		return this.fuelCompanyName.equals(other.fuelCompanyName);
	}

}
