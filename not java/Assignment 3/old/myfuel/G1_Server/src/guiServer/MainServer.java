package guiServer;

import guiServer.ServerWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainServer extends Application {

	private static Stage mainStage;
	private static ServerWindow control;

	@Override
	public void start(Stage primaryStage) {
		mainStage = primaryStage;

		try {
			// constructing our scene
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("ServerWindow.fxml"));
			AnchorPane anchorpane = (AnchorPane) loader.load();
			Scene scene = new Scene(anchorpane);
			control = (ServerWindow) loader.getController();

			// setting the stage
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Server Connection");
			primaryStage.show();
			primaryStage.setOnCloseRequest(event -> {
				if (control.getConnected()) {
					System.out.println("Stage is closing");
					control.disconnectServer();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ServerWindow getControl() {
		return control;
	}

	public static Stage getMainStage() {
		return mainStage;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
