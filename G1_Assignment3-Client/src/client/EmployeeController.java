package client;

import java.io.IOException;
import java.net.ConnectException;

public abstract class EmployeeController extends UserController {

	@Override
	public void handleMessageFromClientUI(String message) {
		super.handleMessageFromClientUI(message);
		try {

			System.out.println("message from clientUI : " + message);
			this.openConnection();
			awaitResponse = true;

			String[] splitMsg = message.split(" ");
			if (splitMsg[0].equals("activity")) {
				System.out.println("sending to server : " + message);
				this.sendToServer(message);
			}

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
