package guiServer;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import server.ServerController;

public class ServerWindow {

	@FXML
	private TextField tfEnterPort;

	@FXML
	private Label lbStautConnection;

	@FXML
	private Button btnConnect;

	@FXML
	private Button btnDisconnect;

	@FXML
	private ImageView imgvMainImage;

	@FXML
	private TextArea taConsoleMessages;

	private ServerController serverController;
	private boolean connectedFlag;
//	private String msg;
	private Alert a;
	private Object lock = new Object();

	@FXML
	public void exitApplication(ActionEvent event) {
		Platform.exit();
	}

	public boolean getConnected() {
		return connectedFlag;
	}

	@FXML
	void initialize() throws IOException {
		lbStautConnection.setVisible(false);
		connectedFlag = false;
		Image image = new Image(getClass().getResource("serverIcon.png").toExternalForm());
		imgvMainImage.setImage(image);
		btnDisconnect.setDisable(true);
		taConsoleMessages.setEditable(false);
	}

	public void updateArea(String msge) {
		taConsoleMessages.appendText(msge);
		taConsoleMessages.appendText("\n");
	}

	public void connectedBtnClicked() {
		lbStautConnection.setVisible(true);
		if (tfEnterPort.getText().equals("")) {
			a = new Alert(Alert.AlertType.ERROR);
			a.setContentText("Please enter data to required field");
			a.show();
			tfEnterPort.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
		} else {
			serverController = new ServerController(Integer.parseInt(tfEnterPort.getText().toString()), lock, this);
			synchronized (lock) {
				serverController.startListening();
				try {
					lock.wait();
				} catch (InterruptedException e) {
				}
			}
			if (serverController.isListening()) {
				connectedFlag = true;
				btnConnect.setDisable(true);
				btnDisconnect.setDisable(false);
				tfEnterPort.setDisable(true);
				tfEnterPort.setStyle("-fx-border-style: none;");
				Image image = new Image(getClass().getResource("connected_icon.png").toExternalForm());
				imgvMainImage.setImage(image);
				lbStautConnection.setText("Server is Connected");
				lbStautConnection.setStyle("-fx-text-fill: #008000; -fx-font-size: 14px;");
			} else {
				tfEnterPort.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
				Image image = new Image(getClass().getResource("disconnected.png").toExternalForm());
				imgvMainImage.setImage(image);
				lbStautConnection.setText("Server Disconnected");
				lbStautConnection.setStyle("-fx-text-fill: #FF0000; -fx-font-size: 14px;");
				a = new Alert(Alert.AlertType.ERROR);
				a.setContentText("Port already in use , enter diffrent port");
				a.show();
			}
		}
	}

	public void connectedBtnHover() {
		btnConnect.setStyle("-fx-background-color: #ADFF2F");
	}

	public void connectedBtnExit() {
		btnConnect.setStyle("-fx-background-color: #90EE90");
	}

	public void disconnectServer() {
		connectedFlag = false;
		serverController.stopListening();
		synchronized (lock) {
			try {
				lock.wait();
			} catch (InterruptedException e) {
			}
		}
	}

	public void disConnectedBtnClicked() throws IOException {
		btnConnect.setDisable(false);
		btnDisconnect.setDisable(true);
		tfEnterPort.setDisable(false);
		disconnectServer();
		Image image = new Image(getClass().getResource("disconnected.png").toExternalForm());
		imgvMainImage.setImage(image);
		lbStautConnection.setText("Server Disconnected");
		lbStautConnection.setStyle("-fx-text-fill: #FF0000; -fx-font-size: 14px;");
		serverController.close();
	}

	public void disConnectedBtnHover() {
		btnDisconnect.setStyle("-fx-background-color: #FFB6C1");

	}

	public void disConnectedBtnExit() {
		btnDisconnect.setStyle("-fx-background-color: #F08080");

	}

}
