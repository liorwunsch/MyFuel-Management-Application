package guiClient;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * 
 * @author Lior - don't change
 *
 */
public class MainClient extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/login/LoginWindow.fxml"));
			Scene scene = new Scene(loader.load());

			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.setTitle("MyFuel Login");
			primaryStage.show();
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we) {
					System.exit(0);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) throws Exception {
		launch(args);
	}

}
