package entities;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class ActivityList implements Serializable {

	private ArrayList<Activity> activities;

	public ActivityList() {
		this.activities = new ArrayList<Activity>();
	}

	public ArrayList<Activity> getActivities() {
		return this.activities;
	}

	public void setActivities(ArrayList<Activity> activities) {
		this.activities = activities;
	}

	public void add(Activity activity) {
		this.activities.add(activity);
	}

}
