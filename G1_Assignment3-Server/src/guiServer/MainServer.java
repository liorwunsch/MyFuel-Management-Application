package guiServer;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * server's exe starts here
 * <p>
 * loads server window
 * 
 * @author Elroy, Lior
 * @category Final
 */
public class MainServer extends Application {

	private static ServerWindow control;

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("ServerWindow.fxml"));
			Scene scene = new Scene(loader.load());
			control = (ServerWindow) loader.getController();

			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.setTitle("MyFuel Server");
			primaryStage.show();
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we) {
					if (control.getConnected())
						control.disconnectServer();
					System.exit(0);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
