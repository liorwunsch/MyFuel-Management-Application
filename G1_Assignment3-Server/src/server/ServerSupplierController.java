package server;

import java.io.IOException;
import database.DatabaseController;
import entities.FuelStationOrder;
import guiServer.ServerWindow;
import ocsf.server.ConnectionToClient;

/**
 * controller for supplier server
 * 
 * @version 1
 * @author Vlad,Elroy, Lior
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
	
	public void handleMessageFromClient(String str, ConnectionToClient client) {
		try {
			String[] arr = str.split(" ");
			//username must be without enter
			if(arr[0].equals("fuel_station_order_approve")) {
				approveFuelStationOrder(Integer.valueOf(arr[1]));
			}else {
				FuelStationOrder[] fs = getFuelStationOrder(arr[1]);
				client.sendToClient(fs);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public FuelStationOrder[] getFuelStationOrder(String username) {
		return this.databaseController.getFuelStationOrder(username);
	}
	
	public void approveFuelStationOrder(int OrdersID) {
		 this.databaseController.approveFuelStationOrder(OrdersID);
	}
	
}
