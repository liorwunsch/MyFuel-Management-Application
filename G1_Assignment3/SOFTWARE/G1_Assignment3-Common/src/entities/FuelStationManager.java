package entities;

import java.io.Serializable;

/**
 * @author Elroy, Vlad, Lior
 */
@SuppressWarnings("serial")
public class FuelStationManager implements Serializable {

	// primary keys
	private int employeeID;

	// fields
	private String phoneNo;

	/**
	 * 
	 * @param employeeID
	 * @param phoneNo
	 */
	public FuelStationManager(int employeeID, String phoneNo) {
		super();
		this.employeeID = employeeID;
		this.phoneNo = phoneNo;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	@Override
	public String toString() {
		return "FuelStationManager [employeeID=" + employeeID + ", phoneNo=" + phoneNo + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof FuelStationManager))
			return false;
		FuelStationManager other = (FuelStationManager) obj;
		return this.employeeID == other.employeeID;
	}

}
