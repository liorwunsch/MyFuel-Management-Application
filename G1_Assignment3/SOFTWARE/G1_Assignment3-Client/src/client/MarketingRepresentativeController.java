package client;

import java.io.IOException;
import java.net.ConnectException;

import entities.Car;
import entities.Customer;
import entities.PricingModel;
import entities.PurchasingProgram;
import entities.User;
import enums.CustomerType;
import enums.FuelCompanyName;
import enums.PricingModelName;
import enums.ProductName;
import enums.PurchasingProgramName;

/**
 * logic controller for marketing representative
 * 
 * @version Final
 * @author Lior
 */
public class MarketingRepresentativeController extends MarketingDepWorkerController {

	/**
	 * singleton instance
	 */
	private static MarketingRepresentativeController instance;

	/**
	 * singleton class constructor
	 */
	private MarketingRepresentativeController() {
		super();
	}

	/**
	 * @return instance of this class
	 */
	public static MarketingRepresentativeController getInstance() {
		if (instance == null) {
			instance = new MarketingRepresentativeController();
		}
		return instance;
	}

	/**
	 * receives string from the window
	 * <p>
	 * opens connection to the server
	 * <p>
	 * sends the server a request accordingly
	 * <p>
	 * calls <code>callAfterMessage()</code> of <code>currentWindow</code>
	 * 
	 * @param message
	 */
	@Override
	public void handleMessageFromClientUI(String message) {
		super.handleMessageFromClientUI(message);
		String[] splitMsg = message.split(" ");

		if (message.startsWith("create sale pattern") || message.startsWith("pull ranking sheets")
				|| message.startsWith("pull product rates") || message.equals("genAnalysis")) {
			super.handleDepWorkerRequest(message);

		} else {
			try {
				openConnection();
				awaitResponse = true;
				boolean flag = true;

				if (splitMsg[0].equals("setpricingmodel")) {
					PricingModel pricingModel = new PricingModel(splitMsg[1], PricingModelName.valueOf(splitMsg[2]),
							Double.parseDouble(splitMsg[3]));
					System.out.println("sending to server : " + pricingModel);
					this.sendToServer(pricingModel);

				} else if (splitMsg[0].equals("setprogram")) {
					PurchasingProgram purchasingProgram = new PurchasingProgram(splitMsg[1],
							PurchasingProgramName.valueOf(splitMsg[2]), FuelCompanyName.valueOf(splitMsg[3]), null,
							null);
					if (!splitMsg[4].equals("NaN"))
						purchasingProgram.setFuelCompanyName2(FuelCompanyName.valueOf(splitMsg[4]));
					if (!splitMsg[5].equals("NaN"))
						purchasingProgram.setFuelCompanyName3(FuelCompanyName.valueOf(splitMsg[5]));

					System.out.println("sending to server : " + purchasingProgram);
					this.sendToServer(purchasingProgram);

				} else if (splitMsg[0].equals("savecar")) {
					Car car = new Car(splitMsg[2], splitMsg[1], ProductName.valueOf(splitMsg[4]), splitMsg[3]);
					car.setFunction("save car");
					System.out.println("sending to server : " + car);
					this.sendToServer(car);

				} else if (splitMsg[0].equals("updatecar")) {
					Car car = new Car(splitMsg[2], splitMsg[1], ProductName.valueOf(splitMsg[4]), splitMsg[3]);
					car.setFunction("update car");
					System.out.println("sending to server : " + car);
					this.sendToServer(car);

				} else if (splitMsg[0].equals("getcustomercars") || splitMsg[0].equals("deletecar")) {
					System.out.println("sending to server : " + message);
					this.sendToServer(message);

				} else if (splitMsg[0].equals("savecustomer")) {
					User user = new User(splitMsg[1], splitMsg[4], splitMsg[2], splitMsg[3]);
					Customer customer = new Customer(splitMsg[1], splitMsg[1], splitMsg[5],
							CustomerType.valueOf(splitMsg[6]));
					user.setFunction("save customer");

					System.out.println("sending to server : " + user + "\n" + customer);
					this.sendToServer(new Object[] { user, customer });

				} else if (splitMsg[0].equals("updatecustomer")) {
					User user = new User(splitMsg[1], splitMsg[4], splitMsg[2], splitMsg[3]);
					Customer customer = new Customer(splitMsg[1], splitMsg[1], splitMsg[5],
							CustomerType.valueOf(splitMsg[6]));
					user.setFunction("update customer");

					System.out.println("sending to server : " + user + "\n" + customer);
					this.sendToServer(new Object[] { user, customer });

				} else if (splitMsg[0].equals("getcustomerdetails") || splitMsg[0].equals("deletecustomer")
						|| splitMsg[0].equals("checkcustomer") || splitMsg[0].equals("getAllPricingModelDiscounts")) {
					System.out.println("sending to server : " + message);
					this.sendToServer(message);

				} else {

					flag = false;
					awaitResponse = false;
				}

				if (flag == true) {
					/* wait for ack or data from the server */
					while (awaitResponse) {
						try {
							Thread.sleep(100);
						} catch (InterruptedException ie) {
							ie.printStackTrace();
						}
					}
					this.currentWindow.callAfterMessage(this.lastMsgFromServer);
				}

			} catch (ConnectException ce) {
				this.currentWindow.openErrorAlert("Server Error", "Error - No connection to server");
				ce.printStackTrace();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

}
