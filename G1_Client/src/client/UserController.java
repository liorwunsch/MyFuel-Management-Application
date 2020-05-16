package client;

import java.io.IOException;
import java.net.ConnectException;

import entities.User;
import guiClient.IFXML;

public class UserController extends ClientController {

	private static boolean awaitResponse = false;
	private static UserController instance;

	private IFXML currentWindow;

	/**
	 * @author Lior - don't change
	 * @author is is really singleton?
	 */

	public static UserController getInstance(String host, int port) {
		if (instance == null) {
			instance = new UserController(host, port);
		}
		return instance;
	}

	private UserController(String host, int port) {
		super(host, port);
	}

	public void setCurrentWindow(IFXML currentWindow) {
		this.currentWindow = currentWindow;
	}

	@Override
	public void handleMessageFromServer(Object obj) {
		awaitResponse = false;
		this.lastMsg = obj.toString();
		System.out.println("message from server : " + this.lastMsg);
	}

	@Override
	public void handleMessageFromClientUI(String message) {
		try {
			System.out.println("message from clientUI : " + message);
			this.openConnection();
			awaitResponse = true;

			String[] splitMsg = message.split(" ");
			User user = new User();

			if (splitMsg[0].equals("login")) {
				user.setUsername(splitMsg[1]);
				user.setPassword(splitMsg[2]);

				if (splitMsg[3].equals("Employee"))
					user.setFunction("login employee");
				if (splitMsg[3].equals("Customer"))
					user.setFunction("login customer");
			}

			if (splitMsg[0].equals("signout")) {
				user.setUsername(splitMsg[1]);
				user.setFunction("sign out");
			}

			System.out.println("sending user to server : " + user);
			this.sendToServer(user);

			while (awaitResponse) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			this.currentWindow.callAfterMessage(this.lastMsg);

		} catch (ConnectException ce) {
			this.currentWindow.openErrorAlert("Server Error", "Error - No connection to server");
			ce.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static UserController getInstance() {
		return instance;
	}

}
