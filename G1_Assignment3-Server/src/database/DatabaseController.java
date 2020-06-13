package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import entities.ActivityList;
import entities.FastFuelList;
import entities.FuelStationOrder;
import entities.HomeFuelOrder;
import entities.SupplierItemInTable;
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

	/*********************** user controller methods ***********************/

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
	 * 
	 * @param username
	 * @param action
	 * @return message for server
	 */
	public String activityLogger(String username, String action) throws SQLException {
		return DatabaseUserController.getInstance(connection).activityLogger(username, action);
	}

	/**
	 * @param username
	 * @param year
	 * @param month
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

	/*********************** customer controller methods ***********************/

	/**
	 * @param username
	 * @param year
	 * @param month
	 * @return fast fuel list for server
	 */
	public FastFuelList getFastFuelsSequence(String username, String year, String month) {
		return DatabaseCustomerController.getInstance(connection).getFastFuelsSequence(username, year, month);
	}

	/**
	 * 
	 * @return home fuel price for server
	 */
	public Double getHomeFuelPriceSequence() {
		return DatabaseCustomerController.getInstance(connection).getHomeFuelPriceSequence();
	}

	/**
	 * 
	 * @param homeFuelOrder
	 * @return string of success or fail
	 */
	public String setNewHomeFuelSequence(HomeFuelOrder homeFuelOrder) {
		return DatabaseCustomerController.getInstance(connection).setNewHomeFuelSequence(homeFuelOrder);
	}
	
	//vlad added
	public SupplierItemInTable[] getFuelStationOrder(int fuelStationIDs) {
		return DatabaseSupplierController.getInstance(connection).getSupplierItemInTable(fuelStationIDs);
	}
	public void approveFuelStationOrder(int ordersID, double amount) {
		DatabaseSupplierController.getInstance(connection).approveFuelStationOrder(ordersID,amount);
	}

	public Integer[] getFuelStationWithOrder(String username) {
		return DatabaseSupplierController.getInstance(connection).getFuelStationWithOrder(username);
	}
	

}
