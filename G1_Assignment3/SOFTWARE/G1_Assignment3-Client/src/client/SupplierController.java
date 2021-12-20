package client;

import java.io.IOException;
import java.net.ConnectException;

/**
 * This Class is connection between the supplier gui and the server request and
 * acknowledge Sending message to serve the message of supplier always starts
 * with fuel_station_order. this is the way designed to recognize the requested
 * approach
 * 
 * @version Final
 * @author Vlad
 * @see guiClient.SupplierWindow
 */
public class SupplierController extends UserController {

	/**
	 * singleton instance
	 */
	private static SupplierController instance;

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

	/**
	 * receives string from the window
	 * <p>
	 * opens connection to the server
	 * <p>
	 * sends the server a request accordingly
	 * <p>
	 * calls <code>callAfterMessage()</code> of <code>currentWindow</code>
	 * 
	 * @param message
	 */
	@Override
	public void handleMessageFromClientUI(String message) {
		super.handleMessageFromClientUI(message);
	}

	/**
	 * sends requset to server to fetch all fuel stations that have orders that were
	 * not supplied yet
	 * 
	 * @param username
	 */
	public void getFuelStationsWithOrdersPending(String username) {
		try {
			openConnection();
			awaitResponse = true;
			sendToServer("fuel_station_order_getfs " + username);

			/* wait for ack or data from the server */
			while (awaitResponse) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
			}
			this.currentWindow.callAfterMessage(this.lastMsgFromServer);

		} catch (ConnectException ce) {
			this.currentWindow.openErrorAlert("Server Error", "Error - No connection to server");
			ce.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * send request to server to get all details of fuel station orders of a fuel
	 * station
	 * 
	 * @param fuelStationIDs
	 */
	public void getSupplierItemInTable(int fuelStationIDs) {
		try {
			openConnection();
			awaitResponse = true;
			sendToServer("fuel_station_order_getsiit " + fuelStationIDs);

			/* wait for ack or data from the server */
			while (awaitResponse) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
			}
			this.currentWindow.callAfterMessage(this.lastMsgFromServer);

		} catch (ConnectException ce) {
			this.currentWindow.openErrorAlert("Server Error", "Error - No connection to server");
			ce.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * sends request to the server to update a fuel station order as supplied
	 * 
	 * @param ordersID
	 * @param amount
	 */
	public void approveFuelStationOrder(int ordersID, double amount) {
		try {
			openConnection();
			awaitResponse = true;
			sendToServer("fuel_station_order_approve " + ordersID + " " + amount);

			/* wait for ack or data from the server */
			while (awaitResponse) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
			}
			this.currentWindow.callAfterMessage(this.lastMsgFromServer);

		} catch (ConnectException ce) {
			this.currentWindow.openErrorAlert("Server Error", "Error - No connection to server");
			ce.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

}
