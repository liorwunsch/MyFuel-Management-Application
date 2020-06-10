package client;

import java.io.IOException;
import java.net.ConnectException;

import entities.FuelStation;
import entities.FuelStationOrder;
import enums.FuelCompanyName;

public class SupplierController extends UserController {

	private static SupplierController instance;
	// set only in successful login
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

	public void getFuelStationOrder() {
		try {
			openConnection();
			awaitResponse = true;
			sendToServer("fuel_station_order " + username);

			/* wait for ack or data from the server */
			while (awaitResponse) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
			}
			// FuelStationOrder[] fsoA = (FuelStationOrder[]) this.lastMsgFromServer;
			this.currentWindow.callAfterMessage(this.lastMsgFromServer);
		} catch (ConnectException ce) {
			this.currentWindow.openErrorAlert("Server Error", "Error - No connection to server");
			ce.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public void approveFuelStationOrder(int OrdersID) {
		try {
			openConnection();
			awaitResponse = true;
			sendToServer("fuel_station_order_approve " + OrdersID);

			/* Doesnt need because it will be relevent for next enter 
			 * wait for ack or data from the server while (awaitResponse) { try {
			 * Thread.sleep(100); } catch (InterruptedException ie) { ie.printStackTrace();
			 * } }
			 */
			// FuelStationOrder[] fsoA = (FuelStationOrder[]) this.lastMsgFromServer;
			this.currentWindow.callAfterMessage(this.lastMsgFromServer);
		} catch (ConnectException ce) {
			this.currentWindow.openErrorAlert("Server Error", "Error - No connection to server");
			ce.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
