package client;

import java.io.IOException;

import java.net.ConnectException;

import entities.MarketingManager;

/**
 * 
 * @author Elroy
 *
 */
public class MarketingManagerController extends MarketingDepWorkerController {

	private static MarketingManagerController instance;

	/**
	 * singleton class constructor
	 */
	private boolean logged = false;

	private MarketingManagerController() {
		super();
	}

	/**
	 * @return instance of this class
	 */
	public static MarketingManagerController getInstance() {
		if (instance == null) {
			instance = new MarketingManagerController();
		}
		return instance;
	}

	@Override
	public void handleMessageFromClientUI(String message) {
		if (logged == false || message.startsWith("signout") || message.startsWith("activity")) {
			if (message.startsWith("signout"))
				logged = false;
			else
				logged = true;
			super.handleMessageFromClientUI(message);
		}

		else {
			try {
				System.out.println("message from clientUI : " + message);
				this.openConnection();
				awaitResponse = true;

				MarketingManager manager = new MarketingManager();
				manager.setFunction(message);
				this.sendToServer(manager);

				/* wait for ack or data from the server */
				while (awaitResponse) {
					try {
						Thread.sleep(100);
						System.out.println("marketing manager is waiting");
					} catch (InterruptedException ie) {
						ie.printStackTrace();
					}
				}

			} catch (ConnectException ce) {
				this.currentWindow.openErrorAlert("Server Error", "Error - No connection to server");
				ce.printStackTrace();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			this.currentWindow.callAfterMessage(this.lastMsgFromServer);
		}

	}

}
