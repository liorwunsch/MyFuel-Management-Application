package guiClient;

import client.FuelStationManagerController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;

public class FuelStationManagerWindow extends EmployeeWindow {

	@FXML
	private ToggleButton sidebar_btn0;

	@FXML
	private ToggleGroup one;

	@FXML
	private ToggleButton sidebar_btn1;

	@FXML
	private ToggleButton sidebar_btn2;

	@FXML
	private ToggleButton sidebar_btn3;

	@FXML
	private AnchorPane thresholdPane;

	@FXML
	private CheckBox cbUTGasoline;

	@FXML
	private CheckBox cbUTDiesel;

	@FXML
	private CheckBox cbUTMotorbike;

	@FXML
	private TextField tfUTGasoline2;

	@FXML
	private TextField tfUTDiesel2;

	@FXML
	private TextField tfUTMotorbike2;

	@FXML
	private TextField tfUTGasoline1;

	@FXML
	private TextField tfUTMotorbike1;

	@FXML
	private TextField tfUTDiesel1;

	@FXML
	private Button btnUTUpdate;

	@FXML
	private AnchorPane assessPane;

	@FXML
	private Button btnASOConfirm;

	@FXML
	private Button btnASODecline;

	@FXML
	private ComboBox<?> cobASOOrderId;

	@FXML
	private TextField tfASOName;

	@FXML
	private TextField tfASOAmount;

	@FXML
	private TextField tfASOFinalPrice;

	@FXML
	private TextField tfASOTime;

	@FXML
	private TextField tfASOAddress;

	@FXML
	private Button btnASOShowOrder;

	@FXML
	private TextField tfASOThreshold;

	@FXML
	private TextField tfASOInStock;

	@FXML
	private AnchorPane quarterlyReportPane;

	@FXML
	private ComboBox<?> cobGQRYear;

	@FXML
	private ComboBox<?> cobGQRQuarter;

	@FXML
	private Button btnGQRView;

	@FXML
	private AnchorPane declineOrderPane;

	@FXML
	private TextArea taDOResons;

	@FXML
	private Button btnDOOk;

	@FXML
	private Button btnDOCancel;

	@FXML
	private AnchorPane quarterReportPane;

	@FXML
	private Label lblQRDateCreated;

	@FXML
	private Label lblQRYear;

	@FXML
	private Label lblQRQuarter;

	@FXML
	private Button btnQRNext;

	@FXML
	private Button btnQRPrevious;

	@FXML
	private AnchorPane paneQRPart1;

	@FXML
	private Label lblQRTitle1;

	@FXML
	private TableView<?> tvQRDetails1;

	@FXML
	private AnchorPane paneQRPart2;

	@FXML
	private Label lblQRTitle2;

	@FXML
	private TableView<?> tvQRDetails2;

	@FXML
	private AnchorPane paneQRPart3;

	@FXML
	private Label lblQRTitle3;

	@FXML
	private TableView<?> tvQRDetails3;

	@FXML
	private Button btnQRClose;

	@FXML
	private Label lblQRPageNum;

	@FXML
	void initialize() {
		this.visibleNow = this.homePane;
		this.controller = FuelStationManagerController.getInstance();
		this.controller.setCurrentWindow(this);
	}

	@Override
	public Window getWindow() {
		return this.assessPane.getScene().getWindow();
	}

	@Override
	public void callAfterMessage(Object lastMsgFromServer) {
		super.callAfterMessage(lastMsgFromServer);
		/**
		 * 
		 */
	}

	@FXML
	void Decline_Order(ActionEvent event) {

	}

	@FXML
	void openAssessStationOrders(ActionEvent event) {

	}

	@FXML
	void openGenerateQuarterlyReport(ActionEvent event) {

	}

	@FXML
	void openUpdateThreshold(ActionEvent event) {

	}

}
