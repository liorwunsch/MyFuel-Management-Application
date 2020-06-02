package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.FieldIndicatorsForInsert;

public class TableInserts {

	/******************* genral insert table *******************/

	private static int insertRow(Connection con, String tableName, String[] fields, Object[] values)
			throws SQLException {
		int res = -1;
		try {
			StringBuilder valueString = new StringBuilder();
			for (int i = 0; i < values.length; i++) {
				valueString.append(values[i].toString() + " , ");
			}

			if (values.length != fields.length)
				throw new SQLException("ERROR: more values than columns in table " + tableName);

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

	public static int insertActivity(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "activity", FieldIndicatorsForInsert.Activity(), values);
	}

	public static int insertCar(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "car", FieldIndicatorsForInsert.Car(), values);
	}

	public static int insertCustomer(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "customer", FieldIndicatorsForInsert.Customer(), values);
	}

	public static int insertCustomerBoughtFromCompany(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "customer_bought_from_company",
				FieldIndicatorsForInsert.CustomerBoughtFromCompany(), values);
	}

	public static int insertCustomerBoughtInSale(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "customer_bought_in_sale", FieldIndicatorsForInsert.CustomerBoughtInSale(),
				values);
	}

	public static int insertEmployee(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "employee", FieldIndicatorsForInsert.Employee(), values);
	}

	public static int insertFastFuel(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "fast_fuel", FieldIndicatorsForInsert.FastFuel(), values);
	}

	public static int insertFuelCompany(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "fuel_company", FieldIndicatorsForInsert.FuelCompany(), values);
	}

	public static int insertFuelStation(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "fuel_station", FieldIndicatorsForInsert.FuelStation(), values);
	}

	public static int insertFuelStationManager(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "fuel_station_manager", FieldIndicatorsForInsert.FuelStationManager(),
				values);
	}

	public static int insertFuelStationOrder1(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "fuel_station_order", FieldIndicatorsForInsert.FuelStationOrder1(), values);
	}

	public static int insertFuelStationOrder2(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "fuel_station_order", FieldIndicatorsForInsert.FuelStationOrder2(), values);
	}
	
	public static int insertFuelStationOrder3(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "fuel_station_order", FieldIndicatorsForInsert.FuelStationOrder3(), values);
	}

	public static int insertHomeFuelOrder(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "home_fuel_order", FieldIndicatorsForInsert.HomeFuelOrder(), values);
	}

	public static int insertIncomeReport(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "income_report", FieldIndicatorsForInsert.IncomeReport(), values);
	}

	public static int insertInventoryReport(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "inventory_report", FieldIndicatorsForInsert.InventoryReport(), values);
	}

	public static int insertNotification(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "notification", FieldIndicatorsForInsert.Notification(), values);
	}

	public static int insertOrders(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "orders", FieldIndicatorsForInsert.Orders(), values);
	}

	public static int insertOutcomeReport(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "outcome_report", FieldIndicatorsForInsert.OutcomeReport(), values);
	}

	public static int insertPeriodicCustomersReport(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "periodic_customers_report",
				FieldIndicatorsForInsert.PeriodicCustomersReport(), values);
	}

	public static int insertPricingModel1(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "pricing_model", FieldIndicatorsForInsert.PricingModel1(), values);
	}

	public static int insertPricingModel2(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "pricing_model", FieldIndicatorsForInsert.PricingModel2(), values);
	}

	public static int insertPricingModelType(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "pricing_model_type", FieldIndicatorsForInsert.PricingModelType(), values);
	}

	public static int insertProduct(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "product", FieldIndicatorsForInsert.Product(), values);
	}

	public static int insertProductInIncomeReport(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "product_in_income_report", FieldIndicatorsForInsert.ProductInIncomeReport(),
				values);
	}

	public static int insertProductInInventoryReport(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "product_in_inventory_report",
				FieldIndicatorsForInsert.ProductInInventoryReport(), values);
	}

	public static int insertProductInOutcomeReport(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "product_in_outcome_report",
				FieldIndicatorsForInsert.ProductInOutcomeReport(), values);
	}

	public static int insertProductInRequest(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "product_in_request", FieldIndicatorsForInsert.ProductInRequest(), values);
	}

	public static int insertProductInSalesPattern(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "product_in_sales_pattern", FieldIndicatorsForInsert.ProductInSalesPattern(),
				values);
	}

	public static int insertProductInStation(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "product_in_station", FieldIndicatorsForInsert.ProductInStation(), values);
	}

	public static int insertProductRatesUpdateRequest1(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "product_rates_update_request",
				FieldIndicatorsForInsert.ProductRatesUpdateRequest1(), values);
	}

	public static int insertProductRatesUpdateRequest2(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "product_rates_update_request",
				FieldIndicatorsForInsert.ProductRatesUpdateRequest2(), values);
	}

	public static int insertPurchasingProgram1(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "purchasing_program", FieldIndicatorsForInsert.PurchasingProgram1(), values);
	}

	public static int insertPurchasingProgram2(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "purchasing_program", FieldIndicatorsForInsert.PurchasingProgram2(), values);
	}

	public static int insertPurchasingProgram3(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "purchasing_program", FieldIndicatorsForInsert.PurchasingProgram3(), values);
	}

	public static int insertPurchasingProgramType(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "purchasing_program_type", FieldIndicatorsForInsert.PurchasingProgramType(),
				values);
	}

	public static int insertQuarterlyReport(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "quarterly_report", FieldIndicatorsForInsert.QuarterlyReport(), values);
	}

	public static int insertRankingSheet(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "ranking_sheet", FieldIndicatorsForInsert.RankingSheet(), values);
	}

	public static int insertSale(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "sale", FieldIndicatorsForInsert.Sale(), values);
	}

	public static int insertSaleCommentsReport(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "sale_comments_report", FieldIndicatorsForInsert.SaleCommentsReport(),
				values);
	}

	public static int insertSalesPattern(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "sales_pattern", FieldIndicatorsForInsert.SalesPattern(), values);
	}

	public static int insertShipmentMethod(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "shipment_method", FieldIndicatorsForInsert.ShipmentMethod(), values);
	}

	public static int insertUser(Connection con, Object[] values) throws SQLException {
		return TableInserts.insertRow(con, "user", FieldIndicatorsForInsert.User(), values);
	}

}
