package guiClient;

import client.MarketingRepresentativeController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;

public class CustomerWindow extends UserWindow {

	@FXML	protected ToggleButton sidebar_btn11;
	@FXML	private ToggleButton sidebar_btn1;
	@FXML	private ToggleButton sidebar_btn3;
	@FXML	private AnchorPane mainwindow_pane;
	@FXML	private Label lblTitleFillFields;
	@FXML	private AnchorPane pricingModel_pane;
	@FXML	private TextField tfDiscout;
	@FXML	private TextField tfAddress;
	@FXML	private ScrollPane purchProg_SingleProgBox_SP;
	@FXML	private RadioButton purchProg_SingleProg_RB;
	@FXML	private ToggleGroup three;
	@FXML	private ScrollPane purchProg_SingleProgBox_SP1;
	@FXML	private RadioButton purchProg_SingleProg_RB1;
	@FXML	private Button btnShowPrice;
	@FXML	private Label lblTitleOrderDetails;
	@FXML	private TextField tfFinalPriceValueOrderDetails;
	@FXML	private TextField tfAmountOrderDetails;
	@FXML	private TextField tfShipmentMethodOrderDetails;
	@FXML	private Button btnConfirmHomeFuelOrder;

	@FXML
	void initialize() { // will change
		this.controller = MarketingRepresentativeController.getInstance();
	}
	
	@Override
	public Window getWindow() {
		return this.btnConfirmHomeFuelOrder.getScene().getWindow();
	}

	@Override
	public void callAfterMessage(Object lastMsgFromServer) {
		if (lastMsgFromServer instanceof String) {
			String message = lastMsgFromServer.toString();
			if (message.startsWith("sign out"))
				this.handleSignOutFromServer(message, this.getWindow());
		}
		
		/**
		 * 
		 */
	}

}
