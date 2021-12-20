package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * executes insert queries by predetermined values and indication
 * 
 * @version Final
 * @author Elroy, Lior, Vlad
 */
public class TableInserts {

	/******************* general insert table *******************/

	/**
	 * 
	 * @param con
	 * @param tableName
	 * @param fields
	 * @param values
	 * @return value of auto-inced member if there is one
	 * @throws SQLException
	 */
	private static int insertRow(Connection con, String tableName, String[] fields, Object[] values)
			throws SQLException {
		int res = -1;
		try {
			StringBuilder valueString = new StringBuilder();
			for (int i = 0; i < values.length; i++) {
				valueString.append(values[i].toString() + " , ");
			}

			if (values.length != fields.length)
				throw new SQLException("Error: more values than columns in table " + tableName);

			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO " + tableName + " (");
			for (int i = 0; i < fields.length; i++) {
				if (i == fields.length - 1) {
					sb.append(fields[i] + ") VALUES (");
				} else {
					sb.append(fields[i] + ", ");
				}
			}

			for (int i = 0; i < values.length; i++) {
				if (i == values.length - 1) {
					sb.append("?)");
				} else {
					sb.append("?, ");
				}
			}

			PreparedStatement ps = con.prepareStatement(sb.toString(), Statement.RETURN_GENERATED_KEYS);
			for (int i = 1; i <= fields.length; ++i) {
				ps.setObject(i, values[i - 1]);
			}
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next() != false) {
				res = rs.getInt(1);
			}
		} catch (Exception e) {
			throw new SQLException(tableName + ": " + e.getMessage());
		}
		return res;
	}

	/******************* insert actual tables *******************/
	/**
	 * 
	 * @param con
	 * @param values
	 * @return int
	 * @throws SQLException
	 */

	public static int insertActivity(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "activity", FieldIndicatorsForInsert.Activity(), values);
	}
	/**
	 * 
	 * @param con
	 * @param values
	 * @return int
	 * @throws SQLException
	 */

	public static int insertCar(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "car", FieldIndicatorsForInsert.Car(), values);
	}
	/**
	 * 
	 * @param con
	 * @param values
	 * @return int
	 * @throws SQLException
	 */

	public static int insertCustomer(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "customer", FieldIndicatorsForInsert.Customer(), values);
	}
	/**
	 * 
	 * @param con
	 * @param values
	 * @return int
	 * @throws SQLException
	 */

	public static int insertCustomerBoughtFromCompany(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "customer_bought_from_company",
				FieldIndicatorsForInsert.CustomerBoughtFromCompany(), values);
	}
	/**
	 * 
	 * @param con
	 * @param values
	 * @return int
	 * @throws SQLException
	 */

	public static int insertCustomerBoughtInSale(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "customer_bought_in_sale", FieldIndicatorsForInsert.CustomerBoughtInSale(),
				values);
	}
	/**
	 * 
	 * @param con
	 * @param values
	 * @return int
	 * @throws SQLException
	 */

	public static int insertEmployee(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "employee", FieldIndicatorsForInsert.Employee(), values);
	}
	/**
	 * 
	 * @param con
	 * @param values
	 * @return int
	 * @throws SQLException
	 */

	public static int insertFastFuel(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "fast_fuel", FieldIndicatorsForInsert.FastFuel(), values);
	}
	/**
	 * 
	 * @param con
	 * @param values
	 * @return int
	 * @throws SQLException
	 */

	public static int insertFuelCompany(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "fuel_company", FieldIndicatorsForInsert.FuelCompany(), values);
	}
	/**
	 * 
	 * @param con
	 * @param values
	 * @return int
	 * @throws SQLException
	 */

	public static int insertFuelStation(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "fuel_station", FieldIndicatorsForInsert.FuelStation(), values);
	}
	/**
	 * 
	 * @param con
	 * @param values
	 * @return int
	 * @throws SQLException
	 */

	public static int insertFuelStationManager(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "fuel_station_manager", FieldIndicatorsForInsert.FuelStationManager(),
				values);
	}
	/**
	 * 
	 * @param con
	 * @param values
	 * @return int
	 * @throws SQLException
	 */

	public static int insertFuelStationOrder1(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "fuel_station_order", FieldIndicatorsForInsert.FuelStationOrder1(), values);
	}
	/**
	 * 
	 * @param con
	 * @param values
	 * @return int
	 * @throws SQLException
	 */

	public static int insertFuelStationOrder2(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "fuel_station_order", FieldIndicatorsForInsert.FuelStationOrder2(), values);
	}
	/**
	 * 
	 * @param con
	 * @param values
	 * @return int
	 * @throws SQLException
	 */

	public static int insertFuelStationOrder3(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "fuel_station_order", FieldIndicatorsForInsert.FuelStationOrder3(), values);
	}
	/**
	 * 
	 * @param con
	 * @param values
	 * @return int
	 * @throws SQLException
	 */

	public static int insertHomeFuelOrder(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "home_fuel_order", FieldIndicatorsForInsert.HomeFuelOrder(), values);
	}
	/**
	 * 
	 * @param con
	 * @param values
	 * @return int
	 * @throws SQLException
	 */

	public static int insertIncomeReport(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "income_report", FieldIndicatorsForInsert.IncomeReport(), values);
	}
	/**
	 * 
	 * @param con
	 * @param values
	 * @return int
	 * @throws SQLException
	 */

	public static int insertInventoryReport(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "inventory_report", FieldIndicatorsForInsert.InventoryReport(), values);
	}
	/**
	 * 
	 * @param con
	 * @param values
	 * @return int
	 * @throws SQLException
	 */

	public static int insertNotification(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "notification", FieldIndicatorsForInsert.Notification(), values);
	}
	/**
	 * 
	 * @param con
	 * @param values
	 * @return int
	 * @throws SQLException
	 */

	public static int insertOrders(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "orders", FieldIndicatorsForInsert.Orders(), values);
	}
	/**
	 * 
	 * @param con
	 * @param values
	 * @return int
	 * @throws SQLException
	 */

	public static int insertOutcomeReport(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "outcome_report", FieldIndicatorsForInsert.OutcomeReport(), values);
	}
	/**
	 * 
	 * @param con
	 * @param values
	 * @return int
	 * @throws SQLException
	 */

	public static int insertPeriodicCustomersReport(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "periodic_customers_report",
				FieldIndicatorsForInsert.PeriodicCustomersReport(), values);
	}
	/**
	 * 
	 * @param con
	 * @param values
	 * @return int
	 * @throws SQLException
	 */

	public static int insertPricingModel1(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "pricing_model", FieldIndicatorsForInsert.PricingModel1(), values);
	}
	/**
	 * 
	 * @param con
	 * @param values
	 * @return int
	 * @throws SQLException
	 */

	public static int insertPricingModel2(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "pricing_model", FieldIndicatorsForInsert.PricingModel2(), values);
	}
	/**
	 * 
	 * @param con
	 * @param values
	 * @return int
	 * @throws SQLException
	 */

	public static int insertPricingModelType(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "pricing_model_type", FieldIndicatorsForInsert.PricingModelType(), values);
	}
	
	/**
	 * 
	 * @param con
	 * @param values
	 * @return int
	 * @throws SQLException
	 */

	public static int insertProduct(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "product", FieldIndicatorsForInsert.Product(), values);
	}
	/**
	 * 
	 * @param con
	 * @param values
	 * @return int
	 * @throws SQLException
	 */

	public static int insertProductInIncomeReport(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "product_in_income_report", FieldIndicatorsForInsert.ProductInIncomeReport(),
				values);
	}
	/**
	 * 
	 * @param con
	 * @param values
	 * @return int
	 * @throws SQLException
	 */

	public static int insertProductInInventoryReport(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "product_in_inventory_report",
				FieldIndicatorsForInsert.ProductInInventoryReport(), values);
	}
	/**
	 * 
	 * @param con
	 * @param values
	 * @return int
	 * @throws SQLException
	 */

	public static int insertProductInOutcomeReport(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "product_in_outcome_report",
				FieldIndicatorsForInsert.ProductInOutcomeReport(), values);
	}

//	public static int insertProductInRequest(Connection con, Object[] values) throws SQLException {
//		return TableInserts.insertRow(con, "product_in_request", FieldIndicatorsForInsert.ProductInRequest(), values);
//	}
	/**
	 * 
	 * @param con
	 * @param values
	 * @return int
	 * @throws SQLException
	 */

	public static int insertProductInSalesPattern(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "product_in_sales_pattern", FieldIndicatorsForInsert.ProductInSalesPattern(),
				values);
	}
	/**
	 * 
	 * @param con
	 * @param values
	 * @return int
	 * @throws SQLException
	 */

	public static int insertProductInStation(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "product_in_station", FieldIndicatorsForInsert.ProductInStation(), values);
	}

//	public static int insertProductRatesUpdateRequest1(Connection con, Object[] values) throws SQLException {
//		return TableInserts.insertRow(con, "product_rates_update_request",
//				FieldIndicatorsForInsert.ProductRatesUpdateRequest1(), values);
//	}
//
//	public static int insertProductRatesUpdateRequest2(Connection con, Object[] values) throws SQLException {
//		return TableInserts.insertRow(con, "product_rates_update_request",
//				FieldIndicatorsForInsert.ProductRatesUpdateRequest2(), values);
//	}
	/**
	 * 
	 * @param con
	 * @param values
	 * @return int
	 * @throws SQLException
	 */

	public static int insertPurchasingProgram1(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "purchasing_program", FieldIndicatorsForInsert.PurchasingProgram1(), values);
	}
	/**
	 * 
	 * @param con
	 * @param values
	 * @return int
	 * @throws SQLException
	 */

	public static int insertPurchasingProgram2(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "purchasing_program", FieldIndicatorsForInsert.PurchasingProgram2(), values);
	}
	/**
	 * 
	 * @param con
	 * @param values
	 * @return int
	 * @throws SQLException
	 */

	public static int insertPurchasingProgram3(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "purchasing_program", FieldIndicatorsForInsert.PurchasingProgram3(), values);
	}
	/**
	 * 
	 * @param con
	 * @param values
	 * @return int
	 * @throws SQLException
	 */

	public static int insertPurchasingProgramType(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "purchasing_program_type", FieldIndicatorsForInsert.PurchasingProgramType(),
				values);
	}
	/**
	 * 
	 * @param con
	 * @param values
	 * @return int
	 * @throws SQLException
	 */

	public static int insertQuarterlyReport(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "quarterly_report", FieldIndicatorsForInsert.QuarterlyReport(), values);
	}
	/**
	 * 
	 * @param con
	 * @param values
	 * @return int
	 * @throws SQLException
	 */

	public static int insertRankingSheet(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "ranking_sheet", FieldIndicatorsForInsert.RankingSheet(), values);
	}
	/**
	 * 
	 * @param con
	 * @param values
	 * @return int
	 * @throws SQLException
	 */

	public static int insertSale(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "sale", FieldIndicatorsForInsert.Sale(), values);
	}
	/**
	 * 
	 * @param con
	 * @param values
	 * @return int
	 * @throws SQLException
	 */

	public static int insertSaleCommentsReport(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "sale_comments_report", FieldIndicatorsForInsert.SaleCommentsReport(),
				values);
	}
	/**
	 * 
	 * @param con
	 * @param values
	 * @return int
	 * @throws SQLException
	 */

	public static int insertSalesPattern(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "sales_pattern", FieldIndicatorsForInsert.SalesPattern(), values);
	}
	/**
	 * 
	 * @param con
	 * @param values
	 * @return int
	 * @throws SQLException
	 */

	public static int insertShipmentMethod(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "shipment_method", FieldIndicatorsForInsert.ShipmentMethod(), values);
	}
	/**
	 * 
	 * @param con
	 * @param values
	 * @return int
	 * @throws SQLException
	 */

	public static int insertUser(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "user", FieldIndicatorsForInsert.User(), values);
	}
	/**
	 * 
	 * @param con
	 * @param values
	 * @return int
	 * @throws SQLException
	 */
	

	public static int insertPricingModelUpdateRequest1(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "pricing_model_update_request",
				FieldIndicatorsForInsert.PricingModelUpdateRequest1(), values);
	}
	/**
	 * 
	 * @param con
	 * @param values
	 * @return int
	 * @throws SQLException
	 */

	public static int insertPricingModelUpdateRequest2(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "pricing_model_update_request",
				FieldIndicatorsForInsert.PricingModelUpdateRequest2(), values);
	}

}
