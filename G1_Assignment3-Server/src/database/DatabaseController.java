package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import entities.ActivityList;
import guiServer.ServerWindow;

/**
 * controller for database
 * 
 * @version N Methods To Final
 * @see activityLogger(), + methods of other database controllers
 * @author Elroy, Lior
 */
public class DatabaseController {

	private static DatabaseController instance;
	private Connection connection;

	/**
	 * singleton class constructor initialize connection to the database
	 * <p>
	 * happens once and before everything done on the server
	 */
	private DatabaseController(ServerWindow serverWindow, String host, String schema, String dbUsername,
			String dbPassword) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			serverWindow.updateArea("Driver definition succeeded");
		} catch (Exception e) {
			serverWindow.updateArea("Driver definition failed");
		}

		try {
			this.connection = DriverManager.getConnection("jdbc:mysql://" + host + "/?serverTimezone=IST", dbUsername,
					dbPassword);
			serverWindow.updateArea("SQL connection succeeded");

			Statement stmt = this.connection.createStatement();
			stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + schema
					+ " DEFAULT CHARACTER SET utf8 \n DEFAULT COLLATE utf8_general_ci");
			serverWindow.updateArea("Database connection succeeded");

			this.connection = DriverManager.getConnection("jdbc:mysql://" + host + "/" + schema + "?serverTimezone=IST",
					dbUsername, dbPassword);

			serverWindow.updateArea(TableGenerator.GenerateTables(this.connection));
			serverWindow.updateArea(DefaultTableInserts.InsertDefaultTables(this.connection));

			PreparedStatement pStmt;
			pStmt = this.connection.prepareStatement("SET SQL_SAFE_UPDATES = 0");
			pStmt.executeUpdate();
			pStmt = this.connection.prepareStatement("UPDATE user SET connected = 0");
			pStmt.executeUpdate();

		} catch (SQLException e) {
			serverWindow.updateArea("SQLException: " + e.getMessage());
			serverWindow.updateArea("SQLState: " + e.getSQLState());
			serverWindow.updateArea("VendorError: " + e.getErrorCode());
		}
	}

	/**
	 * @return instance of this class
	 */
	public static DatabaseController getInstance(ServerWindow serverWindow, String host, String schema,
			String dbUsername, String dbPassword) {
		if (instance == null) {
			instance = new DatabaseController(serverWindow, host, schema, dbUsername, dbPassword);
		}
		return instance;
	}

	/**
	 * insert to database activity table - the current action of employee
	 * 
	 * @param username
	 * @param action
	 * @throws SQLException
	 */
	@SuppressWarnings({ "unused" })
	private void activityLogger(String username, String action) throws SQLException {
		PreparedStatement pStmt;
		pStmt = this.connection.prepareStatement("SELECT employeeID FROM employee WHERE FK_userName = ?");
		pStmt.setString(1, username);
		ResultSet rs1 = pStmt.executeQuery();
		int employeeID = rs1.getInt(1);

		Object[] values1 = { employeeID, new Date(), action };
		TableInserts.insertActivity(connection, values1);
	}

	/**
	 * @param username
	 * @param password
	 * @param type
	 * @return message for server
	 */
	public String loginSequence(String username, String password, String type) {
		return DatabaseUserController.getInstance(connection).loginSequence(username, password, type);
	}

	/**
	 * @param username
	 * @return activity list for server
	 */
	public ActivityList getActivitiesSequence(String username, String year, String month) {
		return DatabaseUserController.getInstance(connection).getActivitiesSequence(username, year, month);
	}
	
	/**
	 * @param username
	 * @return message for server
	 */
	public String signOutSequence(String username) {
		return DatabaseUserController.getInstance(connection).signOutSequence(username);
	}

}
