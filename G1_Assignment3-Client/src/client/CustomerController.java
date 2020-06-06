package client;

import java.io.IOException;
import java.net.ConnectException;

/**
 * logic controller for customer
 * 
 * @version Basic
 * @author Lior
 */
public class CustomerController extends UserController {

	private static CustomerController instance;

	/**
	 * singleton class constructor
	 */
	private CustomerController() {
		super();
	}

	/**
	 * @return instance of this class
	 */
	public static CustomerController getInstance() {
		if (instance == null) {
			instance = new CustomerController();
		}
		return instance;
	}

	@Override
	public void handleMessageFromClientUI(String message) {
		super.handleMessageFromClientUI(message);

		try {
			this.openConnection();
			awaitResponse = true;
			boolean flag = true;

			String[] splitMsg = message.split(" ");

			if (splitMsg[0].equals("fastfuel")) {
				System.out.println("sending to server : " + message);
				this.sendToServer(message);
			} else {
				flag = false;
				awaitResponse = false;
			}

			if (flag == true) {
				/* wait for ack or data from the server */
				while (awaitResponse) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException ie) {
						ie.printStackTrace();
					}
				}
				this.currentWindow.callAfterMessage(this.lastMsgFromServer);
			}

		} catch (ConnectException ce) {
			this.currentWindow.openErrorAlert("Server Error", "Error - No connection to server");
			ce.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

}
