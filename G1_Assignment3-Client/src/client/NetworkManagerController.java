package client;

public class NetworkManagerController extends UserController {

	private static NetworkManagerController instance;

	/**
	 * singleton class constructor
	 */
	private NetworkManagerController() {
		super();
	}

	/**
	 * @return instance of this class
	 */
	public static NetworkManagerController getInstance() {
		if (instance == null) {
			instance = new NetworkManagerController();
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
