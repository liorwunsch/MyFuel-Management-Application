package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import entities.FastFuel;
import entities.FastFuelList;
import enums.ProductName;

/**
 * controller for customer
 * 
 * @version Basic
 * @author Lior
 */
public class DatabaseCustomerController {

	private static DatabaseCustomerController instance;

	private Connection connection;

	/**
	 * singleton class constructor
	 */
	private DatabaseCustomerController(Connection connection) {
		super();
		this.connection = connection;
	}

	/**
	 * @return instance of this class
	 */
	public static DatabaseCustomerController getInstance(Connection connection) {
		if (instance == null) {
			instance = new DatabaseCustomerController(connection);
		}
		return instance;
	}

	/**
	 * returns a list of all the fast fuels of the customer in year and month
	 * 
	 * @param username
	 * @param year
	 * @param month
	 * @return fast fuel list for server
	 */
	public FastFuelList getFastFuelsSequence(String username, String year, String month) {
		try {
			FastFuelList fastFuelList = new FastFuelList();
			PreparedStatement pStmt = this.connection
					.prepareStatement("SELECT customerID FROM customer WHERE FK_userName = ?");
			pStmt.setString(1, username);
			ResultSet rs1 = pStmt.executeQuery();

			// check if exists in database
			if (!rs1.next())
				throw new SQLException("no customerID with that username: " + username);

			String customerID = rs1.getString(1);
			rs1.close();

			pStmt = this.connection.prepareStatement(
					"SELECT FK_registrationPlate, FK_productInStationID, fastFuelTime, amountBought, finalPrice FROM fast_fuel WHERE FK_customerID = ? AND year(fastFuelTime) = ? AND month(fastFuelTime) = ?");
			pStmt.setString(1, customerID);
			pStmt.setString(2, year);
			pStmt.setString(3, month);
			ResultSet rs2 = pStmt.executeQuery();

			// if there are no rows and table is empty
			if (!rs2.next())
				return fastFuelList;

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			do {
				String registrationPlate = rs2.getString(1);
				int productInStationID = rs2.getInt(2);
				Date fastFuelTime = formatter.parse(rs2.getString(3));
				double amountBought = rs2.getDouble(4);
				double finalPrice = rs2.getDouble(5);
				FastFuel fastFuel = new FastFuel(registrationPlate, customerID, productInStationID, fastFuelTime,
						amountBought, finalPrice);
				fastFuelList.add(fastFuel);
			} while (rs2.next());
			rs2.close();

			for (FastFuel fastFuel : fastFuelList.getFastFuels()) {
				int productInStationID = fastFuel.getProductInStationID();
				PreparedStatement pStmt1 = this.connection.prepareStatement(
						"SELECT FK_productName, FK_fuelStationID FROM product_in_station WHERE productInStationID = ?");
				pStmt1.setInt(1, productInStationID);
				ResultSet rs3 = pStmt1.executeQuery();
				if (!rs3.next())
					return new FastFuelList();

				String productName = rs3.getString(1).replaceAll("\\s","");
				ProductName fuelType = ProductName.valueOf(productName);
				fastFuel.setFuelType(fuelType);

				int fuelStationID = rs3.getInt(2);
				PreparedStatement pStmt2 = this.connection
						.prepareStatement("SELECT stationName FROM fuel_station WHERE fuelStationID = ?");
				pStmt2.setInt(1, fuelStationID);
				ResultSet rs4 = pStmt2.executeQuery();
				if (!rs4.next())
					return new FastFuelList();

				String fuelStationName = rs4.getString(1);
				fastFuel.setFuelStationName(fuelStationName);
			}

			return fastFuelList;

		} catch (SQLException e) {
			e.printStackTrace();
			return new FastFuelList();
		} catch (Exception ex) {
			ex.printStackTrace();
			return new FastFuelList();
		}
	}

}
