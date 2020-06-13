package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.FuelStationOrder;
import entities.SupplierItemInTable;

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

	public SupplierItemInTable[] getSupplierItemInTable(int fuelStationIDs) {
		List<SupplierItemInTable> siitList = new ArrayList<>();
		try {
			String sql = "SELECT fs.fuelStationID, o.ordersID, o.orderTime, p.productName, o.amountBought, fs.address " + 
					"FROM  fuel_station fs, product_in_station pis ,fuel_station_order fso, fuel_company fc, orders o , product p " + 
					"WHERE fs.fuelStationID = ? AND fso.approved = 1 AND fso.supplied = 0 AND pis.FK_productName = p.productName " + 
					"AND fc.fuelCompanyName = fs.FK_fuelCompanyName AND pis.FK_fuelStationID = fs.fuelStationID " + 
					"AND pis.productInStationID = fso.FK_productInStationID AND fso.FK_ordersID = o.ordersID;";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, fuelStationIDs);
			ResultSet qr = ps.executeQuery();
			while (qr.next()) {
				Integer fuelStationID = qr.getInt(1);
				Integer orderID = qr.getInt(2);
				Date orderTime = qr.getDate(3);
				String productName = qr.getString(4);
				Double amount = qr.getDouble(5);
				String address = qr.getString(6);
				SupplierItemInTable siit = new SupplierItemInTable(fuelStationID, orderID, orderTime, productName, amount, address);
				siitList.add(siit);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		SupplierItemInTable[] siitA = new SupplierItemInTable[siitList.size()];
		siitA = siitList.toArray(siitA);
		return siitA;
	}

	public void approveFuelStationOrder(int ordersID,double amount) {
		//update fuel station order
		int FK_employeeID;	//manager id station 
		try {
			String sql = 	"UPDATE fuel_station_order AS fso " + 
					"SET fso.supplied = 1 , fso.timeSupplied = NOW() " + 
					"WHERE fso.FK_ordersID = ? ;";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, ordersID);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		//select fuel station manger of cur station that the order got from
		try {
			String sql = 	"SELECT fs.FK_employeeID " + 
					"FROM fuel_station_order fso, product_in_station pis, fuel_station fs " + 
					"WHERE fso.FK_ordersID = ? AND fso.FK_productInStationID = pis.productInStationID " + 
					"AND pis.FK_fuelStationID = fs.fuelStationID ;";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, ordersID);
			ResultSet qr = ps.executeQuery();
			qr.next();
			FK_employeeID = qr.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		//update fuel station product capacity
		try {
			String sql = "UPDATE fuel_station_order fso, product_in_station pis " + 
					"SET pis.capacity = pis.capacity + ? " + 
					"WHERE fso.FK_ordersID = ? AND fso.FK_productInStationID = pis.productInStationID ;";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setDouble(1, amount);
			ps.setInt(2, ordersID);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		//create notification
		Object[] values1 = {FK_employeeID, "fuelstation order " + ordersID + " is supplied.", false, new Date(System.currentTimeMillis())};
		try {
			TableInserts.insertNotification(connection, values1);
		}catch (SQLException e) {
			e.printStackTrace();
			return;
		}

	}

	public Integer[] getFuelStationWithOrder(String username) {
		List<Integer> fsL = new ArrayList<>();
		try {
			String sql = "SELECT DISTINCT fs.fuelStationID " + 
					"FROM  employee e, fuel_station fs, product_in_station pis ,fuel_station_order fso, fuel_company fc, orders o " + 
					"WHERE e.FK_userName = ? AND fso.approved = 1 "
					+ "AND fso.supplied = 0 AND e.employeeID  = fc.FK_employeeID  " + 
					"AND fc.fuelCompanyName = fs.FK_fuelCompanyName AND pis.FK_fuelStationID = fs.fuelStationID " + 
					"AND pis.productInStationID = fso.FK_productInStationID AND fso.FK_ordersID = o.ordersID; " ;
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet qr = ps.executeQuery();
			while (qr.next()) {
				Integer fuelStationID = qr.getInt(1);
				fsL.add(fuelStationID);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		Integer[] fsA = new Integer[fsL.size()];
		fsA = fsL.toArray(fsA);
		return fsA;
	}
}
