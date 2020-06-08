package server;

import java.io.IOException;

import database.DatabaseController;
import entities.FastFuelList;
import entities.HomeFuelOrder;
import entities.HomeFuelOrderList;
import entities.PurchasingProgramType;
import entities.User;
import ocsf.server.ConnectionToClient;

/**
 * controller for customer
 * 
 * @version Basic
 * @author Lior
 */
public class ServerCustomerController {

	private static ServerCustomerController instance;
	private DatabaseController databaseController;

	/**
	 * singleton class constructor
	 */
	private ServerCustomerController(DatabaseController databaseController) {
		super();
		this.databaseController = databaseController;
	}

	/**
	 * @return instance of this class
	 */
	public static ServerCustomerController getInstance(DatabaseController databaseController) {
		if (instance == null) {
			instance = new ServerCustomerController(databaseController);
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

			if (object instanceof String) {
				String[] splitMsg = ((String) object).split(" ");
				if (splitMsg[0].equals("fastfuel")) {
					if (splitMsg[1].equals("get")) {
						FastFuelList fastFuelList = this.databaseController.getFastFuelsSequence(splitMsg[2],
								splitMsg[3], splitMsg[4]);
						client.sendToClient(fastFuelList);
					}

				} else if (splitMsg[0].equals("homefuel")) {
					if (splitMsg[1].equals("get")) {
						if (splitMsg[2].equals("price")) {
							Double homeFuelPrice = this.databaseController.getHomeFuelPriceSequence();
							client.sendToClient(homeFuelPrice);
						} else {
							HomeFuelOrderList homeFuelOrderList = this.databaseController
									.getHomeFuelOrdersSequence(splitMsg[2]);
							client.sendToClient(homeFuelOrderList);
						}
					}
				}

			} else if (object instanceof HomeFuelOrder) {
				String str = this.databaseController.setNewHomeFuelSequence((HomeFuelOrder) object);
				client.sendToClient(str);

			} else if (object instanceof User) {
				PurchasingProgramType purchasingProgramType = this.databaseController
						.getPurchasingProgramSequence(((User) object).getUsername());
				client.sendToClient(purchasingProgramType);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
