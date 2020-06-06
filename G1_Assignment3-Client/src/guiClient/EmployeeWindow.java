package guiClient;

import java.sql.Date;
import java.util.ArrayList;

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
 * @version Almost Final
 * @see logActivity()
 * @author Lior
 */
public abstract class EmployeeWindow extends UserWindow {

	@FXML	protected TableView<Activity> tvHomeActivity;

	/*********************** button listeners ***********************/

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

	@Override
	@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
	public void setUserComponents(String username) {
		super.setUserComponents(username);
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
	
	public void logActivity() {
		/**
		 * 
		 */
	}

}
