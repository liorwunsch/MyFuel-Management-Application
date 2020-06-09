package guiClient;

import client.MarketingManagerController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;

public class MarketingManagerWindow extends EmployeeWindow {

	@FXML
	private ToggleButton sidebar_btn0;

	@FXML
	private ToggleGroup one;

	@FXML
	private ToggleButton sidebar_btn4;

	@FXML
	private ToggleButton sidebar_btn1;

	@FXML
	private ToggleButton sidebar_btn3;

	@FXML
	private ToggleButton sidebar_btn2;

	@FXML
	private Button btnHomeGenerateAnalysis;

	@FXML
	private AnchorPane initiateSalePane;

	@FXML
	private TableView<?> tvISSalesPattern;

	@FXML
	private Button btnISInitiate;

	@FXML
	private DatePicker dpISDate;

	@FXML
	private TextField tfISTime;

	@FXML
	private AnchorPane createSalePatternPane;

	@FXML
	private TableView<?> tvCSPAnalysis;

	@FXML
	private TextField tfCSPDuration;

	@FXML
	private CheckBox cbCSPDiesel;

	@FXML
	private CheckBox cbCSPGasoline;

	@FXML
	private CheckBox cbCSPMotorbike;

	@FXML
	private TextField tfCSPDieselDisc;

	@FXML
	private TextField tfCSPGasolineDisc;

	@FXML
	private TextField tfCSPMotorbikeDisc;

	@FXML
	private Button btnCSPCreate;

	@FXML
	private AnchorPane generateReportPane;

	@FXML
	private Button btnGMRNext;

	@FXML
	private RadioButton rbGMRPeriodicReport;

	@FXML
	private ToggleGroup two;

	@FXML
	private RadioButton rbGMRCommentReport;

	@FXML
	private AnchorPane paneGMRCommentNext;

	@FXML
	private Button btnGMRViewReport;

	@FXML
	private TableView<?> tvGMRPickSale;

	@FXML
	private AnchorPane paneGMRPeriodicNext;

	@FXML
	private DatePicker dpGMRStartDate;

	@FXML
	private DatePicker dpGMREndDate;

	@FXML
	private AnchorPane requestRateUpdatePane;

	@FXML
	private CheckBox cbRPRUDiesel;

	@FXML
	private CheckBox cbRPRUGasoline;

	@FXML
	private CheckBox cbRPRUMotorbike;

	@FXML
	private CheckBox cbRPRUHomeFuel;

	@FXML
	private TextField tfRPRUDiesel2;

	@FXML
	private TextField tfRPRUGasoline2;

	@FXML
	private TextField tfRPRUHomeFuel2;

	@FXML
	private TextField tfRPRUMotorbike2;

	@FXML
	private TextField tfRPRUDiesel1;

	@FXML
	private TextField tfRPRUGasoline1;

	@FXML
	private TextField tfRPRUMotorbike1;

	@FXML
	private TextField tfRPRUHomeFuel1;

	@FXML
	private Button btnRPRUSend;

	@FXML
	private AnchorPane saleCommentReportPane;

	@FXML
	private TextField tfSCRSaleID;

	@FXML
	private TextField tfSCRNumberOf;

	@FXML
	private TextField tfSCREndTime;

	@FXML
	private TextField tfSCRStartTime;

	@FXML
	private TextField tfSCRSumPurchase;

	@FXML
	private Label lblSCRDateCreated;

	@FXML
	private Button btnSCRClose;

	@FXML
	private TableView<?> tvSCRDetails;

	@FXML
	private AnchorPane periodicReportPane;

	@FXML
	private TextField tfPCRTo;

	@FXML
	private TextField tfPCRFrom;

	@FXML
	private Label lblPCRDateCreated;

	@FXML
	private Button btnPCRClose;

	@FXML
	private TableView<?> tvPCRDetails;

	@FXML
	void initialize() {
		this.visibleNow = this.homePane;
		this.controller = MarketingManagerController.getInstance();
		this.controller.setCurrentWindow(this);
	}

	@Override
	public Window getWindow() {
		return this.periodicReportPane.getScene().getWindow();
	}

	@Override
	public void callAfterMessage(Object lastMsgFromServer) {
		super.callAfterMessage(lastMsgFromServer);
		/**
		 * 
		 */
	}

	@FXML
	void openCreateSalesPattern(ActionEvent event) {

	}

	@FXML
	void openGenerateMarketingReport(ActionEvent event) {

	}

	@FXML
	void openInitiateSale(ActionEvent event) {

	}

	@FXML
	void openRequestProductRateUpdate(ActionEvent event) {

	}

	@Override
	public void clearFields() {
		// TODO Auto-generated method stub
		
	}

}
