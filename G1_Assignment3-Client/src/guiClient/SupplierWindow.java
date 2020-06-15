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

/**
 * supplier windows contains screans
 * <ol>
 * <li> homePane 
 * <li> approveSuppliedPane
 * </ol>
 * @author Leptop-Pc
 * @see guiClient.EmployeeWindow
 */
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
	/**
	 * This Table view display on AnchorPane approveSuppliedPan.
	 * @see entities.SupplierItemInTable
	 */
	@FXML
	private TableView<SupplierItemInTable> tvASFSODetails;
	/**
	 *  This arrays sets when the show button pressed in AnchorPane approveSuppliedPane
	 */
	private SupplierItemInTable[] siit;
	/**
	 *  This arrays sets when you enter to AnchorPane approveSuppliedPane
	 */
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
	
	/**
	 * This method adaptation of the parent method for Supplier
	 * @see guiClient.EmployeeWindow#callAfterMessage
	 */
	@Override
	public void callAfterMessage(Object lastMsgFromServer) {
		super.callAfterMessage(lastMsgFromServer);
		//vlad added 
		if(lastMsgFromServer instanceof SupplierItemInTable[]) {
			siit = (SupplierItemInTable[])lastMsgFromServer;
		}
		if(lastMsgFromServer instanceof Integer[]) {
			fuelStationIDs = (Integer[])lastMsgFromServer;
		}
	}
	/**
	 * This method sets the AnchorPane approveSuppliedPan.
	 * <br/>
	 * The method sets:
	 * <ol>
	 * <li> fuelStationIDs pull the info from server
	 * <li> tvASFSODetails tableView set cloumn 
	 * <li> approveSuppliedPane display the anchor
	 * </ol>
	 * @param event press on button to enter the anchor
	 */
	@FXML
	@SuppressWarnings({ "unchecked", "rawtypes" })
	void openApproveSupplied(ActionEvent event) {
		this.homePane.setVisible(false);
		SupplierController.getInstance().getFuelStationWithOrder();
		cobASFSOFuelStationID.getItems().clear();
		tvASFSODetails.getItems().clear();
		List<Integer> fsList = Arrays.asList(fuelStationIDs);
		cobASFSOFuelStationID.getItems().addAll(fsList);

		final TableColumn<SupplierItemInTable, Integer> orderIDColumn = new TableColumn<SupplierItemInTable, Integer>("Order ID");
		orderIDColumn.setCellValueFactory(new PropertyValueFactory("orderID"));
		orderIDColumn.setMinWidth(orderIDColumn.getPrefWidth());
		final TableColumn<SupplierItemInTable, Date> orderTimeColumn = new TableColumn<SupplierItemInTable, Date>("Order Time");
		orderTimeColumn.setCellValueFactory(new PropertyValueFactory("orderTime"));
		orderTimeColumn.setMinWidth(orderTimeColumn.getPrefWidth());
		final TableColumn<SupplierItemInTable, Integer> productNameColumn = new TableColumn<SupplierItemInTable, Integer>("Product Name");
		productNameColumn.setCellValueFactory(new PropertyValueFactory("productName"));
		productNameColumn.setPrefWidth(100);
		productNameColumn.setMinWidth(productNameColumn.getPrefWidth());
		final TableColumn<SupplierItemInTable, Double> amountColumn = new TableColumn<SupplierItemInTable, Double>("Amount");
		amountColumn.setCellValueFactory(new PropertyValueFactory("amount"));
		amountColumn.setMinWidth(amountColumn.getPrefWidth());
		final TableColumn<SupplierItemInTable, String> AddressColumn = new TableColumn<SupplierItemInTable, String>("Address");
		AddressColumn.setCellValueFactory(new PropertyValueFactory("address"));
		AddressColumn.setMinWidth(AddressColumn.getPrefWidth());
		
		tvASFSODetails.getColumns().setAll(orderIDColumn,orderTimeColumn,productNameColumn,amountColumn,AddressColumn);
		approveSuppliedPane.setVisible(true);
	}
	
	
	  @FXML
	  @Override 
	  void openHome(ActionEvent event) { 
		  approveSuppliedPane.setVisible(false);
		  super.openHome(event);
	  }
	 
	/**
	 * This method will show new fuel station order
	 * @param event button btnASFSOShow pressed
	 */
	@FXML
    void btnASFSOShowPressed(ActionEvent event) {
		Integer cur = cobASFSOFuelStationID.getValue();
		if(cur != null) {
			SupplierController.getInstance().getSupplierItemInTable(cur); 
			tvASFSODetails.getItems().clear();
			tvASFSODetails.getItems().addAll(siit);
		}
		
    }
	/**
	 * This method will approve the select fuel station order
	 * @param event btnASFSOApprove pressed
	 */
	   @FXML
	    void btnASFSOApproveSPressed(ActionEvent event) {
		   SupplierItemInTable selectedItem = tvASFSODetails.getSelectionModel().getSelectedItem();
		   if(selectedItem != null) {
			   int approvedId = selectedItem.getOrderID();
			   double amount = selectedItem.getAmount();
			   tvASFSODetails.getItems().remove(tvASFSODetails.getSelectionModel().getSelectedItem());
			   SupplierController.getInstance().approveFuelStationOrder(approvedId,amount);
		   }
		  
	    }
	@Override
	public void clearFields() {
		// TODO Auto-generated method stub

	}

}
