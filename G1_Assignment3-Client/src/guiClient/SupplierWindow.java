package guiClient;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import client.SupplierController;
import entities.FuelStationOrder;
import entities.SupplierItemInTable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;

public class SupplierWindow extends EmployeeWindow {

	@FXML
	private ToggleButton sidebar_btn0;

	@FXML
	private ToggleGroup one;

	@FXML
	private ToggleButton sidebar_btn1;

	@FXML
	private AnchorPane approveSuppliedPane;

	@FXML
	private Button btnASFSOApprove;

	@FXML
	private ComboBox<Integer> cobASFSOFuelStationID;

	@FXML
	private Button btnASFSOShow;

	@FXML
	private TableView<SupplierItemInTable> tvASFSODetails;
	
	private SupplierItemInTable[] siit;
	private Integer[] fuelStationIDs;
	
	@FXML
	void initialize() {
		this.visibleNow = this.homePane;
		this.controller = SupplierController.getInstance();
		this.controller.setCurrentWindow(this);
	}

	@Override
	public Window getWindow() {
		return this.approveSuppliedPane.getScene().getWindow();
	}

	@Override
	public void callAfterMessage(Object lastMsgFromServer) {
		super.callAfterMessage(lastMsgFromServer);
		/**
		 * 
		 */
		if(lastMsgFromServer instanceof SupplierItemInTable[]) {
			siit = (SupplierItemInTable[])lastMsgFromServer;
		}
		if(lastMsgFromServer instanceof Integer[]) {
			fuelStationIDs = (Integer[])lastMsgFromServer;
		}
	}

	@FXML
	@SuppressWarnings({ "unchecked", "rawtypes" })
	void openApproveSupplied(ActionEvent event) {
		this.homePane.setVisible(false);
		SupplierController.getInstance().getFuelStationWithOrder();
		cobASFSOFuelStationID.getItems().clear();
		tvASFSODetails.getItems().clear();
		List<Integer> fsList = Arrays.asList(fuelStationIDs);
		cobASFSOFuelStationID.getItems().addAll(fsList);

		final TableColumn<SupplierItemInTable, Integer> orderIDColumn = new TableColumn<SupplierItemInTable, Integer>("orderID");
		orderIDColumn.setCellValueFactory(new PropertyValueFactory("orderID"));
		final TableColumn<SupplierItemInTable, Date> orderTimeColumn = new TableColumn<SupplierItemInTable, Date>("orderTime");
		orderTimeColumn.setCellValueFactory(new PropertyValueFactory("orderTime"));
		final TableColumn<SupplierItemInTable, Integer> productNameColumn = new TableColumn<SupplierItemInTable, Integer>("productName");
		productNameColumn.setCellValueFactory(new PropertyValueFactory("productName"));
		final TableColumn<SupplierItemInTable, Double> amountColumn = new TableColumn<SupplierItemInTable, Double>("amount");
		amountColumn.setCellValueFactory(new PropertyValueFactory("amount"));
		final TableColumn<SupplierItemInTable, String> AddressColumn = new TableColumn<SupplierItemInTable, String>("address");
		AddressColumn.setCellValueFactory(new PropertyValueFactory("address"));
		
		tvASFSODetails.getColumns().setAll(orderIDColumn,orderTimeColumn,productNameColumn,amountColumn,AddressColumn);
		approveSuppliedPane.setVisible(true);
	}
	
	
	  @FXML
	  @Override 
	  void openHome(ActionEvent event) { 
		  approveSuppliedPane.setVisible(false);
		  super.openHome(event);
	  }
	 
	
	@FXML
    void btnASFSOShowPressed(ActionEvent event) {
		Integer cur = cobASFSOFuelStationID.getValue();
		SupplierController.getInstance().getSupplierItemInTable(cur); 
		tvASFSODetails.getItems().clear();
		tvASFSODetails.getItems().addAll(siit);
    }
	
	   @FXML
	    void btnASFSOApproveSPressed(ActionEvent event) {
		   int approvedId = tvASFSODetails.getSelectionModel().getSelectedItem().getOrderID();
		   double amount = tvASFSODetails.getSelectionModel().getSelectedItem().getAmount();
		   tvASFSODetails.getItems().remove(tvASFSODetails.getSelectionModel().getSelectedItem());
		   SupplierController.getInstance().approveFuelStationOrder(approvedId,amount);
	    }
}
