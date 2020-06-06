package client;

/**
 * @version Basic
 * @author Lior
 */
public class MarketingRepresentativeController extends MarketingDepWorkerController {

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

	@Override
	public void handleMessageFromClientUI(String message) {
		super.handleMessageFromClientUI(message);
		/**
		 * 
		 */
	}

}
