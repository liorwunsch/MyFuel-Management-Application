package guiClient;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map.Entry;

import entities.MyIncomeReport;
import entities.MyInventoryReport;
import entities.MyOutcomeReport;
import entities.ProductInStation;
import entities.RowForQuarterlyReports;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * boundary for network manager's and fuel station manager's view quarterly
 * report window
 * 
 * @version Final
 * @author Lior
 *
 */
public abstract class QuarterlyReportWindow extends EmployeeWindow {

	@FXML
	protected AnchorPane quarterlyReportPane;
	@FXML
	protected ComboBox<String> cobGQRYear;
	@FXML
	protected ComboBox<Integer> cobGQRQuarter;
	@FXML
	protected Button btnGQRView;

	@FXML
	protected AnchorPane assessPane;
	@FXML
	protected Button btnASOShowOrder;
	@FXML
	protected Button btnASODecline;
	@FXML
	protected Button btnASOConfirm;

	@FXML
	protected AnchorPane declineOrderPane;
	@FXML
	protected TextArea taDOReasons;
	@FXML
	protected Button btnDOOk;
	@FXML
	protected Button btnDOCancel;

	@FXML
	protected AnchorPane quarterReportPane;
	@FXML
	protected TableView<RowForQuarterlyReports> tvQRDetails1;
	@FXML
	protected TableView<RowForQuarterlyReports> tvQRDetails2;
	@FXML
	protected TableView<RowForQuarterlyReports> tvQRDetails3;
	@FXML
	protected Button btnQRClose;
	@FXML
	protected TextField tfQRTotalIncome;
	@FXML
	protected TextField tfQRTotalAmountSold;
	@FXML
	protected TextField tfQRTotalAmountBought;
	@FXML
	protected TextField tflQRDateCreated;
	@FXML
	protected TextField tflQRQuarter;
	@FXML
	protected TextField tflQRYear;
	@FXML
	protected TextField tflQRFuelStationID;

	/*********************** methods changing window ***********************/

	/**
	 * viewquarterlyreportpane open
	 */
	protected void openPaneOfViewQuarterlyReport() {
		visibleNow.setVisible(false);
		quarterlyReportPane.setVisible(true);
		visibleNow = quarterlyReportPane;
	}

	/**
	 * 
	 * @param selectedYear
	 * @param selectedQuarter
	 * @return true if fields valid
	 */
	protected boolean openPaneOfQuarterlyReports(int selectedYear, int selectedQuarter) {
		int currYear = Calendar.getInstance().get(Calendar.YEAR);
		int currMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
		if (selectedYear == currYear) {
			if (selectedQuarter == 1 && currMonth <= 3) {
				openErrorAlert("Wrong Input",
						"Year: " + selectedYear + "\nQuarter: " + selectedQuarter + "\nNot exist yet!");
				return false;

			} else if (selectedQuarter == 2 && currMonth <= 6) {
				openErrorAlert("Wrong Input",
						"Year: " + selectedYear + "\nQuarter: " + selectedQuarter + "\nNot exist yet!");
				return false;

			} else if (selectedQuarter == 3 && currMonth <= 9) {
				openErrorAlert("Wrong Input",
						"Year: " + selectedYear + "\nQuarter: " + selectedQuarter + "\nNot exist yet!");
				return false;

			} else if (selectedQuarter == 4 && currMonth <= 12) {
				openErrorAlert("Wrong Input",
						"Year: " + selectedYear + "\nQuarter: " + selectedQuarter + "\nNot exist yet!");
				return false;
			}

		} else if (selectedYear > currYear) {
			openErrorAlert("Wrong Input",
					"Year: " + selectedYear + "\nQuarter: " + selectedQuarter + "\nNot exist yet!");
			return false;
		}
		return true;
	}

	/**
	 * continue of openQuarterlyReport after handleMessageFromClientUI
	 * 
	 * @param reports
	 */
	protected void openQuarterlyReport2(Object[] reports) {
		// reports[0] have string
		MyIncomeReport incomeReport = (MyIncomeReport) reports[1];
		MyOutcomeReport outcomeReport = (MyOutcomeReport) reports[2];
		MyInventoryReport inventoryReport = (MyInventoryReport) reports[3];
		fillQuarterReport(incomeReport, outcomeReport, inventoryReport);
		mainBorderPane.setDisable(true);
		quarterReportPane.setVisible(true);
		visibleNow = quarterReportPane;

		btnQRClose.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				clearFields();
				visibleNow.setVisible(false);
				mainBorderPane.setDisable(false);
				visibleNow = quarterlyReportPane;
			}
		});
	}

	/**
	 * fill QuarterReport tableviews with reports data
	 * 
	 * @param incomeReport
	 * @param outcomeReport
	 * @param inventoryReport
	 */
	protected void fillQuarterReport(MyIncomeReport incomeReport, MyOutcomeReport outcomeReport,
			MyInventoryReport inventoryReport) {
		String[] dateCreated = converDateToFormattedString(incomeReport.getDateCreated()).split(" ");
		tflQRDateCreated.setText(dateCreated[0]);
		tflQRYear.setText(incomeReport.getRepYear());
		tflQRQuarter.setText(incomeReport.getRepQuarter() + "");
		tflQRFuelStationID.setText(incomeReport.getFuelStationID() + "");
		for (int i = 0; i < tvQRDetails1.getItems().size(); i++) {
			tvQRDetails1.getItems().clear();
		}
		for (int i = 0; i < tvQRDetails2.getItems().size(); i++) {
			tvQRDetails2.getItems().clear();
		}
		for (int i = 0; i < tvQRDetails3.getItems().size(); i++) {
			tvQRDetails3.getItems().clear();
		}
		ObservableList<RowForQuarterlyReports> rowsList = FXCollections.observableArrayList();
		for (Entry<ProductInStation, Double> entry : incomeReport.getIncomePerProduct().entrySet()) {
			ProductInStation p = entry.getKey();
			int pID = p.getProductInStationID();
			String pName = p.getProductName().toString();
			Double incomePer = entry.getValue();
			RowForQuarterlyReports row = new RowForQuarterlyReports(pID, pName, incomePer);
			rowsList.add(row);
		}
		tvQRDetails1.setItems(rowsList);

		ObservableList<RowForQuarterlyReports> rowsList2 = FXCollections.observableArrayList();
		for (Entry<ProductInStation, Double> entry : outcomeReport.getAmountBoughtPerProduct().entrySet()) {
			ProductInStation p = entry.getKey();
			int pID = p.getProductInStationID();
			String pName = p.getProductName().toString();
			Double outputPer = entry.getValue();
			RowForQuarterlyReports row = new RowForQuarterlyReports(pID, pName, outputPer);
			rowsList2.add(row);
		}
		tvQRDetails2.setItems(rowsList2);

		ObservableList<RowForQuarterlyReports> rowsList3 = FXCollections.observableArrayList();
		for (Entry<ProductInStation, Double> entry : inventoryReport.getAmountsPerProduct().entrySet()) {
			ProductInStation p = entry.getKey();
			int pID = p.getProductInStationID();
			String pName = p.getProductName().toString();
			Double invPer = entry.getValue();
			RowForQuarterlyReports row = new RowForQuarterlyReports(pID, pName, invPer);
			rowsList3.add(row);
		}
		tvQRDetails3.setItems(rowsList3);

		tvQRDetails1.refresh();
		tvQRDetails2.refresh();
		tvQRDetails3.refresh();
		tfQRTotalIncome.setText(incomeReport.getTotalIncome() + "");
		tfQRTotalAmountBought.setText(outcomeReport.getTotalAmountBoughtFromSupplier() + "");
		tfQRTotalAmountSold.setText(inventoryReport.getTotalAmountSold() + "");
	}

	/**
	 * convert date to string
	 */
	protected String converDateToFormattedString(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy  HH:mm");
		String dateFormatted = dateFormat.format(date);
		return dateFormatted;
	}

	/*********************** init of window ***********************/

	/**
	 * Initialize table view columns
	 */
	protected void initiallizeIncomeReportTables() {
		tvQRDetails1.getColumns().clear();
		TableColumn<RowForQuarterlyReports, Integer> productIDColumn = new TableColumn<RowForQuarterlyReports, Integer>(
				"Product ID");
		productIDColumn.setCellValueFactory(new PropertyValueFactory<>("productID"));
		productIDColumn.setPrefWidth(120);
		this.tvQRDetails1.getColumns().add(productIDColumn);
		TableColumn<RowForQuarterlyReports, String> productNameColumn = new TableColumn<RowForQuarterlyReports, String>(
				"Product Name");
		productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
		productNameColumn.setPrefWidth(250);
		this.tvQRDetails1.getColumns().add(productNameColumn);
		TableColumn<RowForQuarterlyReports, Double> incomePerProductColumn = new TableColumn<RowForQuarterlyReports, Double>(
				"Product Income");
		incomePerProductColumn.setCellValueFactory(new PropertyValueFactory<>("x"));
		incomePerProductColumn.setPrefWidth(200);
		this.tvQRDetails1.getColumns().add(incomePerProductColumn);
	}

	/**
	 * Initialize table view columns
	 */
	protected void initiallizeOutcomeReportTables() {
		tvQRDetails2.getColumns().clear();
		TableColumn<RowForQuarterlyReports, Integer> productIDColumn = new TableColumn<RowForQuarterlyReports, Integer>(
				"Product ID");
		productIDColumn.setCellValueFactory(new PropertyValueFactory<>("productID"));
		productIDColumn.setPrefWidth(120);
		this.tvQRDetails2.getColumns().add(productIDColumn);
		TableColumn<RowForQuarterlyReports, String> productNameColumn = new TableColumn<RowForQuarterlyReports, String>(
				"Product Name");
		productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
		productNameColumn.setPrefWidth(250);
		this.tvQRDetails2.getColumns().add(productNameColumn);
		TableColumn<RowForQuarterlyReports, Double> outcomePerProductColumn = new TableColumn<RowForQuarterlyReports, Double>(
				"Amount Bought");
		outcomePerProductColumn.setCellValueFactory(new PropertyValueFactory<>("x"));
		outcomePerProductColumn.setPrefWidth(200);
		this.tvQRDetails2.getColumns().add(outcomePerProductColumn);
	}

	/**
	 * Initialize table view columns
	 */
	protected void initiallizeInventoryReportTables() {
		tvQRDetails3.getColumns().clear();
		TableColumn<RowForQuarterlyReports, Integer> productIDColumn = new TableColumn<RowForQuarterlyReports, Integer>(
				"Product ID");
		productIDColumn.setCellValueFactory(new PropertyValueFactory<>("productID"));
		productIDColumn.setPrefWidth(120);
		this.tvQRDetails3.getColumns().add(productIDColumn);
		TableColumn<RowForQuarterlyReports, String> productNameColumn = new TableColumn<RowForQuarterlyReports, String>(
				"Product Name");
		productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
		productNameColumn.setPrefWidth(250);
		this.tvQRDetails3.getColumns().add(productNameColumn);
		TableColumn<RowForQuarterlyReports, Double> amountSoldColumn = new TableColumn<RowForQuarterlyReports, Double>(
				"Amount Sold");
		amountSoldColumn.setCellValueFactory(new PropertyValueFactory<>("x"));
		amountSoldColumn.setPrefWidth(200);
		this.tvQRDetails3.getColumns().add(amountSoldColumn);
	}

	/**
	 * fill Generate Quarterly Report comobo boxes
	 */
	protected void fillQuarterlyReportComboBox() {
		this.cobGQRYear.getItems().addAll(new String[] { "2019", "2020" });
		this.cobGQRQuarter.getItems().addAll(new Integer[] { 1, 2, 3, 4 });
		this.cobGQRYear.setValue("2020");
		this.cobGQRQuarter.setValue(1);
	}

	/**
	 * clear fxml entities as if the window was just entered into
	 */
	@Override
	public void clearFields() {
		for (int j = 0; j < tvQRDetails1.getItems().size(); j++)
			tvQRDetails1.getItems().clear();
		for (int k = 0; k < tvQRDetails2.getItems().size(); k++)
			tvQRDetails2.getItems().clear();
		for (int p = 0; p < tvQRDetails3.getItems().size(); p++)
			tvQRDetails3.getItems().clear();
		taDOReasons.clear();
		tfQRTotalIncome.clear();
		tfQRTotalAmountSold.clear();
		tfQRTotalAmountBought.clear();
		tflQRDateCreated.clear();
		tflQRQuarter.clear();
		tflQRYear.clear();
		tflQRFuelStationID.clear();
	}

}
