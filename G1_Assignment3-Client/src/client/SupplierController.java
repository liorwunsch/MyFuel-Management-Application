package client;

import java.util.List;

import entities.FuelStationOrder;

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
}
