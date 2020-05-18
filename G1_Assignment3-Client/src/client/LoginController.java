package client;

import java.io.IOException;
import java.net.ConnectException;

import entities.User;

/**
 * controller for login window
 * 
 * @version Final
 * @author Elroy, Lior
 */
public class LoginController extends ClientController {

	private static LoginController instance;

	/**
	 * singleton class constructor
	 */
	private LoginController() {
		super();
	}

	/**
	 * @return instance of this class
	 */
	public static LoginController getInstance() {
		if (instance == null) {
			instance = new LoginController();
		}
		return instance;
	}

	@Override
	public void handleMessageFromClientUI(String message) {
		try {
			if (!message.startsWith("login"))
				throw new IOException("LoginController should have got login request but got " + message);

			System.out.println("message from clientUI : " + message);
			this.openConnection();
			awaitResponse = true;
			String[] splitMsg = message.split(" ");

			/* construct a new user */
			User user = new User();
			user.setUsername(splitMsg[1]);
			user.setPassword(splitMsg[2]);

			/* determine what the server will do with it */
			if (splitMsg[3].equals("Employee"))
				user.setFunction("login employee");
			else
				user.setFunction("login customer");

			/* announce and send the user to the server */
			System.out.println("sending to server : " + user);
			this.sendToServer(user);

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
