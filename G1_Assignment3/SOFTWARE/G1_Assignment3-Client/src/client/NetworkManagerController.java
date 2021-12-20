package client;

import java.io.IOException;
import java.net.ConnectException;

import entities.MyFuelStationManager;
import entities.MyNetManager;

/**
 * logic controller for network manager
 * 
 * @version Final
 * @author Lior
 *
 */
public class NetworkManagerController extends UserController {

	/**
	 * singleton instance
	 */
	private static NetworkManagerController instance;

	/**
	 * singleton class constructor
	 */
	private NetworkManagerController() {
		super();
	}

	/**
	 * @return instance of this class
	 */
	public static NetworkManagerController getInstance() {
		if (instance == null) {
			instance = new NetworkManagerController();
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
		if (message.startsWith("signout") || message.startsWith("activity")) {
			super.handleMessageFromClientUI(message);

		} else {
			try {
				System.out.println("message from clientUI, [Network manager] : " + message);
				this.openConnection();
				awaitResponse = true;

				String[] splitMsg = message.split("_");// message: <command> <mathodName>_<username>_<params>{param1
														// param2 ...}
				if (splitMsg[0].equals("view QuarterReport")) {
					MyFuelStationManager manager = new MyFuelStationManager();
					manager.setFunction(splitMsg[0]);
					manager.setUserName(splitMsg[1]);
					if (splitMsg.length > 2)
						manager.setParams(splitMsg[2]);
					this.sendToServer(manager);

				} else {
					MyNetManager netManager = new MyNetManager();
					netManager.setFunction(message);
					this.sendToServer(netManager);
				}

				while (awaitResponse) {
					try {
						Thread.sleep(100);
						System.out.println("Network manager is waiting");
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
