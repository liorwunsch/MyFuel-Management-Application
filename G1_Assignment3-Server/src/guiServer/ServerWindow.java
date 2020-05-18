package guiServer;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import server.ServerController;

/**
 * boundary for server window
 * 
 * @version Final
 * @author Elroy, Lior
 */
public class ServerWindow {

	@FXML
	private Label lblStatus;

	@FXML
	private Button btnConnect;

	@FXML
	private Button btnDisconnect;

	@FXML
	private ImageView imgStatus;

	@FXML
	private TextField tfHost;

	@FXML
	private TextField tfSchema;

	@FXML
	private TextField tfPort;

	@FXML
	private TextField tfUsername;

	@FXML
	private PasswordField tfPassword;

	@FXML
	private TextArea taConsole;

	private boolean connectedFlag;
	private Object lock = new Object(); // lock on updateArea
	private ServerController serverController;

	/**
	 * starts with <Code>connectedFlag</Code> false hence server not connected
	 * 
	 * @throws IOException
	 */
	@FXML
	void initialize() throws IOException {
		Image image = new Image(getClass().getResource("serverIcon.png").toExternalForm());
		this.imgStatus.setImage(image);

		this.lblStatus.setVisible(false);
		this.btnConnect.setDisable(false);
		this.btnDisconnect.setDisable(true);
		this.taConsole.setEditable(false);

		this.connectedFlag = false;
	}

	public boolean getConnected() {
		return this.connectedFlag;
	}

	/**
	 * print <Code>msg</Code> to server console
	 * 
	 * @param msg
	 */
	public void updateArea(String msg) {
		this.taConsole.appendText(msg + "\n");
	}

	/**
	 * user clicked on connect button
	 */
	public void connectedBtnClicked() {
		boolean flag = true;
		this.lblStatus.setVisible(true);

		String host = this.tfHost.getText();
		if (host.equals("")) {
			this.tfHost.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
			flag = false;
		}

		String schema = this.tfSchema.getText();
		if (schema.equals("")) {
			this.tfSchema.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
			flag = false;
		}

		String port = this.tfPort.getText();
		if (port.equals("")) {
			this.tfPort.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
			flag = false;
		}

		String username = this.tfUsername.getText();
		if (username.equals("")) {
			this.tfUsername.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
			flag = false;
		}

		String password = this.tfPassword.getText();
		if (password.equals("")) {
			this.tfPassword.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
			flag = false;
		}

		if (flag == false) {
			synchronized (this.lock) {
				this.updateArea("Missing Required Fields");
				this.lock.notifyAll();
			}
			return;
		}

		/* if no info missing, continue connection request */
		if (flag == true) {
			this.serverController = ServerController.getInstance(host, schema, Integer.parseInt(port), username,
					password, this.lock, this);

			if (this.serverController.isListening()) {
				synchronized (this.lock) {
					this.updateArea("Server Already Connected");
					this.lock.notifyAll();
				}
				return;
			}

			synchronized (this.lock) {
				this.serverController.startListening();
				try {
					this.lock.wait();
				} catch (InterruptedException e) {
				}
			}

			if (this.serverController.isListening()) {
				Image image = new Image(getClass().getResource("connected_icon.png").toExternalForm());
				this.imgStatus.setImage(image);

				this.tfHost.setDisable(true);
				this.tfHost.setStyle("-fx-border-style: none;");
				this.tfSchema.setDisable(true);
				this.tfSchema.setStyle("-fx-border-style: none;");
				this.tfPort.setDisable(true);
				this.tfPort.setStyle("-fx-border-style: none;");
				this.tfUsername.setDisable(true);
				this.tfUsername.setStyle("-fx-border-style: none;");
				this.tfPassword.setDisable(true);
				this.tfPassword.setStyle("-fx-border-style: none;");
				this.lblStatus.setText("Server is Connected");
				this.lblStatus.setStyle("-fx-text-fill: #008000; -fx-font-size: 14px;");
				this.btnConnect.setDisable(true);
				this.btnDisconnect.setDisable(false);

				this.connectedFlag = true;

			} else {
				Image image = new Image(getClass().getResource("disconnected.png").toExternalForm());
				this.imgStatus.setImage(image);

				this.tfPort.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
				this.lblStatus.setText("Server Disconnected");
				this.lblStatus.setStyle("-fx-text-fill: #FF0000; -fx-font-size: 14px;");

				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("Port Is In Use");
				alert.show();
			}
		}
	}

	/**
	 * user clicked on disconnect button
	 */
	public void disConnectedBtnClicked() throws IOException {
		Image image = new Image(getClass().getResource("disconnected.png").toExternalForm());
		this.imgStatus.setImage(image);

		this.tfHost.setDisable(false);
		this.tfSchema.setDisable(false);
		this.tfPort.setDisable(false);
		this.tfUsername.setDisable(false);
		this.tfPassword.setDisable(false);
		this.lblStatus.setText("Server Disconnected");
		this.lblStatus.setStyle("-fx-text-fill: #FF0000; -fx-font-size: 14px;");
		this.btnConnect.setDisable(false);
		this.btnDisconnect.setDisable(true);

		this.disconnectServer();
		this.serverController.close();
	}

	public void disconnectServer() {
		this.connectedFlag = false;
		this.serverController.stopListening();
		synchronized (this.lock) {
			try {
				this.lock.wait();
			} catch (InterruptedException e) {
			}
		}
	}

	public void connectedBtnHover() {
		this.btnConnect.setStyle("-fx-background-color: #ADFF2F");
	}

	public void connectedBtnExit() {
		this.btnConnect.setStyle("-fx-background-color: #90EE90");
	}

	public void disConnectedBtnHover() {
		this.btnDisconnect.setStyle("-fx-background-color: #FFB6C1");
	}

	public void disConnectedBtnExit() {
		this.btnDisconnect.setStyle("-fx-background-color: #F08080");
	}

}
