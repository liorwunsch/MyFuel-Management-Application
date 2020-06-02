package entities;

import java.io.Serializable;
import enums.FuelCompanyName;

/**
 * @author Elroy, Vlad, Lior
 */
@SuppressWarnings("serial")
public class FuelStation implements Serializable {

	// primary keys
	private Integer fuelStationID;

	// foreign keys
	private FuelCompanyName fuelCompanyName;
	private int employeeID; // unique, fuel station manager

	// fields
	private String stationName; // unique
	private String address;

	/**
	 * without fuelStationID auto-inc
	 * @param fuelCompanyName
	 * @param employeeID
	 * @param stationName
	 * @param address
	 */
	public FuelStation(FuelCompanyName fuelCompanyName, int employeeID, String stationName, String address) {
		super();
		this.fuelCompanyName = fuelCompanyName;
		this.employeeID = employeeID;
		this.stationName = stationName;
		this.address = address;
	}

	/**
	 * 
	 * @param fuelStationID
	 * @param fuelCompanyName
	 * @param employeeID
	 * @param stationName
	 * @param address
	 */
	public FuelStation(int fuelStationID, FuelCompanyName fuelCompanyName, int employeeID, String stationName,
			String address) {
		super();
		this.fuelStationID = fuelStationID;
		this.fuelCompanyName = fuelCompanyName;
		this.employeeID = employeeID;
		this.stationName = stationName;
		this.address = address;
	}

	public int getFuelStationID() {
		return fuelStationID;
	}

	public FuelCompanyName getFuelCompanyName() {
		return fuelCompanyName;
	}

	public void setFuelCompanyName(FuelCompanyName fuelCompanyName) {
		this.fuelCompanyName = fuelCompanyName;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		String str = "FuelStation [";
		if (fuelStationID != null)
			str += "fastFuelID=" + fuelStationID + ", ";
		str += "fuelCompanyName=" + fuelCompanyName + ", employeeID=" + employeeID + ", stationName=" + stationName
				+ ", address=" + address + "]";
		return str;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof FuelStation))
			return false;
		FuelStation other = (FuelStation) obj;
		return this.stationName.equals(other.stationName);
	}

}
