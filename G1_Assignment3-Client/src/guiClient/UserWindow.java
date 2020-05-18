package guiClient;

import java.util.Optional;

import client.UserController;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

/**
 * boundary for sign out requests
 * <p>
 * all boundaries except login extend this
 * 
 * @version Final
 * @author Elroy, Lior
 */
public abstract class UserWindow implements IFXML {

	protected String username;
	protected UserController controller;

	/**
	 * updates <Code>username</Code> to that of the one connnected
	 * 
	 * @param username
	 */
	public abstract void setUsername(String username);

	/**
	 * @param username
	 * @return the window of the boundary
	 */
	public abstract Window getWindow();

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
			controller.setCurrentWindow(this);
			controller.handleMessageFromClientUI("signout " + username);
			return true;
		}
		if (result.get() == buttonTypeTwo)
			alert.hide();
		return false;
	}

	/**
	 * @param lastMsgFromServer
	 * @param window
	 */
	public void handleSignOutFromServer(String lastMsgFromServer, Window window) {
		System.out.println(lastMsgFromServer);

		if (lastMsgFromServer.startsWith("sign out succeeded")) {
			this.signOutToLogin(window);
		}

		if (lastMsgFromServer.startsWith("sign out failed")) {
			Alert a = new Alert(Alert.AlertType.ERROR);
			a.setContentText("ERROR - sign out failed");
			a.show();
		}
	}

	/**
	 * @param window
	 */
	public void signOutToLogin(Window window) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/login/LoginWindow.fxml"));
			Scene newScene = new Scene(loader.load());
			Stage newStage = new Stage();

			newStage.setResizable(false);
			newStage.setScene(newScene);
			newStage.setTitle("MyFuel Login");
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

}
