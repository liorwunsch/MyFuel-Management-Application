package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.FuelStationOrder;

public class DatabaseSupplierController {

	private static DatabaseSupplierController instance;
	private Connection connection;

	/**
	 * singleton class constructor
	 */
	private DatabaseSupplierController(Connection connection) {
		super();
		this.connection = connection;
	}

	/**
	 * @return instance of this class
	 */
	public static DatabaseSupplierController getInstance(Connection connection) {
		if (instance == null) {
			instance = new DatabaseSupplierController(connection);
		}
		return instance;
	}

	public FuelStationOrder[] getFuelStationOrder(String username) {
		List<FuelStationOrder> fsoList = new ArrayList<>();
		try {
			String sql = "SELECT fso.*, o.* "
					+ "FROM  employee e, fuel_station fs, product_in_station pis ,fuel_station_order fso, fuel_company fc, orders o "
					+ "WHERE e.FK_userName = ? AND e.employeeID  = fc.FK_employeeID "
					+ "AND fc.fuelCompanyName = fs.FK_fuelCompanyName AND pis.FK_fuelStationID = fs.fuelStationID "
					+ "AND pis.productInStationID = fso.FK_productInStationID AND fso.FK_ordersID = o.ordersID AND fso.approved = 0 ;";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet qr = ps.executeQuery();
			while (qr.next()) {
				int ordersID = qr.getInt(1);
				Date orderTime = qr.getDate(9);
				double amountBought = qr.getDouble(10);
				String address = qr.getString(11);
				int productInStaionID = qr.getInt(2);
				boolean assessed = qr.getBoolean(3);
				boolean approved = qr.getBoolean(4);
				String reasonDismissal = qr.getString(5);
				boolean supplied = qr.getBoolean(6);
				Date timeSupplied = qr.getDate(7);
				FuelStationOrder fso = new FuelStationOrder(ordersID, orderTime, amountBought, address,
						productInStaionID, assessed, approved, reasonDismissal, supplied, timeSupplied);
				fsoList.add(fso);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		FuelStationOrder[] fsoA = new FuelStationOrder[fsoList.size()];
		fsoA = fsoList.toArray(fsoA);
		return fsoA;
	}

	public void approveFuelStationOrder(int ordersID) {
		try {
			String sql = 	"UPDATE fuel_station_order AS fso " + 
							"SET fso.approved = 1 " + 
							"WHERE fso.FK_ordersID = ? ;";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, ordersID);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}

	}
}
