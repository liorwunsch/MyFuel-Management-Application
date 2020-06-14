package server;

import java.io.IOException;

import database.DatabaseController;
import entities.FuelStationOrder;
import entities.SupplierItemInTable;
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
	/**
	 * This method  parse the message for it correct route.
	 * @param str the message
	 * @param client
	 */
	public void handleMessageFromClient(String str, ConnectionToClient client) {
		try {
			String[] arr = str.split(" ");
			//username must be without enter
			switch(arr[0]) {
			case "fuel_station_order_getsiit":
				SupplierItemInTable[] fso = getFuelStationOrder(Integer.valueOf(arr[1]));
				client.sendToClient(fso);
				break;
			case "fuel_station_order_approve":
				approveFuelStationOrder( Integer.valueOf(arr[1]), Double.valueOf(arr[2]) );
				break;
			case "fuel_station_order_getfs":
				Integer[] fs = getFuelStationWithOrder(arr[1]);
				client.sendToClient(fs);
				break;
			}
			/*
			 * if(arr[0].equals("fuel_station_order_approve")) {
			 * approveFuelStationOrder(Integer.valueOf(arr[1])); }else { FuelStationOrder[]
			 * fs = getFuelStationOrder(arr[1]); client.sendToClient(fs); }
			 */
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Integer[] getFuelStationWithOrder(String username) {
		return this.databaseController.getFuelStationWithOrder(username);
	}

	public SupplierItemInTable[] getFuelStationOrder(int fuelStationIDs) {
		return this.databaseController.getFuelStationOrder(fuelStationIDs);
	}
	
	public void approveFuelStationOrder(int OrdersID, double amount) {
		 this.databaseController.approveFuelStationOrder(OrdersID,amount);
	}
	
}
