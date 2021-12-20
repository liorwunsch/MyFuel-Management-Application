package guiClient;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import client.SupplierController;
import entities.SupplierItemInTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
 * <li>homePane
 * <li>approveSuppliedPane
 * </ol>
 * 
 * @version Final
 * @author Vlad
 * @see guiClient.EmployeeWindow
 */
public class SupplierWindow extends EmployeeWindow {

	@FXML
	private ToggleGroup one;
	@FXML
	private ToggleButton sidebar_btn0;
	@FXML
	private ToggleButton sidebar_btn1;

	@FXML
	private AnchorPane approveSuppliedPane;
	@FXML
	private Button btnASFSOClear;
	@FXML
	private AnchorPane apASFSO;
	@FXML
	private AnchorPane gpASFSO;
	@FXML
	private Button btnASFSOApprove;
	@FXML
	private ComboBox<Integer> cobASFSOFuelStationID;
	@FXML
	private Button btnASFSOShow;

	/**
	 * This Table view display on AnchorPane approveSuppliedPan.
	 * 
	 * @see entities.SupplierItemInTable
	 */
	@FXML
	private TableView<SupplierItemInTable> tvASFSODetails;

	/**
	 * This arrays sets when the show button pressed in AnchorPane
	 * approveSuppliedPane
	 */
	private SupplierItemInTable[] siit;

	/**
	 * This arrays sets when you enter to AnchorPane approveSuppliedPane
	 */
	private Integer[] fuelStationIDs;

	/**
	 * runs every time this windows goes live
	 */
	@FXML
	void initialize() {
		this.homePane.setVisible(true);
		this.approveSuppliedPane.setVisible(false);
		this.topbar_window_label.setText("Home");
		this.sidebar_btn0.setSelected(true);
		this.visibleNow = this.homePane;
		this.controller = SupplierController.getInstance();
		this.controller.setCurrentWindow(this);
	}

	/**
	 * returns the window entity of this boundary
	 */
	@Override
	public Window getWindow() {
		return this.approveSuppliedPane.getScene().getWindow();
	}

	/**
	 * button listener for home sidebar button
	 * 
	 * @param event
	 */
	void openHome(ActionEvent event) {
		super.openHome(event);
		sidebar_btn0.setSelected(true);
	}

	/**
	 * This method adaptation of the parent method for Supplier
	 * 
	 * @see guiClient.EmployeeWindow#callAfterMessage
	 */
	@Override
	public void callAfterMessage(Object lastMsgFromServer) {
		super.callAfterMessage(lastMsgFromServer);

		if (lastMsgFromServer instanceof SupplierItemInTable[]) {
			siit = (SupplierItemInTable[]) lastMsgFromServer;
		}
		if (lastMsgFromServer instanceof Integer[]) {
			fuelStationIDs = (Integer[]) lastMsgFromServer;
		}
		if (lastMsgFromServer instanceof String) {
			if (this.visibleNow == this.approveSuppliedPane && apASFSO.isDisabled() == false) {
				if (((String) lastMsgFromServer).equals("approveFuelStationOrder success")) {
					this.openConfirmationAlert("Success", "Supply Approved");
					this.requestToLogActivity("approved supply of fuel station order");
				} else if (((String) lastMsgFromServer).equals("approveFuelStationOrder fail")) {
					this.openErrorAlert("Error", "Supply Approval Failed");
				}
			}
		}
	}

	/**
	 * This method sets the AnchorPane approveSuppliedPan. The method sets:
	 * <ol>
	 * <li>fuelStationIDs pull the info from server
	 * <li>tvASFSODetails tableView set cloumn
	 * <li>approveSuppliedPane display the anchor
	 * </ol>
	 * 
	 * @param event press on button to enter the anchor
	 */
	@FXML
	void openApproveSupplied(ActionEvent event) {
		this.homePane.setVisible(false);
		approveSuppliedPane.setVisible(true);
		visibleNow = approveSuppliedPane;
		sidebar_btn1.setSelected(true);
		clearFields();
	}

	/**
	 * This method will show new fuel station order
	 * 
	 * @param event button btnASFSOShow pressed
	 */
	@FXML
	void btnASFSOShowPressed(ActionEvent event) {
		Integer cur = cobASFSOFuelStationID.getValue();
		if (cur != null) {
			SupplierController.getInstance().getSupplierItemInTable(cur);
			gpASFSO.setDisable(true);
			apASFSO.setDisable(false);

			final ObservableList<SupplierItemInTable> list = FXCollections.observableArrayList();
			for (int i = 0; i < this.tvASFSODetails.getItems().size(); ++i)
				this.tvASFSODetails.getItems().clear();
			for (SupplierItemInTable row : siit)
				list.add(row);
			this.tvASFSODetails.setItems(list);

		} else {
			openErrorAlert("Error", "No Fuel Station ID Chosen");
		}
	}

	/**
	 * This method will approve the select fuel station order
	 * 
	 * @param event btnASFSOApprove pressed
	 */
	@FXML
	void btnASFSOApproveSPressed(ActionEvent event) {
		SupplierItemInTable selectedItem = tvASFSODetails.getSelectionModel().getSelectedItem();
		if (selectedItem != null) {
			int approvedId = selectedItem.getOrderID();
			double amount = selectedItem.getAmount();
			tvASFSODetails.getItems().remove(tvASFSODetails.getSelectionModel().getSelectedItem());
			SupplierController.getInstance().approveFuelStationOrder(approvedId, amount);

			if (tvASFSODetails.getItems().isEmpty()) {
				openConfirmationAlert("Station Orders", "There Are No Station Orders Pending");
				clearFields();
			}

		} else {
			openErrorAlert("Error", "No Order Selected From The Table");
		}
	}

	@FXML
	void btnASFSOClearPressed(ActionEvent event) {
		clearFields();
	}

	/**
	 * clear fxml entities as if the window was just entered into
	 */
	@Override
	public void clearFields() {
		cobASFSOFuelStationID.getItems().clear();
		SupplierController.getInstance().getFuelStationsWithOrdersPending(this.username);
		if (fuelStationIDs.length != 0) {
			List<Integer> fsList = Arrays.asList(fuelStationIDs);
			cobASFSOFuelStationID.getItems().addAll(fsList);

		}
//		else {
//			openConfirmationAlert("Station orders", "There Are No Orders");
//		}

		gpASFSO.setDisable(false);
		apASFSO.setDisable(true);

		for (int i = 0; i < this.tvASFSODetails.getItems().size(); ++i)
			this.tvASFSODetails.getItems().clear();
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setUserComponents(String username) {
		super.setUserComponents(username);

		tvASFSODetails.getColumns().clear();
		final TableColumn<SupplierItemInTable, Integer> orderIDColumn = new TableColumn<SupplierItemInTable, Integer>(
				"Order ID");
		orderIDColumn.setCellValueFactory(new PropertyValueFactory("orderID"));
		orderIDColumn.setMinWidth(orderIDColumn.getPrefWidth());
		final TableColumn<SupplierItemInTable, Date> orderTimeColumn = new TableColumn<SupplierItemInTable, Date>(
				"Order Time");
		orderTimeColumn.setCellValueFactory(new PropertyValueFactory("orderTime"));
		orderTimeColumn.setPrefWidth(200);
		orderTimeColumn.setMinWidth(orderTimeColumn.getPrefWidth());
		final TableColumn<SupplierItemInTable, Integer> productNameColumn = new TableColumn<SupplierItemInTable, Integer>(
				"Product Name");
		productNameColumn.setCellValueFactory(new PropertyValueFactory("productName"));
		productNameColumn.setPrefWidth(100);
		productNameColumn.setMinWidth(productNameColumn.getPrefWidth());
		final TableColumn<SupplierItemInTable, Double> amountColumn = new TableColumn<SupplierItemInTable, Double>(
				"Amount");
		amountColumn.setCellValueFactory(new PropertyValueFactory("amount"));
		amountColumn.setMinWidth(amountColumn.getPrefWidth());
		final TableColumn<SupplierItemInTable, String> addressColumn = new TableColumn<SupplierItemInTable, String>(
				"Address");
		addressColumn.setCellValueFactory(new PropertyValueFactory("address"));
		addressColumn.setPrefWidth(200);
		addressColumn.setMinWidth(addressColumn.getPrefWidth());

		tvASFSODetails.getColumns().setAll(orderIDColumn, orderTimeColumn, productNameColumn, amountColumn,
				addressColumn);
	}

}
