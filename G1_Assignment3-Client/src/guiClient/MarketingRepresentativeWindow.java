package guiClient;

import java.io.IOException;

import client.MarketingRepresentativeController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;

/**
 * boundary for marketing representative window
 * 
 * @version Basic
 * @author Elroy, Lior
 */
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

	@FXML
	void initialize() throws IOException {
		this.visableNow = addCustomer_pane;
		this.controller = MarketingRepresentativeController.getInstance();
	}

	@Override
	public void setUsername(String username) {
		this.username = username;
		this.lblHelloUser.setText("Hello, " + username);
	}

	@Override
	public Window getWindow() {
		return this.btnSignOut.getScene().getWindow();
	}

	@Override
	public void callAfterMessage(Object lastMsgFromServer) {
		if (lastMsgFromServer instanceof String) {
			String message = lastMsgFromServer.toString();
			if (message.startsWith("sign out"))
				this.handleSignOutFromServer(message, this.btnSignOut.getScene().getWindow());
		}
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

	public void btnSignOutClicked(ActionEvent event) throws Exception {
		this.signOutClicked(this.getWindow());
	}

	public void btnSignOutHover() {
		btnSignOut.setStyle("-fx-background-color:  #4c606e");
	}

	public void btnSignOutExited() {
		btnSignOut.setStyle("-fx-background-color:  #1e262c");
	}

}
