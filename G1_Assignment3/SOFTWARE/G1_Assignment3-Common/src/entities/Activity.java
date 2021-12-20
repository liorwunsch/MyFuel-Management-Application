package entities;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Elroy, Vlad, Lior
 */
@SuppressWarnings("serial")
public class Activity implements Serializable {

	// primary keys
	private Integer activityID;

	// foreign keys
	private Integer employeeID;

	// fields
	private Date time;
	private String action;

	/**
	 * without activityID auto-inc, employeeID
	 * 
	 * @param time
	 * @param action
	 */
	public Activity(Date time, String action) {
		super();
		this.time = time;
		this.action = action;
	}

	/**
	 * without activityID auto-inc
	 * 
	 * @param employeeID
	 * @param time
	 * @param action
	 */
	public Activity(int employeeID, Date time, String action) {
		super();
		this.employeeID = employeeID;
		this.time = time;
		this.action = action;
	}

	/**
	 * with activityID
	 * 
	 * @param activityID
	 * @param employeeID
	 * @param time
	 * @param action
	 */
	public Activity(int activityID, int employeeID, Date time, String action) {
		super();
		this.activityID = activityID;
		this.employeeID = employeeID;
		this.time = time;
		this.action = action;
	}

	public int getActivityID() {
		return activityID;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public String toString() {
		String str = "Activity [";
		if (activityID != null)
			str += "activityID=" + activityID + ", ";
		if (employeeID != null)
			str += "employeeID=" + employeeID + ", ";
		str += "time=" + time + ", action=" + action;
		return str + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Activity))
			return false;
		Activity other = (Activity) obj;
		if (this.activityID == null || other.activityID == null)
			return false;
		return this.activityID.equals(other.activityID);
	}

}
