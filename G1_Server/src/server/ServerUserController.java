package server;

import java.io.IOException;

import entities.User;
import guiServer.ServerWindow;
import ocsf.server.ConnectionToClient;

/**
 * 
 * @author Lior - don't change
 *
 */

public class ServerUserController {

	private static ServerUserController instance;

	private ServerWindow serverWindow;
	private DatabaseController databaseController;

	public static ServerUserController getInstance(ServerWindow serverWindow, DatabaseController databaseController) {
		if (instance == null) {
			instance = new ServerUserController(serverWindow, databaseController);
		}
		return instance;
	}

	private ServerUserController(ServerWindow serverWindow, DatabaseController databaseController) {
		super();
		this.serverWindow = serverWindow;
		this.databaseController = databaseController;
	}

	public void handleMessageFromClient(User user, ConnectionToClient client) {
		String result = "blank";
		String type = "";

		String function = user.getFunction();
		if (function.startsWith("login")) {
			this.serverWindow.updateArea(client + ": request login with username '" + user.getUsername() + "'");

			if (function.endsWith("employee"))
				type = "Employee";
			if (function.endsWith("customer"))
				type = "Customer";

			result = this.databaseController.loginSequence(user.getUsername(), user.getPassword(), type);
		}

		if (function.equals("sign out")) {
			this.serverWindow.updateArea(client + ": request logout with username '" + user.getUsername() + "'");
			result = this.databaseController.signOutSequence(user.getUsername());
		}

		this.serverWindow.updateArea(client + ": " + result);

		try {
			client.sendToClient(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
