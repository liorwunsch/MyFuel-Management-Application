package guiClient;

import java.text.SimpleDateFormat;

import client.FastFuelController;
import entities.FastFuel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

/**
 * boundary for fast fuel simulator window
 * 
 * @version Final
 * @author Lior
 */
public class FastFuelWindow extends AFXML {

	@FXML
	private AnchorPane emuPane;
	@FXML
	private AnchorPane step1f;
	@FXML
	private Button btnEmulate;
	@FXML
	private TextField tfRegPlate;
	@FXML
	private TextField tfFuelStation;
	@FXML
	private AnchorPane step2f;
	@FXML
	private TextField tfAmount;
	@FXML
	private TextField tfAmount1;
	@FXML
	private Button btnEmulate1;
	@FXML
	private AnchorPane titleBar;
	@FXML
	private Button btnExit;
	@FXML
	private Button btnMini;
	@FXML
	private TextArea simulationData;

	private static FastFuel currentEmulation;
	private double pricePerLiter;

	/**
	 * runs every time this windows goes live
	 */
	@FXML
	void initialize() {
		this.controller = FastFuelController.getInstance();
		this.controller.setCurrentWindow(this);
	}

	/**
	 * returns the window entity of this boundary
	 */
	private Window getWindow() {
		return this.btnEmulate.getScene().getWindow();
	}

	/**
	 * FastFuelController uses this
	 * 
	 * @return current fastfuel entity simulated
	 */
	public static FastFuel getCurrentEmulation() {
		return currentEmulation;
	}

	/*********************** button listeners ***********************/

	/**
	 * button listener for emulate step 1
	 * 
	 * @param event
	 */
	@FXML
	void btnEmulatePressed(ActionEvent event) {
		this.tfAmount.clear();
		this.tfAmount1.clear();
		simulationData.clear();
		String regPlate = this.tfRegPlate.getText();
		String fuelStationID = this.tfFuelStation.getText();

		if (regPlate.isEmpty() || fuelStationID.isEmpty()) {
			openErrorAlert("Error", "Missing Required Fields");
			return;
		}
		try {
			if (Integer.parseInt(regPlate) <= 0) {
				openErrorAlert("Error", "Registration Plate Must Be a Positive Number");
				return;
			}
		} catch (NumberFormatException e) {
			openErrorAlert("Error", "Registration Plate Must Be a Number");
			return;
		}
		if (regPlate.length() != 7 && regPlate.length() != 8) {
			openErrorAlert("Error", "Registration Plate Must Be 7 or 8 digits");
			return;
		}
		try {
			if (Integer.parseInt(fuelStationID) <= 0) {
				openErrorAlert("Error", "Fuel Station ID Must Be a Positive Number");
				return;
			}
		} catch (NumberFormatException e) {
			openErrorAlert("Error", "Fuel Station ID Must Be a Number");
			return;
		}

		this.controller.handleMessageFromClientUI("getdiscount " + regPlate + " " + fuelStationID);
	}

	/**
	 * button listener for emulate step 2
	 * 
	 * @param event
	 */
	@FXML
	void btnEmulatePressed2(ActionEvent event) {
		String amount = this.tfAmount.getText();
		String fuelPump = this.tfAmount1.getText();

		if (fuelPump.isEmpty() || amount.isEmpty()) {
			openErrorAlert("Error", "Missing Required Fields");
			return;
		}
		try {
			if (Double.parseDouble(amount) <= 0) {
				openErrorAlert("Error", "Amount Must Be a Positive Number");
				return;
			}
		} catch (NumberFormatException e) {
			openErrorAlert("Error", "Amount Must Be a Number");
			return;
		}
		try {
			if (Integer.parseInt(fuelPump) < 1 || Integer.parseInt(fuelPump) > 8) {
				openErrorAlert("Error", "Fuel Pump Number Must Be Between 1 and 8");
				return;
			}
		} catch (NumberFormatException e) {
			openErrorAlert("Error", "Fuel Pump Number Must Be a Number");
			return;
		}

		currentEmulation.setAmountBought(Double.parseDouble(amount));
		this.controller.handleMessageFromClientUI("saveFastFuel");
		this.step2f.setDisable(true);
	}

	/**
	 * button listener for close button on topbar
	 * 
	 * @param event
	 */
	@FXML
	public void closeTopBar(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/windows/LoginWindow.fxml"));
			Scene newScene = new Scene(loader.load());
			Stage newStage = new Stage();
			LoginWindow loginWindow = (LoginWindow) loader.getController();
			loginWindow.setVisibleNow(true);
			newStage.setResizable(false);
			newStage.setScene(newScene);
			newStage.setTitle("MyFuel Login");
			newStage.initStyle(StageStyle.UNDECORATED);
			newStage.show();
			newStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we) {
					System.exit(0);
				}
			});
			getWindow().hide();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*************** boundary "logic" - window changes ***************/

	/**
	 * called after server returned a message/object to the client
	 */
	@Override
	public void callAfterMessage(Object lastMsgFromServer) {
		if (!(lastMsgFromServer instanceof FastFuel)) {
			System.out.println("expected fastfuel but got something else");
			return;
		}

		FastFuel fastFuel = (FastFuel) lastMsgFromServer;
		String result = fastFuel.getFunction();
		System.out.println(result);

		if (result.equals("save fast fuel success")) {
			openConfirmationAlert("Success", "Fast Fuel Saved");
			this.simulationData.appendText(
					"\n\nFinal Price = " + fastFuel.getAmountBought().toString() + " * " + this.pricePerLiter);
			this.simulationData.appendText("\nFinal Price = " + fastFuel.getFinalPrice().toString() + " $");
			this.simulationData.appendText("\nPayment Sent To External System");
			this.simulationData.appendText("\nUpdated CustomerBoughtFromCompany Table");
			if (fastFuel.getSaleID() != -1)
				this.simulationData.appendText("\nUpdated CustomerBoughtInSale Table");
			this.step1f.setDisable(false);
			this.step2f.setDisable(false);

		} else if (result.contains("doesn't") || result.contains("fail")) {
			openErrorAlert("Error", result);
			this.step1f.setDisable(false);
			this.step2f.setDisable(true);

		} else if (result.equals("getFuelTypeAndPricePerLiter success")) {
			this.simulationData.clear();
			this.simulationData.appendText("Fuel Type = " + fastFuel.getFuelType().toString());
			this.simulationData.appendText(
					"\nFuel Type Price = " + fastFuel.getCurrentPrice().toString() + " $ (before discounts)");
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			this.simulationData.appendText("\nTime = " + formatter.format(fastFuel.getFastFuelTime()));
			this.simulationData.appendText("\nFuel Company = " + fastFuel.getFuelCompanyName().toString());
			this.simulationData.appendText("\nCustomer ID = " + fastFuel.getCustomerID());
			this.simulationData.appendText("\nPurchasing Program = " + fastFuel.getPurchasingProgramName().toString());
			this.simulationData.appendText("\nPricing Model = " + fastFuel.getPricingModelName().toString());
			this.simulationData
					.appendText("\nPricing Model Discount = " + fastFuel.getPricingModelDiscount().toString() + " %");
			this.simulationData
					.appendText("\nActive Sale ID = " + fastFuel.getSaleID().toString() + " (-1 if no active sale)");
			this.simulationData.appendText("\nSale Discount = " + fastFuel.getSalesDiscount().toString() + " $");
			this.simulationData.appendText("\nPrice Per Liter = (" + fastFuel.getCurrentPrice().toString() + " - "
					+ fastFuel.getSalesDiscount().toString() + ") * (1 - "
					+ fastFuel.getPricingModelDiscount().toString() + ")");
			this.pricePerLiter = fastFuel.getFinalPrice();
			this.simulationData.appendText("\nPrice Per Liter = " + pricePerLiter + " $ (after discounts)");
			this.step1f.setDisable(true);

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Please Continue");
			ButtonType buttonTypeOne = new ButtonType("Enter Fuel Amount And Fuel Pump Number\nAnd\nContinue Fueling");
			alert.getButtonTypes().setAll(buttonTypeOne);
			alert.show();
			final Button btn = (Button) alert.getDialogPane().lookupButton(buttonTypeOne);
			btn.setOnAction(event -> {
				alert.hide();
				currentEmulation = fastFuel;
				this.step2f.setDisable(false);
			});
		}
	}

}
