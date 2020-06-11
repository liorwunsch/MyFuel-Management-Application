package client;

import java.io.IOException;
import java.net.ConnectException;
import java.util.Date;

import entities.HomeFuelOrder;
import entities.User;
import enums.ProductName;
import enums.ShipmentType;

/**
 * logic controller for customer
 * 
 * @version Final
 * @author Lior
 */
public class CustomerController extends UserController {

	private static CustomerController instance;

	/**
	 * singleton class constructor
	 */
	private CustomerController() {
		super();
	}

	/**
	 * @return instance of this class
	 */
	public static CustomerController getInstance() {
		if (instance == null) {
			instance = new CustomerController();
		}
		return instance;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void handleMessageFromClientUI(String message) {
		super.handleMessageFromClientUI(message);
		String[] splitMsg = message.split(" ");

		if (splitMsg[0].equals("gethomefuelfinalprice")) {
			calculateHomeFuelOrderFinalPrice(splitMsg[1], splitMsg[2], splitMsg[3]);
			return;
		}

		try {
			openConnection();
			awaitResponse = true;
			boolean flag = true;

			if (splitMsg[0].equals("updatepassword")) {
				this.sendToServer(message);

			} else if (splitMsg[0].equals("getcustomerpurchasingprogram")) {
				User user = new User(splitMsg[1]);
				user.setFunction("get purchasing program");

				System.out.println("sending to server : " + user);
				this.sendToServer(user);

			} else if (splitMsg[0].equals("fastfuel")) {
				System.out.println("sending to server : " + message);
				this.sendToServer(message);

			} else if (splitMsg[0].equals("homefuel")) {
				if (splitMsg[1].equals("get")) {
					System.out.println("sending to server : " + message);
					this.sendToServer(message);

				} else if (splitMsg[1].equals("set")) {
					Date now = new Date();
					now.setHours(now.getHours() - 3);
					now.setMinutes(now.getMinutes() + 30);

					Date dueTime = new Date();
					if (splitMsg[3].equals("Urgent"))
						dueTime.setHours(now.getHours() + 6);
					else
						dueTime.setHours(now.getHours() + 240);

					HomeFuelOrder homeFuelOrder = new HomeFuelOrder(now, Double.parseDouble(splitMsg[5]), splitMsg[6],
							splitMsg[2], ProductName.HomeFuel, ShipmentType.valueOf(splitMsg[3]), dueTime,
							Double.parseDouble(splitMsg[4]));
					this.sendToServer(homeFuelOrder);
				}

			} else {
				flag = false;
				awaitResponse = false;
			}

			if (flag == true) {
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

	private void calculateHomeFuelOrderFinalPrice(String amount, String price, String shipmentType) {
		double amountDiscount = 0;
		double amountToBuy = Double.parseDouble(amount);
		if (amountToBuy >= 600 && amountToBuy <= 800) {
			amountDiscount = 0.03;
		} else if (amountToBuy > 800) {
			amountDiscount = 0.04;
		}

		double shipmentMul = 1;
		if (shipmentType.equals("Urgent"))
			shipmentMul = 1.02;

		double homeFuelPrice = Double.parseDouble(price);

		Float finalPrice = (float) ((1 - amountDiscount) * (shipmentMul * homeFuelPrice * amountToBuy + 5.5));
		this.currentWindow.callAfterMessage(finalPrice);
	}

}
