package guiClient;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import entities.Activity;
import entities.ActivityList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

/**
 * boundary for sign out requests
 * <p>
 * all boundaries except login extend this
 * 
 * @version Almost Final
 * @see logActivity(), ...
 * @author Elroy, Lior
 */
public abstract class UserWindow extends AFXML {

	@FXML	protected BorderPane main_pane;
	@FXML	protected AnchorPane mainwindow_pane;
	@FXML	protected Label lblHelloUser;
	@FXML	protected Label topbar_window_label;

	@FXML	protected AnchorPane homePane;
	@FXML	protected Label lblHomeUserName;
	@FXML	protected ComboBox<Integer> cobHomeYear;
	@FXML	protected ComboBox<Integer> cobHomeMonth;
	@FXML	protected Button btnHomeUpdate;
	@FXML	protected TableView<Activity> tvHomeActivity;
	@FXML	protected Button btnSignOut;

	protected String username; // the username of the current user of the window

	/**
	 * @param username
	 * @return the window of the boundary
	 */
	public abstract Window getWindow();

	/**
	 * updates <Code>username</Code> to that of the one connnected
	 * 
	 * @param username
	 */
	@SuppressWarnings({ "deprecation", "rawtypes", "unchecked" })
	public void setUserComponents(String username) {
		this.username = username;
		this.lblHelloUser.setText("Hello, " + username);
		this.lblHomeUserName.setText(username + " !");

		this.cobHomeYear.getItems().removeAll((Collection<?>) this.cobHomeYear.getItems());
		this.cobHomeYear.getItems().addAll(new Integer[] { 2019, 2020 });
		this.cobHomeYear.setValue(new java.util.Date().getYear() + 1900);
		this.cobHomeMonth.getItems().removeAll((Collection<?>) this.cobHomeMonth.getItems());
		this.cobHomeMonth.getItems().addAll(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 });
		this.cobHomeMonth.setValue(new java.util.Date().getMonth() + 1);

		final TableColumn<Activity, Date> timeColumn = (TableColumn<Activity, Date>) new TableColumn("Date");
		timeColumn.setCellValueFactory((Callback) new PropertyValueFactory("time"));
		timeColumn.impl_setWidth(200);
		this.tvHomeActivity.getColumns().add(timeColumn);
		final TableColumn<Activity, String> actionColumn = (TableColumn<Activity, String>) new TableColumn("Action");
		actionColumn.setCellValueFactory((Callback) new PropertyValueFactory("action"));
		actionColumn.impl_setWidth(442);
		this.tvHomeActivity.getColumns().add(actionColumn);

		this.controller.handleMessageFromClientUI(("activity get " + username + " "
				+ (new java.util.Date().getYear() + 1900) + " " + (new java.util.Date().getMonth() + 1)));
	}

	/*********************** button listeners ***********************/

	@FXML
	public void closeTopBar(ActionEvent event) {
		if (!this.signOutClicked(this.getWindow()))
			event.consume();
	}

	@FXML
	void Home(ActionEvent event) {
		visableNow.setVisible(false);
		homePane.setVisible(true);
		visableNow = homePane;
		topbar_window_label.setText("Home");
	}

	@FXML
	void btnHomeUpdatePressed(ActionEvent event) {
		this.controller.handleMessageFromClientUI(("activity get " + username + " " + cobHomeYear.getValue().toString()
				+ " " + cobHomeMonth.getValue().toString()));
	}

	@FXML
	void btnSignOutClicked(ActionEvent event) {
		this.signOutClicked(this.getWindow());
	}

	/*********************** button functions ***********************/

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
			this.controller.setCurrentWindow(this);
			this.controller.handleMessageFromClientUI("signout " + this.username);
			return true;
		}
		if (result.get() == buttonTypeTwo)
			alert.hide();
		return false;
	}

	/*************** boundary "logic" - window changes ***************/

	@Override
	public void callAfterMessage(Object lastMsgFromServer) {
		if (lastMsgFromServer instanceof String) {
			String message = (String) lastMsgFromServer;
			if (message.startsWith("sign out"))
				handleSignOutFromServer(message, this.getWindow());
		}
		if (lastMsgFromServer instanceof ActivityList) {
			ActivityList activityList = (ActivityList) lastMsgFromServer;
			handleGetActivityListFromServer(activityList);
		}
	}

	public void logActivity() {
		/**
		 * 
		 */
	}

	/**
	 * fills the tableview in home with activities
	 * 
	 * @param activityList
	 */
	public void handleGetActivityListFromServer(ActivityList activityList) {
		final ObservableList<Activity> list = FXCollections.observableArrayList();
		for (int i = 0; i < this.tvHomeActivity.getItems().size(); ++i) {
			this.tvHomeActivity.getItems().clear();
		}

		ArrayList<Activity> activities = activityList.getActivities();
		for (Activity activity : activities) {
			list.add(activity);
		}
		this.tvHomeActivity.setItems(list);
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
			loader.setLocation(getClass().getResource("/windows/LoginWindow.fxml"));
			Scene newScene = new Scene(loader.load());
			Stage newStage = new Stage();

			newStage.setResizable(false);
			newStage.setScene(newScene);
			newStage.setTitle("MyFuel Login");
			newStage.initStyle(StageStyle.UNDECORATED);
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
