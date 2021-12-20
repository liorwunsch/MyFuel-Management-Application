package server;

import java.io.IOException;

import database.DatabaseController;
import entities.FastFuel;
import ocsf.server.ConnectionToClient;

/**
 * controller for marketing representative
 * 
 * @version Final
 * @author Lior
 */
public class ServerFastFuelController {

	private static ServerFastFuelController instance;
	private DatabaseController databaseController;

	/**
	 * singleton class constructor
	 */
	private ServerFastFuelController(DatabaseController databaseController) {
		super();
		this.databaseController = databaseController;
	}

	/**
	 * @return instance of this class
	 */
	public static ServerFastFuelController getInstance(DatabaseController databaseController) {
		if (instance == null) {
			instance = new ServerFastFuelController(databaseController);
		}
		return instance;
	}

	/**
	 * 
	 * @param object
	 * @param client
	 */
	public void handleMessageFromClient(Object object, ConnectionToClient client) {
		try {
			FastFuel fastFuel = (FastFuel) object;
			FastFuel result = null;
			if (fastFuel.getFunction().equals("get")) {
				result = this.databaseController.getFuelTypeAndPricePerLiter(fastFuel);

			} else if (fastFuel.getFunction().equals("save")) {
				result = this.databaseController.saveFastFuel(fastFuel);
			}

			client.sendToClient(result);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
