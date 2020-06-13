package guiClient;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import client.MarketingManagerController;
import entities.CustomerBoughtFromCompany;
import entities.CustomerBoughtInSale;
import entities.PeriodicCustomersReport;
import entities.PeriodicReportList;
import entities.Product;
import entities.ProductInSalePatternList;
import entities.ProductInSalesPattern;
import entities.ProductRateList;
import entities.RankingSheetList;
import entities.RowForSalesPatternTable;
import entities.RowInSaleCommentsReportTable;
import entities.SaleCommentsReportList;
import entities.SaleCommentsReport;
import entities.SalesList;
import entities.SalesPattern;
import entities.SalesPatternList;
import enums.FuelCompanyName;
import enums.ProductName;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;

/**
 * 
 * @author Elroy
 *
 */
public class MarketingManagerWindow extends MarketingDepWorkerWindow {
	@FXML
	private AnchorPane paneChooseReportType;
	@FXML
	private Label lblDieselERR2;
	@FXML
	private Label lblGasolineERR2;
	@FXML
	private Label lblMotorERR2;
	@FXML
	private Label lblHomeERR2;

	@FXML
	private ToggleGroup one;
	@FXML
	private ToggleButton tbHomePage;
	@FXML
	private ToggleButton tbInitiateSale;
	@FXML
	private ToggleButton tbCreateSalePattern;
	@FXML
	private ToggleButton tbGenerateReport;
	@FXML
	private ToggleButton tbRequestProductRateUpdate;

	@FXML
	private ToggleGroup one1;
	@FXML
	private ToggleGroup one2;

	@FXML
	private AnchorPane initiateSalePane; // initiate sale page
	@FXML
	private TableView<RowForSalesPatternTable> tvISSalesPattern; // table view of sales patterns
	@FXML
	private Button btnISInitiate;
	@FXML
	private DatePicker dpISDate;
	@FXML
	private TextField tfISTime;

	@FXML
	private AnchorPane generateReportPane; // generate marketing report page
	@FXML
	private Button btnGMRViewReport2;
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
	private TableView<RowInSaleCommentsReportTable> tvGMRPickSale;

	@FXML
	private AnchorPane paneGMRPeriodicNext;
	@FXML
	private DatePicker dpGMRStartDate;
	@FXML
	private DatePicker dpGMREndDate;

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
	private TableView<CustomerBoughtInSale> tvSCRDetails;

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
	private TableView<CustomerBoughtFromCompany> tvPCRDetails;

	@FXML
	private AnchorPane requestRateUpdatePane; // request page
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
	private TableColumn<RowForSalesPatternTable, Integer> idColumn;
	@FXML
	private TableColumn<RowForSalesPatternTable, Integer> durationColumn;
	@FXML
	private TableColumn<RowForSalesPatternTable, Double> dieselDiscountColumn;
	@FXML
	private TableColumn<RowForSalesPatternTable, Double> gasolineDiscountColumn;
	@FXML
	private TableColumn<RowForSalesPatternTable, Double> motorBikeDiscountColumn;

	private String userName;
	private SalesPatternList salesPatternList;
	private ProductInSalePatternList productInSalesPatternList;
	private int choosesPatternID = 0;
	private int choosesPatternDuration = 0;

	@FXML
	void initialize() {
		this.visibleNow = homePane;
		this.controller = MarketingManagerController.getInstance();
		this.controller.setCurrentWindow(this);
		initializeSalesPatternTable();
		initializeRankingSheetTable();
		initiateSaleCommentsReportTable();
		initiateCustomersTableInCommonReport();
		initiateCustomersTableInPeriodicReport();

		tfRPRUDiesel1.setMouseTransparent(true);
		tfRPRUDiesel1.setFocusTraversable(false);
		tfRPRUGasoline1.setMouseTransparent(true);
		tfRPRUGasoline1.setFocusTraversable(false);
		tfRPRUMotorbike1.setMouseTransparent(true);
		tfRPRUMotorbike1.setFocusTraversable(false);
		tfRPRUHomeFuel1.setMouseTransparent(true);
		tfRPRUHomeFuel1.setFocusTraversable(false);

		tfSCRSaleID.setMouseTransparent(true);
		tfSCRSaleID.setFocusTraversable(false);
		tfSCRStartTime.setMouseTransparent(true);
		tfSCRStartTime.setFocusTraversable(false);
		tfSCRNumberOf.setMouseTransparent(true);
		tfSCRNumberOf.setFocusTraversable(false);
		tfSCREndTime.setMouseTransparent(true);
		tfSCREndTime.setFocusTraversable(false);
		tfSCRSumPurchase.setMouseTransparent(true);
		tfSCRSumPurchase.setFocusTraversable(false);
		tvSCRDetails.setMouseTransparent(true);
		tvSCRDetails.setFocusTraversable(false);
		tfPCRFrom.setMouseTransparent(true);
		tfPCRFrom.setFocusTraversable(false);
		tfPCRTo.setMouseTransparent(true);
		tfPCRTo.setFocusTraversable(false);
		tvPCRDetails.setMouseTransparent(true);
		tvPCRDetails.setFocusTraversable(false);
	}

	@Override
	public Window getWindow() {
		return this.requestRateUpdatePane.getScene().getWindow();
	}

	@Override
	public void callAfterMessage(Object lastMsgFromServer) {// hello
		/**
		 * get sales patterns and product in sale pattern and than sert it to the table
		 * view
		 */
		if (lastMsgFromServer instanceof PeriodicReportList) {
			PeriodicReportList report = (PeriodicReportList) lastMsgFromServer;
			if (report.getReport() == null) {
				Alert a = new Alert(AlertType.ERROR);
				a.setTitle("Cant initiate periodic report");
				a.setContentText("there is a technical problem , contact technician");
				a.show();
			} else {
				PeriodicCustomersReport periodicReport = report.getReport();
				System.out.println("Got: = " + periodicReport);
				Date dateFrom = new java.sql.Date(periodicReport.getDateFrom().getTime());
				Date dateTo = new java.sql.Date(periodicReport.getDateTo().getTime());
				Date dateCreated = new java.sql.Date(periodicReport.getDateCreated().getTime());
				tfPCRFrom.setText(dateFrom.toString());
				tfPCRTo.setText(dateTo.toString());
				lblPCRDateCreated.setText(dateCreated.toString());
				updateCustomersTableInPeriodicReportTable(report.getList());
				if (report.isGenerated()) {
					addActivity("Generated New Periodic Report With From Date = " + dateFrom.toString()
							+ " , To Date = " + dateTo.toString());
				}
			}
		}

		else if (lastMsgFromServer instanceof SaleCommentsReportList) {
			SaleCommentsReportList report = (SaleCommentsReportList) lastMsgFromServer;
			if (report.getReport() == null) {
				Alert a = new Alert(AlertType.ERROR);
				a.setTitle("Cant initiate commonets report");
				a.setContentText("there is a technical problem , contact technician");
				a.show();
			} else {
				SaleCommentsReport saleReport = report.getReport();
				tfSCRNumberOf.setText(saleReport.getNumberOfCustomersBoughtInSale() + "");
				tfSCRSumPurchase.setText(saleReport.getSumOfPurchases() + "");
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String strDate = formatter.format(saleReport.getDateCreated());
				lblSCRDateCreated.setText(strDate);// elro3
				tfSCRSaleID.setText(saleReport.getSaleID() + "");
				updateCustomersTableInCommonReportTable(report.getList());
				if (report.isGenerated())
					addActivity("Generated New Common Report For Sale = " + saleReport.getSaleID());
				// continue for filling customer table
			}
		}

		else if (lastMsgFromServer instanceof SaleCommentsReport) {
			SaleCommentsReport report = (SaleCommentsReport) lastMsgFromServer;
			if (report.getSaleID() == 0) {
				Alert a = new Alert(AlertType.CONFIRMATION);
				a.setTitle("Cant initiate commonets report");
				a.setContentText("there are no sales common reports");
				a.show();
			} else {
				tfSCRNumberOf.setText(report.getNumberOfCustomersBoughtInSale() + "");
				tfSCRSumPurchase.setText(report.getSumOfPurchases() + "");
				lblSCRDateCreated.setText(report.getDateCreated().toString());
				// continue for filling customer table
			}
		}

		else if (lastMsgFromServer instanceof SalesList) {
			SalesList list = (SalesList) lastMsgFromServer;
			if (list.getList().isEmpty()) {
				Alert a = new Alert(AlertType.CONFIRMATION);
				a.setTitle("Cant initiate common report table");
				a.setContentText("there are no sales");
				a.show();
			} else {
				updateCommentsReportTable(list);
			}
		}

		else if (lastMsgFromServer instanceof ProductRateList) {
			ProductRateList list = (ProductRateList) lastMsgFromServer;
			if (list.getList().isEmpty()) {
				Alert a = new Alert(AlertType.CONFIRMATION);
				a.setTitle("Product Rate:");
				a.setContentText("there are no products");
				a.show();
			} else {
				productRateList = list;
				updateRatesInFields();
			}
		}

		else if (lastMsgFromServer instanceof RankingSheetList) {
			RankingSheetList list = (RankingSheetList) lastMsgFromServer;
			if (list.getList().isEmpty()) {
				Alert a = new Alert(AlertType.CONFIRMATION);
				a.setTitle("Ranking Sheet:");
				a.setContentText("there are no ranking sheets");
				a.show();
			} else {
				rankingSheetList = list;
				updateRankingSheetListInTable();
			}
		} else if (lastMsgFromServer instanceof SalesPatternList) {
			SalesPatternList list = (SalesPatternList) lastMsgFromServer;
			if (list.getList().isEmpty()) {
				Alert a = new Alert(AlertType.CONFIRMATION);
				a.setTitle("Sales Patterns:");
				a.setContentText("there are no sales patterns");
				a.show();
			} else {
				salesPatternList = list;
			}
		} else if (lastMsgFromServer instanceof ProductInSalePatternList) {
			ProductInSalePatternList list = (ProductInSalePatternList) lastMsgFromServer;
			productInSalesPatternList = list;
			updateSalesListInTable();
		} else if (lastMsgFromServer instanceof String) {
			String message = (String) lastMsgFromServer;
			if (message.startsWith("active sale")) {
				String[] msg = message.split(" ");
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Initiate Sale");
				alert.setContentText("There is a sale actie with product = " + msg[2]);
				alert.show();
			}

			else if (message.equals("ERROR range")) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText("Software error , call of technician");
				alert.show();
			} else if (message.equals("sale is in range")) {
				dpISDate.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText("There is a sale already in such range of days");
				alert.show();
			} else if (message.equals("sale not in range")) { // elro
				dpISDate.setStyle("-fx-border-style: none;");
				checkForActiveSales();
			}

			else if (message.equals("inactive sale")) {
				initiateNewSale(); // build method
			} else if (message.equals("sale failed")) {
				Alert a = new Alert(AlertType.ERROR);
				a.setTitle("Initaite Sale Failed");
				a.setContentText("there is a problem in saving the new sale");
				a.show();
			} else if (message.startsWith("new sale")) {
				openConfirmationAlert("Sale", "Initiate Sale Success");
				addActivity("Initialzing Sale"); // add activity
			} else if (message.startsWith("failed to create sale pattern")) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Creating Sale Pattern");
				alert.setContentText("There was a technical probel in creating 'Sale Pattern' , Contact Technician");
				alert.show();
			} else if (message.startsWith("created sale pattern")) {
				String[] str = message.split(" ");
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Creating Sale Pattern");
				alert.setHeaderText("Creation Successful!");
				alert.setContentText("the id is: " + str[3]);
				alert.show();
				addActivity("Created A Sale Pattern With ID= " + str[3]); // add activity
			} else if (message.startsWith("failed PRUR")) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Update Prodcut Rate");
				alert.setHeaderText("Update Prodcut Rate Failed");
				alert.setContentText("There was a technical problem in 'Update Prodcut Rate' , Contact Technician");
				alert.show();
			} else if (message.startsWith("success PRUR")) {
				openConfirmationAlert("Product Rates Update Request", "Request Sent To Network Manager");
				String[] str = message.split(" ");
				addActivity("Update Prodcut Rate Reuest ID= " + str[2]); // add activity
			}
		}

		super.callAfterMessage(lastMsgFromServer);
	}

///////// Home Page Start: //////////////

	@FXML
	void openHome(ActionEvent event) {
		this.topbar_window_label.setText("Home");
		sendToClientController(("activity get " + username + " " + cobHomeYear.getValue().toString() + " "
				+ cobHomeMonth.getValue().toString()));

		removeAllPanesVisiblity();
		this.clearFields();
		homePane.setVisible(true);
	}

///////// Home Page End //////////////

///////// Initiate sale Start: //////////////

	public void tbInitiateSaleClicked() {
		this.tbInitiateSale.setSelected(true);
		removeAllPanesVisiblity();
		initiateSalePane.setVisible(true);
		clearFields();
		LocalTime currentTime = LocalTime.now();
		int hours = currentTime.getHour();
		int minutes = currentTime.getMinute();
		if (minutes < 10)
			tfISTime.setText(hours + ":0" + minutes);
		else
			tfISTime.setText(hours + ":" + minutes);
		dpISDate.setValue(LocalDate.now());
		getAllSalesPatterns();
		getAllProductInSalePatterns();
	}

	/**
	 * Initiate sale
	 */
	public void btnISInitiateClicked() { // elroye
		if (checkTimeByClock(tfISTime) && checkDateIsCorrect(dpISDate)
				&& checkIfRowSelectedFromTable(tvISSalesPattern)) { // check time does not work
			// enter stuff here
			checkSaleInDates();
		}
	}

	/**
	 * method that sets opacity after mouse has entered the button
	 */
	public void btnISInitiateHover() {
		btnISInitiate.setOpacity(0.85);
	}

	/**
	 * method that sets back opacity after mouse has exited the button
	 */

	public void btnISInitiateExit() {
		btnISInitiate.setOpacity(1);
	}

///////// Initiate sale End //////////////

///////// Create Sale Pattern Start: //////////////

	@FXML
	void openCreateSalesPattern(ActionEvent event) {
		this.tbCreateSalePattern.setSelected(true);
		this.topbar_window_label.setText("Create Sales Pattern");
		removeAllPanesVisiblity();
		createSalePatternPane.setVisible(true);
		clearFields();
		getAllRankingSheets();
		getAllProductRanks();
	}

///////// Create Sale Pattern End //////////////

///////// Generate Marketing Report Start: //////////////

	public void tbGenerateReportClicked() {
		this.tbGenerateReport.setSelected(true);
		removeAllPanesVisiblity();
		generateReportPane.setVisible(true);
		clearFields();
		paneChooseReportType.setVisible(true);
		rbGMRCommentReport.setSelected(true);

	}

	public void btnGMRNextClicked() {
		if (rbGMRCommentReport.isSelected()) {
			paneGMRCommentNext.setVisible(true);
			paneGMRPeriodicNext.setVisible(false);
			this.sendToClientController("pull common data for common tableView");
		}

		if (rbGMRPeriodicReport.isSelected()) {
			paneGMRCommentNext.setVisible(false);
			paneGMRPeriodicNext.setVisible(true);
			dpGMRStartDate.setStyle("-fx-border-style: none;");
			dpGMREndDate.setStyle("-fx-border-style: none;");
		}

	}

	public void btnGMRNextHover() {
		btnGMRNext.setOpacity(0.85);
	}

	public void btnGMRNextExit() {
		btnGMRNext.setOpacity(1);
	}

	public void btnGMRViewReportClicked() {
		if (checkIfRowSelectedFromTable(tvGMRPickSale) == true) {
			RowInSaleCommentsReportTable row = tvGMRPickSale.getSelectionModel().getSelectedItem();
			// filing fields acoridng to selected item
			tfSCRStartTime.setText(row.getStartTime().toString());
			tfSCREndTime.setText(row.getEndTime().toString());
//			tfSCRSaleID.setText(row.getSaleID() + "");
			this.sendToClientController("generate SaleCommentReport " + row.getSaleID());
			generateReportPane.setVisible(false);
			saleCommentReportPane.setVisible(true);
		}

	}

	public void btnGMRViewReportHover() {
		btnGMRViewReport.setOpacity(0.85);
	}

	public void btnGMRViewReportExit() {
		btnGMRViewReport.setOpacity(1);
	}

	public void btnGMRViewReport2Clicked() { // here
		// do stuff
		if (checkDatePickerHasValue(dpGMRStartDate) == true && checkDatePickerHasValue(dpGMREndDate) == true) {
			LocalDate localDate = dpGMRStartDate.getValue();
			Date startDate = java.sql.Date.valueOf(localDate);

			localDate = dpGMREndDate.getValue();
			Date endDate = java.sql.Date.valueOf(localDate);

			if (startDate.compareTo(endDate) > 0) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText("'Start Date' is bigger than 'End Date'");
				alert.show();
				dpGMRStartDate.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
			} else {
				dpGMRStartDate.setStyle("-fx-border-style: none;");
				// do what need to be done

				Calendar calendar1 = Calendar.getInstance();
				LocalDate ld1 = this.dpGMRStartDate.getValue();
				Date ldDate1 = java.sql.Date.valueOf(ld1);
				calendar1.setTime(ldDate1);
				Calendar calendar2 = Calendar.getInstance();
				LocalDate ld2 = this.dpGMREndDate.getValue();
				Date ldDate2 = java.sql.Date.valueOf(ld2);
				calendar2.setTime(ldDate2);

				System.out.println(
						"generate periodic report " + calendar1.get(Calendar.YEAR) + " " + calendar1.get(Calendar.MONTH)
								+ " " + calendar1.get(Calendar.DAY_OF_MONTH) + " " + calendar2.get(Calendar.YEAR) + " "
								+ calendar2.get(Calendar.MONTH) + " " + calendar2.get(Calendar.DAY_OF_MONTH));
				this.sendToClientController(
						"generate periodic report " + calendar1.get(Calendar.YEAR) + " " + calendar1.get(Calendar.MONTH)
								+ " " + calendar1.get(Calendar.DAY_OF_MONTH) + " " + calendar2.get(Calendar.YEAR) + " "
								+ calendar2.get(Calendar.MONTH) + " " + calendar2.get(Calendar.DAY_OF_MONTH));
				periodicReportPane.setVisible(true);
				generateReportPane.setVisible(false);
			}
		}

	}

	public void btnGMRViewReport2Hover() {
		btnGMRViewReport2.setOpacity(0.85);
	}

	public void btnGMRViewReport2Exit() {
		btnGMRViewReport2.setOpacity(1);
	}

	public void btnSCRCloseClicked() {
		generateReportPane.setVisible(true);
		saleCommentReportPane.setVisible(false);
		this.sendToClientController("pull common data for common tableView");

	}

	public void btnSCRCloseHover() {
		btnSCRClose.setOpacity(0.85);
	}

	public void btnSCRCloseExit() {
		btnSCRClose.setOpacity(1);
	}

	public void btnPCRCloseClicked() {
		// do stuff
		periodicReportPane.setVisible(false);
		generateReportPane.setVisible(true);

	}

	public void btnPCRCloseHover() {
		btnPCRClose.setOpacity(0.85);
	}

	public void btnPCRCloseExit() {
		btnPCRClose.setOpacity(1);
	}

///////// Generate Marketing Report End //////////////

///////// Request Product Rate Update Start: //////////////

	public void tbRequestProductRateUpdateClicked() {
		this.tbRequestProductRateUpdate.setSelected(true);
		removeAllPanesVisiblity();
		requestRateUpdatePane.setVisible(true);
		clearFields();
		getAllProductRanks();
	}

	public void btnRPRUSendClicked() {
		if (checkRPRUCheckBoxes() && checkRPRUCheckFields()) {
			createNewPRUR();
		}

	}

	public void btnRPRUSendHover() {// *
		btnRPRUSend.setOpacity(0.85);
	}

	public void btnRPRUSendExit() {// *
		btnRPRUSend.setOpacity(1);
	}

	public void cbRPRUDieselClicked() {// *
		tfRPRUDiesel2.setDisable(!tfRPRUDiesel2.isDisable());
		if (tfRPRUDiesel2.isDisable()) {
			this.lblDieselERR2.setVisible(false);
			tfRPRUDiesel2.setStyle("-fx-border-style: none;");
			tfRPRUDiesel2.clear();
		}
	}

	public void cbRPRUGasolineClicked() {// *
		tfRPRUGasoline2.setDisable(!tfRPRUGasoline2.isDisable());
		if (tfRPRUGasoline2.isDisable()) {
			this.lblGasolineERR2.setVisible(false);
			tfRPRUGasoline2.setStyle("-fx-border-style: none;");
			tfRPRUGasoline2.clear();
		}
	}

	public void cbRPRUMotorbikeClicked() {// *
		tfRPRUMotorbike2.setDisable(!tfRPRUMotorbike2.isDisable());
		if (tfRPRUMotorbike2.isDisable()) {
			this.lblMotorERR2.setVisible(false);
			tfRPRUMotorbike2.setStyle("-fx-border-style: none;");
			tfRPRUMotorbike2.clear();
		}
	}

	public void cbRPRUHomeFuelClicked() {// *
		tfRPRUHomeFuel2.setDisable(!tfRPRUHomeFuel2.isDisable());
		if (tfRPRUHomeFuel2.isDisable()) {
			this.lblHomeERR2.setVisible(false);
			tfRPRUHomeFuel2.setStyle("-fx-border-style: none;");
			tfRPRUHomeFuel2.clear();
		}
	}

///////// Request Product Rate Update End //////////////

/////////////////// private functions //////////////////////

	/**
	 * method that check if there is a sale with start date
	 * 
	 * @return
	 */

	private boolean checkSaleInDates() { // elro2
		String message = "check sale range";
		RowForSalesPatternTable item = tvISSalesPattern.getSelectionModel().getSelectedItem();
		choosesPatternID = item.getSalePatternID();
		choosesPatternDuration = item.getDuration();

		String[] timeStr = tfISTime.getText().split(":");
		int hours = Integer.parseInt(timeStr[0]);
		int minutes = Integer.parseInt(timeStr[1]);
		Calendar calendar1 = Calendar.getInstance();
		LocalDate ld = this.dpISDate.getValue();
		Date ldDate = java.sql.Date.valueOf(ld);
		calendar1.setTime(ldDate);
		calendar1.set(Calendar.HOUR, hours);
		calendar1.set(Calendar.MINUTE, minutes);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(calendar1.getTime());
		calendar2.add(Calendar.MINUTE, choosesPatternDuration);

		message = "check sale range " + calendar1.get(Calendar.YEAR) + " " + calendar1.get(Calendar.MONTH) + " "
				+ calendar1.get(Calendar.DAY_OF_MONTH) + " " + calendar2.get(Calendar.YEAR) + " "
				+ calendar2.get(Calendar.MONTH) + " " + calendar2.get(Calendar.DAY_OF_MONTH);

		System.out.println("///////////////////////message of in range= " + message);

		this.sendToClientController(message);
		return false;
	}

	/**
	 * method that check if picked date happend before current date
	 * 
	 * @param dpISDate2
	 * @return
	 */
	private boolean checkDateIsCorrect(DatePicker dp) {
		Calendar calendar1 = Calendar.getInstance();
		Date currendDate = new Date();
		calendar1.setTime(currendDate);

		Calendar calendar2 = Calendar.getInstance();
		LocalDate ld = dp.getValue();
		Date picked = java.sql.Date.valueOf(ld);
		calendar2.setTime(picked);

		if (((calendar1.getTime()).compareTo(calendar2.getTime()) < 0)
				|| (calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
						&& calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH)
						&& calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH))) {
			dp.setStyle("-fx-border-style: none;");
			return true;
		}

		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText("Please enter a date which is today or future date");
		dp.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
		alert.show();
		return false;

	}

	/**
	 * method that check if the time is correct like in a clock view
	 * 
	 * @param tfISTime2
	 * @return
	 */
	private boolean checkTimeByClock(TextField tf) {
		if (!tf.getText().contains(":")) {
			tf.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
			return false;
		}
		String[] str = tf.getText().split(":");

		if (this.checkValidTextField(str[0], "digits", "Time is only digits") == false
				|| this.checkValidTextField(str[1], "digits", "Time is only digits") == false) {
			return false;
		}

		if (Integer.parseInt(str[0]) >= 24 || Integer.parseInt(str[0]) < 0 || Integer.parseInt(str[1]) >= 60
				|| Integer.parseInt(str[1]) < 0) {
			tf.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
			return false;
		}
		tf.setStyle("-fx-border-style: none;");
		return true;
	}

	/**
	 * method that will call for client to search if there is a sale with such
	 * salePatternID active in SQL
	 */
	private void checkForActiveSales() { // elroye
		RowForSalesPatternTable item = tvISSalesPattern.getSelectionModel().getSelectedItem();
		choosesPatternID = item.getSalePatternID();
		choosesPatternDuration = item.getDuration();

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confrimation");
		alert.setHeaderText("Create Sale With Pattern ID = " + item.getSalePatternID() + "?");
		ButtonType buttonTypeOne = new ButtonType("Yes");
		ButtonType buttonTypeTwo = new ButtonType("No");
		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeOne) {
			sendToClientController("check active sales " + item.getSalePatternID());
		}
		if (result.get() == buttonTypeTwo) {
		}
	}

	/**
	 * method that add activity to SQL to recored
	 * 
	 * @param action
	 */
	private void addActivity(String action) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		String message = "add activity " + username + " " + calendar.get(Calendar.YEAR) + " "
				+ calendar.get(Calendar.MONTH) + " " + calendar.get(Calendar.DAY_OF_MONTH) + " "
				+ (calendar.get(Calendar.HOUR) - 2) + " " + (calendar.get(Calendar.MINUTE) - 30) + " " + action;
		this.sendToClientController(message);
	}

	/**
	 * method that will call for client to insert a new row for sale table
	 */
	private void initiateNewSale() { // elro2
		String message = "insert sale";
		String[] timeStr = tfISTime.getText().split(":");
		int hours = Integer.parseInt(timeStr[0]);
		int minutes = Integer.parseInt(timeStr[1]);
		Calendar calendar1 = Calendar.getInstance();
		LocalDate ld = this.dpISDate.getValue();
		Date ldDate = java.sql.Date.valueOf(ld);
		calendar1.setTime(ldDate);
		calendar1.set(Calendar.HOUR, hours);
		calendar1.set(Calendar.MINUTE, minutes);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(calendar1.getTime());
		calendar2.add(Calendar.MINUTE, choosesPatternDuration);

		message = "insert sale " + this.choosesPatternID + " " + calendar1.get(Calendar.YEAR) + " "
				+ calendar1.get(Calendar.MONTH) + " " + calendar1.get(Calendar.DAY_OF_MONTH) + " "
				+ (calendar1.get(Calendar.HOUR) - 2) + " " + (calendar1.get(Calendar.MINUTE) - 30) + " "
				+ calendar2.get(Calendar.YEAR) + " " + calendar2.get(Calendar.MONTH) + " "
				+ calendar2.get(Calendar.DAY_OF_MONTH) + " " + (calendar2.get(Calendar.HOUR) - 2) + " "
				+ (calendar2.get(Calendar.MINUTE) - 30);
		this.sendToClientController(message);
	}

	/**
	 * method that check if a datePicker field has a value
	 * 
	 * @param picker
	 * @return
	 */
	private boolean checkDatePickerHasValue(DatePicker picker) {
		if (picker.getValue() == null) {
			picker.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
			return false;
		}
		picker.setStyle("-fx-border-style: none;");
		return true;
	}

	/**
	 * method that create a new Product Rate Update Request
	 */
	private void createNewPRUR() {
		StringBuilder message = new StringBuilder();
		message.append("create new PRUR ");
		if (!cbRPRUDiesel.isSelected())
			message.append("0 ");
		else
			message.append(tfRPRUDiesel2.getText() + " ");

		if (!cbRPRUGasoline.isSelected())
			message.append("0 ");
		else
			message.append(tfRPRUGasoline2.getText() + " ");

		if (!cbRPRUMotorbike.isSelected())
			message.append("0 ");
		else
			message.append(tfRPRUMotorbike2.getText() + " ");

		if (!cbRPRUHomeFuel.isSelected())
			message.append("0 ");
		else
			message.append(tfRPRUHomeFuel2.getText() + " ");

		System.out.println(message.toString());
		this.sendToClientController(message.toString());
	}

	/**
	 * method that check that if at least one check box is selected
	 * 
	 * @return
	 */
	private boolean checkRPRUCheckBoxes() {
		if (!cbRPRUDiesel.isSelected() && !cbRPRUGasoline.isSelected() && !cbRPRUMotorbike.isSelected()
				&& !cbRPRUHomeFuel.isSelected()) {
//			cbRPRUDiesel.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
//			cbRPRUGasoline.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
//			cbRPRUMotorbike.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
//			cbRPRUHomeFuel.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText("You must enter at least one rate");
			alert.setContentText("");
			alert.show();
			return false;
		}
		return true;
	}

	/**
	 * method that check if fields has a correct value before program continues
	 * 
	 * @return
	 */
	private boolean checkRPRUCheckFields() {// *

		cbRPRUDiesel.setStyle("-fx-border-style: none;");
		cbRPRUGasoline.setStyle("-fx-border-style: none;");
		cbRPRUMotorbike.setStyle("-fx-border-style: none;");
		cbRPRUHomeFuel.setStyle("-fx-border-style: none;");

		if (cbRPRUDiesel.isSelected() && tfRPRUDiesel2.getText().trim().isEmpty()
				|| this.checkValidTextField(tfRPRUDiesel2.getText(), "digits", "Rate is only digits") == false) {
			tfRPRUDiesel2.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
			return false;
		}

		if (cbRPRUDiesel.isSelected() && !tfRPRUDiesel2.getText().trim().isEmpty()
				|| this.checkValidTextField(tfRPRUDiesel2.getText(), "digits", "Rate is only digits") == true) {
			tfRPRUDiesel2.setStyle("-fx-border-style: none;");
		}
		if (cbRPRUGasoline.isSelected() && tfRPRUGasoline2.getText().trim().isEmpty()
				|| this.checkValidTextField(tfRPRUGasoline2.getText(), "digits", "Rate is only digits") == false) {
			tfRPRUGasoline2.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
			return false;
		}
		if (cbRPRUGasoline.isSelected() && !tfRPRUGasoline2.getText().trim().isEmpty()
				|| this.checkValidTextField(tfRPRUGasoline2.getText(), "digits", "Rate is only digits") == true) {
			tfRPRUGasoline2.setStyle("-fx-border-style: none;");
		}
		if (cbRPRUMotorbike.isSelected() && tfRPRUMotorbike2.getText().trim().isEmpty()
				|| this.checkValidTextField(tfRPRUMotorbike2.getText(), "digits", "Rate is only digits") == false) {
			tfRPRUMotorbike2.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
			return false;
		}
		if (cbRPRUMotorbike.isSelected() && !tfRPRUMotorbike2.getText().trim().isEmpty()
				|| this.checkValidTextField(tfRPRUMotorbike2.getText(), "digits", "Rate is only digits") == true) {
			tfRPRUMotorbike2.setStyle("-fx-border-style: none;");
		}
		if (cbRPRUHomeFuel.isSelected() && tfRPRUHomeFuel2.getText().trim().isEmpty()
				|| this.checkValidTextField(tfRPRUHomeFuel2.getText(), "digits", "Rate is only digits") == false) {
			tfRPRUHomeFuel2.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
			return false;
		}

		boolean flagDiesel = true;
		boolean flagGasoline = true;
		boolean flagMotorbikeFuel = true;
		boolean flagHomeFuel = true;
		for (Product product : productRateList.getList()) {
			if (product.getProductName().equals(ProductName.Diesel)) {
				if (!tfRPRUDiesel2.getText().trim().isEmpty() && Double.parseDouble(tfRPRUDiesel2.getText()) > 0
						&& Double.parseDouble(tfRPRUDiesel2.getText()) < product.getMaxPrice()) {
					tfRPRUDiesel2.setStyle("-fx-border-style: none;");
					this.lblDieselERR2.setVisible(false);
					flagDiesel = true;
				}
				if (!tfRPRUDiesel2.getText().trim().isEmpty() && (Double.parseDouble(tfRPRUDiesel2.getText()) <= 0
						|| Double.parseDouble(tfRPRUDiesel2.getText()) >= product.getMaxPrice())) {
					lblDieselERR2.setVisible(true);
					lblDieselERR2.setText(">= " + product.getMaxPrice() + " or 0");
					tfRPRUDiesel2.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
					flagDiesel = false;
				}
			}
			if (product.getProductName().equals(ProductName.Gasoline)) {
				if (!tfRPRUGasoline2.getText().trim().isEmpty() && Double.parseDouble(tfRPRUGasoline2.getText()) > 0
						&& Double.parseDouble(tfRPRUGasoline2.getText()) < product.getMaxPrice()) {
					tfRPRUGasoline2.setStyle("-fx-border-style: none;");
					lblGasolineERR2.setVisible(false);
					flagGasoline = true;
				}
				if (!tfRPRUGasoline2.getText().trim().isEmpty() && (Double.parseDouble(tfRPRUGasoline2.getText()) <= 0
						|| Double.parseDouble(tfRPRUGasoline2.getText()) >= product.getMaxPrice())) {
					this.lblGasolineERR2.setVisible(true);
					lblGasolineERR2.setText(">= " + product.getMaxPrice() + " or 0");
					tfRPRUGasoline2.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
					flagGasoline = false;
				}
			}
			if (product.getProductName().equals(ProductName.MotorbikeFuel)) {
				if (!tfRPRUMotorbike2.getText().trim().isEmpty() && Double.parseDouble(tfRPRUMotorbike2.getText()) > 0
						&& Double.parseDouble(tfRPRUMotorbike2.getText()) < product.getMaxPrice()) {
					tfRPRUMotorbike2.setStyle("-fx-border-style: none;");
					lblMotorERR2.setVisible(false);
					flagMotorbikeFuel = true;
				}
				if (!tfRPRUMotorbike2.getText().trim().isEmpty() && (Double.parseDouble(tfRPRUMotorbike2.getText()) <= 0
						|| Double.parseDouble(tfRPRUMotorbike2.getText()) >= product.getMaxPrice())) {
					this.lblMotorERR2.setVisible(true);
					lblMotorERR2.setText(">= " + product.getMaxPrice() + " or 0");
					tfRPRUMotorbike2.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
					flagMotorbikeFuel = false;
				}
			}
			if (product.getProductName().equals(ProductName.HomeFuel)) {
				if (!tfRPRUHomeFuel2.getText().trim().isEmpty() && Double.parseDouble(tfRPRUHomeFuel2.getText()) > 0
						&& Double.parseDouble(tfRPRUHomeFuel2.getText()) < product.getMaxPrice()) {
					tfRPRUHomeFuel2.setStyle("-fx-border-style: none;");
					lblHomeERR2.setVisible(false);
					flagHomeFuel = true;
				}
				if (!tfRPRUHomeFuel2.getText().trim().isEmpty() && (Double.parseDouble(tfRPRUHomeFuel2.getText()) <= 0
						|| Double.parseDouble(tfRPRUHomeFuel2.getText()) >= product.getMaxPrice())) {
					this.lblHomeERR2.setVisible(true);
					lblHomeERR2.setText(">= " + product.getMaxPrice() + " or 0");
					tfRPRUHomeFuel2.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
					flagHomeFuel = false;
				}
			}

		}
		return flagDiesel && flagGasoline && flagMotorbikeFuel && flagHomeFuel;
	}

	/**
	 * method that put values is text fields to indicate data to the user
	 */
	private void updateRatesInFields() {// *
		double rate;
		for (Product product : productRateList.getList()) {
			System.out.println("product name: " + product.getProductName().toString());
			rate = product.getMaxPrice() - product.getCurrentPrice();
			if (product.getProductName().equals(ProductName.Diesel))
				tfRPRUDiesel1.setText(rate + "");
			if (product.getProductName().equals(ProductName.Gasoline))
				tfRPRUGasoline1.setText(rate + "");
			if (product.getProductName().equals(ProductName.MotorbikeFuel))
				tfRPRUMotorbike1.setText(rate + "");
			if (product.getProductName().equals(ProductName.HomeFuel))
				tfRPRUHomeFuel1.setText(rate + "");
		}
	}

	private void getAllProductInSalePatterns() {
		this.sendToClientController("pull product in sales patterns " + userName);
	}

	/**
	 * a method that will pull form SQL the sales patterns and show them on the
	 * table view
	 */
	private void getAllSalesPatterns() {
		this.sendToClientController("pull sales patterns " + userName);
	}

	/**
	 * check if table view has a selected row
	 * 
	 * @param tb
	 * @return
	 */
	private boolean checkIfRowSelectedFromTable(TableView<?> tb) {
		if (tb.getSelectionModel().getSelectedItem() == null) {
			tb.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
			return false;
		}

		if (tb.getSelectionModel().getSelectedItem() != null) {
			tb.setStyle("-fx-border-style: none;");
		}
		return true;
	}

	/**
	 * metohd that updates the sale patterns table
	 */
	private void updateSalesListInTable() {
		List<SalesPattern> spList = salesPatternList.getList();
		List<ProductInSalesPattern> PISPList = productInSalesPatternList.getList();
		ObservableList<RowForSalesPatternTable> rowsList = FXCollections.observableArrayList();

		for (int i = 0; i < tvISSalesPattern.getItems().size(); i++) {
			tvISSalesPattern.getItems().clear();
		}
		Iterator<SalesPattern> iterator = spList.iterator();
		while (iterator.hasNext()) {
			SalesPattern sp = (SalesPattern) iterator.next();
			RowForSalesPatternTable row = new RowForSalesPatternTable(sp.getSalesPatternID(),
					sp.getDurationInMinutes());

			Iterator<ProductInSalesPattern> PISPiterator = PISPList.iterator();
			while (PISPiterator.hasNext()) {
				ProductInSalesPattern PISP = (ProductInSalesPattern) PISPiterator.next();
				if (sp.getSalesPatternID() == PISP.getSalesPatternID()) {
					if (PISP.getProductName().equals(ProductName.Diesel))
						row.setDieselDiscount(PISP.getSalesDiscount());

					if (PISP.getProductName().equals(ProductName.Gasoline))
						row.setGasolineDiscount(PISP.getSalesDiscount());

					if (PISP.getProductName().equals(ProductName.MotorbikeFuel))
						row.setMotorBikeDiscount(PISP.getSalesDiscount());
				}
			}
			rowsList.add(row);
		}
		tvISSalesPattern.setItems(rowsList);
	}

	/**
	 * method that updates the common report table
	 * 
	 * @param list
	 */
	private void updateCommentsReportTable(SalesList list) {
		List<RowInSaleCommentsReportTable> rowLister = list.getList();
		ObservableList<RowInSaleCommentsReportTable> rowsList = FXCollections.observableArrayList();

		for (int i = 0; i < tvGMRPickSale.getItems().size(); i++) {
			tvGMRPickSale.getItems().clear();
		}
		for (RowInSaleCommentsReportTable row : rowLister) {
			rowsList.add(row);
		}
		tvGMRPickSale.setItems(rowsList);
	}

	/**
	 * method that will set all panes visibilt as false
	 */
	private void removeAllPanesVisiblity() {
		homePane.setVisible(false);
		initiateSalePane.setVisible(false);
		createSalePatternPane.setVisible(false);
		generateReportPane.setVisible(false);
		requestRateUpdatePane.setVisible(false);
	}

	/**
	 * initializes the table of sales pattern
	 */
	private void initializeSalesPatternTable() {
		tvISSalesPattern.getColumns().clear();
		idColumn = new TableColumn<RowForSalesPatternTable, Integer>("Pattern ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("salePatternID"));
		tvISSalesPattern.getColumns().add(idColumn);

		durationColumn = new TableColumn<RowForSalesPatternTable, Integer>("Duration");
		durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
		tvISSalesPattern.getColumns().add(durationColumn);

		dieselDiscountColumn = new TableColumn<RowForSalesPatternTable, Double>("Diesel Discount");
		dieselDiscountColumn.setCellValueFactory(new PropertyValueFactory<>("dieselDiscount"));
		tvISSalesPattern.getColumns().add(dieselDiscountColumn);

		gasolineDiscountColumn = new TableColumn<RowForSalesPatternTable, Double>("Gasoline Discount");
		gasolineDiscountColumn.setCellValueFactory(new PropertyValueFactory<>("gasolineDiscount"));
		tvISSalesPattern.getColumns().add(gasolineDiscountColumn);

		motorBikeDiscountColumn = new TableColumn<RowForSalesPatternTable, Double>("Motor Bike Discount");
		motorBikeDiscountColumn.setCellValueFactory(new PropertyValueFactory<>("motorBikeDiscount"));
		tvISSalesPattern.getColumns().add(motorBikeDiscountColumn);

		idColumn.prefWidthProperty().bind(tvISSalesPattern.widthProperty().multiply(0.1));
		durationColumn.prefWidthProperty().bind(tvISSalesPattern.widthProperty().multiply(0.1));
		dieselDiscountColumn.prefWidthProperty().bind(tvISSalesPattern.widthProperty().multiply(0.21));
		gasolineDiscountColumn.prefWidthProperty().bind(tvISSalesPattern.widthProperty().multiply(0.21));
		motorBikeDiscountColumn.prefWidthProperty().bind(tvISSalesPattern.widthProperty().multiply(0.21));

		idColumn.setResizable(false);
		durationColumn.setResizable(false);
		dieselDiscountColumn.setResizable(false);
		gasolineDiscountColumn.setResizable(false);
		motorBikeDiscountColumn.setResizable(false);
	}

	private void initiateCustomersTableInPeriodicReport() { // here
		tvPCRDetails.getColumns().clear();
		TableColumn<CustomerBoughtFromCompany, String> customerIDColumn = new TableColumn<CustomerBoughtFromCompany, String>(
				"Customer ID");
		customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
		tvPCRDetails.getColumns().add(customerIDColumn);

		TableColumn<CustomerBoughtFromCompany, FuelCompanyName> FuelCompanyColumn = new TableColumn<CustomerBoughtFromCompany, FuelCompanyName>(
				"Fuel Company");
		FuelCompanyColumn.setCellValueFactory(new PropertyValueFactory<>("fuelCompanyName"));
		tvPCRDetails.getColumns().add(FuelCompanyColumn);

		TableColumn<CustomerBoughtFromCompany, Double> amountBoughtColumn = new TableColumn<CustomerBoughtFromCompany, Double>(
				"Amount Bought");
		amountBoughtColumn.setCellValueFactory(new PropertyValueFactory<>("amountBoughtFromCompany"));
		tvPCRDetails.getColumns().add(amountBoughtColumn);

		TableColumn<CustomerBoughtFromCompany, Double> paidColumn = new TableColumn<CustomerBoughtFromCompany, Double>(
				"Paid");
		paidColumn.setCellValueFactory(new PropertyValueFactory<>("amountPaidCompany"));
		tvPCRDetails.getColumns().add(paidColumn);

		customerIDColumn.prefWidthProperty().bind(tvPCRDetails.widthProperty().multiply(0.25));
		FuelCompanyColumn.prefWidthProperty().bind(tvPCRDetails.widthProperty().multiply(0.25));
		amountBoughtColumn.prefWidthProperty().bind(tvPCRDetails.widthProperty().multiply(0.22));
		paidColumn.prefWidthProperty().bind(tvPCRDetails.widthProperty().multiply(0.15));

		customerIDColumn.setResizable(false);
		FuelCompanyColumn.setResizable(false);
		amountBoughtColumn.setResizable(false);
		paidColumn.setResizable(false);
	}

	/**
	 * method that puts items in table view
	 * 
	 * @param list
	 */

	private void updateCustomersTableInPeriodicReportTable(List<CustomerBoughtFromCompany> list) {// here
		for (int i = 0; i < tvPCRDetails.getItems().size(); i++) {
			tvPCRDetails.getItems().clear();
		}
		if (list.isEmpty()) {
			tvPCRDetails.setPlaceholder(new Label("No Customers In Such Dates"));
		} else {
			ObservableList<CustomerBoughtFromCompany> rowsList = FXCollections.observableArrayList();
			for (CustomerBoughtFromCompany row : list) {
				rowsList.add(row);
			}
			tvPCRDetails.setItems(rowsList);
		}

	}

	/**
	 * method that initiate a table
	 */
	private void initiateCustomersTableInCommonReport() {
		tvSCRDetails.getColumns().clear();

		TableColumn<CustomerBoughtInSale, String> cusotmerIDColumn = new TableColumn<CustomerBoughtInSale, String>(
				"Customer ID");
		cusotmerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
		tvSCRDetails.getColumns().add(cusotmerIDColumn);

		TableColumn<CustomerBoughtInSale, Double> amountPaidColumn = new TableColumn<CustomerBoughtInSale, Double>(
				"Paid");
		amountPaidColumn.setCellValueFactory(new PropertyValueFactory<>("amountPaid"));
		tvSCRDetails.getColumns().add(amountPaidColumn);

		cusotmerIDColumn.prefWidthProperty().bind(tvSCRDetails.widthProperty().multiply(0.45));
		amountPaidColumn.prefWidthProperty().bind(tvSCRDetails.widthProperty().multiply(0.25));

		cusotmerIDColumn.setResizable(false);
		amountPaidColumn.setResizable(false);
	}

	/**
	 * method that updates table with customer bought in sale
	 * 
	 * @param list
	 */
	private void updateCustomersTableInCommonReportTable(List<CustomerBoughtInSale> list) {
		for (int i = 0; i < tvSCRDetails.getItems().size(); i++) {
			tvSCRDetails.getItems().clear();
		}
		if (list.isEmpty()) {
			tvSCRDetails.setPlaceholder(new Label("Sale Has No Customers"));
		} else {
			ObservableList<CustomerBoughtInSale> rowsList = FXCollections.observableArrayList();
			for (CustomerBoughtInSale row : list) {
				rowsList.add(row);
			}
			tvSCRDetails.setItems(rowsList);
		}
	}

	/**
	 * method that initiate a table
	 */

	private void initiateSaleCommentsReportTable() {
		tvGMRPickSale.getColumns().clear();
		TableColumn<RowInSaleCommentsReportTable, Integer> saleIDColumn = new TableColumn<RowInSaleCommentsReportTable, Integer>(
				"ID");
		saleIDColumn.setCellValueFactory(new PropertyValueFactory<>("saleID"));
		tvGMRPickSale.getColumns().add(saleIDColumn);

		TableColumn<RowInSaleCommentsReportTable, Date> startTimeColumn = new TableColumn<RowInSaleCommentsReportTable, Date>(
				"Start Time");
		startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
		tvGMRPickSale.getColumns().add(startTimeColumn);

		TableColumn<RowInSaleCommentsReportTable, Date> endTimeColumn = new TableColumn<RowInSaleCommentsReportTable, Date>(
				"End Time");
		endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
		tvGMRPickSale.getColumns().add(endTimeColumn);

		TableColumn<RowInSaleCommentsReportTable, Double> dieselDiscColumn = new TableColumn<RowInSaleCommentsReportTable, Double>(
				"Diesel Discount");
		dieselDiscColumn.setCellValueFactory(new PropertyValueFactory<>("dieselDisc"));
		tvGMRPickSale.getColumns().add(dieselDiscColumn);

		TableColumn<RowInSaleCommentsReportTable, Double> gasolineDiscColumn = new TableColumn<RowInSaleCommentsReportTable, Double>(
				"Gasoline Discount");
		gasolineDiscColumn.setCellValueFactory(new PropertyValueFactory<>("gasolineDisc"));
		tvGMRPickSale.getColumns().add(gasolineDiscColumn);

		TableColumn<RowInSaleCommentsReportTable, Double> motorDiscColumn = new TableColumn<RowInSaleCommentsReportTable, Double>(
				"Motorbike Fuel Discount");
		motorDiscColumn.setCellValueFactory(new PropertyValueFactory<>("motorDisc"));
		tvGMRPickSale.getColumns().add(motorDiscColumn);

//		saleIDColumn.prefWidthProperty().bind(tvGMRPickSale.widthProperty().multiply(1));
		startTimeColumn.prefWidthProperty().bind(tvGMRPickSale.widthProperty().multiply(0.14));
		endTimeColumn.prefWidthProperty().bind(tvGMRPickSale.widthProperty().multiply(0.14));
		dieselDiscColumn.prefWidthProperty().bind(tvGMRPickSale.widthProperty().multiply(0.16));
		gasolineDiscColumn.prefWidthProperty().bind(tvGMRPickSale.widthProperty().multiply(0.2));
		motorDiscColumn.prefWidthProperty().bind(tvGMRPickSale.widthProperty().multiply(0.23));

		saleIDColumn.setResizable(false);
//		startTimeColumn.setResizable(false);
//		endTimeColumn.setResizable(false);
		dieselDiscColumn.setResizable(false);
		gasolineDiscColumn.setResizable(false);
		motorDiscColumn.setResizable(false);
	}

	/**
	 * method that clears all fields before shows the user a new page
	 */
	@Override
	public void clearFields() {
		saleCommentReportPane.setVisible(false);
		periodicReportPane.setVisible(false);
		dpGMRStartDate.setValue(null);
		dpGMREndDate.setValue(null);
		paneGMRCommentNext.setVisible(false);
		paneGMRPeriodicNext.setVisible(false);
		this.lblDieselERR2.setVisible(false);
		this.lblGasolineERR2.setVisible(false);
		this.lblHomeERR2.setVisible(false);
		this.lblMotorERR2.setVisible(false);
		tvISSalesPattern.setStyle("-fx-border-style: none;");
		tfISTime.clear();
		tfISTime.setStyle("-fx-border-style: none;");
		dpISDate.setValue(null);
		dpISDate.setStyle("-fx-border-style: none;");
		clearSalePatternPane();
		cbRPRUDiesel.setSelected(false);
		cbRPRUDiesel.setStyle("-fx-border-style: none;");
		cbRPRUGasoline.setSelected(false);
		cbRPRUGasoline.setStyle("-fx-border-style: none;");
		cbRPRUMotorbike.setSelected(false);
		cbRPRUMotorbike.setStyle("-fx-border-style: none;");
		cbRPRUHomeFuel.setSelected(false);
		cbRPRUHomeFuel.setStyle("-fx-border-style: none;");
		tfRPRUDiesel2.setDisable(true);
		tfRPRUDiesel2.setStyle("-fx-border-style: none;");
		tfRPRUGasoline2.setDisable(true);
		tfRPRUGasoline2.setStyle("-fx-border-style: none;");
		tfRPRUMotorbike2.setDisable(true);
		tfRPRUMotorbike2.setStyle("-fx-border-style: none;");
		tfRPRUHomeFuel2.setDisable(true);
		tfRPRUHomeFuel2.setStyle("-fx-border-style: none;");
		tfRPRUDiesel2.clear();
		tfRPRUDiesel2.setStyle("-fx-border-style: none;");
		tfRPRUGasoline2.clear();
		tfRPRUGasoline2.setStyle("-fx-border-style: none;");
		tfRPRUMotorbike2.clear();
		tfRPRUMotorbike2.setStyle("-fx-border-style: none;");
		tfRPRUHomeFuel2.clear();
		tfRPRUHomeFuel2.setStyle("-fx-border-style: none;");
	}
}
