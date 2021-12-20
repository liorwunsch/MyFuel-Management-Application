package guiServer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * server's exe starts here
 * <p>
 * loads server window
 * 
 * @version Final
 * @author Elroy, Lior
 */
public class MainServer extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("ServerWindow.fxml"));
			Scene scene = new Scene(loader.load());

			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.setTitle("MyFuel Server");
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
