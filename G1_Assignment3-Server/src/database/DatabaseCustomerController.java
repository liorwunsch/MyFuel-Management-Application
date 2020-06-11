package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import entities.FastFuel;
import entities.FastFuelList;
import entities.HomeFuelOrder;
import entities.HomeFuelOrderList;
import entities.PurchasingProgramType;
import enums.ProductName;
import enums.PurchasingProgramName;
import enums.ShipmentType;

/**
 * controller for customer
 * 
 * @version Final
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
	 * fetch customerID of username
	 */
	private String getCustomerIDbyUsername(String username) {
		try {
			PreparedStatement pStmt = this.connection
					.prepareStatement("SELECT customerID FROM customer WHERE FK_userName = ? AND deleted = 0");
			pStmt.setString(1, username);
			ResultSet rs1 = pStmt.executeQuery();

			if (!rs1.next())
				throw new SQLException("no customerID with that username: " + username);

			String customerID = rs1.getString(1);
			rs1.close();
			return customerID;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
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
			String customerID = getCustomerIDbyUsername(username);
			if (customerID == null)
				return null;

			PreparedStatement pStmt = this.connection.prepareStatement(
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
					return null;

				String productName = rs3.getString(1).replaceAll("\\s", "");
				ProductName fuelType = ProductName.valueOf(productName);
				fastFuel.setFuelType(fuelType);

				int fuelStationID = rs3.getInt(2);
				PreparedStatement pStmt2 = this.connection
						.prepareStatement("SELECT stationName FROM fuel_station WHERE fuelStationID = ?");
				pStmt2.setInt(1, fuelStationID);
				ResultSet rs4 = pStmt2.executeQuery();
				if (!rs4.next())
					return null;

				String fuelStationName = rs4.getString(1);
				fastFuel.setFuelStationName(fuelStationName);
			}

			return fastFuelList;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @return home fuel price for server
	 */
	public Double getHomeFuelPriceSequence() {
		try {
			PreparedStatement pStmt = this.connection
					.prepareStatement("SELECT currentPrice FROM product WHERE productName = ?");
			pStmt.setString(1, "Home Fuel");
			ResultSet rs1 = pStmt.executeQuery();

			// check if exists in database
			if (!rs1.next())
				throw new SQLException("no home fuel product in db");

			Double price = rs1.getDouble(1);
			rs1.close();

			return price;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @param homeFuelOrder
	 * @return string of success or fail
	 */
	public String setNewHomeFuelSequence(HomeFuelOrder homeFuelOrder) {
		try {
			String customerID = getCustomerIDbyUsername(homeFuelOrder.getCustomerID());
			if (customerID == null)
				return "set homefuelorder fail";

			// "orderTime", "amountBought", "address"
			Object[] values1 = { homeFuelOrder.getOrderTime(), homeFuelOrder.getAmountBought(),
					homeFuelOrder.getAddress() };
			TableInserts.insertOrders(connection, values1);

			PreparedStatement pStmt = this.connection.prepareStatement("SELECT MAX(ordersID) FROM orders");
			ResultSet rs2 = pStmt.executeQuery();
			if (!rs2.next())
				return "set homefuelorder fail";
			int ordersID = rs2.getInt(1);
			rs2.close();

			// "FK_ordersID", "FK_customerID", "FK_product_Name", "FK_shipmentType",
			// "duetime", "finalPrice"
			Object[] values2 = { ordersID, customerID, homeFuelOrder.getProductName().toString(),
					homeFuelOrder.getShipmentMethod().toString(), homeFuelOrder.getDueTime(),
					homeFuelOrder.getFinalPrice() };
			TableInserts.insertHomeFuelOrder(connection, values2);
			return "set homefuelorder success";

		} catch (SQLException e) {
			e.printStackTrace();
			return "set homefuelorder fail";
		} catch (Exception ex) {
			ex.printStackTrace();
			return "set homefuelorder fail";
		}
	}

	/**
	 * 
	 * @param username
	 * @return purchasing program of customer with that username
	 */
	public PurchasingProgramType getPurchasingProgramSequence(String username) {
		try {
			String customerID = getCustomerIDbyUsername(username);
			if (customerID == null)
				return null;

			PreparedStatement pStmt = this.connection.prepareStatement(
					"SELECT FK_purchasingProgramName FROM purchasing_program WHERE FK_customerID = ?");
			pStmt.setString(1, customerID);
			ResultSet rs1 = pStmt.executeQuery();
			if (!rs1.next())
				return null;
			String purchasingProgramName = rs1.getString(1);
			rs1.close();

			pStmt = this.connection.prepareStatement(
					"SELECT monthlyPrice FROM purchasing_program_type WHERE purchasingProgramName = ?");
			pStmt.setString(1, purchasingProgramName);
			ResultSet rs2 = pStmt.executeQuery();
			if (!rs2.next())
				return null;
			Double monthlyPrice = rs2.getDouble(1);
			rs2.close();

			return new PurchasingProgramType(PurchasingProgramName.valueOf(purchasingProgramName), "a", monthlyPrice);

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @param string
	 * @return home fuel orders of customer with that username
	 */
	public HomeFuelOrderList getHomeFuelOrdersSequence(String username) {
		try {
			HomeFuelOrderList homeFuelOrderList = new HomeFuelOrderList();
			String customerID = getCustomerIDbyUsername(username);
			if (customerID == null)
				return null;

			PreparedStatement pStmt = this.connection.prepareStatement(
					"SELECT FK_ordersID, FK_product_Name, FK_shipmentType, duetime, finalPrice FROM home_fuel_order WHERE FK_customerID = ?");
			pStmt.setString(1, customerID);
			ResultSet rs2 = pStmt.executeQuery();

			// if there are no rows and table is empty
			if (!rs2.next())
				return homeFuelOrderList;

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			do {
				int ordersID = rs2.getInt(1);
				String productNameStr = rs2.getString(2).replaceAll("\\s", "");
				ProductName productName = ProductName.valueOf(productNameStr);
				ShipmentType shipmentMethod = ShipmentType.valueOf(rs2.getString(3));
				Date dueTime = formatter.parse(rs2.getString(4));
				double finalPrice = rs2.getDouble(5);

				PreparedStatement pStmt1 = this.connection
						.prepareStatement("SELECT orderTime, amountBought, address FROM orders WHERE ordersID = ?");
				pStmt1.setInt(1, ordersID);
				ResultSet rs3 = pStmt1.executeQuery();
				if (!rs3.next())
					return null;

				Date orderTime = formatter.parse(rs3.getString(1));
				double amountBought = rs3.getDouble(2);
				String address = rs3.getString(3);

				HomeFuelOrder homeFuelOrder = new HomeFuelOrder(ordersID, orderTime, amountBought, address, customerID,
						productName, shipmentMethod, dueTime, finalPrice);
				homeFuelOrderList.add(homeFuelOrder);
				rs3.close();

			} while (rs2.next());
			rs2.close();

			return homeFuelOrderList;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @param username
	 * @param password
	 * @return string of success or fail
	 */
	public String updatePassword(String username, String password) {
		try {
			PreparedStatement pStmt = this.connection
					.prepareStatement("UPDATE user SET password = ? WHERE username = ?");
			pStmt.setString(1, password);
			pStmt.setString(2, username);
			pStmt.executeUpdate();
			return "update password success";

		} catch (SQLException e) {
			e.printStackTrace();
			return "update password fail";
		} catch (Exception ex) {
			ex.printStackTrace();
			return "update password fail";
		}
	}

}
