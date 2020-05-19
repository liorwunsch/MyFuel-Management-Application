package guiClient;

import client.MarketingRepresentativeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

	@FXML	private ToggleButton sidebar_btn1;
	@FXML	private ToggleButton sidebar_btn2;
	@FXML	private ToggleButton sidebar_btn3;
	@FXML	private ToggleButton sidebar_btn4;
	@FXML	private AnchorPane addCustomer_pane;
	@FXML	private AnchorPane addCar_pane;
	@FXML	private AnchorPane PurchProg_pane;
	@FXML	private AnchorPane pricingModel_pane;
	@FXML	private BorderPane main_pane;
	@FXML	private AnchorPane visableNow;

	@FXML
	void initialize() {
		this.visableNow = addCustomer_pane;
		this.controller = MarketingRepresentativeController.getInstance();
	}

	@Override
	public Window getWindow() {
		return this.addCustomer_pane.getScene().getWindow();
	}

	@Override
	public void callAfterMessage(Object lastMsgFromServer) {
		if (lastMsgFromServer instanceof String) {
			String message = lastMsgFromServer.toString();
			if (message.startsWith("sign out"))
				this.handleSignOutFromServer(message, this.btnSignOut.getScene().getWindow());
		}
	}

	@FXML
	void Add_Customer(ActionEvent event) {
		visableNow.setVisible(false);
		addCustomer_pane.setVisible(true);
		visableNow = addCustomer_pane;
		topbar_window_label.setText("Add Customer");
	}

	@FXML
	void Add_Car(ActionEvent event) {
		visableNow.setVisible(false);
		addCar_pane.setVisible(true);
		visableNow = addCar_pane;
		topbar_window_label.setText("Add Car");
	}

	@FXML
	void Purchase_Prog(ActionEvent event) {
		visableNow.setVisible(false);
		PurchProg_pane.setVisible(true);
		visableNow = PurchProg_pane;
		topbar_window_label.setText("Purchasing Program");
	}

	@FXML
	void Pricing_Model(ActionEvent event) {
		visableNow.setVisible(false);
		pricingModel_pane.setVisible(true);
		visableNow = pricingModel_pane;
		topbar_window_label.setText("Pricing Model");
	}

}
