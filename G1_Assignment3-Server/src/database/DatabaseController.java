package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import entities.ActivityList;
import entities.Car;
import entities.CarList;
import entities.Customer;
import entities.FastFuel;
import entities.FastFuelList;
import entities.HomeFuelOrder;
import entities.HomeFuelOrderList;
import entities.PeriodicReportList;
import entities.PricingModel;
import entities.ProductInSalePatternList;
import entities.ProductRateList;
import entities.PurchasingProgram;
import entities.PurchasingProgramType;
import entities.RankingSheetList;
import entities.SaleCommentReportList;
import entities.SalesList;
import entities.SalesPatternList;
import entities.User;
import guiServer.ServerWindow;

/**
 * controller for database
 * 
 * @version N Methods To Final
 * @see methods of other database controllers
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
			this.connection = DriverManager.getConnection("jdbc:mysql://" + host + "/?serverTimezone=Asia/Jerusalem",
					dbUsername, dbPassword);
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
	public String activityLogger(String username, String[] action) {
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

	/**
	 * 
	 * @param username
	 * @return purchasing program of customer with that username
	 */
	public PurchasingProgramType getPurchasingProgramSequence(String username) {
		return DatabaseCustomerController.getInstance(connection).getPurchasingProgramSequence(username);
	}

	/**
	 * 
	 * @param string
	 * @return home fuel orders of customer with that username
	 */
	public HomeFuelOrderList getHomeFuelOrdersSequence(String username) {
		return DatabaseCustomerController.getInstance(connection).getHomeFuelOrdersSequence(username);
	}

	/**
	 * 
	 * @param username
	 * @param password
	 * @return string of success or fail
	 */
	public String updatePassword(String username, String password) {
		return DatabaseCustomerController.getInstance(connection).updatePassword(username, password);
	}

	/************* marketing representative controller methods **************/

	/**
	 * 
	 * @param user
	 * @param customer
	 * @return string of success or fail
	 */
	public String saveNewCustomerSequence(User user, Customer customer) {
		return DatabaseMarketingRepresentativeController.getInstance(connection).saveNewCustomerSequence(user,
				customer);
	}

	/**
	 * 
	 * @param customerID
	 * @return user and customer entities
	 */
	public Object[] getCustomerDetails(String customerID) {
		return DatabaseMarketingRepresentativeController.getInstance(connection).getCustomerDetails(customerID);
	}

	/**
	 * delete customer and its username from db
	 * 
	 * @param customerID
	 * @return false if failed
	 */
	public boolean deleteCustomer(String customerID) {
		return DatabaseMarketingRepresentativeController.getInstance(connection).deleteCustomer(customerID);
	}

	/**
	 * check if customerID exists, 0 if exists, 1 if deleted, 2 if doesnt exist
	 * 
	 * @param customerID
	 */
	public Integer checkCustomerExists(String customerID) {
		return DatabaseMarketingRepresentativeController.getInstance(connection).checkCustomerExists(customerID);
	}

	/**
	 * 
	 * @param user
	 * @param customer
	 * @return string of success or fail
	 */
	public String updateCustomer(User user, Customer customer) {
		return DatabaseMarketingRepresentativeController.getInstance(connection).updateCustomer(user, customer);
	}

	/**
	 * 
	 * @param car
	 * @return string of success or fail
	 */
	public String saveNewCarSequence(Car car) {
		return DatabaseMarketingRepresentativeController.getInstance(connection).saveNewCarSequence(car);
	}

	/**
	 * 
	 * @param customerID
	 * @return list of cars of customer
	 */
	public CarList getCustomerCars(String customerID) {
		return DatabaseMarketingRepresentativeController.getInstance(connection).getCustomerCars(customerID);
	}

	/**
	 * 
	 * @param regPlate
	 * @return string of success or fail
	 */
	public Boolean deleteCar(String regPlate) {
		return DatabaseMarketingRepresentativeController.getInstance(connection).deleteCar(regPlate);
	}

	/**
	 * 
	 * @param car
	 * @return string of success or fail
	 */
	public String updateCar(Car car) {
		return DatabaseMarketingRepresentativeController.getInstance(connection).updateCar(car);
	}

	/**
	 * 
	 * @param purchasingProgram
	 * @return string of success or fail
	 */
	public String setPurchasingProgram(PurchasingProgram purchasingProgram) {
		return DatabaseMarketingRepresentativeController.getInstance(connection)
				.setPurchasingProgram(purchasingProgram);
	}

	/**
	 * 
	 * @param pricingModel
	 * @return string of success or fail
	 */
	public String setPricingModel(PricingModel pricingModel) {
		return DatabaseMarketingRepresentativeController.getInstance(connection).setPricingModel(pricingModel);
	}

	/************* fast fuel controller methods **************/

	/**
	 * 
	 * @param fastFuel
	 * @return fuel type of car and price per liter after discounts
	 */
	public FastFuel getFuelTypeAndPricePerLiter(FastFuel fastFuel) {
		return DatabaseFastFuelController.getInstance(connection).getFuelTypeAndPricePerLiter(fastFuel);
	}

	/**
	 * 
	 * @param fastFuel
	 * @return message of success or fail in fastFuel->function
	 */
	public FastFuel saveFastFuel(FastFuel fastFuel) {
		return DatabaseFastFuelController.getInstance(connection).saveFastFuel(fastFuel);
	}

	/************* marketing manager controller methods **************/

	public SalesPatternList getAllSalePatterns() {
		return DatabaseMarketingManagerController.getInstance(connection).getAllSalePatterns();
	}

	public ProductInSalePatternList getAllProductInSalePatterns() {
		return DatabaseMarketingManagerController.getInstance(connection).getAllProductInSalePatterns();
	}

	public String checkActiveOfSale(int salePatternID) {
		return DatabaseMarketingManagerController.getInstance(connection).checkActiveOfSale(salePatternID);
	}

	public String insertNewSale(int[] values) {
		return DatabaseMarketingManagerController.getInstance(connection).insertNewSale(values);
	}

	public String insertNewActivity(String username, Date date, String action) {
		return DatabaseMarketingManagerController.getInstance(connection).insertNewActivity(username, date, action);
	}

	public RankingSheetList getAllRankignSheets() {
		return DatabaseMarketingManagerController.getInstance(connection).getAllRankignSheets();
	}

	public String createNewSalePatternID(int duration, String[] productInSP) {
		return DatabaseMarketingManagerController.getInstance(connection).createNewSalePatternID(duration, productInSP);
	}

	public ProductRateList getAllProductRanks() {
		return DatabaseMarketingManagerController.getInstance(connection).getAllProductRanks();
	}

	public String createNewPRUR(double dieselRank, double gasolineRank, double motorRank, double homeRank) {
		return DatabaseMarketingManagerController.getInstance(connection).createNewPRUR(dieselRank, gasolineRank,
				motorRank, homeRank);
	}

	public SalesList getSaleList() {
		return DatabaseMarketingManagerController.getInstance(connection).getSaleList();
	}

	public SaleCommentReportList generateSaleCommentReport(int saleID) {
		return DatabaseMarketingManagerController.getInstance(connection).generateSaleCommentReport(saleID);
	}

	public PeriodicReportList generatePeriodicReport(Date fromDate, Date toDate) {
		return DatabaseMarketingManagerController.getInstance(connection).generatePeriodicReport(fromDate, toDate);
	}

	public Object checkSaleRange(Date startDate, Date endDate) {
		return DatabaseMarketingManagerController.getInstance(connection).checkSaleRange(startDate, endDate);
	}

}
