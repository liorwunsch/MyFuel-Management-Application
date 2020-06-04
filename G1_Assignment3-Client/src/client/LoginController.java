package client;

import java.io.IOException;
import java.net.ConnectException;

import entities.User;
import guiClient.AFXML;

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
	 * checks connection to server
	 */
	private LoginController(String host, int port, AFXML currentWindow) {
		super(host, port);
		try {
			this.setCurrentWindow(currentWindow);
			this.openConnection();
			awaitResponse = true;
			sendToServer("ack request");

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
	 * @return instance of this class
	 */
	public static LoginController getInstance(String host, int port, AFXML currentWindow) {
		if (instance == null) {
			instance = new LoginController(host, port, currentWindow);
		}
		return instance;
	}

	@Override
	public void handleMessageFromClientUI(String message) {
		try {
			if (!message.startsWith("login"))
				throw new IOException("LoginController should have got login request but got " + message);

			System.out.println("message from clientUI : " + message);
			openConnection();
			awaitResponse = true;
			String[] splitMsg = message.split(" ");

			/* construct a new user */
			User user = new User(splitMsg[1], splitMsg[2]);

			/* determine what the server will do with it */
			if (splitMsg[3].equals("Employee"))
				user.setFunction("login employee");
			else
				user.setFunction("login customer");

			/* announce and send the user to the server */
			System.out.println("sending to server : " + user);
			sendToServer(user);

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
