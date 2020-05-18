package guiClient;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

/**
 * all boundaries implement this
 * 
 * @author Elroy, Lior
 * @category Final
 */
public interface IFXML {

	/**
	 * executes window methods according to input
	 * 
	 * @param lastMsgFromServer
	 */
	public void callAfterMessage(Object lastMsgFromServer);

	/**
	 * opens error popup window
	 * all windows use this
	 * 
	 * @param title
	 * @param msg
	 */
	public default void openErrorAlert(String title, String msg) {
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
}
