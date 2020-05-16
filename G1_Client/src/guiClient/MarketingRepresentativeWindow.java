package guiClient;

import java.io.IOException;
import java.util.Optional;

import client.UserController;

//import java.util.Set;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
//import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
//import javafx.scene.control.PasswordField;
//import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class MarketingRepresentativeWindow extends UserWindow {

	@FXML
	private ToggleButton sidebar_btn1;

	@FXML
	private ToggleButton sidebar_btn2;

	@FXML
	private ToggleButton sidebar_btn3;

	@FXML
	private ToggleButton sidebar_btn4;

	@FXML
	private Button btnSignOut;

	@FXML
	private AnchorPane addCustomer_pane;

	@FXML
	private AnchorPane addCar_pane;

	@FXML
	private AnchorPane PurchProg_pane;

	@FXML
	private AnchorPane pricingModel_pane;

	@FXML
	private Label topbar_window_label;

	@FXML
	private BorderPane main_pane;

	@FXML
	private AnchorPane visableNow;

	@FXML
	private Label lblHelloUser;

	private String username;
	private UserController userController;

	@FXML
	void initialize() throws IOException {
		this.visableNow = addCustomer_pane;
		this.userController = UserController.getInstance();
	}

	@Override
	public void callAfterMessage(String lastMsg) {
		if (lastMsg.startsWith("sign out"))
			this.handleSignOut(lastMsg, this.btnSignOut.getScene().getWindow());
	}

	public void btnSignOutClicked(ActionEvent event) throws Exception {
		this.signOutClicked();
	}

	public void Add_Customer(ActionEvent event) throws Exception {
		visableNow.setVisible(false);
		addCustomer_pane.setVisible(true);
		visableNow = addCustomer_pane;
		topbar_window_label.setText("Add Customer");
	}

	public void Add_Car(ActionEvent event) throws Exception {
		visableNow.setVisible(false);
		addCar_pane.setVisible(true);
		visableNow = addCar_pane;
		topbar_window_label.setText("Add Car");
	}

	public void Purchase_Prog(ActionEvent event) throws Exception {
		visableNow.setVisible(false);
		PurchProg_pane.setVisible(true);
		visableNow = PurchProg_pane;
		topbar_window_label.setText("Purchasing Program");
	}

	public void Pricing_Model(ActionEvent event) throws Exception {
		visableNow.setVisible(false);
		pricingModel_pane.setVisible(true);
		visableNow = pricingModel_pane;
		topbar_window_label.setText("Pricing Model");
	}

	public void btnSignOutHover() {
		btnSignOut.setStyle("-fx-background-color:  #4c606e");
	}

	public void btnSignOutExited() {
		btnSignOut.setStyle("-fx-background-color:  #1e262c");
	}

	public void setUsername(String username) {
		this.username = username;
		lblHelloUser.setText("Hello, " + username);
	}

	public void signOutClicked() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Sign Out");
		alert.setHeaderText("Would you like to sign out?");
		ButtonType buttonTypeOne = new ButtonType("Yes");
		ButtonType buttonTypeTwo = new ButtonType("No");
		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeOne) {
			this.userController.setCurrentWindow(this);
			this.userController.handleMessageFromClientUI("signout " + username);
		}
		if (result.get() == buttonTypeTwo)
			alert.hide();
	}

}
