package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import guiServer.ServerWindow;

/**
 * 
 * @author Lior - add functions routing to other database***controllers
 *
 */

public class DatabaseController {

	private static DatabaseController instance;

	private Connection connection;

	public static DatabaseController getInstance() {
		return instance;
	}

	public static DatabaseController getInstance(ServerWindow serverWindow, String host, String schema,
			String dbUsername, String dbPassword) {
		if (instance == null) {
			instance = new DatabaseController(serverWindow, host, schema, dbUsername, dbPassword);
		}
		return instance;
	}

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
		} catch (SQLException e) {
			serverWindow.updateArea("SQLException: " + e.getMessage());
			serverWindow.updateArea("SQLState: " + e.getSQLState());
			serverWindow.updateArea("VendorError: " + e.getErrorCode());
		}
	}

	public String loginSequence(String username, String password, String type) {
		return DatabaseUserController.getInstance(connection).loginSequence(username, password, type);
	}

	public String signOutSequence(String username) {
		return DatabaseUserController.getInstance(connection).signOutSequence(username);
	}

}
