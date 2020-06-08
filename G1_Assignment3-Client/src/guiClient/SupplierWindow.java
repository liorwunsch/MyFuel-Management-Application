package guiClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

import client.SupplierController;
import entities.FastFuelList;
import entities.FuelStation;
import entities.FuelStationOrder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
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
	private ComboBox<FuelStation> cobASFSOFuelStationID;

	@FXML
	private Button btnASFSOShow;

	@FXML
	private TableView<?> tvASFSODetails;
	
	private FuelStation[] fs;
	
	@FXML
	void initialize() {
		fs = SupplierController.getInstance().getFuelStation();
		cobASFSOFuelStationID.getItems().addAll(Arrays.asList(fs));
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
		/*
		 * if (lastMsgFromServer instanceof FuelStationOrder[]) { FuelStationOrder[] fso
		 * = (FuelStationOrder[])lastMsgFromServer; tvASFSODetails.getColumns().add(e);
		 * }
		 */
	}

	@FXML
	void openApproveSupplied(ActionEvent event) {
		this.homePane.setVisible(false);
		approveSuppliedPane.setVisible(true);
		
	}
	
	
}
