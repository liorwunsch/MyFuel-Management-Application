package guiClient;

import client.MarketingRepresentativeController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Window;

/**
 * boundary for fuel station manager window
 * 
 * @version Basic
 * @author Lior
 */
public class FuelStationManagerWindow extends UserWindow {

	@FXML	private ToggleGroup one;
	@FXML	private ToggleButton sidebar_btn0;
	@FXML	private ToggleButton sidebar_btn1;
	@FXML	private ToggleButton sidebar_btn2;
	@FXML	private ToggleButton sidebar_btn3;

	@FXML	private Button btnConfirmAssess;

	@FXML
	void initialize() { // will change
		this.controller = MarketingRepresentativeController.getInstance();
	}

	@Override
	public Window getWindow() {
		return this.btnConfirmAssess.getScene().getWindow();
	}

	@Override
	public void callAfterMessage(Object lastMsgFromServer) {
		if (lastMsgFromServer instanceof String) {
			String message = lastMsgFromServer.toString();
			if (message.startsWith("sign out"))
				this.handleSignOutFromServer(message, this.getWindow());
		}

		/**
		 * 
		 */
	}

}
