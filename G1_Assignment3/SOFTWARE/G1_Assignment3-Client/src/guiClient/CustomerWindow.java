package guiClient;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import javafx.scene.input.KeyEvent;
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
	private Button btnOHFCancel;

	@FXML
	private AnchorPane viewOrderPane;
	@FXML
	private TableView<HomeFuelOrder> tvVODetails;

	/**
	 * runs every time this windows goes live
	 */
	@FXML
	void initialize() {
		this.homePane.setVisible(true);
		this.viewOrderPane.setVisible(false);
		this.orderHomeFuelPane.setVisible(false);
		this.topbar_window_label.setText("Home");
		this.visibleNow = this.homePane;
		this.controller = CustomerController.getInstance();
		this.controller.setCurrentWindow(this);
	}

	/**
	 * returns the window entity of this boundary
	 */
	@Override
	public Window getWindow() {
		return this.orderHomeFuelPane.getScene().getWindow();
	}

	/*********************** button listeners ***********************/

	/**
	 * button listener for home sidebar button
	 * 
	 * @param event
	 */
	@FXML
	void openHome(ActionEvent event) {
		this.sidebar_btn0.setSelected(true);
		this.visibleNow.setVisible(false);
		this.homePane.setVisible(true);
		this.visibleNow = this.homePane;
		this.topbar_window_label.setText("Home");
		clearFields();
	}

	/**
	 * button listener for home update button updates the activies tableview
	 * 
	 * @param event
	 */
	@FXML
	void btnHomeUpdatePressed(ActionEvent event) {
		this.controller.handleMessageFromClientUI("fastfuel get " + username + " "
				+ this.cobHomeYear.getValue().toString() + " " + this.cobHomeMonth.getValue().toString());
	}

	/**
	 * button listener for updating customer password
	 * 
	 * @param event
	 */
	@FXML
	void btnHomeUpdatePassPressed(ActionEvent event) {
		String pass = this.tfHomeNewPass.getText();
		if (pass.isEmpty()) {
			openErrorAlert("Error", "Missing Password Field");
			return;
		}
		if (pass.contains(" ")) {
			openErrorAlert("Error", "Password Should Not Contain Spaces");
			return;
		}
		if (pass.length() > 50) {
			openErrorAlert("Error", "Password Too Long, Max 50 Characters");
			return;
		}

		this.controller.handleMessageFromClientUI("updatepassword " + username + " " + pass);
	}

	/**
	 * button listener for order home fuel sidebar button
	 * 
	 * @param event
	 */
	@FXML
	void openOrderHomeFuel(ActionEvent event) {
		this.sidebar_btn1.setSelected(true);
		this.visibleNow.setVisible(false);
		this.orderHomeFuelPane.setVisible(true);
		this.visibleNow = this.orderHomeFuelPane;
		this.topbar_window_label.setText("Order Home Fuel");
		this.controller.handleMessageFromClientUI("homefuel get price");
		this.apOHFPurchaseInfo.setDisable(false);
		this.apOHFOrderDetails.setDisable(true);
		clearFields();
	}

	/**
	 * button listener for show home fuel order final price button
	 * 
	 * @param event
	 */
	@FXML
	void btnOHFShowPricePressed(ActionEvent event) {
		String amount = this.tfOHFAmount1.getText();
		String address = this.tfOHFAddress.getText();

		if (amount.isEmpty() || address.isEmpty()) {
			openErrorAlert("Error", "Missing Required Fields");
			return;
		}
		try {
			if (Double.parseDouble(amount) <= 0) {
				openErrorAlert("Error", "Amount Must Be a Positive Number");
				return;
			}
			if (Double.parseDouble(amount) > 10000) {
				openErrorAlert("Error", "Amount Too Big\nMax 10000");
				return;
			}
		} catch (NumberFormatException e) {
			openErrorAlert("Error", "Amount Must Be a Number");
			return;
		}
		if (address.length() > 50) {
			openErrorAlert("Error", "Address Too Long\nMax 50 Characters");
			return;
		}

		ShipmentType shipmentType = null;
		if (this.rbOHFShipment1.isSelected())
			shipmentType = ShipmentType.Regular;
		else
			shipmentType = ShipmentType.Urgent;

		this.controller.handleMessageFromClientUI(
				("gethomefuelfinalprice " + amount + " " + tfOHFPrice1.getText() + " " + shipmentType.toString()));
	}

	/**
	 * button listener for confirm home fuel order button
	 * 
	 * @param event
	 */
	@FXML
	void btnOHFConfirmPressed(ActionEvent event) {
		ShipmentType shipmentType;
		if (this.rbOHFShipment1.isSelected())
			shipmentType = ShipmentType.Regular;
		else
			shipmentType = ShipmentType.Urgent;

		String address = tfOHFAddress.getText().replaceAll("\\s", "@");

		this.controller.handleMessageFromClientUI(("homefuel set " + this.username + " " + shipmentType.toString() + " "
				+ tfOHFFinalPrice.getText() + " " + tfOHFAmount1.getText() + "_" + address));
	}

	/**
	 * button listener for cance; home fuel order button
	 * 
	 * @param event
	 */
	@FXML
	void btnOHFCancelPressed(ActionEvent event) {
		tfOHFDate.clear();
		tfOHFFinalPrice.clear();
		tfOHFAmount2.clear();
		tfOHFShipmentReview.clear();
		apOHFOrderDetails.setDisable(true);
		apOHFPurchaseInfo.setDisable(false);
	}

	/**
	 * button listener for show home fuel orders of customer sidebar button
	 * 
	 * @param event
	 */
	@FXML
	void openViewOrders(ActionEvent event) {
		this.sidebar_btn2.setSelected(true);
		this.visibleNow.setVisible(false);
		this.viewOrderPane.setVisible(true);
		this.visibleNow = this.viewOrderPane;
		this.topbar_window_label.setText("View Home Fuel Orders");

		this.controller.handleMessageFromClientUI("homefuel get " + username);
	}

	/*************** boundary "logic" - window changes ***************/

	/**
	 * called after server returned a message/object to the client
	 */
	@Override
	public void callAfterMessage(Object lastMsgFromServer) {
		super.callAfterMessage(lastMsgFromServer);
		DecimalFormat df = new DecimalFormat("#.##");
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

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
			this.tfOHFPrice1.setText(df.format((Double) lastMsgFromServer));

		} else if (lastMsgFromServer instanceof Float) {
			this.tfOHFFinalPrice.setText(df.format((Float) lastMsgFromServer));
			this.apOHFPurchaseInfo.setDisable(true);
			this.apOHFOrderDetails.setDisable(false);
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
				clearFields();

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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setUserComponents(String username) {
		super.setUserComponents(username);
		final TableColumn<FastFuel, Date> timeColumn = (TableColumn<FastFuel, Date>) new TableColumn("Time");
		timeColumn.setCellValueFactory((Callback) new PropertyValueFactory("fastFuelTime"));
		timeColumn.setMinWidth(170);
		this.tvHomeFastFuel.getColumns().add(timeColumn);
		final TableColumn<FastFuel, String> regPlateColumn = (TableColumn<FastFuel, String>) new TableColumn(
				"Registration Plate");
		regPlateColumn.setCellValueFactory((Callback) new PropertyValueFactory("registrationPlate"));
		this.tvHomeFastFuel.getColumns().add(regPlateColumn);
		final TableColumn<FastFuel, String> fuelStationColumn = (TableColumn<FastFuel, String>) new TableColumn(
				"Fuel Station");
		fuelStationColumn.setCellValueFactory((Callback) new PropertyValueFactory("fuelStationName"));
		fuelStationColumn.setMinWidth(fuelStationColumn.getPrefWidth() * 1.5);
		this.tvHomeFastFuel.getColumns().add(fuelStationColumn);
		final TableColumn<FastFuel, ProductName> fuelTypeColumn = (TableColumn<FastFuel, ProductName>) new TableColumn(
				"Fuel Type");
		fuelTypeColumn.setCellValueFactory((Callback) new PropertyValueFactory("fuelType"));
		fuelTypeColumn.setMinWidth(fuelTypeColumn.getPrefWidth() * 1.15);
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
//		orderIDColumn.setSortType(SortType.DESCENDING);
		this.tvVODetails.getColumns().add(orderIDColumn);
		final TableColumn<HomeFuelOrder, Date> orderTimeColumn = (TableColumn<HomeFuelOrder, Date>) new TableColumn(
				"Time Bought");
		orderTimeColumn.setCellValueFactory((Callback) new PropertyValueFactory("orderTime"));
		orderTimeColumn.setMinWidth(170);
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
		orderShipment.setMinWidth(orderShipment.getPrefWidth() * 2);
		this.tvVODetails.getColumns().add(orderShipment);
		final TableColumn<HomeFuelOrder, Date> orderDueTimeColumn = (TableColumn<HomeFuelOrder, Date>) new TableColumn(
				"Due Time");
		orderDueTimeColumn.setCellValueFactory((Callback) new PropertyValueFactory("dueTime"));
		orderDueTimeColumn.setMinWidth(170);
		this.tvVODetails.getColumns().add(orderDueTimeColumn);
		final TableColumn<HomeFuelOrder, Double> orderPrice = (TableColumn<HomeFuelOrder, Double>) new TableColumn(
				"Total Price");
		orderPrice.setCellValueFactory((Callback) new PropertyValueFactory("finalPrice"));
		this.tvVODetails.getColumns().add(orderPrice);

		Calendar calendar = Calendar.getInstance();
		Date now = new Date();
		calendar.setTime(now);
		this.controller.handleMessageFromClientUI(("fastfuel get " + username + " " + (calendar.get(Calendar.YEAR))
				+ " " + (calendar.get(Calendar.MONTH) + 1)));

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
		this.tfHomeTotal.setText(new DecimalFormat("#.##").format(total));
	}

	/**
	 * fills the tableview in view home fuel orders pane
	 * 
	 * @param homeFuelOrderList
	 */
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

	/**
	 * clear fxml entities as if the window was just entered into
	 */
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
		this.apOHFPurchaseInfo.setDisable(false);
		this.apOHFOrderDetails.setDisable(true);
	}

	/*********************** key listeners ***********************/

	@FXML
	void tabRbOHFShipment1(KeyEvent event) {
		switch (event.getCode()) {
		case TAB:
			this.rbOHFShipment2.setSelected(true);
			break;
		default:
			break;
		}
	}

	@FXML
	void tabRbOHFShipment2(KeyEvent event) {
		switch (event.getCode()) {
		case TAB:
			this.rbOHFShipment1.setSelected(true);
			break;
		default:
			break;
		}
	}

}
