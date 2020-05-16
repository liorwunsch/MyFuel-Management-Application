package guiClient;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public abstract class UserWindow implements IFXML {

	public void handleSignOut(String lastMsg, Window window) {
		System.out.println(lastMsg);

		if (lastMsg.startsWith("sign out succeeded")) {
			this.signOut(window);
		}

		if (lastMsg.startsWith("sign out failed")) {
			Alert a = new Alert(Alert.AlertType.ERROR);
			a.setContentText("ERROR - sign out failed");
			a.show();
		}
	}

	public void signOut(Window window) {
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

	@Override
	public void openErrorAlert(String title, String msg) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(msg);
	}

}
