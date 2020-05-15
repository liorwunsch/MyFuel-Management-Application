package guiClient;

import java.io.IOException;
import java.util.Optional;

import client.LoginController;

//import java.util.Set;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.stage.Stage;

public class MarketingWindow implements IFXML {

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

	private String userName;
	@SuppressWarnings("unused")
	private LoginWindow loginWindow;
	private LoginController loginController;

	@FXML
	void initialize() throws IOException {
		visableNow = addCustomer_pane;
		loginController = new LoginController("localhost", 5555, this);
	}

	@FXML
	public void exitApplication(ActionEvent event) {
		btnSignOutClicked();
	}

	public void btnSignOutHover() {
		btnSignOut.setStyle("-fx-background-color:  #4c606e");
	}

	public void btnSignOutExited() {
		btnSignOut.setStyle("-fx-background-color:  #1e262c");
	}

	public void setUserName(String userName) {
		this.userName = userName;
		lblHelloUser.setText("Hello, " + userName);
	}

	public void setLoginWindow(LoginWindow loginWindow) {
		this.loginWindow = loginWindow;
	}

	@Override
	public void callAfterMessage() {
		System.out.println("Last Message from client: " + loginController.lastMsg);
		if (loginController.lastMsg.startsWith("sign out complete")) {
			signOut();
		}
		if (loginController.lastMsg.startsWith("sign out failed")) {
			Alert a = new Alert(Alert.AlertType.ERROR);
			a.setContentText("signing out has failed!");
			a.show();
		}
	}

	public void signOut() {
		btnSignOut.getScene().getWindow().hide();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/login/LoginWindow.fxml"));
			Parent root = (Parent) loader.load();
			Scene newScene = new Scene(root);
			Stage newStage = new Stage();
			newStage.setScene(newScene);
			newStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void btnSignOutClicked() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Sign Out");
		alert.setHeaderText("Would you like to sign out?");
		ButtonType buttonTypeOne = new ButtonType("Yes");
		ButtonType buttonTypeTwo = new ButtonType("No");
		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeOne) {
			System.out.println("the user: " + userName);
			loginController.handleMessageFromClientUI("sign out " + userName);
		} else if (result.get() == buttonTypeTwo) {
			alert.hide();
		}
	}

	public void Add_Customer(ActionEvent event) throws Exception {
		System.out.println("add cust");
		visableNow.setVisible(false);
		addCustomer_pane.setVisible(true);
		visableNow = addCustomer_pane;
		topbar_window_label.setText("Add Customer");
		AnchorPane anchorPane = addCustomer_pane;
		System.out.println(anchorPane.isVisible());
	}

	public void Add_Car(ActionEvent event) throws Exception {
		System.out.println("add car");
		visableNow.setVisible(false);
		addCar_pane.setVisible(true);
		visableNow = addCar_pane;
		topbar_window_label.setText("Add Car");
	}

	public void Purchase_Prog(ActionEvent event) throws Exception {
		System.out.println("Purchase Program");
		visableNow.setVisible(false);
		PurchProg_pane.setVisible(true);
		visableNow = PurchProg_pane;
		topbar_window_label.setText("Purchasing Program");
	}

	public void Pricing_Model(ActionEvent event) throws Exception {
		System.out.println("Pricing Model");
		visableNow.setVisible(false);
		pricingModel_pane.setVisible(true);
		visableNow = pricingModel_pane;
		topbar_window_label.setText("Pricing Model");
	}

}
