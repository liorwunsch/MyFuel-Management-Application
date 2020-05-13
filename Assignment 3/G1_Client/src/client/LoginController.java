package client;

import java.io.IOException;

import guiClient.IFXML;

public class LoginController extends ClientController {

//	private static User u1 = new User();
	private IFXML loginWindow;

	/**
	 * @author send object and not string - fix
	 */

	public LoginController(String host, int port, IFXML iFXML) throws IOException {
		super(host, port);
		this.loginWindow = iFXML;
	}

	public void handleMessageFromServer(Object msg) {
		System.out.println("--> handleMessageFromServer");
		awaitResponse = false;
		String st;
		st = msg.toString();
		lastMsg = st;
		System.out.println(st);
	}

	public void handleMessageFromClientUI(String message) {
		try {
			openConnection();
			awaitResponse = true;
			sendToServer(message);
			// wait for response
			while (awaitResponse) {
				try {
					System.out.println("client waiting");
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			loginWindow.callAfterMessage();
		} catch (IOException e) {
			e.printStackTrace();
			display("Could not send message to server: Terminating client." + e);
		}
	}

}
