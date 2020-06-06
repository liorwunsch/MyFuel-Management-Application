package server;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import database.DatabaseController;
import entities.ActivityList;
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
	 * @param user entity or string
	 * @param client
	 */
	public void handleMessageFromClient(Object object, ConnectionToClient client) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			Date date = new Date();

			if (object instanceof User) {
				String result = "blank";
				String type = "";

				User user = (User) object;
				String function = user.getFunction();
				if (function.startsWith("login")) {
					if (function.endsWith("employee"))
						type = "Employee";
					if (function.endsWith("customer"))
						type = "Customer";

					result = this.databaseController.loginSequence(user.getUsername(), user.getPassword(), type);
				}

				if (function.equals("sign out")) {
					synchronized (this.lock) {
						this.serverWindow.updateArea(formatter.format(date) + " : " + client
								+ " : request signout with username '" + user.getUsername() + "'");
						this.lock.notifyAll();
					}
					result = this.databaseController.signOutSequence(user.getUsername());
				}

				synchronized (this.lock) {
					this.serverWindow.updateArea(formatter.format(date) + " : " + client + " : " + result);
					this.lock.notifyAll();
				}

				/* send message back to client */
				client.sendToClient(result);
			}

			if (object instanceof String) {
				String[] splitMsg = ((String) object).split(" ");
				if (splitMsg[0].equals("activity")) {
					if (splitMsg[1].equals("get")) {
						ActivityList activityList = this.databaseController.getActivitiesSequence(splitMsg[2],
								splitMsg[3], splitMsg[4]);
						synchronized (this.lock) {
							this.serverWindow
									.updateArea(formatter.format(date) + " : " + client + " : activity list fetched");
							this.lock.notifyAll();
						}

						/* send message back to client */
						client.sendToClient(activityList);
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
