package guiClient;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;

import client.CustomerController;
import entities.FastFuel;
import entities.FastFuelList;
import entities.HomeFuelOrder;
import enums.ProductName;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

	@FXML	private ToggleGroup one;
	@FXML	private ToggleButton sidebar_btn0;
	@FXML	private ToggleButton sidebar_btn1;
	@FXML	private ToggleButton sidebar_btn2;
	
	@FXML	private Label lblHomeMember;
	@FXML	private Label lblHomePayment;
	@FXML	private TableView<FastFuel> tvHomeFastFuel;
	@FXML	private TextField tfHomeTotal;

	@FXML	private AnchorPane orderHomeFuelPane;
	@FXML	private TextField tfOHFAmount1;
	@FXML	private TextField tfOHFAddress;
	@FXML	private TextField tfOHFPrice1;
	@FXML	private Label lblOHFShipmentPrice1;
	@FXML	private Label lblOHFShipmentMul1;
	@FXML	private Label lblOHFShipmentDelivery1;
	@FXML	private Label lblOHFShipmentPrice2;
	@FXML	private Label lblOHFShipmentMul2;
	@FXML	private Label lblOHFShipmentDelivery2;
	@FXML	private ToggleGroup two;
	@FXML	private RadioButton rbOHFShipment1;
	@FXML	private RadioButton rbOHFShipment2;
	@FXML	private Button btnOHFShowPrice;
	@FXML	private TextField tfOHFDate;
	@FXML	private TextField tfOHFFinalPrice;
	@FXML	private TextField tfOHFAmount2;
	@FXML	private TextField tfOHFShipmentReview;
	@FXML	private Button btnOHFConfirm;
	
	@FXML	private AnchorPane viewOrderPane;
	@FXML	private TableView<HomeFuelOrder> tvVODetails;
	
	@FXML	private AnchorPane fastFuelPane;
	@FXML	private Label lblFFPricePerLiter;
	
	@FXML
	void initialize() {
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

	}

	@FXML
	void openViewOrders(ActionEvent event) {

	}
	
	/*************** boundary "logic" - window changes ***************/
	
	@Override
	public void callAfterMessage(Object lastMsgFromServer) {
		super.callAfterMessage(lastMsgFromServer);
		
		if (lastMsgFromServer instanceof FastFuelList) {
			FastFuelList fastFuelList = (FastFuelList) lastMsgFromServer;
			handleGetFastFuelListFromServer(fastFuelList);
		}
		/**
		 * 
		 */
	}
	
	/**
	 * initialized tableview in home of customer only
	 */
	@Override
	@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
	public void setUserComponents(String username) {
		super.setUserComponents(username);
		final TableColumn<FastFuel, Date> timeColumn = (TableColumn<FastFuel, Date>) new TableColumn("Time");
		timeColumn.setCellValueFactory((Callback) new PropertyValueFactory("fastFuelTime"));
		timeColumn.impl_setWidth(170);
		this.tvHomeFastFuel.getColumns().add(timeColumn);
		final TableColumn<FastFuel, String> regPlateColumn = (TableColumn<FastFuel, String>) new TableColumn("Registration Plate");
		regPlateColumn.setCellValueFactory((Callback) new PropertyValueFactory("registrationPlate"));
		this.tvHomeFastFuel.getColumns().add(regPlateColumn);
		final TableColumn<FastFuel, String> fuelStationColumn = (TableColumn<FastFuel, String>) new TableColumn("Fuel Station");
		fuelStationColumn.setCellValueFactory((Callback) new PropertyValueFactory("fuelStationName"));
		this.tvHomeFastFuel.getColumns().add(fuelStationColumn);
		final TableColumn<FastFuel, ProductName> fuelTypeColumn = (TableColumn<FastFuel, ProductName>) new TableColumn("Fuel Type");
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
