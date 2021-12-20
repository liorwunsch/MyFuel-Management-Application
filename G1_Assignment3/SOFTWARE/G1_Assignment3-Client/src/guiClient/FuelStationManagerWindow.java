package guiClient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.TreeSet;

import client.FuelStationManagerController;
import entities.FuelStationOrder;
import entities.Notification;
import entities.ProductInStation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;
import javafx.util.Callback;

/**
 * boundary for fuel station manager window
 * 
 * @version Final
 * @author Liad
 */
public class FuelStationManagerWindow extends QuarterlyReportWindow {

	@FXML
	private ToggleButton sidebar_btn0;
	@FXML
	private ToggleButton sidebar_btn1;
	@FXML
	private ToggleButton sidebar_btn2;
	@FXML
	private ToggleButton sidebar_btn3;

	@FXML
	private AnchorPane thresholdPane;
	@FXML
	private CheckBox cbUTGasoline;
	@FXML
	private CheckBox cbUTDiesel;
	@FXML
	private CheckBox cbUTMotorbike;
	@FXML
	private TextField tfUTGasoline2;
	@FXML
	private TextField tfUTDiesel2;
	@FXML
	private TextField tfUTMotorbike2;
	@FXML
	private TextField tfUTGasoline1;
	@FXML
	private TextField tfUTMotorbike1;
	@FXML
	private TextField tfUTDiesel1;
	@FXML
	private TextField tfUTGasoline11;
	@FXML
	private TextField tfUTMotorbike11;
	@FXML
	private TextField tfUTDiesel11;
	@FXML
	private Button btnUTUpdate;

	@FXML
	private ComboBox<Integer> cobASOOrderId;
	@FXML
	private TextField tfASOName;
	@FXML
	private TextField tfASOAmount;
	@FXML
	private TextField tfASOTime;
	@FXML
	private TextField tfASOAddress;
	@FXML
	private TextField tfASOThreshold;
	@FXML
	private TextField tfASOInStock;

	@FXML
	private AnchorPane notificationPane;
	@FXML
	private Button btnHomeNotification;
	@FXML
	private Button btnNotifyClose;
	@FXML
	private TableView<Notification> tvHomeNotifytable;
	@FXML
	private Button btnNotifyDissmiss;

	/**
	 * called after server returned a message/object to the client
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void callAfterMessage(Object lastMsgFromServer) {
		super.callAfterMessage(lastMsgFromServer);

		if (lastMsgFromServer instanceof Object[]) {
			Object[] objArr = (Object[]) lastMsgFromServer;
			if (objArr.length == 0) {
				System.out.println("callAfterMessage: object[] empty");

			} else if (objArr[0] instanceof String) {
				String str = (String) objArr[0];
				if (str.equals("Unassesd Order IDs")) {
					TreeSet<Integer> unassedOrderList = (TreeSet<Integer>) objArr[1];
					fillUnassessedOrderToComboBox(unassedOrderList);

				} else if (str.equals("No Unassessd Orders")) {
					this.cobASOOrderId.getItems().removeAll((Collection<?>) this.cobASOOrderId.getItems());
					this.openConfirmationAlert("Information",
							"There are no orders waiting for assessment in your station.");

				} else if (str.equals("Quarter Report")) {
					openQuarterlyReport2(objArr);
					requestToLogActivity("Genarated generated quarter report Year: " + cobGQRYear.getValue()
							+ " Quarter: " + cobGQRQuarter.getValue() + ".");

				} else if (str.equals("Undismissed Notifications")) {
					ArrayList<Notification> notificationsList = (ArrayList<Notification>) objArr[1];
					fillNotificationsTable(notificationsList);

				} else if (str.equals("No Undismissed Notifications")) {
					for (int i = 0; i < tvHomeNotifytable.getItems().size(); i++) {
						tvHomeNotifytable.getItems().clear();
					}

				} else if (str.equals("Station Products In Orders")) {
					ProductInStation productInStation = (ProductInStation) objArr[1];
					FuelStationOrder fuelStationOrder = (FuelStationOrder) objArr[2];
					fillUnassessedOrderData(productInStation, fuelStationOrder);

				} else if (str.equals("products In Station")) {
					ArrayList<ProductInStation> productInStationList = (ArrayList<ProductInStation>) objArr[1];
					fillUpdateThreshold(productInStationList);

				} else if (str.equals("No Product In Station")) {
					this.openConfirmationAlert("Information", "There is no products in your station.");
				}
			}
		}

		else if (lastMsgFromServer instanceof Boolean) {
			if (visibleNow == thresholdPane) {
				Boolean bool = (Boolean) lastMsgFromServer;
				if (bool.booleanValue() == true) {
					requestToLogActivity("Updated Threshold");
					this.openConfirmationAlert("Success", "Threshold updated successfully");
					initializeThresholdPane();
				} else {
					this.openErrorAlert("Error", "Threshold must be less than capacity");
				}

			} else if (visibleNow == assessPane) {
				int orderID = cobASOOrderId.getValue();
				requestToLogActivity("Confirmed unassessed order No." + orderID);
				this.openConfirmationAlert("Success", "Order: " + orderID + " Approved, Assessment completed!");
				initializeAssessPane();

			} else if (visibleNow == declineOrderPane) {
				int orderID = cobASOOrderId.getValue();
				requestToLogActivity("Declined unassessed order No." + orderID);
				mainBorderPane.setDisable(false);
				declineOrderPane.setVisible(false);
				this.openConfirmationAlert("Success", "Order: " + orderID + " Declined, Assessment completed!");
				visibleNow = assessPane;
				initializeAssessPane();

			} else if (visibleNow == notificationPane) {
				requestToLogActivity("Notification Dismissed");
				this.openConfirmationAlert("Success", "Notification Dismissed");
				getNotifications();
			}
		}
	}

	/**
	 * returns the window entity of this boundary
	 */
	@Override
	public Window getWindow() {
		return this.assessPane.getScene().getWindow();
	}

	/**********************************************************************
	 * open methods
	 **********************************************************************/

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
	 * click notifications in Home
	 * 
	 * @param event
	 */
	@FXML
	void openNotification(ActionEvent event) {
		mainBorderPane.setDisable(true);
		notificationPane.setVisible(true);
		visibleNow = notificationPane;
		btnNotifyClose.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mainBorderPane.setDisable(false);
				notificationPane.setVisible(false);
				visibleNow = homePane;
			}
		});
		getNotifications();
	}

	/**
	 * click on side button Update Threshold
	 * 
	 * @param event
	 */
	@FXML
	void openUpdateThreshold(ActionEvent event) {
		initializeThresholdPane();
		visibleNow.setVisible(false);
		thresholdPane.setVisible(true);
		visibleNow = thresholdPane;
		topbar_window_label.setText("Update Threshold");
		// click on check boxes
		cbUTGasoline.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				boolean cbChecked = cbUTGasoline.isSelected();
				checkOtherCheckBoxes();
				if (!cbChecked)
					tfUTGasoline2.clear();
				tfUTGasoline2.setDisable(!cbChecked);
			}
		});
		cbUTDiesel.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				boolean cbChecked = cbUTDiesel.isSelected();
				checkOtherCheckBoxes();
				if (!cbChecked)
					tfUTDiesel2.clear();
				tfUTDiesel2.setDisable(!cbChecked);
			}
		});
		cbUTMotorbike.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				boolean cbChecked = cbUTMotorbike.isSelected();
				checkOtherCheckBoxes();
				if (!cbChecked)
					tfUTMotorbike2.clear();
				tfUTMotorbike2.setDisable(!cbChecked);
			}
		});
	}

	/**
	 * click on side button Assess Station Orders
	 * 
	 * @param event
	 */
	@FXML
	void openAssessStationOrders(ActionEvent event) {
		initializeAssessPane();
		visibleNow.setVisible(false);
		assessPane.setVisible(true);
		visibleNow = assessPane;
		topbar_window_label.setText("Assess Station Orders");
		// Decline order,secondary window pop up
		btnASODecline.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mainBorderPane.setDisable(true);
				declineOrderPane.setVisible(true);
				visibleNow = declineOrderPane;
				taDOReasons.clear();
				btnDOCancel.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						declineOrderPane.setVisible(false);
						mainBorderPane.setDisable(false);
						visibleNow = assessPane;
					}
				});
				btnDOOk.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						int orderID = cobASOOrderId.getValue();
						String declineReason = taDOReasons.getText();
						if (declineReason.isEmpty()) {
							openErrorAlert("Input incomplete",
									"Please write the reason you wish to decline your order.");
							return;
						} else if (declineReason.length() > 300) {
							openErrorAlert("Error", "Decline Reason Not Valid, Max 300 Characters");
							return;
						} else {
							String message = "update doneAssessmentOrder" + "_" + username + "_" + orderID + " "
									+ "false" + " " + declineReason;
							controller.handleMessageFromClientUI(message);
						}
					}
				});
			}
		});
		// Order approval,alert pop up
		btnASOConfirm.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("aprroved");
				int orderID = cobASOOrderId.getValue();
				String message = "update doneAssessmentOrder" + "_" + username + "_" + orderID + " " + "true" + " Nan";
				controller.handleMessageFromClientUI(message);
			}
		});
		btnASOShowOrder.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (cobASOOrderId.getItems().size() <= 0)
					return;
				else {
					btnASOConfirm.setDisable(false);
					btnASODecline.setDisable(false);
					int orderID = cobASOOrderId.getValue();
					String message = "get StationProductInOrder" + "_" + username + "_" + orderID;
					controller.handleMessageFromClientUI(message);
				}
			}
		});
	}

	/**
	 * click on side button Generate Quarterly Report
	 * 
	 * @param event
	 */
	@FXML
	void openGenerateQuarterlyReport(ActionEvent event) {
		openPaneOfViewQuarterlyReport();
		sidebar_btn3.setSelected(true);
		topbar_window_label.setText("Generate Quarterly Report");
	}

	/**
	 * click on View button in Generate Quarterly Report
	 * 
	 * @param event
	 */
	@FXML
	void openQuarterlyReport(ActionEvent event) { // click on view report
		int selectedYear = Integer.parseInt(cobGQRYear.getValue());
		int selectedQuarter = cobGQRQuarter.getValue();
		if (openPaneOfQuarterlyReports(selectedYear, selectedQuarter) == true) {
			String message = "generate QuarterReport" + "_" + this.username + "_" + selectedYear + " "
					+ selectedQuarter;
			controller.handleMessageFromClientUI(message);
		}
	}

	/***************************************************
	 * fill data methods
	 ****************************************************/

	/**
	 * fill Assess Station Orders combo box with orderID
	 * 
	 * @param list
	 */
	private void fillUnassessedOrderToComboBox(TreeSet<Integer> list) {
		this.cobASOOrderId.getItems().removeAll((Collection<?>) this.cobASOOrderId.getItems());
		this.cobASOOrderId.getItems().addAll(list);
		this.cobASOOrderId.setValue(list.pollFirst());
	}

	/**
	 * when click on View Order button fill Assess Station Orders textfields with
	 * orderID data
	 * 
	 * @param productInStation
	 * @param fuelStationOrder
	 */
	private void fillUnassessedOrderData(ProductInStation productInStation, FuelStationOrder fuelStationOrder) {
		tfASOName.setText(productInStation.getProductName().toString());
		tfASOThreshold.setText(String.valueOf(productInStation.getThreshold()));
		tfASOInStock.setText(String.valueOf(productInStation.getCapacity()));
		tfASOAmount.setText(String.valueOf(fuelStationOrder.getAmountBought()));
		tfASOAddress.setText(fuelStationOrder.getAddress());
		tfASOTime.setText(converDateToFormattedString(fuelStationOrder.getOrderTime()));
	}

	/**
	 * fill UpdateThreshold textfields with threshold data
	 * 
	 * @param productList
	 */
	private void fillUpdateThreshold(ArrayList<ProductInStation> productList) {
		for (ProductInStation product : productList) {
			if (product.getProductName().toString().equals("Gasoline")) {
				tfUTGasoline1.setText(product.getThreshold() + "");
				tfUTGasoline11.setText(product.getCapacity() + "");

			} else if (product.getProductName().toString().equals("Diesel")) {
				tfUTDiesel1.setText(product.getThreshold() + "");
				tfUTDiesel11.setText(product.getCapacity() + "");

			} else if (product.getProductName().toString().equals("Motorbike Fuel")) {
				tfUTMotorbike1.setText(product.getThreshold() + "");
				tfUTMotorbike11.setText(product.getCapacity() + "");
			}
		}
	}

	/**
	 * fill Notification tableview with notifications data
	 * 
	 * @param list
	 */
	private void fillNotificationsTable(ArrayList<Notification> list) {
		for (int i = 0; i < tvHomeNotifytable.getItems().size(); i++) {
			tvHomeNotifytable.getItems().clear();
		}
		ObservableList<Notification> rowsList = FXCollections.observableArrayList();
		for (Notification notif : list) {
			rowsList.add(notif);
		}
		tvHomeNotifytable.setItems(rowsList);
	}

	/**********************************************************************
	 * initialize methods
	 **********************************************************************/

	/**
	 * runs every time this windows goes live
	 */
	@FXML
	void initialize() {
		this.visibleNow = this.homePane;
		this.controller = FuelStationManagerController.getInstance();
		this.controller.setCurrentWindow(this);
		initiallizeNotificationTable();
		initiallizeIncomeReportTables();
		initiallizeOutcomeReportTables();
		initiallizeInventoryReportTables();
		fillQuarterlyReportComboBox();
		sidebar_btn0.setSelected(true);
		tfUTDiesel1.setFocusTraversable(false);
		tfUTDiesel11.setFocusTraversable(false);
		tfUTGasoline1.setFocusTraversable(false);
		tfUTGasoline11.setFocusTraversable(false);
		tfUTMotorbike1.setFocusTraversable(false);
		tfUTMotorbike11.setFocusTraversable(false);
	}

	/**
	 * clears threshold pane
	 */
	private void initializeThresholdPane() {
		sidebar_btn1.setSelected(true);
		tfUTGasoline1.clear();
		tfUTDiesel1.clear();
		tfUTMotorbike1.clear();
		tfUTGasoline11.clear();
		tfUTDiesel11.clear();
		tfUTMotorbike11.clear();

		tfUTGasoline2.clear();
		tfUTMotorbike2.clear();
		tfUTDiesel2.clear();
		tfUTGasoline2.setDisable(true);
		tfUTMotorbike2.setDisable(true);
		tfUTDiesel2.setDisable(true);

		cbUTGasoline.setSelected(false);
		cbUTDiesel.setSelected(false);
		cbUTMotorbike.setSelected(false);
		btnUTUpdate.setDisable(true);
		String message = "get ProductThreshold" + "_" + this.username;
		controller.handleMessageFromClientUI(message);
	}

	/**
	 * clears assess pane
	 */
	private void initializeAssessPane() {
		sidebar_btn2.setSelected(true);
		btnASOConfirm.setDisable(true);
		btnASODecline.setDisable(true);
		tfASOName.clear();
		tfASOAmount.clear();
		tfASOTime.clear();
		tfASOAddress.clear();
		tfASOThreshold.clear();
		tfASOInStock.clear();
		String message = "get unassesedOrdersID" + "_" + this.username;
		controller.handleMessageFromClientUI(message);
	}

	/**
	 * Initialize table view columns
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initiallizeNotificationTable() {
		final TableColumn<Notification, Integer> notificationIDColumn = (TableColumn<Notification, Integer>) new TableColumn(
				"Notification ID");
		notificationIDColumn.setCellValueFactory((Callback) new PropertyValueFactory("notificationID"));
		notificationIDColumn.setPrefWidth(0);
		final TableColumn<Notification, Date> timeColumn = (TableColumn<Notification, Date>) new TableColumn("Time");
		timeColumn.setCellValueFactory((Callback) new PropertyValueFactory("dateCreated"));
		timeColumn.setPrefWidth(170);
		this.tvHomeNotifytable.getColumns().add(timeColumn);
		final TableColumn<Notification, String> messageColumn = (TableColumn<Notification, String>) new TableColumn(
				"Message");
		messageColumn.setCellValueFactory((Callback) new PropertyValueFactory("message"));
		messageColumn.setPrefWidth(250);
		this.tvHomeNotifytable.getColumns().add(messageColumn);
	}

	/**********************************************************************
	 * update/refresh methods
	 **********************************************************************/

	/**
	 * method check if the numbers in updateThreshold textfield is ok and send
	 * update to server
	 * 
	 * @param event
	 */
	@FXML
	private void updateThresholdData(ActionEvent event) {
		Double newGasoline, newDiesel, newMotorbike;
		String func = "";
		String appendedError = "";
		try {
			if (!tfUTGasoline2.isDisable()) {
				appendedError = "Gasoline";
				newGasoline = Double.parseDouble(tfUTGasoline2.getText());
				if (newGasoline <= 0)
					throw new java.lang.NumberFormatException();
				func += "Gasoline " + newGasoline + " ";
			}
			if (!tfUTDiesel2.isDisable()) {
				appendedError = "Diesel";
				newDiesel = Double.parseDouble(tfUTDiesel2.getText());
				if (newDiesel <= 0)
					throw new java.lang.NumberFormatException();
				func += "Diesel " + newDiesel + " ";

			}
			if (!tfUTMotorbike2.isDisable()) {
				appendedError = "Motorbike Fuel";
				newMotorbike = Double.parseDouble(tfUTMotorbike2.getText());
				if (newMotorbike <= 0)
					throw new java.lang.NumberFormatException();
				func += "Motorbike-Fuel " + newMotorbike + " ";

			}
		} catch (java.lang.NumberFormatException e) {
			this.openErrorAlert("Wrong Input", appendedError + " Must Be a Positive Number");
			return;
		}
		if (func.isEmpty())
			return;
		String message = "update Threshold" + "_" + this.username + "_" + func;
		controller.handleMessageFromClientUI(message);
	}

	/**
	 * get notification id from the selected row in the table and dismiss it
	 * 
	 * @param event
	 */
	@FXML
	private void updateNotificationData(ActionEvent event) {
		Notification notification = tvHomeNotifytable.getSelectionModel().getSelectedItem();
		if (notification == null)
			return;
		int notificationID = notification.getNotificationID();
		// get notificationID from selected line in notification table
		String message = "updated dismissNotifications" + "_" + this.username + "_" + notificationID;
		controller.handleMessageFromClientUI(message);
	}

	/**********************************************************************
	 * others/helpers methods
	 **********************************************************************/

	/**
	 * if all of the checkboxes is not selected disable update button
	 */
	private void checkOtherCheckBoxes() {// linked to threshold
		if (!cbUTMotorbike.isSelected() && !cbUTDiesel.isSelected() && !cbUTGasoline.isSelected())
			btnUTUpdate.setDisable(true);
		else
			btnUTUpdate.setDisable(false);
	}

	/**
	 * send to server to get notifications
	 */
	private void getNotifications() {
		String message = "get undismissedNotifications" + "_" + username;
		controller.handleMessageFromClientUI(message);
	}

	/**
	 * clear fxml entities as if the window was just entered into
	 */
	@Override
	public void clearFields() {
		cbUTGasoline.setSelected(false);
		cbUTDiesel.setSelected(false);
		cbUTMotorbike.setSelected(false);
		tfUTGasoline2.clear();
		tfUTDiesel2.clear();
		tfUTMotorbike2.clear();
		tfASOName.clear();
		tfUTGasoline1.clear();
		tfUTDiesel1.clear();
		tfUTMotorbike1.clear();
		tfUTGasoline11.clear();
		tfUTDiesel11.clear();
		tfUTMotorbike11.clear();
		cobASOOrderId.getItems().removeAll((Collection<?>) this.cobASOOrderId.getItems());
		tfASOTime.clear();
		tfASOAmount.clear();
		tfASOAddress.clear();
		tfASOInStock.clear();
		tfASOThreshold.clear();
		for (int i = 0; i < tvHomeNotifytable.getItems().size(); i++)
			tvHomeNotifytable.getItems().clear();
		super.clearFields();
	}

	@Override
	public void setUserComponents(String username) {
		super.setUserComponents(username);
		this.controller.handleMessageFromClientUI("checkForQuarterReportNotYetCreated_" + username);
	}

}
