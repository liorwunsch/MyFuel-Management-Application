package guiClient;

import java.util.Optional;

import client.ClientController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * all boundaries extend this
 * 
 * @version Final
 * @author Elroy, Lior
 */
public abstract class AFXML {

	@FXML	protected AnchorPane titleBar;
	@FXML	protected Button btnMini;
	@FXML	protected Button btnExit;
	
	protected AnchorPane visableNow;
	protected ClientController controller;
	
	/**
	 * executes window methods according to input
	 * 
	 * @param lastMsgFromServer
	 */
	public abstract void callAfterMessage(Object lastMsgFromServer);

	/**
	 * opens error popup window all windows use this
	 * 
	 * @param title
	 * @param msg
	 */
	public void openErrorAlert(String title, String msg) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(msg);
		alert.show();
		ButtonType buttonTypeOne = new ButtonType("OK");
		alert.getButtonTypes().setAll(buttonTypeOne);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeOne)
			alert.hide();
	}

	/* methods for functionality of the top bar close, minimize, drag window */
	private double x = 0;
	private double y = 0;

	@FXML
	public void minimizeTopBar(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setIconified(true);
	}

	@FXML
	public void clickOnTopBar(MouseEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		x = stage.getX() - event.getScreenX();
		y = stage.getY() - event.getScreenY();
	}

	@FXML
	public void dragTopBar(MouseEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setX(event.getScreenX() + x);
		stage.setY(event.getScreenY() + y);
	}

}
