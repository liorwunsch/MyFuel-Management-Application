package server;

import java.io.IOException;

import guiServer.ServerWindow;
import ocsf.server.ConnectionToClient;

public class ServerUserController {

	private ServerWindow serverWindow;
	private DatabaseController db;

	/**
	 * @author Singleton - fix
	 */
	public ServerUserController(ServerWindow serverWindow, DatabaseController db) {
		super();
		this.serverWindow = serverWindow;
		this.db = db;
	}

	public void handleMessageFromClient(Object msg, ConnectionToClient client) {
		String message = (String) msg;
		String[] words = message.split(" ");
		try {
			if (message.startsWith("login")) {
				serverWindow.updateArea(words[1] + " " + words[2]);
				String result = db.loginSequence(words[1], words[2]);
				serverWindow.updateArea(result);
				client.sendToClient(result);
			}
			if (message.startsWith("sign out")) {
				System.out.println("word: " + words[2]);
				String result = db.signOutSequence(words[2]);
				client.sendToClient(result);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			serverWindow.updateArea(e.getMessage());
		}
	}

}
