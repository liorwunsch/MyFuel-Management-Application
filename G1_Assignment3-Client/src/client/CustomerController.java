package client;

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

	@Override
	public void handleMessageFromClientUI(String message) {
		super.handleMessageFromClientUI(message);
		/**
		 * 
		 */
	}

}
