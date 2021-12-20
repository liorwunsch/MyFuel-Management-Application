package guiClient;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import entities.Product;
import entities.ProductRateList;
import entities.RankingSheet;
import entities.RankingSheetList;
import entities.RowForRankingSheetTable;
import enums.ProductName;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * boundary for marketing department workers' windows
 * 
 * @version Final
 * @author Lior, Elroy
 */
public abstract class MarketingDepWorkerWindow extends EmployeeWindow {

	@FXML
	protected Button btnHomeGenerateAnalysis;

	@FXML
	protected AnchorPane createSalePatternPane;
	@FXML
	protected Label lblCSPUpdateToDate;
	@FXML
	protected TableView<RowForRankingSheetTable> tvCSPAnalysis;
	@FXML
	protected TextField tfCSPDuration;
	@FXML
	protected TextField tfCSPDieselDisc;
	@FXML
	protected CheckBox cbCSPDiesel;
	@FXML
	protected CheckBox cbCSPGasoline;
	@FXML
	protected CheckBox cbCSPMotorbike;
	@FXML
	protected TextField tfCSPGasolineDisc;
	@FXML
	protected TextField tfCSPMotorbikeDisc;
	@FXML
	protected Button btnCSPCreate;

	protected ProductRateList productRateList;
	protected RankingSheetList rankingSheetList;

	/*********************** button listeners ***********************/

	/**
	 * button listener for generate analytics of customers
	 * 
	 * @param event
	 */
	@FXML
	void btnHomeGenerateAnalysisPressed(ActionEvent event) {
		this.sendToClientController("genAnalysis");
	}

	/**
	 * button listener for create sales pattern
	 * 
	 * @param event
	 */
	@FXML
	void btnCSPCreateClicked(MouseEvent event) {
		if (checkOfCreateSalePatternFields() == true && checkDuration(tfCSPDuration) && checkPrecentageInCSP()) {
			String message = "create sale pattern";
			message += " " + tfCSPDuration.getText();

			if (cbCSPDiesel.isSelected())
				message += " " + "Diesel " + Double.parseDouble(tfCSPDieselDisc.getText());
			else
				message += " " + "Diesel 0.0";

			if (cbCSPGasoline.isSelected())
				message += " " + "Gasoline " + Double.parseDouble(tfCSPGasolineDisc.getText());
			else
				message += " " + "Gasoline 0.0";

			if (cbCSPMotorbike.isSelected())
				message += " " + "MotorbikeFuel " + Double.parseDouble(tfCSPMotorbikeDisc.getText());
			else
				message += " " + "MotorbikeFuel 0.0";

			this.sendToClientController(message);
		}
	}

	/**
	 * fxml button css
	 * 
	 * @param event
	 */
	@FXML
	void btnCSPCreateHover(MouseEvent event) {
		btnCSPCreate.setOpacity(0.85);
	}

	/**
	 * fxml button css
	 * 
	 * @param event
	 */
	@FXML
	void btnCSPCreateExit(MouseEvent event) {
		btnCSPCreate.setOpacity(1);
	}

	/**
	 * buttong listener for diesel combobox in create sales pattern pane
	 * 
	 * @param event
	 */
	@FXML
	void cbCSPDieselClicked(MouseEvent event) {
		checkBoxesOfCreateSalePattern();
		tfCSPDieselDisc.setDisable(!tfCSPDieselDisc.isDisable());
		if (tfCSPDieselDisc.isDisable()) {
			tfCSPDieselDisc.clear();
		}
	}

	/**
	 * buttong listener for gasoline checkbox in create sales pattern pane
	 * 
	 * @param event
	 */
	@FXML
	void cbCSPGasolineClicked(MouseEvent event) {
		checkBoxesOfCreateSalePattern();
		tfCSPGasolineDisc.setDisable(!tfCSPGasolineDisc.isDisable());
		if (tfCSPGasolineDisc.isDisable()) {
			tfCSPGasolineDisc.clear();
		}
	}

	/**
	 * buttong listener for motorbike fuel checkbox in create sales pattern pane
	 * 
	 * @param event
	 */
	@FXML
	void cbCSPMotorbikeClicked(MouseEvent event) {
		checkBoxesOfCreateSalePattern();
		tfCSPMotorbikeDisc.setDisable(!tfCSPMotorbikeDisc.isDisable());
		if (tfCSPMotorbikeDisc.isDisable()) {
			tfCSPMotorbikeDisc.clear();
		}
	}

	/*************** boundary "logic" - window changes ***************/

	/**
	 * called after server returned a message/object to the client
	 */
	@Override
	public void callAfterMessage(Object lastMsgFromServer) {
		super.callAfterMessage(lastMsgFromServer);
		if ((lastMsgFromServer instanceof String)) {
			String str = (String) lastMsgFromServer;
			if (str.equals("genAnalysis success")) {
				openConfirmationAlert("Success", "Analytics Generated");
				requestToLogActivity("generated or updated analysis");
			} else if (str.equals("genAnalysis fail")) {
				openErrorAlert("Fail", "Analytics Generate Failed");
			}
		}
	}

	/**
	 * method that initiate a table
	 */
	protected void initializeRankingSheetTable() {
		tvCSPAnalysis.getColumns().clear();
		TableColumn<RowForRankingSheetTable, String> cusotmerIDColumn = new TableColumn<RowForRankingSheetTable, String>(
				"Customer ID");
		cusotmerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
		tvCSPAnalysis.getColumns().add(cusotmerIDColumn);
		TableColumn<RowForRankingSheetTable, Double> typeRankColumn = new TableColumn<RowForRankingSheetTable, Double>(
				"Customer Type Rank");
		typeRankColumn.setCellValueFactory(new PropertyValueFactory<>("customerTypeRank"));
		tvCSPAnalysis.getColumns().add(typeRankColumn);
		TableColumn<RowForRankingSheetTable, Double> fuelingHoursColumn = new TableColumn<RowForRankingSheetTable, Double>(
				"Fueling Hours Rank");
		fuelingHoursColumn.setCellValueFactory(new PropertyValueFactory<>("fuelingHoursRank"));
		tvCSPAnalysis.getColumns().add(fuelingHoursColumn);
		TableColumn<RowForRankingSheetTable, Double> fuelingTypesColumn = new TableColumn<RowForRankingSheetTable, Double>(
				"Fueling Types Rank");
		fuelingTypesColumn.setCellValueFactory(new PropertyValueFactory<>("fuelTypesRank"));
		tvCSPAnalysis.getColumns().add(fuelingTypesColumn);

		cusotmerIDColumn.prefWidthProperty().bind(tvCSPAnalysis.widthProperty().multiply(0.22));
		typeRankColumn.prefWidthProperty().bind(tvCSPAnalysis.widthProperty().multiply(0.25));
		fuelingHoursColumn.prefWidthProperty().bind(tvCSPAnalysis.widthProperty().multiply(0.25));
		fuelingTypesColumn.prefWidthProperty().bind(tvCSPAnalysis.widthProperty().multiply(0.25));
		cusotmerIDColumn.setResizable(false);
		typeRankColumn.setResizable(false);
		fuelingHoursColumn.setResizable(false);
		fuelingTypesColumn.setResizable(false);
	}

	/**
	 * method that updates the ranking sheet table
	 */
	protected void updateRankingSheetListInTable() {
		List<RankingSheet> RSList = this.rankingSheetList.getList();
		ObservableList<RowForRankingSheetTable> rowsList = FXCollections.observableArrayList();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(RSList.get(0).getUpdatedForDate());
		cal.add(Calendar.DAY_OF_MONTH, -1);
		this.lblCSPUpdateToDate.setText(formatter.format(cal.getTime()));

		for (int i = 0; i < tvCSPAnalysis.getItems().size(); i++) {
			tvCSPAnalysis.getItems().clear();
		}
		Iterator<RankingSheet> iterator = RSList.iterator();
		while (iterator.hasNext()) {
			RankingSheet rS = (RankingSheet) iterator.next();
			RowForRankingSheetTable row = new RowForRankingSheetTable(rS.getCustomerID(), rS.getCustomerTypeRank(),
					rS.getFuelingHoursRank(), rS.getFuelTypesRank());
			rowsList.add(row);
		}
		tvCSPAnalysis.setItems(rowsList);
	}

	/**
	 * check if fields are empty
	 * 
	 * @return result flag
	 */
	protected boolean checkOfCreateSalePatternFields() {
		boolean result = false;
		if (tfCSPDuration.getText().trim().isEmpty() || this.checkValidTextField(tfCSPDuration.getText(), "digits",
				"Duration Must Be a Positive Number") == false) {
			this.openErrorAlert("Error", "Please enter duration");
			return false;
		}
		if (Integer.parseInt(tfCSPDuration.getText()) > 60 * 24) {
			openErrorAlert("Error", "Sale can't be more than 24 hours");
			return false;
		}

		if (!cbCSPDiesel.isSelected() && !cbCSPGasoline.isSelected() && !cbCSPGasoline.isSelected()
				&& !cbCSPMotorbike.isSelected()) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Discounts are empty");
			alert.setContentText("You must fill at least one discount");
			alert.show();
			return false;
		}
		if (cbCSPDiesel.isSelected()) {
			if (tfCSPDieselDisc.getText().trim().isEmpty()) {
				this.openErrorAlert("Error", "Diesel discount is empty");
				return false;
			} else if (this.checkValidTextField(tfCSPDieselDisc.getText(), "double",
					"Diesel discount must be a positive number") == false) {
				return false;
			}
		}
		if (cbCSPGasoline.isSelected()) {
			if (tfCSPGasolineDisc.getText().trim().isEmpty()) {
				this.openErrorAlert("Error", "Gasoline discount is empty");
				return false;
			} else if (this.checkValidTextField(tfCSPGasolineDisc.getText(), "double",
					"Gasoline discount must be a positive number") == false) {
				return false;
			}
		}
		if (cbCSPMotorbike.isSelected()) {
			if (tfCSPMotorbikeDisc.getText().trim().isEmpty()) {
				this.openErrorAlert("Error", "Motorbike Fuel discount is empty");
				return false;
			} else if (this.checkValidTextField(tfCSPMotorbikeDisc.getText(), "double",
					"Motorbike Fuel discount must be a positive number") == false) {
				return false;
			}
		}
		if (!tfCSPDuration.getText().trim().isEmpty()
				&& ((cbCSPDiesel.isSelected() && !tfCSPDieselDisc.getText().trim().isEmpty())
						|| (cbCSPGasoline.isSelected() && !tfCSPGasolineDisc.getText().trim().isEmpty())
						|| (cbCSPMotorbike.isSelected() && !tfCSPMotorbikeDisc.getText().trim().isEmpty()))) {
			result = true;
		}
		return result;
	}

	/**
	 * method that check the values of precentages fields is correct
	 * 
	 * @return true if create sales pattern fields are valid
	 */
	protected boolean checkPrecentageInCSP() {
		boolean flagDiesel = true;
		boolean flagGasoline = true;
		boolean flagMotorbikeFuel = true;
		for (Product product : productRateList.getList()) {
			if (product.getProductName().equals(ProductName.Diesel)) {
				if (!tfCSPDieselDisc.getText().trim().isEmpty() && Double.parseDouble(tfCSPDieselDisc.getText()) > 0
						&& Double.parseDouble(tfCSPDieselDisc.getText()) < product.getCurrentPrice()) {
					flagDiesel = true;
				}
				if (!tfCSPDieselDisc.getText().trim().isEmpty() && (Double.parseDouble(tfCSPDieselDisc.getText()) <= 0
						|| Double.parseDouble(tfCSPDieselDisc.getText()) >= product.getCurrentPrice())) {
					this.openErrorAlert("Error", "Please enter a number of Diesel discount \n lower than "
							+ product.getCurrentPrice() + " and not 0");
					flagDiesel = false;
				}
			}
			if (product.getProductName().equals(ProductName.Gasoline)) {
				if (!tfCSPGasolineDisc.getText().trim().isEmpty() && Double.parseDouble(tfCSPGasolineDisc.getText()) > 0
						&& Double.parseDouble(tfCSPGasolineDisc.getText()) < product.getCurrentPrice()) {
					flagGasoline = true;
				}
				if (!tfCSPGasolineDisc.getText().trim().isEmpty()
						&& (Double.parseDouble(tfCSPGasolineDisc.getText()) <= 0
								|| Double.parseDouble(tfCSPGasolineDisc.getText()) >= product.getCurrentPrice())) {
					this.openErrorAlert("Error", "Please enter a number of Gasoline discount \n lower than "
							+ product.getCurrentPrice() + " and not 0");
					flagGasoline = false;
				}
			}
			if (product.getProductName().equals(ProductName.MotorbikeFuel)) {
				if (!tfCSPMotorbikeDisc.getText().trim().isEmpty()
						&& Double.parseDouble(tfCSPMotorbikeDisc.getText()) > 0
						&& Double.parseDouble(tfCSPMotorbikeDisc.getText()) < product.getCurrentPrice()) {
					flagMotorbikeFuel = true;
				}
				if (!tfCSPMotorbikeDisc.getText().trim().isEmpty()
						&& (Double.parseDouble(tfCSPMotorbikeDisc.getText()) <= 0
								|| Double.parseDouble(tfCSPMotorbikeDisc.getText()) >= product.getCurrentPrice())) {
					this.openErrorAlert("Error", "Please enter a number of Motorbike Fuel discount \n lower than "
							+ product.getCurrentPrice() + " and not 0");
					flagMotorbikeFuel = false;
				}
			}
		}
		return flagDiesel && flagGasoline && flagMotorbikeFuel;
	}

	/**
	 * method that check if boxes are selected
	 */
	private void checkBoxesOfCreateSalePattern() {
		if (!cbCSPDiesel.isSelected() && !cbCSPGasoline.isSelected() && !cbCSPMotorbike.isSelected()) {
			btnCSPCreate.setDisable(true);
			btnCSPCreate.setOpacity(0.7);
		} else {
			btnCSPCreate.setDisable(false);
			btnCSPCreate.setOpacity(1);
		}
	}

	/**
	 * @param tf
	 * @return result check that the time field value is correct
	 */
	protected boolean checkDuration(TextField tf) {
		boolean result = false;
		if (Integer.parseInt(tf.getText()) > 0) {
			result = true;
		}
		if (result == false)
			this.openErrorAlert("ERROR", "Please Enter Duration Bigger Than 0");

		return result;
	}

	/**
	 * a method that will pull form SQL the ranking sheets and show them on the
	 * table view
	 */
	protected void getAllRankingSheets() {
		this.sendToClientController("pull ranking sheets");
	}

	/**
	 * a method that will pull form SQL the produtn in sales patterns and show them
	 * on the table view
	 */
	protected void getAllProductRanks() {
		this.sendToClientController("pull product rates");
	}

	/**
	 * clear sale pattern pane
	 */
	protected void clearSalePatternPane() {
		tfCSPDuration.clear();
		cbCSPDiesel.setSelected(false);
		cbCSPGasoline.setSelected(false);
		cbCSPMotorbike.setSelected(false);
		tfCSPDieselDisc.clear();
		tfCSPGasolineDisc.clear();
		tfCSPMotorbikeDisc.clear();
		tfCSPDieselDisc.setDisable(true);
		tfCSPGasolineDisc.setDisable(true);
		tfCSPMotorbikeDisc.setDisable(true);
		btnCSPCreate.setDisable(true);
		btnCSPCreate.setOpacity(0.7);
	}

	/**
	 * method that will sent to the client controller the required message
	 * 
	 * @param message
	 */
	protected void sendToClientController(String message) {
		this.controller.handleMessageFromClientUI(message);
	}
}
