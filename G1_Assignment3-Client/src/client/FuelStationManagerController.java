package client;

public class FuelStationManagerController extends UserController {

	private static FuelStationManagerController instance;

	/**
	 * singleton class constructor
	 */
	private FuelStationManagerController() {
		super();
	}

	/**
	 * @return instance of this class
	 */
	public static FuelStationManagerController getInstance() {
		if (instance == null) {
			instance = new FuelStationManagerController();
		}
		return instance;
	}

	@Override
	public void handleMessageFromClientUI(String message) {
		super.handleMessageFromClientUI(message);
		/**
		 * 
		 */
	}

}
