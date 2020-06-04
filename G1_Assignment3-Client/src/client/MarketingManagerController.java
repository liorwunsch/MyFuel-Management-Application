package client;

public class MarketingManagerController extends MarketingDepWorkerController {

	private static MarketingManagerController instance;

	/**
	 * singleton class constructor
	 */
	private MarketingManagerController() {
		super();
	}

	/**
	 * @return instance of this class
	 */
	public static MarketingManagerController getInstance() {
		if (instance == null) {
			instance = new MarketingManagerController();
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
