package server;

import java.io.IOException;

import database.DatabaseController;
import entities.MyNetManager;
import ocsf.server.ConnectionToClient;

/**
 * @version Final
 * @author Lior
 *
 */
public class ServerNetworkManagerController {

	private static ServerNetworkManagerController instance;
	private DatabaseController databaseController;

	/**
	 * singleton class constructor
	 */
	private ServerNetworkManagerController(DatabaseController databaseController) {
		super();
		this.databaseController = databaseController;
	}

	/**
	 * @return instance of this class
	 */
	public static ServerNetworkManagerController getInstance(DatabaseController databaseController) {
		if (instance == null) {
			instance = new ServerNetworkManagerController(databaseController);
		}
		return instance;
	}

	/**
	 * handles client request and sends it to the database controller sends result
	 * got from database controller back to the client
	 * 
	 * @param netManager
	 * @param client
	 */
	public void handleMessageFromClient(MyNetManager netManager, ConnectionToClient client) {
		try {
			String func = netManager.getFunction();

			if (func.equals("getAllFuelStationIDs")) {
				MyNetManager resultNetManager = this.databaseController.getAllFuelStationIDs(netManager);
				client.sendToClient(resultNetManager);

			} else if (func.equals("getAllUnAssessedRequests")) {
				MyNetManager resultNetManager = this.databaseController.getAllUnAssessedRequests(netManager);
				client.sendToClient(resultNetManager);

			} else if (func.startsWith("setRequestApproved")) {
				MyNetManager resultNetManager = this.databaseController.setRequestApproved(netManager);
				client.sendToClient(resultNetManager);

			} else if (func.startsWith("setRequestDeclined")) {
				MyNetManager resultNetManager = this.databaseController.setRequestDeclined(netManager);
				client.sendToClient(resultNetManager);

			} else if (func.startsWith("getRequestDetails")) {
				MyNetManager resultNetManager = this.databaseController.getRequestDetails(netManager);
				client.sendToClient(resultNetManager);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
