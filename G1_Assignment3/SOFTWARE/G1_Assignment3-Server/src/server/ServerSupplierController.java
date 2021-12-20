package server;

import java.io.IOException;

import database.DatabaseController;
import entities.SupplierItemInTable;
import ocsf.server.ConnectionToClient;

/**
 * controller for supplier server
 * 
 * @version Final
 * @author Vlad
 */
public class ServerSupplierController {
	private static ServerSupplierController instance;
	private DatabaseController databaseController;

	/**
	 * singleton class constructor
	 */
	private ServerSupplierController(DatabaseController databaseController) {
		super();
		this.databaseController = databaseController;
	}

	/**
	 * @return instance of this class
	 */
	public static ServerSupplierController getInstance(DatabaseController databaseController) {
		if (instance == null) {
			instance = new ServerSupplierController(databaseController);
		}
		return instance;
	}

	/**
	 * This method parse the message for it correct route.
	 * 
	 * @param str    the message
	 * @param client
	 */
	public void handleMessageFromClient(String str, ConnectionToClient client) {
		try {
			String[] arr = str.split(" ");
			// username must be without enter
			switch (arr[0]) {
			case "fuel_station_order_getsiit":
				SupplierItemInTable[] fso = getFuelStationOrder(Integer.valueOf(arr[1]));
				client.sendToClient(fso);
				break;
			case "fuel_station_order_approve":
				client.sendToClient(approveFuelStationOrder(Integer.valueOf(arr[1]), Double.valueOf(arr[2])));
				break;
			case "fuel_station_order_getfs":
				Integer[] fs = getFuelStationWithOrder(arr[1]);
				client.sendToClient(fs);
				break;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
/**
 * method that request from DB to pull all order id's
 * of all fuel station orders of the required fuel station manager
 * @param username
 * @return
 */
	private Integer[] getFuelStationWithOrder(String username) {
		return this.databaseController.getFuelStationWithOrder(username);
	}
	
/**
 * method that request from DB to pull all order id's
 * of all fuel station order of the required fuel station id
 * @param fuelStationIDs
 * @return
 */
	private SupplierItemInTable[] getFuelStationOrder(int fuelStationIDs) {
		return this.databaseController.getFuelStationOrder(fuelStationIDs);
	}
	
	/**
	 * method that request from DB to update a fuel station order
	 * by changing it to status 'approved' and stock
	 * @param OrdersID
	 * @param amount
	 * @return
	 */

	private String approveFuelStationOrder(int OrdersID, double amount) {
		return this.databaseController.approveFuelStationOrder(OrdersID, amount);
	}

}
