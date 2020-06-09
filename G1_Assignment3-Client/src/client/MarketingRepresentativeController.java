package client;

import java.io.IOException;
import java.net.ConnectException;

import entities.Customer;
import entities.User;
import enums.CustomerType;

/**
 * logic controller for marketing representative
 * 
 * @version Basic
 * @author Lior
 */
public class MarketingRepresentativeController extends MarketingDepWorkerController {

	private static MarketingRepresentativeController instance;

	/**
	 * singleton class constructor
	 */
	private MarketingRepresentativeController() {
		super();
	}

	/**
	 * @return instance of this class
	 */
	public static MarketingRepresentativeController getInstance() {
		if (instance == null) {
			instance = new MarketingRepresentativeController();
		}
		return instance;
	}

	@Override
	public void handleMessageFromClientUI(String message) {
		super.handleMessageFromClientUI(message);
		String[] splitMsg = message.split(" ");

		try {
			openConnection();
			awaitResponse = true;
			boolean flag = true;

			if (splitMsg[0].equals("savecustomer")) {
				User user = new User(splitMsg[1], splitMsg[4], splitMsg[2], splitMsg[3]);
				Customer customer = new Customer(splitMsg[1], splitMsg[1], splitMsg[5],
						CustomerType.valueOf(splitMsg[6]));
				user.setFunction("save customer");

				System.out.println("sending to server : " + user + "\n" + customer);
				this.sendToServer(new Object[] { user, customer });

			} else if (splitMsg[0].equals("getcustomerdetails") || splitMsg[0].equals("deletecustomer")) {
				System.out.println("sending to server : " + message);
				this.sendToServer(message);

			} else {
				flag = false;
				awaitResponse = false;
			}

			if (flag == true) {
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
