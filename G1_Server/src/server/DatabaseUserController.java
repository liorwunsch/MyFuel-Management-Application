package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author Lior - don't change
 *
 */

public class DatabaseUserController {

	private static DatabaseUserController instance;

	private Connection connection;

	public static DatabaseUserController getInstance(Connection conn) {
		if (instance == null) {
			instance = new DatabaseUserController(conn);
		}
		return instance;
	}

	private DatabaseUserController(Connection connection) {
		super();
		this.connection = connection;
	}

	public String loginSequence(String username, String password, String type) {
		PreparedStatement pStmt = null;
		String role = "";

		try {
			if (type.equals("Employee"))
				pStmt = this.connection.prepareStatement("SELECT * FROM Employee WHERE fkUsername = ?");
			if (type.equals("Customer"))
				pStmt = this.connection.prepareStatement("SELECT * FROM Customer WHERE fkUsername = ?");

			pStmt.setString(1, username);
			ResultSet rs1 = pStmt.executeQuery();

			// check if username exists in database
			if (!rs1.next())
				return "login failed - " + type + " username not found";

			rs1.close();

			pStmt = this.connection.prepareStatement("SELECT password, connected FROM User WHERE username = ?");
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
			pStmt = this.connection.prepareStatement("UPDATE User SET connected = ? WHERE userName = ?");
			pStmt.setString(1, "1");
			pStmt.setString(2, username);
			pStmt.executeUpdate();

			if (type.equals("Employee")) {
				pStmt = this.connection.prepareStatement("SELECT role FROM Employee WHERE fkUsername = ?");
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

	public String signOutSequence(String username) {
		try {
			// update connected to false for user
			PreparedStatement pStmt = this.connection
					.prepareStatement("UPDATE User SET connected = ? WHERE username = ?");
			pStmt.setString(1, "0");
			pStmt.setString(2, username);
			pStmt.executeUpdate();

			pStmt = this.connection.prepareStatement("SELECT connected FROM User WHERE username = ?");
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