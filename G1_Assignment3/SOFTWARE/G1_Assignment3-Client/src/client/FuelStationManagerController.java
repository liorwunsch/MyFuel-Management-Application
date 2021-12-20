package client;

import java.io.IOException;
import java.net.ConnectException;

import entities.MyFuelStationManager;

/**
 * logic controller for fuel station manager
 * 
 * @version Final
 * @author Liad
 */
public class FuelStationManagerController extends UserController {

	/**
	 * singleton instance
	 */
	private static FuelStationManagerController instance;

	private boolean logged = false;

	/**
	 * singleton class constructor
	 */
	private FuelStationManagerController() {
		super();
	}

	/**
	 * @return instance of this class
	 */
	public static FuelStationManagerController getInstance() {
		if (instance == null) {
			instance = new FuelStationManagerController();
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
		if (logged == false || message.startsWith("signout") || message.startsWith("activity")) {
			if (message.startsWith("signout"))
				logged = false;
			else
				logged = true;
			super.handleMessageFromClientUI(message);

		} else {
			try {
				System.out.println("message from clientUI, [Fuel Station manager] : " + message);
				this.openConnection();
				awaitResponse = true;

				MyFuelStationManager manager = new MyFuelStationManager();
				String[] splitMsg = message.split("_");// message: <command> <mathodName>_<username>_<params>{param1
														// param2 ...}
				manager.setFunction(splitMsg[0]);
				manager.setUserName(splitMsg[1]);
				if (splitMsg.length > 2)
					manager.setParams(splitMsg[2]);
				this.sendToServer(manager);
				while (awaitResponse) {
					try {
						Thread.sleep(100);
						System.out.println("Fuel station manager is waiting");
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

}
