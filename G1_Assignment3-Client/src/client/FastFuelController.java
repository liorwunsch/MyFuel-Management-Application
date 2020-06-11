package client;

import java.io.IOException;
import java.net.ConnectException;

import entities.FastFuel;
import guiClient.FastFuelWindow;

/**
 * @version Basic
 * @author Lior
 */
public class FastFuelController extends ClientController {

	private static FastFuelController instance;

	/**
	 * singleton class constructor
	 */
	private FastFuelController() {
		super();
	}

	/**
	 * @return instance of this class
	 */
	public static FastFuelController getInstance() {
		if (instance == null) {
			instance = new FastFuelController();
		}
		return instance;
	}

	@Override
	public void handleMessageFromClientUI(String message) {
		String[] splitMsg = message.split(" ");

		try {
			openConnection();
			awaitResponse = true;
			boolean flag = true;

			if (splitMsg[0].equals("getdiscount")) {
				FastFuel fastFuel = new FastFuel(splitMsg[1]);
				fastFuel.setFuelStationID(Integer.parseInt(splitMsg[2]));
				fastFuel.setAmountBought(Double.parseDouble(splitMsg[3]));
				fastFuel.setFunction("get");
				this.sendToServer(fastFuel);

			} else if (splitMsg[0].equals("saveFastFuel")) {
				System.out.println("got saveFastFuel from boundary");
				FastFuel fastFuel = FastFuelWindow.getCurrentEmulation();
				fastFuel.setFunction("save");
				this.sendToServer(fastFuel);
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

		} catch (

		ConnectException ce) {
			this.currentWindow.openErrorAlert("Server Error", "Error - No connection to server");
			ce.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

}
