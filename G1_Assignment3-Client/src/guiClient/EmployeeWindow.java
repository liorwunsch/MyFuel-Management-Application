package guiClient;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;

import entities.Activity;
import entities.ActivityList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * boundary for activities of employees in windows
 * <p>
 * all boundaries except login and customer extend this
 * 
 * @version Final
 * @author Lior
 */
public abstract class EmployeeWindow extends UserWindow {

	@FXML
	protected TableView<Activity> tvHomeActivity;

	/*********************** button listeners ***********************/

	@FXML
	void openHome(ActionEvent event) {
		this.visibleNow.setVisible(false);
		this.homePane.setVisible(true);
		this.visibleNow = homePane;
		this.topbar_window_label.setText("Home");
		this.controller.handleMessageFromClientUI(("activity get " + username + " " + cobHomeYear.getValue().toString()
				+ " " + cobHomeMonth.getValue().toString()));
	}

	@FXML
	void btnHomeUpdatePressed(ActionEvent event) {
		this.controller.handleMessageFromClientUI(("activity get " + username + " " + cobHomeYear.getValue().toString()
				+ " " + cobHomeMonth.getValue().toString()));
	}

	/*************** boundary "logic" - window changes ***************/

	@Override
	public void callAfterMessage(Object lastMsgFromServer) {
		super.callAfterMessage(lastMsgFromServer);

		if (lastMsgFromServer instanceof ActivityList) {
			ActivityList activityList = (ActivityList) lastMsgFromServer;
			handleGetActivityListFromServer(activityList);
		}
	}

	/**
	 * initialized tableview in home of employees
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setUserComponents(String username) {
		super.setUserComponents(username);
		final TableColumn<Activity, Date> timeColumn = (TableColumn<Activity, Date>) new TableColumn("Date");
		timeColumn.setCellValueFactory((Callback) new PropertyValueFactory("time"));
		timeColumn.setMinWidth(170);
		timeColumn.setMaxWidth(170);
		this.tvHomeActivity.getColumns().add(timeColumn);
		final TableColumn<Activity, String> actionColumn = (TableColumn<Activity, String>) new TableColumn("Action");
		actionColumn.setCellValueFactory((Callback) new PropertyValueFactory("action"));
		actionColumn.setMinWidth(472);
		actionColumn.setMaxWidth(472);
		this.tvHomeActivity.getColumns().add(actionColumn);

		Calendar calendar = Calendar.getInstance();
		Date now = new Date();
		calendar.setTime(now);

		this.controller.handleMessageFromClientUI(("activity get " + username + " " + (calendar.get(Calendar.YEAR))
				+ " " + (calendar.get(Calendar.MONTH) + 1)));
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

}
