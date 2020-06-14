package guiClient;

import java.util.Calendar;
import java.util.Collection;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

/**
 * boundary for sign out requests
 * <p>
 * all boundaries except login extend this
 * 
 * @version Almost Final
 * @author Elroy, Lior
 */
public abstract class UserWindow extends AFXML {

	@FXML
	protected BorderPane mainBorderPane;
	@FXML
	protected AnchorPane mainwindow_pane;
	@FXML
	protected Label lblHelloUser;
	@FXML
	protected Label topbar_window_label;

	@FXML
	protected AnchorPane homePane;
	@FXML
	protected Label lblHomeUserName;
	@FXML
	protected ComboBox<Integer> cobHomeYear;
	@FXML
	protected ComboBox<Integer> cobHomeMonth;
	@FXML
	protected Button btnHomeUpdate;
	@FXML
	protected Button btnSignOut;

	protected String username; // the username of the current user of the window

	public abstract Window getWindow();

	public abstract void clearFields();

	/**
	 * initialize all components shared by all users
	 * 
	 * @param username
	 */
	public void setUserComponents(String username) {
		this.username = username;
		this.lblHelloUser.setText("Hello, " + username);
		this.lblHomeUserName.setText(username + " !");

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new java.util.Date());

		this.cobHomeYear.getItems().removeAll((Collection<?>) this.cobHomeYear.getItems());
		this.cobHomeYear.getItems().addAll(new Integer[] { 2019, 2020 });
		this.cobHomeYear.setValue(calendar.get(Calendar.YEAR));
		this.cobHomeMonth.getItems().removeAll((Collection<?>) this.cobHomeMonth.getItems());
		this.cobHomeMonth.getItems().addAll(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 });
		this.cobHomeMonth.setValue(calendar.get(Calendar.MONTH) + 1);
	}

	/**
	 * checks there are no digits / letters in a tf value
	 * 
	 * @param tf
	 * @param kind         = { digits, letters }
	 * @param errorMessage
	 * @return true if valid, else false
	 */
	protected boolean checkValidTextField(String tf, String kind, String errorMessage) {
		if (kind.equals("digits")) {
			if (tf.matches(".*[ -/].*") || tf.matches(".*[:-~].*")) {
				openErrorAlert("Error", errorMessage);
				return false;
			}
		} else if (kind.equals("letters")) {
			if (tf.matches(".*[ -@].*")) {
				openErrorAlert("Error", errorMessage);
				return false;
			}
		} else {
			System.out.println("expected digits or letters but got " + kind);
			return false;
		}
		return true;
	}

	/*********************** button listeners ***********************/

	@FXML
	public void closeTopBar(ActionEvent event) {
		if (!this.signOutClicked(this.getWindow()))
			event.consume();
	}

	@FXML
	void btnSignOutClicked(ActionEvent event) {
		this.signOutClicked(this.getWindow());
	}

	/*********************** button functions ***********************/

	/**
	 * if signout request confirmed, send username to the appropriate controller
	 * 
	 * @param window
	 * @return true if signout request confirmed
	 */
	public boolean signOutClicked(Window window) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Sign Out");
		alert.setHeaderText("Would you like to sign out?");
		ButtonType buttonTypeOne = new ButtonType("Yes");
		ButtonType buttonTypeTwo = new ButtonType("No");
		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeOne) {
			this.controller.setCurrentWindow(this);
			this.controller.handleMessageFromClientUI("signout " + this.username);
			return true;
		}
		if (result.get() == buttonTypeTwo)
			alert.hide();
		return false;
	}

	/*************** boundary "logic" - window changes ***************/

	@Override
	public void callAfterMessage(Object lastMsgFromServer) {
		if (lastMsgFromServer == null) {
			openErrorAlert("Error", "Something went Wrong");

		} else if (lastMsgFromServer instanceof String) {
			String message = (String) lastMsgFromServer;
			if (message.startsWith("sign out"))
				handleSignOutFromServer(message, getWindow());
		}
	}

	/**
	 * @param lastMsgFromServer
	 * @param window
	 */
	public void handleSignOutFromServer(String lastMsgFromServer, Window window) {
		System.out.println(lastMsgFromServer);

		if (lastMsgFromServer.startsWith("sign out succeeded")) {
			this.signOutToLogin(window);

		} else if (lastMsgFromServer.startsWith("sign out failed")) {
			Alert a = new Alert(Alert.AlertType.ERROR);
			a.setContentText("Error - sign out failed");
			a.show();
		}
	}

	/**
	 * @param window
	 */
	public void signOutToLogin(Window window) {
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
			window.hide();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * send activity to log in db
	 * 
	 * @param action
	 */
	public void requestToLogActivity(String action) {
		String message = "activity log " + username + " " + action;
		this.controller.handleMessageFromClientUI(message);
	}
}
