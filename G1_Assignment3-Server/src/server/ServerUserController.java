package server;

import java.io.IOException;

import entities.User;
import guiServer.ServerWindow;
import ocsf.server.ConnectionToClient;

/**
 * controller for client login and signout on server
 * 
 * @version Final
 * @author Elroy, Lior
 */
public class ServerUserController {

	private static ServerUserController instance;

	private Object lock;
	private ServerWindow serverWindow;
	private DatabaseController databaseController;

	/**
	 * singleton class constructor
	 */
	private ServerUserController(ServerWindow serverWindow, DatabaseController databaseController, Object lock) {
		super();
		this.lock = lock;
		this.serverWindow = serverWindow;
		this.databaseController = databaseController;
	}

	/**
	 * @return instance of this class
	 */
	public static ServerUserController getInstance(ServerWindow serverWindow, DatabaseController databaseController,
			Object lock) {
		if (instance == null) {
			instance = new ServerUserController(serverWindow, databaseController, lock);
		}
		return instance;
	}

	/**
	 * handles client request and sends it to the database controller sends result
	 * got from database controller back to the client
	 * 
	 * @param user
	 * @param client
	 */
	public void handleMessageFromClient(User user, ConnectionToClient client) {
		String result = "blank";
		String type = "";

		String function = user.getFunction();
		if (function.startsWith("login")) {
			synchronized (this.lock) {
				this.serverWindow.updateArea(client + ": request login with username '" + user.getUsername() + "'");
				this.lock.notifyAll();
			}

			if (function.endsWith("employee"))
				type = "Employee";
			if (function.endsWith("customer"))
				type = "Customer";

			result = this.databaseController.loginSequence(user.getUsername(), user.getPassword(), type);
		}

		if (function.equals("sign out")) {
			synchronized (this.lock) {
				this.serverWindow.updateArea(client + ": request signout with username '" + user.getUsername() + "'");
				this.lock.notifyAll();
			}
			result = this.databaseController.signOutSequence(user.getUsername());
		}

		synchronized (this.lock) {
			this.serverWindow.updateArea(client + ": " + result);
			this.lock.notifyAll();
		}

		try {
			/* send message back to client */
			client.sendToClient(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
