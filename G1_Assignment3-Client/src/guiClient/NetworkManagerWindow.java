package guiClient;

import client.NetworkManagerController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;

public class NetworkManagerWindow extends EmployeeWindow {

	@FXML
	private ToggleButton sidebar_btn0;

	@FXML
	private ToggleGroup one;

	@FXML
	private ToggleButton sidebar_btn1;

	@FXML
	private ToggleButton sidebar_btn2;

	@FXML
	private AnchorPane assessProductRatesUpdatePane;

	@FXML
	private Button btnAPRURConfirm;

	@FXML
	private Button btnAPRURDecline;

	@FXML
	private ComboBox<?> cobAPRURRequestID;

	@FXML
	private Button btnAPRURShow;

	@FXML
	private TableView<?> tvAPRURDetails;

	@FXML
	private AnchorPane viewQuarterReportPane;

	@FXML
	private ComboBox<?> cobVQRYear;

	@FXML
	private ComboBox<?> cobVQRQuarter;

	@FXML
	private ComboBox<?> cobVQRFuelStation;

	@FXML
	private Button btnVQRView;

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
	private Label lblQRFuelStationName;

	@FXML
	private Label lblQRPageNum;

	@FXML
	void initialize() {
		this.visibleNow = this.homePane;
		this.controller = NetworkManagerController.getInstance();
		this.controller.setCurrentWindow(this);
	}

	@Override
	public Window getWindow() {
		return this.quarterReportPane.getScene().getWindow();
	}

	@Override
	public void callAfterMessage(Object lastMsgFromServer) {
		super.callAfterMessage(lastMsgFromServer);
		/**
		 * 
		 */
	}

	@FXML
	void openAssessProductRates(ActionEvent event) {

	}

	@FXML
	void openViewQuarterlyReport(ActionEvent event) {

	}

	@Override
	public void clearFields() {
		// TODO Auto-generated method stub

	}

}
