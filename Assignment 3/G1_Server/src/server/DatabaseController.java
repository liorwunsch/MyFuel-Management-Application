package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import guiServer.ServerWindow;

public class DatabaseController {
	private Connection conn;
	private ServerWindow serverWindowController;

	// a new instance of DatabaseController automatically connects to the database
	public DatabaseController(ServerWindow serverWindowController) {
		this.serverWindowController = serverWindowController;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			System.out.println("Driver definition succeeded");
			serverWindowController.updateArea("Driver definition succeeded");
		} catch (Exception e) {
			System.out.println("Driver definition failed");
			serverWindowController.updateArea("Driver definition failed");
		}

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/test?serverTimezone=IST", "root", "Aa123456");
			System.out.println("SQL connection succeeded\n");
			serverWindowController.updateArea("SQL connection succeeded");

		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			serverWindowController.updateArea("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			serverWindowController.updateArea("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
			serverWindowController.updateArea("VendorError: " + e.getErrorCode());
		}
	}

	// check correctness of such users
	// failure message back that login failed
	public String loginSequence(String userName, String password) {
		serverWindowController.updateArea("looking user in DB");
		try {
			// then, execute the query for getting all employees
//			Statement stmt = conn.createStatement();
			PreparedStatement pStmt = conn.prepareStatement(
					"SELECT userName,password,type,connected FROM Users WHERE userName=? And password=?");
			pStmt.setString(1, userName);
			pStmt.setString(2, password);
			ResultSet rs = pStmt.executeQuery();
			boolean flag = false;
			String type = "";
			// check that the update did occur
			if (rs.next() && rs.getString(1).equals(userName) && rs.getString(2).equals(password)) {
				if (rs.getString(4).equals("1"))
					return "login already connected";
				pStmt = conn.prepareStatement("UPDATE Users " + "SET connected = ? WHERE userName = ?");
				pStmt.setString(1, "1");
				pStmt.setString(2, userName);
				pStmt.executeUpdate();
				flag = true;
				type = rs.getString(3);
			}
			rs.close();

			// if table is empty, return a message that it is
			if (flag == false)
				return "login failed";
			return "login success" + " " + type;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			serverWindowController.updateArea("login failed, please contact the developers");
			return "login failed, please contact the developers";
		}
	}

	public String signOutSequence(String userName) {
		boolean check = false;
		try {
			// then, execute the query for getting all employees
			PreparedStatement pStmt = conn.prepareStatement("UPDATE Users SET connected = ? WHERE userName = ?");
			pStmt.setString(1, "0");
			pStmt.setString(2, userName);
			pStmt.executeUpdate();

			pStmt = conn.prepareStatement("SELECT connected FROM Users WHERE userName=?");
			pStmt.setString(1, userName);
			ResultSet rs2 = pStmt.executeQuery();
			if (!rs2.next())
				return "sign out failed, not found";

			// check that the update did occur
			if (rs2.getString(1).equals("0"))
				check = true;

		} catch (SQLException e) {
			System.out.println("Update Connected Failed");
			serverWindowController.updateArea("Update Connected Failed");
			System.out.println(e.getMessage());
			return "sign out failed";
		}
		if (check == false) {
			System.out.println("Update Connected Failed");
			serverWindowController.updateArea("Update Connected Failed");
			return "sign out failed";
		}
		return "sign out complete";

	}

//	// returns all employee table's rows as a structural string
//	public String printEmployees() {
//		StringBuilder b = new StringBuilder();
//		DatabaseMetaData dbm;
//		Statement stmt;
//
//		try {
//			// first, check if table exists in the database at all
//			dbm = (DatabaseMetaData) conn.getMetaData();
//			ResultSet tables = dbm.getTables(null, null, "Employee", null);
//			if (!tables.next())
//				return "Employees table doesn't exist";
//
//			// then, execute the query for getting all employees
//			stmt = conn.createStatement();
//			ResultSet rs = stmt.executeQuery("SELECT * FROM Employee");
//			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
//			int columnsNumber = rsmd.getColumnCount();
//
//			boolean flag = false;
//			while (rs.next()) {
//				flag = true;
//				for (int i = 1; i <= columnsNumber; i++)
//					b.append(rs.getString(i) + "\t\t");
//			}
//			rs.close();
//
//			// if table is empty, return a message that it is
//			if (flag == false)
//				return "Employees table empty";
//
//		} catch (SQLException e) {
//			System.out.println(e.getMessage());
//			serverWindowController.updateArea("Print employees failed, please contact the developers");
//			return "Print employees failed, please contact the developers";
//		}
//
//		return "success\t\t" + b.toString();
//	}

	// update employee's role in the database and return either a
	// failure message or the updated table as a string
//	public String updateEmployeeRole(String employee_id, String new_role) {
//		boolean check = false;
//		PreparedStatement pStmt;
//		Statement stmt;
//
//		if (new_role.equals("Assistant"))
//			new_role += " Manager";
//		if (new_role.equals("Network"))
//			new_role += " Manager";
//
//		try {
//			stmt = conn.createStatement();
//
//			// first, check that employeeID exists in the database
//			ResultSet rs = stmt.executeQuery("SELECT * FROM Employee WHERE employeeID=" + employee_id);
//			if (!rs.next())
//				return "Employee ID not found";
//
//			// then, update the database with a prepared statement
//			pStmt = conn.prepareStatement("UPDATE Employee " + "SET role = ? WHERE employeeID = ?");
//			pStmt.setString(1, new_role);
//			pStmt.setString(2, employee_id);
//			pStmt.executeUpdate();
//			ResultSet rs2 = stmt.executeQuery("SELECT role FROM Employee WHERE employeeID=" + employee_id);
//			if (!rs2.next())
//				return "Employee ID not found";
//
//			// check that the update did occur
//			if (rs2.getString(1).equals(new_role))
//				check = true;
//
//		} catch (SQLException e) {
//			System.out.println("Update Employee Role Failed");
//			serverWindowController.updateArea("Update Employee Role Failed");
//			System.out.println(e.getMessage());
//			return "Update employee role failed, please contact the developers";
//		}
//
//		// if check went wrong, then something with the update didn't occur
//		if (check == false) {
//			System.out.println("Update Employee Role Failed");
//			serverWindowController.updateArea("Update Employee Role Failed");
//			return "Update employee role failed, please contact the developers";
//		}
//
//		return printEmployees();
//	}

}
