package guiClient;

import client.LoginController;
import client.SupplierController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * boundary for login window
 * 
 * @version Final
 * @author Elroy, Lior
 */
public class LoginWindow extends AFXML {

	@FXML	private AnchorPane serverPane;
	@FXML	private TextField tfLoginServerHost;
	@FXML	private TextField tfLoginServerPort;
	@FXML	private Label lblError1;
	@FXML	private Button btnContinue;

	@FXML	private AnchorPane loginPane;
	@FXML	private TextField tfLoginUserName;
	@FXML	private PasswordField tfLoginPassword;
	@FXML	private ToggleGroup rb1;
	@FXML	private RadioButton rbEmployee;
	@FXML	private RadioButton rbCustomer;
	@FXML	private Label lblError;
	@FXML	private Button btnSignIn;
	//vlad added
	private String username;
	
	@FXML
	void initialize() {
		this.visibleNow = this.serverPane;
	}

	/**
	 * true if called from user window
	 * 
	 * @param flag
	 */
	public void setVisibleNow(boolean flag) {
		if (flag == true) {
			this.controller = LoginController.getInstance();
//			this.controller.setCurrentWindow(this);
		}
		this.visibleNow.setVisible(false);
		this.loginPane.setVisible(true);
		this.visibleNow = this.loginPane;
	}

	/*********************** button listeners ***********************/

	@FXML
	void btnContinuePressed(ActionEvent event) {
		myContinue();
	}

	@FXML
	void btnSignInPressed(ActionEvent event) {
		mySignIn();
	}

	/*********************** button functions ***********************/

	/**
	 * executes when continue button is pressed, requests server connection from
	 * controller
	 */
	private void myContinue() {
		String host = this.tfLoginServerHost.getText();
		String port = this.tfLoginServerPort.getText();
		if (port.isEmpty() || port.matches(".*[A-z].*")
				|| (!host.equals("localhost") && (!host.startsWith("192.168.") || host.matches(".*[A-z].*")))) {
			this.lblError1.setText("Syntax Error");
			this.lblError1.setVisible(true);
			this.tfLoginServerHost.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
			this.tfLoginServerPort.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
			return;
		}
		this.lblError1.setVisible(false);
		this.tfLoginServerHost.setStyle("-fx-border-style: none;");
		this.tfLoginServerPort.setStyle("-fx-border-style: none;");

		this.controller = LoginController.getInstance(host, Integer.parseInt(port), this);
		this.controller.setCurrentWindow(this);
	}

	/**
	 * handles sign in request by: checking no information is missing and prompting
	 * for errors, sending all information to <Code>handleMessageFromClientUI</Code>
	 * as a string
	 */
	private void mySignIn() {
		username = this.tfLoginUserName.getText();
		String password = this.tfLoginPassword.getText();
		String userType;

		if (username.isEmpty() || password.isEmpty()) {
			this.lblError.setText("Missing Required Fields");
			this.lblError.setVisible(true);
			this.tfLoginUserName.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
			this.tfLoginPassword.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
			return;
		}
		this.lblError.setVisible(false);
		this.tfLoginUserName.setStyle("-fx-border-style: none;");
		this.tfLoginPassword.setStyle("-fx-border-style: none;");

		if (this.rbEmployee.isSelected())
			userType = this.rbEmployee.getText();
		else
			userType = this.rbCustomer.getText();

		this.controller.setCurrentWindow(this);
		this.controller.handleMessageFromClientUI(("login" + " " + username + " " + password + " " + userType));
	}

	/*************** boundary "logic" - window changes ***************/

	/**
	 * if login details valid, send <code>Role</Code> to <Code>successLogin()</Code>
	 * so another window will open accordingly
	 * 
	 * @param lastMsgFromServer
	 */
	@Override
	public void callAfterMessage(Object lastMsgFromServer) {
		if (lastMsgFromServer instanceof String) {
			String message = lastMsgFromServer.toString();

			if (message.startsWith("login succeeded")) {
				String[] splitMsg = message.split(" ");
				successLogin(splitMsg[2]);
			}

			if (message.startsWith("login failed"))
				failedLogin();
			if (message.startsWith("login already connected"))
				alreadyConnectedLogin();
		}
	}

	/**
	 * called when sign in is valid, determines path of fxml file and window title
	 * according to <Code>Role</Code>, loads new window accordingly and sends
	 * username to its boundary
	 * 
	 * @param role
	 */
	private void successLogin(String role) {
		this.lblError.setVisible(false);

		String newWindowPath = "";
		String newWindowTitle = "";

		if (role.equals("Customer")) {
			newWindowPath = "/windows/CustomerWindow.fxml";
			newWindowTitle = "MyFuel Customer";
		}
		if (role.equals("FuelStationManager")) {
			newWindowPath = "/windows/FuelStationManagerWindow.fxml";
			newWindowTitle = "MyFuel Fuel Station Manager";
		}
		if (role.equals("MarketingManager")) {
			newWindowPath = "/windows/MarketingManagerWindow.fxml";
			newWindowTitle = "MyFuel Marketing Manager";
		}
		if (role.equals("MarketingRepresentative")) {
			newWindowPath = "/windows/MarketingRepresentativeWindow.fxml";
			newWindowTitle = "MyFuel Marketing Representative";
		}
		if (role.equals("NetworkManager")) {
			newWindowPath = "/windows/NetworkManagerWindow.fxml";
			newWindowTitle = "MyFuel Network Manager";
		}
		if (role.equals("Supplier")) {
			newWindowPath = "/windows/SupplierWindow.fxml";
			newWindowTitle = "MyFuel Supplier";
			//vlad added
			SupplierController.getInstance().username = username;
		}

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(newWindowPath));
			Scene newScene = new Scene(loader.load());
			Stage newStage = new Stage();

			UserWindow newWindow = loader.getController();
			newWindow.setUserComponents(this.tfLoginUserName.getText());

			newStage.setResizable(false);
			newStage.setScene(newScene);
			newStage.setTitle(newWindowTitle);
			newStage.initStyle(StageStyle.UNDECORATED);
			newStage.show();
			this.btnSignIn.getScene().getWindow().hide();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * called when sign in failed
	 */
	private void failedLogin() {
		this.lblError.setText("wrong username or password");
		this.lblError.setVisible(true);
		this.tfLoginUserName.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
		this.tfLoginPassword.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
	}

	/**
	 * called when user is already connected in another client
	 */
	private void alreadyConnectedLogin() {
		this.lblError.setText("user already connected");
		this.lblError.setVisible(true);
	}

	/*********************** key listeners ***********************/

	@FXML
	void enterKeyPressed(KeyEvent event) {
		switch (event.getCode()) {
		case ENTER:
			this.mySignIn();
			break;
		default:
			break;
		}
	}

	@FXML
	void enterKeyContinue(KeyEvent event) {
		switch (event.getCode()) {
		case ENTER:
			this.myContinue();
			break;
		default:
			break;
		}
	}

	@FXML
	void tabEmployeePressed(KeyEvent event) {
		switch (event.getCode()) {
		case TAB:
			this.rbCustomer.setSelected(true);
			break;
		case ENTER:
			this.mySignIn();
			break;
		default:
			break;
		}
	}

	@FXML
	void tabCustomerPressed(KeyEvent event) {
		switch (event.getCode()) {
		case TAB:
			this.rbEmployee.setSelected(true);
			break;
		case ENTER:
			this.mySignIn();
			break;
		default:
			break;
		}
	}

	@FXML
	public void closeTopBar(ActionEvent event) {
		System.exit(0);
	}

}
