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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

/**
 * @version Basic
 * @author Lior
 */
public class FastFuelWindow extends AFXML {

	@FXML
	private AnchorPane emuPane;
	@FXML
	private TextField tfRegPlate;
	@FXML
	private TextField tfFuelStation;
	@FXML
	private TextField tfAmount;
	@FXML
	private Button btnEmulate;
	@FXML
	private Label lblPrice;
	@FXML
	private Label lblFuelType;
	@FXML
	private Label lblFuelCompanyNameRes;
	@FXML
	private Label lblSaleIDRes;
	@FXML
	private Label lblCustomerIDRes;
	@FXML
	private Label lblFuelTimeRes;

	private static FastFuel currentEmulation;

	@FXML
	void initialize() {
		this.controller = FastFuelController.getInstance();
		this.controller.setCurrentWindow(this);
	}

	private Window getWindow() {
		return this.lblFuelType.getScene().getWindow();
	}

	public static FastFuel getCurrentEmulation() {
		return currentEmulation;
	}

	/*********************** button listeners ***********************/

	@FXML
	void btnEmulatePressed(ActionEvent event) {
		String regPlate = this.tfRegPlate.getText();
		String fuelStationID = this.tfFuelStation.getText();
		String amount = this.tfAmount.getText();

		if (regPlate.isEmpty() || fuelStationID.isEmpty() || amount.isEmpty()) {
			openErrorAlert("Error", "Missing Required Fields");
			return;
		}
		if (regPlate.matches(".*[ -/].*") || regPlate.matches(".*[:-~].*")
				|| (regPlate.length() != 7 && regPlate.length() != 8)) {
			openErrorAlert("Error", "Registration Plate Not Valid");
			return;
		}
		if (fuelStationID.matches(".*[ -/].*") || fuelStationID.matches(".*[:-~].*")) {
			openErrorAlert("Error", "Fuel Station ID Not Valid");
			return;
		}
		if (amount.matches(".*[ -/].*") || amount.matches(".*[:-~].*")) {
			openErrorAlert("Error", "Amount Not Valid");
			return;
		}

		this.controller.handleMessageFromClientUI("getdiscount " + regPlate + " " + fuelStationID + " " + amount);
	}

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
			openConfirmationAlert("Success", "Fast Fuel Saved Successfully");
			this.emuPane.setDisable(false);

		} else if (result.contains("doesn't") || result.equals("fail")) {
			openErrorAlert("Error", result);
			this.emuPane.setDisable(false);

		} else if (result.equals("getFuelTypeAndPricePerLiter success")) {
			this.lblFuelType.setText(fastFuel.getFuelType().toString());
			this.lblPrice.setText(fastFuel.getFinalPrice().toString());
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			this.lblFuelTimeRes.setText("Time = " + formatter.format(fastFuel.getFastFuelTime()));
			this.lblCustomerIDRes.setText("Customer ID = " + fastFuel.getCustomerID());
			this.lblFuelCompanyNameRes.setText("Fuel Company = " + fastFuel.getFuelCompanyName().toString());
			this.lblSaleIDRes.setText("Sale ID = " + fastFuel.getSaleID().toString());
			this.emuPane.setDisable(true);

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Please Continue");
			ButtonType buttonTypeOne = new ButtonType("Continue To Fueling");
			alert.show();
			alert.getButtonTypes().setAll(buttonTypeOne);
			final Button btn = (Button) alert.getDialogPane().lookupButton(buttonTypeOne);
			btn.setOnAction(event -> {
				alert.hide();
				currentEmulation = fastFuel;
				this.controller.handleMessageFromClientUI("saveFastFuel");
			});

		}

	}

}
