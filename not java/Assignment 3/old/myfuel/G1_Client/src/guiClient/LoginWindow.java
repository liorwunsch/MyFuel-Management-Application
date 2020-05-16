package guiClient;

import java.io.IOException;

import client.LoginController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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

	private LoginController loginController;

	@FXML
	void initialize() throws IOException {
		loginController = new LoginController("localhost", 5555, this);
	}

	private void alreadyConnectedLogin() {
		lblError.setText("this user is already connected");
		lblError.setVisible(true);
	}

	private void failedLogin() {
		lblError.setText("Failed login, wrong username or password");
		lblError.setVisible(true);
		System.out.println("alerting for failure");
		tfLoginUserName.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
		tfLoginPassword.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");

	}

	// type inorder to open the required window
	private void successLogin(String type) throws IOException {
		// required fill for windows
		lblError.setVisible(false);
		System.out.println("login successs");
		Parent root;
		FXMLLoader loader;
		System.out.println("the type is: " + type);

		if (type.equals("Marketing Representative")) {
			System.out.println("going for new window");
			try {
				loader = new FXMLLoader(getClass().getResource("/marketing/MarketingWindow.fxml"));
				root = (Parent) loader.load();
				MarketingWindow ctrl = loader.getController();
				System.out.println(tfLoginUserName.getText());
				ctrl.setUserName(tfLoginUserName.getText());
				ctrl.setLoginWindow(this);
				Scene newScene = new Scene(root);
				Stage newStage = new Stage();
				newStage.setScene(newScene);
				newStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
					public void handle(WindowEvent we) {
						ctrl.btnSignOutClicked();
					}
				});
				newStage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		btnSignIn.getScene().getWindow().hide();
	}

	@Override
	public void callAfterMessage() {
		String[] result = loginController.lastMsg.split(" ");
		System.out.println("Last Message from client: " + loginController.lastMsg);
		if (loginController.lastMsg.startsWith("login success")) {
			if (result[2].equals("Marketing")) {
				try {
					successLogin(result[2] + " " + result[3]);
				} catch (Exception e) {
				}
			} else {
				try {
					successLogin(result[2]);
				} catch (Exception e) {
				}
			}
		}
		if (loginController.lastMsg.startsWith("login failed"))
			failedLogin();
		if (loginController.lastMsg.startsWith("login already connected"))
			alreadyConnectedLogin();
	}

	public void btnSignInHover() {
		btnSignIn.setStyle("-fx-background-color: #FFA07A ; -fx-background-radius: 7");
	}

	public void btnSignInExit() {
		btnSignIn.setStyle("-fx-background-color:  #F56B2C ; -fx-background-radius: 7");
	}

	public void signIn(ActionEvent event) throws Exception {
		System.out.println("Sign In Clicked");
		String userName = tfLoginUserName.getText();
		String password = tfLoginPassword.getText();

		if (userName.isEmpty() || password.isEmpty()) {
			System.out.println("field is empty");
			lblError.setText("Please fill the required fields");
			lblError.setVisible(true);
			tfLoginUserName.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
			tfLoginPassword.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
		}

		else {
			lblError.setVisible(false);
			tfLoginUserName.setStyle("-fx-border-style: none;");
			tfLoginPassword.setStyle("-fx-border-style: none;");
			System.out.println("go for server");

			loginController.handleMessageFromClientUI(("login" + " " + userName + " " + password));

		}
	}

}
