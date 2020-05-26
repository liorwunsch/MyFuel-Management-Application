package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import guiServer.ServerWindow;

/**
 * controller for database
 * 
 * @version N Methods To Final
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
			this.connection = DriverManager.getConnection("jdbc:mysql://" + host + "/" + schema + "?serverTimezone=IST",
					dbUsername, dbPassword);
			serverWindow.updateArea("SQL connection succeeded");

			PreparedStatement pStmt;
			pStmt = this.connection.prepareStatement("UPDATE User SET connected = ?");
			pStmt.setString(1, "0");
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

	public String loginSequence(String username, String password, String type) {
		return DatabaseUserController.getInstance(connection).loginSequence(username, password, type);
	}

	public String signOutSequence(String username) {
		return DatabaseUserController.getInstance(connection).signOutSequence(username);
	}

}
