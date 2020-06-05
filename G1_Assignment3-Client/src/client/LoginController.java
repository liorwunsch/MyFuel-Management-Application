package client;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.UnknownHostException;

import entities.User;
import guiClient.LoginWindow;

/**
 * controller for login window
 * 
 * @version Final
 * @author Elroy, Lior
 */
public class LoginController extends ClientController {

	private static LoginController instance;

	/**
	 * singleton class constructor checks connection to server
	 */
	private LoginController(String host, int port, LoginWindow loginWindow) {
		super(host, port);
		try {
			System.out.println("sending ack request to host '" + host + "' at port '" + port + "'");
			openConnection();
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

			loginWindow.callAfterMessage(this.lastMsgFromServer);
			loginWindow.setVisibleNow(false);

		} catch (IllegalArgumentException iae) {
			loginWindow.openErrorAlert("Server Error", "Port Not Valid");
			iae.printStackTrace();
		} catch (UnknownHostException uhe) {
			loginWindow.openErrorAlert("Server Error", "Host Not Valid");
			uhe.printStackTrace();
		} catch (ConnectException ce) {
			loginWindow.openErrorAlert("Server Error", "Error - No connection to server");
			ce.printStackTrace();
		} catch (SocketException se) {
			loginWindow.openErrorAlert("Server Error", "Error - No connection to server");
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * @return instance of this class for parameter host port
	 */
	public static LoginController getInstance(String host, int port, LoginWindow loginWindow) {
		if (instance == null) {
			instance = new LoginController(host, port, loginWindow);
		}
		return instance;
	}

	public static LoginController getInstance() {
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
