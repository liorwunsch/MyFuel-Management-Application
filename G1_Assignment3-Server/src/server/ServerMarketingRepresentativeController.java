package server;

import java.io.IOException;

import database.DatabaseController;
import entities.Customer;
import entities.User;
import ocsf.server.ConnectionToClient;

/**
 * controller for marketing representative
 * 
 * @version Basic
 * @author Lior
 */
public class ServerMarketingRepresentativeController {

	private static ServerMarketingRepresentativeController instance;
	private DatabaseController databaseController;

	/**
	 * singleton class constructor
	 */
	private ServerMarketingRepresentativeController(DatabaseController databaseController) {
		super();
		this.databaseController = databaseController;
	}

	/**
	 * @return instance of this class
	 */
	public static ServerMarketingRepresentativeController getInstance(DatabaseController databaseController) {
		if (instance == null) {
			instance = new ServerMarketingRepresentativeController(databaseController);
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
			if (object instanceof Object[]) {
				Object[] objArr = (Object[]) object;
				if (objArr.length == 2 && objArr[0] instanceof User && objArr[1] instanceof Customer) {
					String str = this.databaseController.saveNewCustomerSequence((User) objArr[0],
							(Customer) objArr[1]);
					client.sendToClient(str);
				}

			} else if (object instanceof String) {
				String[] splitMsg = ((String) object).split(" ");
				if (splitMsg[0].equals("getcustomerdetails")) {
					Object[] objArr = this.databaseController.getCustomerDetails(splitMsg[1]);
					client.sendToClient(objArr);

				} else if (splitMsg[0].equals("deletecustomer")) {
					boolean bool = this.databaseController.deleteCustomer(splitMsg[1]);
					if (bool == true)
						client.sendToClient("Customer Deleted");
					else
						client.sendToClient("Customer Delete Failed");
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
