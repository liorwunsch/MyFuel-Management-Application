package guiClient;

import client.CustomerController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;

public class CustomerWindow extends UserWindow {

	@FXML	private ToggleGroup one;
	@FXML	private ToggleButton sidebar_btn0;
	@FXML	private ToggleButton sidebar_btn1;
	@FXML	private ToggleButton sidebar_btn2;
	
	@FXML	private Label lblHomePayment;
	@FXML	private TextField tfHomeTotal;
	@FXML	private Label lblHomeMember;
	@FXML	private TableView<?> tvHomeActivity;
	@FXML	private AnchorPane orderHomeFuelPane;
	@FXML	private Label lblTitleFillFields;
	@FXML	private AnchorPane pricingModel_pane;
	@FXML	private TextField tfOHFAmount1;
	@FXML	private TextField tfOHFAddress;
	@FXML	private TextField tfOHFPrice1;
	@FXML	private ScrollPane purchProg_SingleProgBox_SP;
	@FXML	private Label lblOHFShipmentPrice1;
	@FXML	private Label lblOHFShipmentMul1;
	@FXML	private Label lblOHFShipmentDelivery1;
	@FXML	private RadioButton rbOHFShipment1;
	@FXML	private ToggleGroup three;
	@FXML	private ScrollPane purchProg_SingleProgBox_SP1;
	@FXML	private Label lblOHFShipmentPrice2;
	@FXML	private Label lblOHFShipmentMul2;
	@FXML	private Label lblOHFShipmentDelivery2;
	@FXML	private RadioButton rbOHFShipment2;
	@FXML	private Button btnOHFShowPrice;
	@FXML	private Label lblTitleOrderDetails;
	@FXML	private AnchorPane paneOHFOrderDetails;
	@FXML	private TextField tfOHFDate;
	@FXML	private TextField tfOHFFinalPrice;
	@FXML	private TextField tfOHFAmount2;
	@FXML	private TextField tfOHFShipmentReview;
	@FXML	private Button btnOHFConfirm;
	@FXML	private Label lblTitleOrderDetails1;
	@FXML	private AnchorPane viewOrderPane;
	@FXML	private TableView<?> tvVODetails;

	@FXML
	void initialize() {
		this.visibleNow = this.homePane;
		this.controller = CustomerController.getInstance();
		this.controller.setCurrentWindow(this);
	}
	
	@Override
	public Window getWindow() {
		return this.rbOHFShipment1.getScene().getWindow();
	}
	
	@Override
	public void callAfterMessage(Object lastMsgFromServer) {
		super.callAfterMessage(lastMsgFromServer);
		/**
		 * 
		 */
	}
	
	@FXML
	void btnHomeUpdatePressed(ActionEvent event) {
		/**
		 * 
		 */
	}
}
