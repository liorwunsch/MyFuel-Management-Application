package client;

import entities.FuelStation;
import entities.FuelStationOrder;
import enums.FuelCompanyName;

public class SupplierController extends UserController {

	private static SupplierController instance;
	//set only in successful login
	public String username;
	/**
	 * singleton class constructor
	 */
	private SupplierController() {
		super();
	}

	/**
	 * @return instance of this class
	 */
	public static SupplierController getInstance() {
		if (instance == null) {
			instance = new SupplierController();
		}
		return instance;
	}

	@Override
	public void handleMessageFromClientUI(String message) {
		super.handleMessageFromClientUI(message);
		/**
		 * 
		 */
	}
	
	private FuelStationOrder[] getFuelStationOrder(){
		System.out.println(username);
		return null;
	}
	
	public FuelStation[] getFuelStation() {
		FuelStation[] fs = {new FuelStation(1, FuelCompanyName.Paz, 1, "paz","hi"),new FuelStation(2, FuelCompanyName.Paz, 2, "pazi","hi")};
		return fs;
	}
}
