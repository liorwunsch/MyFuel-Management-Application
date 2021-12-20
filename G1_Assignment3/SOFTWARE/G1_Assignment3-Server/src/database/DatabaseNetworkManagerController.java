package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import entities.MyNetManager;
import entities.PricingModelUpdateRequest;
import enums.PricingModelName;

/**
 * @version Final
 * @author Lior
 *
 */
public class DatabaseNetworkManagerController {

	private static DatabaseNetworkManagerController instance;
	private Connection connection;

	/**
	 * singleton class constructor
	 */
	private DatabaseNetworkManagerController(Connection connection) {
		super();
		this.connection = connection;
	}

	/**
	 * @return instance of this class
	 */
	public static DatabaseNetworkManagerController getInstance(Connection connection) {
		if (instance == null) {
			instance = new DatabaseNetworkManagerController(connection);
		}
		return instance;
	}
	
	/**
	 * 
	 * @param netManager
	 * @return MyNetManager
	 */

	public MyNetManager getAllFuelStationIDs(MyNetManager netManager) {
		try {
			PreparedStatement pStmt = this.connection.prepareStatement("SELECT fuelStationID FROM fuel_station");
			ResultSet rs1 = pStmt.executeQuery();
			if (!rs1.next()) {
				return null;
			}

			ArrayList<Integer> fuelStationIDs = new ArrayList<>();
			do {
				fuelStationIDs.add(rs1.getInt(1));
			} while (rs1.next());
			rs1.close();

			for (int a : fuelStationIDs)
				System.out.print(a);
			System.out.println();

			netManager.setParams(new Object[] { fuelStationIDs, "fuelStationIDs" });
			return netManager;

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
	 * @param netManager
	 * @return MyNetManager
	 */

	public MyNetManager getAllUnAssessedRequests(MyNetManager netManager) {
		try {
			ArrayList<Integer> requestIDs = new ArrayList<>();
			PreparedStatement pStmt = this.connection
					.prepareStatement("SELECT requestID FROM pricing_model_update_request WHERE assessed = 0");
			ResultSet rs1 = pStmt.executeQuery();
			if (!rs1.next()) {
				netManager.setParams(new Object[] { requestIDs, "requestIDs" });
				return netManager;
			}

			do {
				requestIDs.add(rs1.getInt(1));
			} while (rs1.next());
			rs1.close();

			for (int a : requestIDs)
				System.out.print(a);
			System.out.println();

			netManager.setParams(new Object[] { requestIDs, "requestIDs" });
			return netManager;

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
	 * @param netManager
	 * @return MyNetManager
	 */

	public MyNetManager setRequestDeclined(MyNetManager netManager) {
		try {
			String[] splitMsg = netManager.getFunction().split(" ");
			int requestID = Integer.parseInt(splitMsg[1]);
			String declineReason = splitMsg[2];

			PreparedStatement pStmt = this.connection.prepareStatement(
					"UPDATE pricing_model_update_request SET assessed = 1, approved = 0, reasonDismissal = ? WHERE requestID = ?");
			pStmt.setString(1, declineReason);
			pStmt.setInt(2, requestID);
			pStmt.executeUpdate();

			netManager.setParams(new Object[] { new String("Request Decline Success") });
			return netManager;

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
	 * @param netManager
	 * @return MyNetManager
	 */

	public MyNetManager setRequestApproved(MyNetManager netManager) {
		try {
			String[] splitMsg = netManager.getFunction().split(" ");
			int requestID = Integer.parseInt(splitMsg[1]);

			// update request to approved
			PreparedStatement pStmt = this.connection.prepareStatement(
					"UPDATE pricing_model_update_request SET assessed = 1, approved = 1 WHERE requestID = ?");
			pStmt.setInt(1, requestID);
			pStmt.executeUpdate();

			// fetch details of request
			pStmt = this.connection.prepareStatement(
					"SELECT FK_pricingModelName, requestedDiscount FROM pricing_model_update_request WHERE requestID = ?");
			pStmt.setInt(1, requestID);
			ResultSet rs = pStmt.executeQuery();
			if (!rs.next()) {
				return null;
			}
			String pricingModelName = rs.getString(1);
			Double requestedDiscount = rs.getDouble(2);
			rs.close();

			// update new discount of pricing model
			pStmt = this.connection
					.prepareStatement("UPDATE pricing_model_type SET defaultDiscount = ? WHERE pricingModelName = ?");
			pStmt.setDouble(1, requestedDiscount / 100);
			pStmt.setString(2, pricingModelName);
			pStmt.executeUpdate();

			// update new discount for those customers who has this pricing model
			pStmt = this.connection
					.prepareStatement("UPDATE pricing_model SET currentDiscount = ? WHERE FK_pricingModelName = ?");
			pStmt.setDouble(1, requestedDiscount / 100);
			pStmt.setString(2, pricingModelName);
			pStmt.executeUpdate();

			netManager.setParams(new Object[] { new String("Request Approve Success") });
			return netManager;

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
	 * @param netManager
	 * @return MyNetManager
	 */
	public MyNetManager getRequestDetails(MyNetManager netManager) {
		try {
			String[] splitMsg = netManager.getFunction().split(" ");
			int requestID = Integer.parseInt(splitMsg[1]);

			System.out.println("get details of request " + requestID);
			PreparedStatement pStmt = this.connection.prepareStatement(
					"SELECT FK_pricingModelName, requestDate, requestedDiscount FROM pricing_model_update_request WHERE requestID = ?");
			pStmt.setInt(1, requestID);
			ResultSet rs = pStmt.executeQuery();
			if (!rs.next()) {
				return null;
			}
			String pmn = rs.getString(1);
			String pricingModelName = rs.getString(1).replaceAll("\\s", "");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date requestDate = formatter.parse(rs.getString(2));
			Double requestedDiscount = rs.getDouble(3);
			rs.close();

			System.out.println("build request entity with details of request " + requestID);
			PricingModelUpdateRequest pmur = new PricingModelUpdateRequest(requestID);
			pmur.setPricingModelName(PricingModelName.valueOf(pricingModelName));
			pmur.setRequestDate(requestDate);
			pmur.setRequestedDiscount(requestedDiscount);

			System.out.println("get details of pricing model '" + pmn + "'");
			pStmt = this.connection.prepareStatement(
					"SELECT defaultDiscount, description FROM pricing_model_type WHERE pricingModelName = ?");
			pStmt.setString(1, pmn);
			rs = pStmt.executeQuery();
			if (!rs.next()) {
				return null;
			}
			Double defaultDiscount = rs.getDouble(1);
			String description = rs.getString(2);
			rs.close();

			System.out.println("sending full request " + requestID + " with details to client");
			netManager.setParams(new Object[] { pmur, defaultDiscount, description });
			return netManager;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
