package guiClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import client.SupplierController;
import entities.FuelStationOrder;
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
	private TableView<itemInTable> tvASFSODetails;
	
	private FuelStationOrder[] fso;
	
	@FXML
	void initialize() {
		this.visibleNow = this.homePane;
		this.controller = SupplierController.getInstance();
		this.controller.setCurrentWindow(this);
		 SupplierController.getInstance().getFuelStationOrder(); 
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
		if(lastMsgFromServer instanceof FuelStationOrder[]) {
			fso = (FuelStationOrder[])lastMsgFromServer;
		}
	}

	@FXML
	@SuppressWarnings({ "unchecked", "rawtypes" })
	void openApproveSupplied(ActionEvent event) {
		List<Integer> pisList = new ArrayList<>(); 
		this.homePane.setVisible(false);
		if(cobASFSOFuelStationID.getItems().size() == 0) {
			for(FuelStationOrder item: fso) {
				  pisList.add(item.getProductInStaionID()); }
				  
				  cobASFSOFuelStationID.getItems().addAll(pisList);
		}

		final TableColumn<itemInTable, Integer> orderIDColumn = new TableColumn<itemInTable, Integer>("orderID");
		orderIDColumn.setCellValueFactory(new PropertyValueFactory("orderID"));
		final TableColumn<itemInTable, Date> orderTimeColumn = new TableColumn<itemInTable, Date>("orderTime");
		orderTimeColumn.setCellValueFactory(new PropertyValueFactory("orderTime"));
		final TableColumn<itemInTable, Integer> productNameColumn = new TableColumn<itemInTable, Integer>("productName");
		productNameColumn.setCellValueFactory(new PropertyValueFactory("productName"));
		final TableColumn<itemInTable, Double> amountColumn = new TableColumn<itemInTable, Double>("amount");
		amountColumn.setCellValueFactory(new PropertyValueFactory("amount"));
		final TableColumn<itemInTable, Double> totalPriceColumn = new TableColumn<itemInTable, Double>("totalPrice");
		totalPriceColumn.setCellValueFactory(new PropertyValueFactory("totalPrice"));
		
		tvASFSODetails.getColumns().setAll(orderIDColumn,orderTimeColumn,productNameColumn
				,amountColumn,totalPriceColumn);
		approveSuppliedPane.setVisible(true);
	}
	
	
	  @FXML
	  @Override void openHome(ActionEvent event) { 
		  approveSuppliedPane.setVisible(false);
		  super.openHome(event);
	  }
	 
	
	@FXML
    void btnASFSOShowPressed(ActionEvent event) {
		Integer cur = cobASFSOFuelStationID.getValue();
		tvASFSODetails.getItems().clear();
		for (FuelStationOrder item: fso) {
			if(item != null && cur.equals(item.getProductInStaionID())) {
				tvASFSODetails.getItems().add(new itemInTable(item.getOrdersID(), item.getOrderTime(),item.getProductInStaionID(), item.getAmountBought(), item.getAmountBought()*2));
			}
		}

    }
	public class itemInTable{
		Integer orderID;
		Date orderTime;
		Integer productName;
		Double amount;
		Double totalPrice;
		public itemInTable(int orderID, Date orderTime, int productName, double amount, double totalPrice) {
			super();
			this.orderID = orderID;
			this.orderTime = orderTime;
			this.productName = productName;
			this.amount = amount;
			this.totalPrice = totalPrice;
		}
		public Integer getOrderID() {
			return orderID;
		}
		public void setOrderID(Integer orderID) {
			this.orderID = orderID;
		}
		public Date getOrderTime() {
			return orderTime;
		}
		public void setOrderTime(Date orderTime) {
			this.orderTime = orderTime;
		}
		public Integer getProductName() {
			return productName;
		}
		public void setProductName(Integer productName) {
			this.productName = productName;
		}
		public Double getAmount() {
			return amount;
		}
		public void setAmount(Double amount) {
			this.amount = amount;
		}
		public Double getTotalPrice() {
			return totalPrice;
		}
		public void setTotalPrice(Double totalPrice) {
			this.totalPrice = totalPrice;
		}
	}
	
	
	   @FXML
	    void btnASFSOApproveSPressed(ActionEvent event) {
		   int approvedId = tvASFSODetails.getSelectionModel().getSelectedItem().orderID;
		   tvASFSODetails.getItems().remove(tvASFSODetails.getSelectionModel().getSelectedItem());
		   for (int i=0 ; i < fso.length ; i++) {
			  if(fso[i].getOrdersID() == approvedId) {
				  fso[i] = null;
			  }
		   }
		   SupplierController.getInstance().approveFuelStationOrder(approvedId);
	    }
}
