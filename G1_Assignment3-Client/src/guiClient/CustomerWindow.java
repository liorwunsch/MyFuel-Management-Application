package guiClient;

import java.util.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import client.CustomerController;
import entities.FastFuel;
import entities.FastFuelList;
import entities.HomeFuelOrder;
import enums.ProductName;
import enums.ShipmentType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
import javafx.util.Callback;

/**
 * boundary for customer window
 * 
 * @version Basic
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
	private AnchorPane fastFuelPane;
	@FXML
	private Label lblFFPricePerLiter;

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
	void btnHomeUpdatePressed(ActionEvent event) {
		this.controller.handleMessageFromClientUI(("fastfuel get " + username + " " + cobHomeYear.getValue().toString()
				+ " " + cobHomeMonth.getValue().toString()));
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
		for (Node node : orderHomeFuelPane.getChildren()) {
			if (node instanceof TextField) {
				((TextField) node).setText("");
			}
		}
	}

	@FXML
	void btnOHFShowPricePressed(ActionEvent event) {
		String amount = this.tfOHFAmount1.getText();
		String address = this.tfOHFAddress.getText();
		ShipmentType shipmentType;

		if (amount.isEmpty() || address.isEmpty() || amount.matches(".*[A-z].*")) {
			openErrorAlert("ERROR", "Missing Required Fields");
			this.tfOHFAmount1.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
			this.tfOHFAddress.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
			return;
		}
		this.tfOHFAmount1.setStyle("-fx-border-style: none;");
		this.tfOHFAddress.setStyle("-fx-border-style: none;");

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
	}

	/*************** boundary "logic" - window changes ***************/

	@Override
	public void callAfterMessage(Object lastMsgFromServer) {
		super.callAfterMessage(lastMsgFromServer);

		if (lastMsgFromServer instanceof FastFuelList) {
			FastFuelList fastFuelList = (FastFuelList) lastMsgFromServer;
			handleGetFastFuelListFromServer(fastFuelList);
		}
		if (lastMsgFromServer instanceof Double) {
			DecimalFormat df = new DecimalFormat("#.##");
			this.tfOHFPrice1.setText(df.format((Double) lastMsgFromServer));
		}
		if (lastMsgFromServer instanceof Float) {
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
		}
		if (lastMsgFromServer instanceof String) {
			if (((String) lastMsgFromServer).equals("set homefuelorder success"))
				openErrorAlert("Success", "Order Saved");
			if (((String) lastMsgFromServer).equals("set homefuelorder fail"))
				openErrorAlert("ERROR", "Order Failed");
			this.apOHFPurchaseInfo.setDisable(false);
			this.apOHFOrderDetails.setDisable(true);
			for (Node node : orderHomeFuelPane.getChildren()) {
				if (node instanceof TextField) {
					((TextField) node).setText("");
				}
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

		this.controller.handleMessageFromClientUI(("fastfuel get " + username + " "
				+ (new java.util.Date().getYear() + 1900) + " " + (new java.util.Date().getMonth() + 1)));
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

}
