package guiClient;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import client.CustomerController;
import entities.FastFuel;
import entities.FastFuelList;
import entities.HomeFuelOrder;
import entities.HomeFuelOrderList;
import entities.PurchasingProgramType;
import enums.ProductName;
import enums.ShipmentType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;
import javafx.util.Callback;

/**
 * boundary for customer window
 * 
 * @version Final
 * @author Lior
 */
public class CustomerWindow extends UserWindow {

	@FXML
	private ToggleGroup one;
	@FXML
	private ToggleButton sidebar_btn0;
	@FXML
	private ToggleButton sidebar_btn1;
	@FXML
	private ToggleButton sidebar_btn2;

	@FXML
	private Label lblHomeMember;
	@FXML
	private Label lblHomePayment;
	@FXML
	private TableView<FastFuel> tvHomeFastFuel;
	@FXML
	private TextField tfHomeTotal;
	@FXML
	private PasswordField tfHomeNewPass;
	@FXML
	private Button btnHomeUpdatePass;

	@FXML
	private AnchorPane orderHomeFuelPane;
	@FXML
	private AnchorPane apOHFPurchaseInfo;
	@FXML
	private TextField tfOHFAmount1;
	@FXML
	private TextField tfOHFAddress;
	@FXML
	private TextField tfOHFPrice1;
	@FXML
	private ToggleGroup two;
	@FXML
	private RadioButton rbOHFShipment1;
	@FXML
	private RadioButton rbOHFShipment2;
	@FXML
	private Button btnOHFShowPrice;
	@FXML
	private AnchorPane apOHFOrderDetails;
	@FXML
	private TextField tfOHFDate;
	@FXML
	private TextField tfOHFFinalPrice;
	@FXML
	private TextField tfOHFAmount2;
	@FXML
	private TextField tfOHFShipmentReview;
	@FXML
	private Button btnOHFConfirm;

	@FXML
	private AnchorPane viewOrderPane;
	@FXML
	private TableView<HomeFuelOrder> tvVODetails;

	@FXML
	void initialize() {
		this.homePane.setVisible(true);
		this.viewOrderPane.setVisible(false);
		this.orderHomeFuelPane.setVisible(false);
		this.visibleNow = this.homePane;
		this.controller = CustomerController.getInstance();
		this.controller.setCurrentWindow(this);
	}

	@Override
	public Window getWindow() {
		return this.orderHomeFuelPane.getScene().getWindow();
	}

	/*********************** button listeners ***********************/

	@FXML
	void openHome(ActionEvent event) {
		this.visibleNow.setVisible(false);
		this.homePane.setVisible(true);
		this.visibleNow = homePane;
		this.topbar_window_label.setText("Home");
		clearFields();
	}

	@FXML
	void btnHomeUpdatePressed(ActionEvent event) {
		this.controller.handleMessageFromClientUI("fastfuel get " + username + " "
				+ this.cobHomeYear.getValue().toString() + " " + this.cobHomeMonth.getValue().toString());
	}

	@FXML
	void btnHomeUpdatePassPressed(ActionEvent event) {
		String pass = this.tfHomeNewPass.getText();
		if (pass.isEmpty()) {
			openErrorAlert("Error", "Missing Password Field");
			return;
		}
		if (pass.matches(".*[ -/].*") || pass.matches(".*[:-~].*")) {
			openErrorAlert("Error", "Password Not Valid\n Only Digits");
			return;
		}

		this.controller.handleMessageFromClientUI("updatepassword " + username + " " + pass);
	}

	@FXML
	void openOrderHomeFuel(ActionEvent event) {
		this.visibleNow.setVisible(false);
		this.orderHomeFuelPane.setVisible(true);
		this.visibleNow = this.orderHomeFuelPane;
		this.topbar_window_label.setText("Order Home Fuel");
		this.controller.handleMessageFromClientUI("homefuel get price");
		this.apOHFPurchaseInfo.setDisable(false);
		this.apOHFOrderDetails.setDisable(true);
		clearFields();
	}

	@FXML
	void btnOHFShowPricePressed(ActionEvent event) {
		String amount = this.tfOHFAmount1.getText();
		String address = this.tfOHFAddress.getText();
		ShipmentType shipmentType;

		if (amount.isEmpty() || address.isEmpty()) {
			openErrorAlert("Error", "Missing Required Fields");
			this.tfOHFAmount1.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
			this.tfOHFAddress.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
			return;
		}
		if (amount.matches(".*[ -/].*") || amount.matches(".*[:-~].*") || amount.length() >= 5) {
			openErrorAlert("Error", "Amount Not Valid");
			return;
		}

		if (this.rbOHFShipment1.isSelected())
			shipmentType = ShipmentType.Regular;
		else
			shipmentType = ShipmentType.Urgent;

		this.controller.handleMessageFromClientUI(
				("gethomefuelfinalprice " + amount + " " + tfOHFPrice1.getText() + " " + shipmentType.toString()));
	}

	@FXML
	void btnOHFConfirmPressed(ActionEvent event) {
		ShipmentType shipmentType;
		if (this.rbOHFShipment1.isSelected())
			shipmentType = ShipmentType.Regular;
		else
			shipmentType = ShipmentType.Urgent;
		this.controller.handleMessageFromClientUI(("homefuel set " + this.username + " " + shipmentType.toString() + " "
				+ tfOHFFinalPrice.getText() + " " + tfOHFAmount1.getText() + " " + tfOHFAddress.getText()));
	}

	@FXML
	void openViewOrders(ActionEvent event) {
		this.visibleNow.setVisible(false);
		this.viewOrderPane.setVisible(true);
		this.visibleNow = this.viewOrderPane;
		this.topbar_window_label.setText("View Home Fuel Orders");

		this.controller.handleMessageFromClientUI("homefuel get " + username);
	}

	/*************** boundary "logic" - window changes ***************/

	@Override
	public void callAfterMessage(Object lastMsgFromServer) {
		super.callAfterMessage(lastMsgFromServer);

		if (lastMsgFromServer instanceof HomeFuelOrderList) {
			HomeFuelOrderList homeFuelOrderList = (HomeFuelOrderList) lastMsgFromServer;
			handleGetHomeFuelOrderListFromServer(homeFuelOrderList);

		} else if (lastMsgFromServer instanceof FastFuelList) {
			FastFuelList fastFuelList = (FastFuelList) lastMsgFromServer;
			handleGetFastFuelListFromServer(fastFuelList);

		} else if (lastMsgFromServer instanceof PurchasingProgramType) {
			PurchasingProgramType purchasingProgramType = (PurchasingProgramType) lastMsgFromServer;
			this.lblHomeMember.setText(purchasingProgramType.getPurchasingProgramName().toString() + " - "
					+ purchasingProgramType.getMonthlyPrice() + "$ per month");
			this.lblHomePayment.setText(((Double) (Double.parseDouble(this.tfHomeTotal.getText())
					+ purchasingProgramType.getMonthlyPrice())).toString() + " $");

		} else if (lastMsgFromServer instanceof Double) {
			DecimalFormat df = new DecimalFormat("#.##");
			this.tfOHFPrice1.setText(df.format((Double) lastMsgFromServer));

		} else if (lastMsgFromServer instanceof Float) {
			DecimalFormat df = new DecimalFormat("#.##");
			this.tfOHFFinalPrice.setText(df.format((Float) lastMsgFromServer));
			this.apOHFPurchaseInfo.setDisable(true);
			this.apOHFOrderDetails.setDisable(false);
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			this.tfOHFDate.setText(formatter.format(new Date()));
			this.tfOHFAmount2.setText(this.tfOHFAmount1.getText());
			if (this.rbOHFShipment1.isSelected())
				this.tfOHFShipmentReview.setText(this.rbOHFShipment1.getText());
			else
				this.tfOHFShipmentReview.setText(this.rbOHFShipment1.getText());

		} else if (lastMsgFromServer instanceof String) {
			String str = (String) lastMsgFromServer;

			if (str.equals("set homefuelorder success")) {
				openConfirmationAlert("Success", "Order Saved");
				this.apOHFOrderDetails.setDisable(true);

			} else if (str.equals("set homefuelorder fail")) {
				openErrorAlert("Error", "Order Failed");

			} else if (str.equals("update password success")) {
				openConfirmationAlert("Success", "Password Updated");
				this.tfHomeNewPass.clear();

			} else if (str.equals("update password fail")) {
				openErrorAlert("Error", "Password Update Failed");
			}

		}
	}

	/**
	 * initialized tableview in home of customer only
	 * 
	 * @param username
	 */
	@Override
	@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
	public void setUserComponents(String username) {
		super.setUserComponents(username);
		final TableColumn<FastFuel, Date> timeColumn = (TableColumn<FastFuel, Date>) new TableColumn("Time");
		timeColumn.setCellValueFactory((Callback) new PropertyValueFactory("fastFuelTime"));
		timeColumn.impl_setWidth(170);
		this.tvHomeFastFuel.getColumns().add(timeColumn);
		final TableColumn<FastFuel, String> regPlateColumn = (TableColumn<FastFuel, String>) new TableColumn(
				"Registration Plate");
		regPlateColumn.setCellValueFactory((Callback) new PropertyValueFactory("registrationPlate"));
		this.tvHomeFastFuel.getColumns().add(regPlateColumn);
		final TableColumn<FastFuel, String> fuelStationColumn = (TableColumn<FastFuel, String>) new TableColumn(
				"Fuel Station");
		fuelStationColumn.setCellValueFactory((Callback) new PropertyValueFactory("fuelStationName"));
		this.tvHomeFastFuel.getColumns().add(fuelStationColumn);
		final TableColumn<FastFuel, ProductName> fuelTypeColumn = (TableColumn<FastFuel, ProductName>) new TableColumn(
				"Fuel Type");
		fuelTypeColumn.setCellValueFactory((Callback) new PropertyValueFactory("fuelType"));
		this.tvHomeFastFuel.getColumns().add(fuelTypeColumn);
		final TableColumn<FastFuel, String> amountColumn = (TableColumn<FastFuel, String>) new TableColumn("Amount");
		amountColumn.setCellValueFactory((Callback) new PropertyValueFactory("amountBought"));
		this.tvHomeFastFuel.getColumns().add(amountColumn);
		final TableColumn<FastFuel, String> priceColumn = (TableColumn<FastFuel, String>) new TableColumn("Price");
		priceColumn.setCellValueFactory((Callback) new PropertyValueFactory("finalPrice"));
		this.tvHomeFastFuel.getColumns().add(priceColumn);

		final TableColumn<HomeFuelOrder, Integer> orderIDColumn = (TableColumn<HomeFuelOrder, Integer>) new TableColumn(
				"Order ID");
		orderIDColumn.setCellValueFactory((Callback) new PropertyValueFactory("ordersID"));
		this.tvVODetails.getColumns().add(orderIDColumn);
		final TableColumn<HomeFuelOrder, Date> orderTimeColumn = (TableColumn<HomeFuelOrder, Date>) new TableColumn(
				"Time Bought");
		orderTimeColumn.impl_setWidth(170);
		orderTimeColumn.setCellValueFactory((Callback) new PropertyValueFactory("orderTime"));
		this.tvVODetails.getColumns().add(orderTimeColumn);
		final TableColumn<HomeFuelOrder, String> orderAddress = (TableColumn<HomeFuelOrder, String>) new TableColumn(
				"Address");
		orderAddress.setCellValueFactory((Callback) new PropertyValueFactory("address"));
		this.tvVODetails.getColumns().add(orderAddress);
		final TableColumn<HomeFuelOrder, Double> orderAmount = (TableColumn<HomeFuelOrder, Double>) new TableColumn(
				"Amount");
		orderAmount.setCellValueFactory((Callback) new PropertyValueFactory("amountBought"));
		this.tvVODetails.getColumns().add(orderAmount);
		final TableColumn<HomeFuelOrder, ShipmentType> orderShipment = (TableColumn<HomeFuelOrder, ShipmentType>) new TableColumn(
				"Shipment Method");
		orderShipment.setCellValueFactory((Callback) new PropertyValueFactory("shipmentMethod"));
		this.tvVODetails.getColumns().add(orderShipment);
		final TableColumn<HomeFuelOrder, Date> orderDueTimeColumn = (TableColumn<HomeFuelOrder, Date>) new TableColumn(
				"Due Time");
		orderDueTimeColumn.impl_setWidth(170);
		orderDueTimeColumn.setCellValueFactory((Callback) new PropertyValueFactory("dueTime"));
		this.tvVODetails.getColumns().add(orderDueTimeColumn);
		final TableColumn<HomeFuelOrder, Double> orderPrice = (TableColumn<HomeFuelOrder, Double>) new TableColumn(
				"Total Price");
		orderPrice.setCellValueFactory((Callback) new PropertyValueFactory("finalPrice"));
		this.tvVODetails.getColumns().add(orderPrice);

		this.controller.handleMessageFromClientUI(("fastfuel get " + username + " "
				+ (new java.util.Date().getYear() + 1900) + " " + (new java.util.Date().getMonth() + 1)));

		this.controller.handleMessageFromClientUI("getcustomerpurchasingprogram " + username);
	}

	/**
	 * fills the tableview in home with fast fuels
	 * 
	 * @param fastFuelList
	 */
	public void handleGetFastFuelListFromServer(FastFuelList fastFuelList) {
		final ObservableList<FastFuel> list = FXCollections.observableArrayList();
		for (int i = 0; i < this.tvHomeFastFuel.getItems().size(); ++i) {
			this.tvHomeFastFuel.getItems().clear();
		}

		double total = 0;
		ArrayList<FastFuel> fastFuels = fastFuelList.getFastFuels();
		for (FastFuel fastFuel : fastFuels) {
			list.add(fastFuel);
			total += fastFuel.getFinalPrice();
		}
		this.tvHomeFastFuel.setItems(list);
		DecimalFormat df = new DecimalFormat("#.##");
		this.tfHomeTotal.setText(df.format(total));
	}

	private void handleGetHomeFuelOrderListFromServer(HomeFuelOrderList homeFuelOrderList) {
		final ObservableList<HomeFuelOrder> list = FXCollections.observableArrayList();
		for (int i = 0; i < this.tvVODetails.getItems().size(); ++i) {
			this.tvVODetails.getItems().clear();
		}

		ArrayList<HomeFuelOrder> homeFuelOrders = homeFuelOrderList.getHomeFuelOrders();
		for (HomeFuelOrder homeFuelOrder : homeFuelOrders) {
			list.add(homeFuelOrder);
		}
		this.tvVODetails.setItems(list);
	}

	@Override
	public void clearFields() {
		this.tfOHFAmount1.clear();
		this.tfOHFAddress.clear();
		this.tfOHFDate.clear();
		this.tfOHFFinalPrice.clear();
		this.tfOHFAmount2.clear();
		this.tfOHFShipmentReview.clear();
		this.rbOHFShipment1.setSelected(true);
		this.tfHomeNewPass.clear();
	}

}
