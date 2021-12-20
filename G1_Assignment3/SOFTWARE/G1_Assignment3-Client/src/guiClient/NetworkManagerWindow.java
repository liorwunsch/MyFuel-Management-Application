package guiClient;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

import client.NetworkManagerController;
import entities.MyNetManager;
import entities.PricingModelUpdateRequest;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;

/**
 * 
 * @version Final
 * @author Lior
 *
 */
public class NetworkManagerWindow extends QuarterlyReportWindow {

	@FXML
	private ToggleGroup one;
	@FXML
	private ToggleButton sidebar_btn0;
	@FXML
	private ToggleButton sidebar_btn1;
	@FXML
	private ToggleButton sidebar_btn2;

	@FXML
	private ComboBox<Integer> cobGQRFuelStationID;

	@FXML
	private AnchorPane apAPM;
	@FXML
	private TextField tfAPMPricingName;
	@FXML
	private TextField tfAPMTime;
	@FXML
	private TextField tfAPMcurDiscount;
	@FXML
	private TextField tfAPMnewDiscount;
	@FXML
	private TextArea taAPMdescription;
	@FXML
	private ComboBox<Integer> cobAPMRequestID;

	/**
	 * runs every time this windows goes live
	 */
	@FXML
	void initialize() {
		this.visibleNow = this.homePane;
		this.controller = NetworkManagerController.getInstance();
		this.controller.setCurrentWindow(this);
		initiallizeIncomeReportTables();
		initiallizeOutcomeReportTables();
		initiallizeInventoryReportTables();
		fillQuarterlyReportComboBox();
		sidebar_btn0.setSelected(true);
		// send to server to get fuelstationids and fill cb
		controller.handleMessageFromClientUI("getAllFuelStationIDs");
	}

	/**
	 * returns the window entity of this boundary
	 */
	@Override
	public Window getWindow() {
		return this.assessPane.getScene().getWindow();
	}

	/**
	 * clear fxml entities as if the window was just entered into
	 */
	@Override
	public void clearFields() {
		super.clearFields();
		/*
		 * clear assess pane
		 */
		apAPM.setDisable(true);
		tfAPMPricingName.clear();
		tfAPMTime.clear();
		tfAPMcurDiscount.clear();
		tfAPMnewDiscount.clear();
		taAPMdescription.clear();
	}

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
				if (str.equals("Quarter Report")) {
					openQuarterlyReport2(objArr);
					requestToLogActivity("Viewed generated quarter report of Fuel Station: "
							+ cobGQRFuelStationID.getValue() + " Year: " + cobGQRYear.getValue() + " Quarter: "
							+ cobGQRQuarter.getValue() + ".");
				} else if (str.equals("NonExist Quarter Report")) {
					openErrorAlert("Error", "Quarterly Report Does not Exist");
				}
			}

		} else if (lastMsgFromServer instanceof MyNetManager) {
			Object[] objArr = ((MyNetManager) lastMsgFromServer).getParams();
			if (objArr[0] instanceof ArrayList<?>) {
				String func = (String) objArr[1];
				if (func.equals("fuelStationIDs")) {
					ArrayList<Integer> fuelStationIDs = (ArrayList<Integer>) objArr[0];
					this.cobGQRFuelStationID.getItems().removeAll((Collection<?>) this.cobGQRFuelStationID.getItems());
					this.cobGQRFuelStationID.getItems().addAll(fuelStationIDs);
					if (fuelStationIDs.size() != 0)
						this.cobGQRFuelStationID.setValue(fuelStationIDs.get(0));

				} else if (func.equals("requestIDs")) {
					ArrayList<Integer> requestIDs = (ArrayList<Integer>) objArr[0];
					this.cobAPMRequestID.getItems().removeAll((Collection<?>) this.cobAPMRequestID.getItems());
					this.cobAPMRequestID.getItems().addAll(requestIDs);
					if (requestIDs.size() != 0)
						this.cobAPMRequestID.setValue(requestIDs.get(0));
				}

			} else if (objArr[0] instanceof String) {
				String str = (String) objArr[0];
				if (str.equals("Request Decline Success")) {
					this.openConfirmationAlert("Success", "Request Declined Successfully");
					this.requestToLogActivity(
							"declined pricing model update request " + this.cobAPMRequestID.getValue());
					clearFields();
					this.controller.handleMessageFromClientUI("getAllUnAssessedRequests");
					declineOrderPane.setVisible(false);
					mainBorderPane.setDisable(false);
					visibleNow = assessPane;

				} else if (str.equals("Request Approve Success")) {
					this.openConfirmationAlert("Success", "Request Approved\nDiscount Updated");
					this.requestToLogActivity(
							"approved pricing model update request " + this.cobAPMRequestID.getValue());
					clearFields();
					this.controller.handleMessageFromClientUI("getAllUnAssessedRequests");
				}

			} else if (objArr[0] instanceof PricingModelUpdateRequest) {
				PricingModelUpdateRequest pmur = (PricingModelUpdateRequest) objArr[0];
				double defaultDiscount = (Double) objArr[1];
				String description = (String) objArr[2];
				tfAPMPricingName.setText(pmur.getPricingModelName().toString());
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				tfAPMTime.setText(formatter.format(pmur.getRequestDate()));
				taAPMdescription.setText(description);

				DecimalFormat dec = new DecimalFormat("#0.00");
				tfAPMcurDiscount.setText("" + dec.format(defaultDiscount * 100));
				tfAPMnewDiscount.setText(pmur.getRequestedDiscount().toString());
				this.apAPM.setDisable(false);
			}
		}
	}

	/*********************** button listeners ***********************/

	/**
	 * assessrequestspane open
	 * 
	 * @param event
	 */
	@FXML
	void openAssessPane(ActionEvent event) {
		clearFields();

		this.controller.handleMessageFromClientUI("getAllUnAssessedRequests");
		visibleNow.setVisible(false);
		assessPane.setVisible(true);
		visibleNow = assessPane;
		topbar_window_label.setText("Assess Pricing Model Update Request");
		sidebar_btn1.setSelected(true);

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
						int requestID = cobAPMRequestID.getValue();
						String declineReason = taDOReasons.getText();
						if (declineReason.isEmpty()) {
							openErrorAlert("Input incomplete",
									"Please write the reason you wish to decline the request.");
							return;

						} else if (declineReason.length() > 300) {
							openErrorAlert("Error", "Decline Reason Not Valid, Max 300 Characters");
							return;

						} else {
							controller
									.handleMessageFromClientUI("setRequestDeclined " + requestID + " " + declineReason);
						}
					}
				});
			}
		});

		btnASOConfirm.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				int requestID = cobAPMRequestID.getValue();
				controller.handleMessageFromClientUI("setRequestApproved " + requestID);
			}
		});

		btnASOShowOrder.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (cobAPMRequestID.getItems().size() <= 0) {
					openErrorAlert("Error", "No Request Chosen");
					return;

				} else {
					int requestID = cobAPMRequestID.getValue();
					controller.handleMessageFromClientUI("getRequestDetails " + requestID);
				}
			}
		});
	}

	/**
	 * generatequarterlyreportpane open
	 * 
	 * @param event
	 */
	@FXML
	void openGenerateQuarterlyReport(ActionEvent event) {
		clearFields();
		openPaneOfViewQuarterlyReport();
		sidebar_btn2.setSelected(true);
		topbar_window_label.setText("View Quarterly Report");
	}

	/**
	 * generatequarterlyreportpane show quarterly report
	 * 
	 * @param event
	 */
	@FXML
	void openQuarterlyReport(ActionEvent event) {
		int selectedYear = Integer.parseInt(cobGQRYear.getValue());
		int selectedQuarter = cobGQRQuarter.getValue();
		int selectedFuelStationID = this.cobGQRFuelStationID.getValue();
		/*
		 * selectedFuelStationID checks
		 */
		if (openPaneOfQuarterlyReports(selectedYear, selectedQuarter) == true) {
			String message = "view QuarterReport" + "_" + selectedFuelStationID + "_" + selectedYear + " "
					+ selectedQuarter + " network";
			controller.handleMessageFromClientUI(message);
		}
	}

	/**
	 * click on side button Home
	 * 
	 * @param event
	 */
	void openHome(ActionEvent event) {
		super.openHome(event);
		sidebar_btn0.setSelected(true);
	}

}
