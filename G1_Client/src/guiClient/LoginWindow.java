package guiClient;

import java.io.IOException;
import java.util.Optional;

import client.UserController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class LoginWindow implements IFXML {

	@FXML
	private TextField tfLoginUserName;

	@FXML
	private PasswordField tfLoginPassword;

	@FXML
	private Label lblError;

	@FXML
	private Button btnSignIn;

	@FXML
	private ToggleGroup rb1;

	@FXML
	private RadioButton rbEmployee;

	@FXML
	private RadioButton rbCustomer;

	private UserController userController;

	@FXML
	void initialize() throws IOException {
		this.userController = UserController.getInstance("localhost", 5555);
	}

	@Override
	public void callAfterMessage(String lastMsg) {
		if (lastMsg.startsWith("login succeeded")) {
			String[] splitMsg = lastMsg.split(" ");
			this.successLogin(splitMsg[2]);
		}

		if (lastMsg.startsWith("login failed"))
			this.failedLogin();

		if (lastMsg.startsWith("login already connected"))
			this.alreadyConnectedLogin();
	}

	public void signIn(ActionEvent event) throws Exception {
		this.mySignIn();
	}

	private void successLogin(String role) {
		this.lblError.setVisible(false);

		if (role.equals("MarketingRepresentative")) {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/marketing/MarketingRepresentativeWindow.fxml"));
				Scene newScene = new Scene(loader.load());
				Stage newStage = new Stage();

				MarketingRepresentativeWindow marketingRepresentativeWindow = loader.getController();
				marketingRepresentativeWindow.setUsername(tfLoginUserName.getText());

				newStage.setResizable(false);
				newStage.setScene(newScene);
				newStage.setTitle("MyFuel Marketing Representative");
				newStage.show();
				newStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
					public void handle(WindowEvent we) {
						marketingRepresentativeWindow.signOutClicked();
					}
				});
				this.btnSignIn.getScene().getWindow().hide();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void mySignIn() {
		String username = tfLoginUserName.getText();
		String password = tfLoginPassword.getText();
		String userType;

		if (username.isEmpty() || password.isEmpty()) {
			this.lblError.setText("missing required fields");
			this.lblError.setVisible(true);
			this.tfLoginUserName.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
			this.tfLoginPassword.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
		} else {
			this.lblError.setVisible(false);
			this.tfLoginUserName.setStyle("-fx-border-style: none;");
			this.tfLoginPassword.setStyle("-fx-border-style: none;");

			if (this.rbEmployee.isSelected())
				userType = this.rbEmployee.getText();
			else
				userType = this.rbCustomer.getText();

			this.userController.setCurrentWindow(this);
			this.userController.handleMessageFromClientUI(("login" + " " + username + " " + password + " " + userType));
		}
	}

	private void alreadyConnectedLogin() {
		this.lblError.setText("user already connected");
		this.lblError.setVisible(true);
	}

	private void failedLogin() {
		this.lblError.setText("wrong username or password");
		this.lblError.setVisible(true);
		this.tfLoginUserName.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
		this.tfLoginPassword.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
	}

	public void btnSignInHover() {
		this.btnSignIn.setStyle("-fx-background-color: #FFA07A ; -fx-background-radius: 7");
	}

	public void btnSignInExit() {
		this.btnSignIn.setStyle("-fx-background-color:  #F56B2C ; -fx-background-radius: 7");
	}

	@FXML
	void enterKeyPressed(KeyEvent event) {
		switch (event.getCode()) {
		case ENTER:
			this.mySignIn();
			break;
		default:
			break;
		}
	}

	@FXML
	void tabEmployeePressed(KeyEvent event) {
		switch (event.getCode()) {
		case TAB:
			this.rbCustomer.setSelected(true);
			break;
		default:
			break;
		}
	}

	@FXML
	void tabCustomerPressed(KeyEvent event) {
		switch (event.getCode()) {
		case TAB:
			this.rbEmployee.setSelected(true);
			break;
		default:
			break;
		}
	}

	@Override
	public void openErrorAlert(String title, String msg) {
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
