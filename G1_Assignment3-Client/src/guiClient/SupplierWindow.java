package guiClient;

import client.SupplierController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
	private ComboBox<?> cobASFSOFuelStationID;

	@FXML
	private Button btnASFSOShow;

	@FXML
	private TableView<?> tvASFSODetails;

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
	}

	@FXML
	void openApproveSupplied(ActionEvent event) {

	}

	@Override
	public void clearFields() {
		// TODO Auto-generated method stub
		
	}

}
