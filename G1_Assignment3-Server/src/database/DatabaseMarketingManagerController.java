package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import entities.CustomerBoughtFromCompany;
import entities.CustomerBoughtInSale;
import entities.PeriodicCustomersReport;
import entities.PeriodicReportList;
import entities.Product;
import entities.ProductInSalePatternList;
import entities.ProductInSalesPattern;
import entities.ProductRateList;
import entities.RankingSheet;
import entities.RankingSheetList;
import entities.RowInSaleCommentsReportTable;
import entities.SaleCommentsReport;
import entities.SaleCommentsReportList;
import entities.SalesList;
import entities.SalesPattern;
import entities.SalesPatternList;
import enums.CustomerType;
import enums.FuelCompanyName;
import enums.ProductName;

/**
 * 
 * @author Elroy, Lior
 *
 */
public class DatabaseMarketingManagerController {
	private static DatabaseMarketingManagerController instance;

	private Connection connection;

	/**
	 * singleton class constructor
	 */
	private DatabaseMarketingManagerController(Connection connection) {
		super();
		this.connection = connection;
	}

	/**
	 * @return instance of this class
	 */
	public static DatabaseMarketingManagerController getInstance(Connection connection) {
		if (instance == null) {
			instance = new DatabaseMarketingManagerController(connection);
		}
		return instance;
	}

	//////////// functions//////////////////

	/**
	 * 
	 * @return SalesPatternList
	 */
	public SalesPatternList getAllSalePatterns() {
		List<SalesPattern> list = new ArrayList<>();
		SalesPatternList salesPatternList = new SalesPatternList(new ArrayList<>());

		Statement stmt = null;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM sales_pattern");
			while (rs.next()) {
				SalesPattern salePattern = new SalesPattern(rs.getInt(1), rs.getInt(2));
				list.add(salePattern);
			}
			salesPatternList.setList(list);
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return salesPatternList;
	}

	/**
	 * method that pulls all products that are in a Sale Pattern
	 * 
	 * @return ProductInSalePatternList
	 */
	public ProductInSalePatternList getAllProductInSalePatterns() {
		List<ProductInSalesPattern> list = new ArrayList<>();
		ProductInSalePatternList salesPatternList = new ProductInSalePatternList(new ArrayList<>());

		Statement stmt = null;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM product_in_sales_pattern");
			while (rs.next()) {
				ProductInSalesPattern productInSalePattern = null;
				if (rs.getString(2).equals(ProductName.Diesel.toString()))
					productInSalePattern = new ProductInSalesPattern(rs.getInt(1), ProductName.Diesel, rs.getDouble(3));

				if (rs.getString(2).equals(ProductName.Gasoline.toString()))
					productInSalePattern = new ProductInSalesPattern(rs.getInt(1), ProductName.Gasoline,
							rs.getDouble(3));

				if (rs.getString(2).equals(ProductName.MotorbikeFuel.toString()))
					productInSalePattern = new ProductInSalesPattern(rs.getInt(1), ProductName.MotorbikeFuel,
							rs.getDouble(3));

				// home fuel?
				list.add(productInSalePattern);
			}
			salesPatternList.setList(list);
			rs.close();
		} catch (SQLException e) {

		}
		return salesPatternList;
	}

	/**
	 * method that check if a sale with required sale pattern id is active or not
	 * 
	 * @param salePatternID
	 * @return String
	 */
	public String checkActiveOfSale(int salePatternID) {
		List<String> productNames = new ArrayList<>();
		productNames = getProductNameFromSP(salePatternID);
		try {
			for (String pName : productNames) {
				PreparedStatement pStmt = this.connection.prepareStatement(
						"SELECT FK_salesPatternID FROM product_in_sales_pattern WHERE FK_productName=?");
				pStmt.setString(1, pName);
				ResultSet rs = pStmt.executeQuery();
				while (rs.next()) {
					int num = rs.getInt(1);
					PreparedStatement pStmt2 = this.connection
							.prepareStatement("SELECT startTime, endTime FROM sale WHERE FK_salesPatternID=?");
					pStmt2.setInt(1, num);
					ResultSet rs2 = pStmt2.executeQuery();
					if (rs2.next()) {
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Date startTime = formatter.parse(rs2.getString(1));
						Date endTime = formatter.parse(rs2.getString(2));
						if (startTime.compareTo(new Date()) <= 0 && endTime.compareTo(new Date()) >= 0) {
							return "active sale " + pName;
						}
					}
					rs2.close();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "inactive sale";
	}

	/**
	 * method that creats a new row of sale
	 * 
	 * @param values
	 * @return String
	 */
	public String insertNewSale(int[] values) {
		int salePatternID = values[0];
		int flag;

		Calendar calendar1 = Calendar.getInstance(); // startDate
		calendar1.set(Calendar.YEAR, values[1]);
		calendar1.set(Calendar.MONTH, values[2]);
		calendar1.set(Calendar.DAY_OF_MONTH, values[3]);
		calendar1.set(Calendar.HOUR, values[4]);
		calendar1.set(Calendar.MINUTE, values[5]);

		Calendar calendar2 = Calendar.getInstance(); // endDate
		calendar2.set(Calendar.YEAR, values[6]);
		calendar2.set(Calendar.MONTH, values[7]);
		calendar2.set(Calendar.DAY_OF_MONTH, values[8]);
		calendar2.set(Calendar.HOUR, values[9]);
		calendar2.set(Calendar.MINUTE, values[10]);

		System.out.println(calendar1.getTime() + ", " + calendar2.getTime());

		// "FK_salesPatternID", "startTime", "endTime"
		Object[] values2 = { salePatternID, calendar1.getTime(), calendar2.getTime() };
		try {
			flag = TableInserts.insertSale(connection, values2);
		} catch (Exception e) {
			flag = -1;
		}
		if (flag == -1)
			return "sale failed";

		return "new sale id: " + flag;
	}

	/**
	 * create a new activity in sql
	 * 
	 * @param employeeID
	 * @param date
	 * @param action
	 * @return String
	 */
	public String insertNewActivity(String username, Date date, String action) {
		int flag;
		int employeeID = getEmployeeID(username);
		if (employeeID == -1)
			return "activity failed";

		Object[] values2 = { employeeID, date, action };
		try {
			flag = TableInserts.insertActivity(connection, values2);
		} catch (Exception e) {
			flag = -1;
		}
		if (flag == -1)
			return "activity failed";

		return "new activity id: " + flag;
	}

	/**
	 * method that pulls all ranking sheets from data base
	 * 
	 * @return RankingSheetList
	 */
	public RankingSheetList getAllRankingSheets() {
		List<RankingSheet> list = new ArrayList<>();
		RankingSheetList rankingSheetList = new RankingSheetList(new ArrayList<>());
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM ranking_sheet");
			while (rs.next()) {
				RankingSheet rankingSheet = new RankingSheet(rs.getString(1), rs.getDouble(2), rs.getDouble(3),
						rs.getDouble(4), rs.getDate(5));
				list.add(rankingSheet);
			}
			rankingSheetList.setList(list);
			rs.close();
		} catch (SQLException e) {

		}
		return rankingSheetList;
	}

	/**
	 * method that create a new Sale Pattern
	 * 
	 * @param duration
	 * @param productInSP
	 * @return String
	 */
	public String createNewSalePatternID(int duration, String[] productInSP) {
		String result = "failed to create sale pattern";
		try {
			Object[] values1 = { duration };
			int newSalePatternID = TableInserts.insertSalesPattern(connection, values1);
			if (newSalePatternID == -1)
				return result;

			for (int i = 1; i < 6; i += 2) {
				if (!productInSP[i].equals("0.0")) // assuming that all products are in the sql
				{
					// if there are prdocuts that must be entered than add product here
					if (i == 5) {
						Object[] values2 = { newSalePatternID + "", "Motorbike Fuel",
								Double.parseDouble(productInSP[i]) };
						TableInserts.insertProductInSalesPattern(connection, values2);
					} else {
						Object[] values2 = { newSalePatternID + "", productInSP[i - 1],
								Double.parseDouble(productInSP[i]) };
						TableInserts.insertProductInSalesPattern(connection, values2);
					}
				}
			}
			result = "created sale pattern: " + newSalePatternID;
		} catch (Exception e) {

		}
		return result;
	}

	/**
	 * method that pulls all Product Ranks
	 * 
	 * @return ProductRateList
	 */
	public ProductRateList getAllProductRanks() {
		List<Product> list = new ArrayList<>();
		ProductRateList productRateList = new ProductRateList(new ArrayList<>());
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM product");
			while (rs.next()) {
				Product product = new Product(getProductName(rs.getString(1)), rs.getDouble(2), rs.getDouble(3));
				list.add(product);
			}
			productRateList.setList(list);
			rs.close();
		} catch (SQLException e) {

		}
		return productRateList;
	}

	/**
	 * method that create a new Product Rate Update Request
	 * 
	 * @param dieselRank
	 * @param gasolineRank
	 * @param motorRank
	 * @param homeRank
	 * @return String
	 */
	public String createNewPRUR(double dieselRank, double gasolineRank, double motorRank, double homeRank) {
		int updateRateRequestID;
		try {
			Calendar calendar3 = Calendar.getInstance();
			Date now = new Date();
			calendar3.setTime(now);
			calendar3.add(Calendar.HOUR, -2);
			calendar3.add(Calendar.MINUTE, -30);
			now = calendar3.getTime();

			Object[] values1 = { now, false, false };
			System.out.println("Create new PRUR");
			updateRateRequestID = TableInserts.insertProductRatesUpdateRequest2(connection, values1);
			System.out.println("new ID= " + updateRateRequestID);
			if (dieselRank != 0) {
				Object[] values2 = { updateRateRequestID + "", ProductName.Diesel.toString(), dieselRank };
				TableInserts.insertProductInRequest(connection, values2);
			}
			if (gasolineRank != 0) {
				Object[] values2 = { updateRateRequestID + "", ProductName.Gasoline.toString(), gasolineRank };
				TableInserts.insertProductInRequest(connection, values2);
			}

			if (motorRank != 0) {
				Object[] values2 = { updateRateRequestID + "", ProductName.MotorbikeFuel.toString(), motorRank };
				TableInserts.insertProductInRequest(connection, values2);
			}

			if (homeRank != 0) {
				Object[] values2 = { updateRateRequestID + "", ProductName.HomeFuel.toString(), homeRank };
				TableInserts.insertProductInRequest(connection, values2);
			}

		} catch (Exception e) {
			return "failed PRUR";
		}

		return "success PRUR " + updateRateRequestID;

	}

	/**
	 * method that pulls sale products data from sql
	 * 
	 * @return SalesList
	 */
	public SalesList getSaleList() {
		List<RowInSaleCommentsReportTable> list = new ArrayList<>();
		SalesList saleList = new SalesList(new ArrayList<>());
		Statement stmt = null;
		Statement stmt2 = null;
		try {
			stmt = connection.createStatement();
			stmt2 = connection.createStatement();
			ResultSet rs2 = null;
			ResultSet rs = stmt.executeQuery("SELECT saleID,FK_salesPatternID,startTime,endTime FROM sale");
			while (rs.next()) {
				RowInSaleCommentsReportTable row = new RowInSaleCommentsReportTable();
				int saleID = rs.getInt(1);
				int salesPatternID = rs.getInt(2);

				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date startTime = formatter.parse(rs.getString(3));
				Date endTime = formatter.parse(rs.getString(4));

				if (endTime.compareTo(new Date()) > 0) {
					continue;
				}

				row.setSaleID(saleID);
				row.setStartTime(startTime);
				row.setEndTime(endTime);
				rs2 = stmt2.executeQuery(
						"SELECT FK_productName,salesDiscount FROM product_in_sales_pattern WHERE FK_salesPatternID="
								+ salesPatternID);
				while (rs2.next()) {
					String productName = rs2.getString(1);
					if (productName.equals("Diesel")) {
						row.setDieselDisc(rs2.getDouble(2));
					}
					if (productName.equals("Gasoline")) {
						row.setGasolineDisc(rs2.getDouble(2));
					}
					if (productName.equals("Motorbike Fuel")) {
						row.setMotorDisc(rs2.getDouble(2));
					}
				}
				list.add(row);
			}
			saleList.setList(list);
			rs2.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getSQLState());
			System.out.println(e.getMessage());
			return new SalesList(new ArrayList<>());
		} catch (Exception ex) {
			ex.printStackTrace();
			return new SalesList(new ArrayList<>());
		}

		return saleList;
	}

	/**
	 * method that generate a new commmon report
	 * 
	 * @param saleID
	 * @return SaleCommentsReportList
	 */
	public SaleCommentsReportList generateSaleCommentReport(int saleID) {
		SaleCommentsReportList report = new SaleCommentsReportList();
		List<CustomerBoughtInSale> customerList = new ArrayList<>();
		Statement stmt = null;
		boolean found = false;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			stmt = connection.createStatement();
			CustomerBoughtInSale customerBought;
			ResultSet rs;
			SaleCommentsReport saleReport = null;
			rs = stmt.executeQuery("SELECT * FROM sale_comments_report WHERE FK_saleID=" + saleID);
			while (rs.next()) {
				saleReport = new SaleCommentsReport(rs.getInt(1), rs.getInt(2), rs.getDouble(3),
						formatter.parse(rs.getString(4)));
				found = true;
			}
			rs = stmt.executeQuery("SELECT * FROM customer_bought_in_sale WHERE FK_saleID=" + saleID);
			while (rs.next()) {
				System.out.println("sale id=" + rs.getInt(1));
				System.out.println("customer id=" + rs.getString(2));
				customerBought = new CustomerBoughtInSale(rs.getInt(1), rs.getString(2), rs.getDouble(3));
				customerList.add(customerBought);
			}

			if (found == true) {
				report.setReport(saleReport);
				report.setList(customerList);
				rs.close();
				return report;
			}

			Calendar calendar = Calendar.getInstance();
			Date dateCreated = new Date();
			calendar.setTime(dateCreated);
			calendar.add(Calendar.HOUR, -2);
			calendar.add(Calendar.MINUTE, -30);
			dateCreated = calendar.getTime();

			double sumOfPurchases = 0;
			int numberOfParticepents = customerList.size();
			for (CustomerBoughtInSale customer : customerList) {
				sumOfPurchases += customer.getAmountPaid();
			}
			Object[] values1 = { saleID + "", numberOfParticepents, sumOfPurchases, dateCreated };
			TableInserts.insertSaleCommentsReport(connection, values1);

			report.setList(customerList);
			saleReport = new SaleCommentsReport(saleID, numberOfParticepents, sumOfPurchases, dateCreated);
			report.setReport(saleReport);
			report.setGenerated(true);
			rs.close();
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getSQLState());
			System.out.println(e.getMessage());
			return new SaleCommentsReportList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new SaleCommentsReportList();
		}
		return report;
	}

	/**
	 * method that generate new periodic report if not exists and pulls customer
	 * data bought form the compnay in date between fromDate : toDate
	 * 
	 * @param fromDate
	 * @param toDate
	 * @return PeriodicReportList
	 */
	public PeriodicReportList generatePeriodicReport(Date fromDate, Date toDate) { // here
		PeriodicReportList periodicReport = new PeriodicReportList();
		List<CustomerBoughtFromCompany> customerList = new ArrayList<>();
		Statement stmt = null;
		PreparedStatement pStmt = null;
		boolean found = false;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			stmt = connection.createStatement();
			PeriodicCustomersReport PCReport = null;
			ResultSet rs;
			CustomerBoughtFromCompany customerBoughtFromCompany = null;
			pStmt = this.connection.prepareStatement(
					"SELECT * FROM periodic_customers_report WHERE DATE(dateFrom)=? AND DATE(dateTo)=?");
			pStmt.setDate(1, new java.sql.Date(fromDate.getTime()));
			pStmt.setDate(2, new java.sql.Date(toDate.getTime())); // what in date
			rs = pStmt.executeQuery();
			while (rs.next()) {
				System.out.println("found");
				PCReport = new PeriodicCustomersReport(formatter.parse(rs.getString(1)),
						formatter.parse(rs.getString(2)), formatter.parse(rs.getString(3)));
				System.out.println("found Report = " + PCReport);
				found = true;
			}
			if (found == false) {
				Object[] values1 = { fromDate, toDate, new Date() };
				TableInserts.insertPeriodicCustomersReport(connection, values1);
				PCReport = new PeriodicCustomersReport(fromDate, toDate, new Date());
				System.out.println("Not found Report = " + PCReport);
				periodicReport.setGenerated(true);
			}
			rs = stmt.executeQuery("SELECT * FROM customer_bought_from_company");
			while (rs.next()) {
				Date datePurchase = formatter.parse(rs.getString(3));
				Date from2Date = new java.sql.Date(fromDate.getTime());
				Date to2Date = new java.sql.Date(toDate.getTime());
				if (datePurchase.compareTo(from2Date) >= 0 && datePurchase.compareTo(to2Date) <= 0) {
					FuelCompanyName company = null;
					if (rs.getString(2).equals("Paz"))
						company = FuelCompanyName.Paz;
					if (rs.getString(2).equals("Delek"))
						company = FuelCompanyName.Delek;
					if (rs.getString(2).equals("Sonol"))
						company = FuelCompanyName.Sonol;
					customerBoughtFromCompany = new CustomerBoughtFromCompany(rs.getString(1), company,
							formatter.parse(rs.getString(3)), rs.getDouble(4), rs.getDouble(5));
					customerList.add(customerBoughtFromCompany);
				}
			}
			rs.close();
			periodicReport.setList(customerList);
			periodicReport.setReport(PCReport);

		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getSQLState());
			System.out.println(e.getMessage());
			return new PeriodicReportList();
		} catch (Exception e) {
			e.printStackTrace();
			return new PeriodicReportList();
		}
		return periodicReport;

	}

	/**
	 * method that check if a new sale will be in some range of other sale dates
	 * 
	 * @param startDate
	 * @param endDate
	 * @return String
	 */
	public String checkSaleRange(Date startDate, Date endDate) {
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		try {
			pStmt = this.connection// DATE(dateFrom)
					.prepareStatement(
							"SELECT COUNT(*) FROM sale WHERE  (DATE(startTime) <=  ? AND DATE(endTime) >= ?) OR (DATE(startTime) >= ? AND DATE(endTime) <= ?)");
			pStmt.setDate(1, new java.sql.Date(startDate.getTime()));
			pStmt.setDate(2, new java.sql.Date(endDate.getTime()));
			pStmt.setDate(3, new java.sql.Date(startDate.getTime()));
			pStmt.setDate(4, new java.sql.Date(endDate.getTime()));
			rs = pStmt.executeQuery();
			if (rs.next()) {
				if (rs.getInt(1) > 0)
					return "sale is in range";
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println(e.getSQLState());
			System.out.println(e.getMessage());
			return "ERROR range";
		}
		return "sale not in range";
	}

	/**
	 * generate or update customers' ranking sheets
	 * 
	 * @return string of success or fail
	 */
	public String generateAnalysis() {
		try {
			ArrayList<String> customerIDs = getAllCustomerIDs();
			if (customerIDs == null) {
				System.out.println("generateAnalysis - customerIDs is null");
				return "genAnalysis fail";
			}

			ResultSet rs;
			PreparedStatement pStmt;
			for (String customerID : customerIDs) {
				pStmt = this.connection.prepareStatement("SELECT COUNT(*) FROM fast_fuel WHERE FK_customerID = ?");
				pStmt.setString(1, customerID);
				rs = pStmt.executeQuery();
				int fuelingHours;
				if (!rs.next()) {
					fuelingHours = 0;
				}
				fuelingHours = rs.getInt(1);
				rs.close();
				double fuelingHoursRank = (fuelingHours > 50) ? 10 : ((fuelingHours / 50) * 10);

				pStmt = this.connection
						.prepareStatement("SELECT COUNT(DISTINCT FK_productName) FROM car WHERE FK_customerID = ?");
				pStmt.setString(1, customerID);
				rs = pStmt.executeQuery();
				int fuelTypes;
				if (!rs.next()) {
					fuelTypes = 0;
				}
				fuelTypes = rs.getInt(1);
				rs.close();
				double fuelTypesRank = (fuelTypes / 3) * 10;

				pStmt = this.connection.prepareStatement("SELECT customerType FROM customer WHERE customerID = ?");
				pStmt.setString(1, customerID);
				rs = pStmt.executeQuery();
				double customerTypeRank;
				if (!rs.next()) {
					customerTypeRank = 0;
				}
				String customerType = rs.getString(1);
				rs.close();
				customerTypeRank = CustomerType.getRank(customerType);

				Calendar calendar1 = Calendar.getInstance();
//				calendar1.set(Calendar.MONTH, 12 - 1);

				pStmt = this.connection.prepareStatement("SELECT * FROM ranking_sheet WHERE FK_customerID = ?");
				pStmt.setString(1, customerID);
				rs = pStmt.executeQuery();
				if (!rs.next()) { // ranking sheet doesn't already exist
					// "FK_customerID", "customerTypeRank", "fuelingHoursRank", "fuelTypesRank",
					// "updatedForDate"
					Object[] values1 = { customerID, customerTypeRank, fuelingHoursRank, fuelTypesRank,
							calendar1.getTime() };
					TableInserts.insertRankingSheet(connection, values1);
				} else {
					pStmt = this.connection.prepareStatement(
							"UPDATE ranking_sheet SET customerTypeRank = ?, fuelingHoursRank = ?, fuelTypesRank = ?, updatedForDate = ? WHERE FK_customerID = ?");
					pStmt.setDouble(1, customerTypeRank);
					pStmt.setDouble(2, fuelingHoursRank);
					pStmt.setDouble(3, fuelTypesRank);
					pStmt.setDate(4, new java.sql.Date(calendar1.getTime().getTime()));
					pStmt.setString(5, customerID);
					pStmt.executeUpdate();
				}
				rs.close();
			}

			return "genAnalysis success";

		} catch (SQLException e) {
			e.printStackTrace();
			return "genAnalysis fail";
		} catch (Exception ex) {
			ex.printStackTrace();
			return "genAnalysis fail";
		}
	}

	////////// private functions ///////////

	/**
	 * merhod that pulls a list of product names from a required salePatternID
	 * 
	 * @param salePatternID
	 * @return List of String
	 */
	private List<String> getProductNameFromSP(int salePatternID) {
		List<String> productNames = new ArrayList<>();
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT FK_productName FROM product_in_sales_pattern WHERE FK_salesPatternID=" + salePatternID);
			while (rs.next()) {
				if (!productNames.contains(rs.getString(1)))
					productNames.add(rs.getString(1));
			}
			rs.close();
		} catch (SQLException e) {

		}
		return productNames;
	}

	/**
	 * method that gets a ProductName enum from a string
	 * 
	 * @param name
	 * @return ProductName
	 */
	private ProductName getProductName(String name) {
		if (name.equals("Gasoline"))
			return ProductName.Gasoline;
		if (name.equals("Diesel"))
			return ProductName.Diesel;
		if (name.equals("Motorbike Fuel"))
			return ProductName.MotorbikeFuel;
		return ProductName.HomeFuel;
	}

	/**
	 * method that will return the employee id of the username
	 * 
	 * @param username
	 * @return int
	 */
	private int getEmployeeID(String username) {
		int result = -1;
		try {
			PreparedStatement pStmt = this.connection
					.prepareStatement("SELECT employeeID FROM employee WHERE FK_userName = ?");
			pStmt.setString(1, username);
			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * method that will return all the customer ids that are not deleted in the
	 * database
	 * 
	 * @return ArrayList of String
	 */
	private ArrayList<String> getAllCustomerIDs() {
		ArrayList<String> customerIDList = new ArrayList<String>();
		try {
			PreparedStatement pStmt = this.connection
					.prepareStatement("SELECT customerID FROM customer WHERE deleted = 0");
			ResultSet rs2 = pStmt.executeQuery();
			if (!rs2.next()) {
				System.out.println("no customers");
				return customerIDList;
			}

			do {
				customerIDList.add(rs2.getString(1));
			} while (rs2.next());
			rs2.close();

			return customerIDList;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
