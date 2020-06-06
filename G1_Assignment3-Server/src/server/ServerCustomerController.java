package server;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import database.DatabaseController;
import entities.FastFuelList;
import guiServer.ServerWindow;
import ocsf.server.ConnectionToClient;

/**
 * controller for customer
 * 
 * @version Basic
 * @author Lior
 */
public class ServerCustomerController {

	private static ServerCustomerController instance;

	private Object lock;
	private ServerWindow serverWindow;
	private DatabaseController databaseController;

	/**
	 * singleton class constructor
	 */
	private ServerCustomerController(ServerWindow serverWindow, DatabaseController databaseController, Object lock) {
		super();
		this.lock = lock;
		this.serverWindow = serverWindow;
		this.databaseController = databaseController;
	}

	/**
	 * @return instance of this class
	 */
	public static ServerCustomerController getInstance(ServerWindow serverWindow, DatabaseController databaseController,
			Object lock) {
		if (instance == null) {
			instance = new ServerCustomerController(serverWindow, databaseController, lock);
		}
		return instance;
	}

	/**
	 * 
	 * @param string //\\
	 * @param client
	 */
	public void handleMessageFromClient(Object object, ConnectionToClient client) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			Date date = new Date();
			
			if (object instanceof String) {
				String[] splitMsg = ((String) object).split(" ");
				if (splitMsg[0].equals("fastfuel")) {
					if (splitMsg[1].equals("get")) {
						FastFuelList fastFuelList = this.databaseController.getFastFuelsSequence(splitMsg[2],
								splitMsg[3], splitMsg[4]);
						synchronized (this.lock) {
							this.serverWindow
									.updateArea(formatter.format(date) + " : " + client + " : fastfuel list fetched");
							this.lock.notifyAll();
						}

						/* send message back to client */
						client.sendToClient(fastFuelList);
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
