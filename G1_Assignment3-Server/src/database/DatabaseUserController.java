package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import entities.Activity;
import entities.ActivityList;

/**
 * controller for client login and signout on database
 * 
 * @version Final
 * @author Elroy, Lior
 */
public class DatabaseUserController {

	private static DatabaseUserController instance;

	private Connection connection;

	/**
	 * singleton class constructor
	 */
	private DatabaseUserController(Connection connection) {
		super();
		this.connection = connection;
	}

	/**
	 * @return instance of this class
	 */
	public static DatabaseUserController getInstance(Connection connection) {
		if (instance == null) {
			instance = new DatabaseUserController(connection);
		}
		return instance;
	}

	/**
	 * executes queries to handle login request from the client
	 * 
	 * @param username
	 * @param password
	 * @param type
	 * @return result of request from the database as string
	 */
	public String loginSequence(String username, String password, String type) {
		PreparedStatement pStmt = null;
		String role = "";

		try {
			if (type.equals("Employee"))
				pStmt = this.connection.prepareStatement("SELECT * FROM employee WHERE FK_userName = ?");
			if (type.equals("Customer"))
				pStmt = this.connection
						.prepareStatement("SELECT * FROM customer WHERE FK_userName = ? AND deleted = 0");

			pStmt.setString(1, username);
			ResultSet rs1 = pStmt.executeQuery();

			// check if username exists in database
			if (!rs1.next())
				return "login failed - " + type + " username not found";

			rs1.close();

			pStmt = this.connection.prepareStatement("SELECT password, connected FROM user WHERE username = ?");
			pStmt.setString(1, username);
			ResultSet rs2 = pStmt.executeQuery();

			// check if username exists in database
			if (!rs2.next())
				return "login failed - " + type + " username not found";

			// check if user already connected
			String connected = rs2.getString(2);
			if (connected.equals("1"))
				return "login already connected";

			// check if password matches
			String actualPassword = rs2.getString(1);
			if (!password.equals(actualPassword))
				return "login failed - password incorrect";

			rs2.close();

			// user is valid - update connected to true
			pStmt = this.connection.prepareStatement("UPDATE user SET connected = ? WHERE username = ?");
			pStmt.setString(1, "1");
			pStmt.setString(2, username);
			pStmt.executeUpdate();

			if (type.equals("Employee")) {
				pStmt = this.connection.prepareStatement("SELECT role FROM Employee WHERE FK_userName = ?");
				pStmt.setString(1, username);
				ResultSet rs4 = pStmt.executeQuery();

				if (!rs4.next())
					return "login failed - " + type + " username not found";
				role = rs4.getString(1);
				rs4.close();
			}

			if (type.equals("Customer"))
				role = "Customer";

			return "login succeeded " + role;

		} catch (SQLException e) {
			e.printStackTrace();
			return "login failed - please contact the developers";
		}
	}

	/**
	 * insert to database activity table - the current action of employee
	 * 
	 * @param username
	 * @param action
	 * @return message for server
	 */
	@SuppressWarnings("deprecation")
	public String activityLogger(String username, String[] action) {
		try {
			PreparedStatement pStmt;
			pStmt = this.connection.prepareStatement("SELECT employeeID FROM employee WHERE FK_userName = ?");
			pStmt.setString(1, username);
			ResultSet rs1 = pStmt.executeQuery();
			if (!rs1.next())
				return "activityLogger failed";
			int employeeID = rs1.getInt(1);

			Date now = new Date();
			now.setHours(now.getHours() - 3);
			now.setMinutes(now.getMinutes() + 30);

			StringBuilder myAction = new StringBuilder("");
			for (int i = 3; i < action.length; i++)
				myAction.append(action[i] + " ");

			Object[] values1 = { employeeID, now, myAction.toString() };
			TableInserts.insertActivity(connection, values1);
			return "activityLogger succeeded";

		} catch (SQLException e) {
			return "activityLogger failed";
		}
	}

	/**
	 * returns a list of all the activities of the employee in year and month
	 * 
	 * @param username
	 * @param year
	 * @param month
	 * @return activity list of username
	 */
	public ActivityList getActivitiesSequence(String username, String year, String month) {
		try {
			ActivityList activityList = new ActivityList();
			PreparedStatement pStmt = this.connection
					.prepareStatement("SELECT employeeID FROM employee WHERE FK_userName = ?");
			pStmt.setString(1, username);
			ResultSet rs1 = pStmt.executeQuery();

			// check if exists in database
			if (!rs1.next())
				throw new SQLException("no employeeID with that username: " + username);

			String employeeID = rs1.getString(1);
			rs1.close();

			pStmt = this.connection.prepareStatement(
					"SELECT time, action FROM activity WHERE FK_employeeID = ? AND year(time) = ? AND month(time) = ?");
			pStmt.setString(1, employeeID);
			pStmt.setString(2, year);
			pStmt.setString(3, month);
			ResultSet rs2 = pStmt.executeQuery();

			// if there are no rows and table is empty
			if (!rs2.next())
				return activityList;

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			do {
				Date time = formatter.parse(rs2.getString(1));
				String action = rs2.getString(2);
				Activity activity = new Activity(time, action);
				activityList.add(activity);
			} while (rs2.next());
			rs2.close();

			return activityList;

		} catch (SQLException e) {
			e.printStackTrace();
			return new ActivityList();
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ActivityList();
		}
	}

	/**
	 * executes queries to handle signout request from the client
	 * 
	 * @param username
	 * @return result of request from the database as string
	 */
	public String signOutSequence(String username) {
		try {
			// update connected to false for user
			PreparedStatement pStmt = this.connection
					.prepareStatement("UPDATE user SET connected = ? WHERE username = ?");
			pStmt.setString(1, "0");
			pStmt.setString(2, username);
			pStmt.executeUpdate();

			pStmt = this.connection.prepareStatement("SELECT connected FROM user WHERE username = ?");
			pStmt.setString(1, username);
			ResultSet rs1 = pStmt.executeQuery();

			// check if username exists in database
			if (!rs1.next())
				return "sign out failed - username not found";

			rs1.close();
			return "sign out succeeded";

		} catch (SQLException e) {
			e.printStackTrace();
			return "sign out failed - please contact the developers";
		}
	}

}
