package database;

/**
 * indicates the values of input of a query
 * 
 * @author Lior, Vlad, Elroy
 */
public class FieldIndicatorsForInsert {

	public static String[] Activity() {
		return new String[] { "FK_employeeID", "time", "action" };
	}

	public static String[] Car() {
		return new String[] { "registrationPlate", "FK_customerID", "FK_productName", "ownerName", "deleted" };
	}

	public static String[] Customer() {
		return new String[] { "customerID", "FK_userName", "creditCard", "customerType", "deleted" };
	}

	public static String[] CustomerBoughtFromCompany() {
		return new String[] { "FK_customerID", "FK_fuelCompanyName", "dateOfPurchase", "amountBoughtFromCompany",
				"amountPaidCompany" };
	}

	public static String[] CustomerBoughtInSale() {
		return new String[] { "FK_saleID", "FK_customerID", "amountPaid" };
	}

	public static String[] Employee() {
		return new String[] { "FK_userName", "role", "affiliation" };
	}

	public static String[] FastFuel() {
		return new String[] { "FK_registrationPlate", "FK_customerID", "FK_productInStationID", "fastFuelTime",
				"amountBought", "finalPrice" };
	}

	public static String[] FuelCompany() {
		return new String[] { "fuelCompanyName", "FK_employeeID" };
	}

	public static String[] FuelStation() {
		return new String[] { "FK_fuelCompanyName", "FK_employeeID", "stationName", "address" };
	}

	public static String[] FuelStationManager() {
		return new String[] { "FK_employeeID", "phoneNo" };
	}

	public static String[] FuelStationOrder1() {
		return new String[] { "FK_ordersID", "FK_productInStationID", "assessed", "supplied" };
	}

	public static String[] FuelStationOrder2() {
		return new String[] { "FK_ordersID", "FK_productInStationID", "assessed", "approved", "reasonDismissal",
				"supplied" };
	}

	public static String[] FuelStationOrder3() {
		return new String[] { "FK_ordersID", "FK_productInStationID", "assessed", "approved", "reasonDismissal",
				"supplied", "timeSupplied" };
	}

	public static String[] HomeFuelOrder() {
		return new String[] { "FK_ordersID", "FK_customerID", "FK_product_Name", "FK_shipmentType", "duetime",
				"finalPrice" };
	}

	public static String[] IncomeReport() {
		return new String[] { "FK_repQuarter", "FK_repYear", "FK_fuelStationID", "totalIncome" };
	}

	public static String[] InventoryReport() {
		return new String[] { "FK_repQuarter", "FK_repYear", "FK_fuelStationID" };
	}

	public static String[] Notification() {
		return new String[] { "FK_employeeID", "message", "dismissed", "dateCreated" };
	}

	public static String[] Orders() {
		return new String[] { "orderTime", "amountBought", "address" };
	}

	public static String[] OutcomeReport() {
		return new String[] { "FK_repQuarter", "FK_repYear", "FK_fuelStationID" };
	}

	public static String[] PeriodicCustomersReport() {
		return new String[] { "dateFrom", "dateTo", "dateCreated" };
	}

	public static String[] PricingModel1() {
		return new String[] { "FK_customerID", "FK_pricingModelName", "currentDiscount" };
	}

	public static String[] PricingModel2() {
		return new String[] { "FK_customerID", "FK_pricingModelName", "currentDiscount", "lastMonthUtillization" };
	}

	public static String[] PricingModelType() {
		return new String[] { "pricingModelName", "description", "defaultDiscount" };
	}

	public static String[] Product() {
		return new String[] { "productName", "maxPrice", "currentPrice" };
	}

	public static String[] ProductInIncomeReport() {
		return new String[] { "FK_productInStationID", "FK_repQuarter_IncomeReport", "FK_repYear_IncomeReport",
				"incomePerProduct", "avgPrice" };
	}

	public static String[] ProductInInventoryReport() {
		return new String[] { "FK_productInStationID", "FK_repQuarter_inventoryReport", "FK_repYear_inventoryReport",
				"amountSold", "amountBegin", "amountEnd" };
	}

	public static String[] ProductInOutcomeReport() {
		return new String[] { "FK_productInStationID", "FK_repQuarter_outcomeReport", "FK_repYear_outcomeReport",
				"amountBoughtFromSupplier" };
	}

	public static String[] ProductInRequest() {
		return new String[] { "FK_updateRateRequestID", "FK_productName", "requestedRate" };
	}

	public static String[] ProductInSalesPattern() {
		return new String[] { "FK_salesPatternID", "FK_productName", "salesDiscount" };
	}

	public static String[] ProductInStation() {
		return new String[] { "FK_productName", "FK_fuelStationID", "capacity", "threshold" };
	}

	public static String[] ProductRatesUpdateRequest1() {
		return new String[] { "requestDate", "assessed" };
	}

	public static String[] ProductRatesUpdateRequest2() {
		return new String[] { "requestDate", "assessed", "approved" };
	}

	public static String[] PurchasingProgram1() {
		return new String[] { "FK_customerID", "FK_purchasingProgramName", "FK_fuelCompanyName1" };
	}

	public static String[] PurchasingProgram2() {
		return new String[] { "FK_customerID", "FK_purchasingProgramName", "FK_fuelCompanyName1",
				"FK_fuelCompanyName2" };
	}

	public static String[] PurchasingProgram3() {
		return new String[] { "FK_customerID", "FK_purchasingProgramName", "FK_fuelCompanyName1", "FK_fuelCompanyName2",
				"FK_fuelCompanyName3" };
	}

	public static String[] PurchasingProgramType() {
		return new String[] { "purchasingProgramName", "description", "monthlyPrice" };
	}

	public static String[] QuarterlyReport() {
		return new String[] { "repQuarter", "repYear", "FK_fuelStationID", "dateCreated" };
	}

	public static String[] RankingSheet() {
		return new String[] { "FK_customerID", "customerTypeRank", "fuelingHoursRank", "fuelTypesRank",
				"updatedForDate" };
	}

	public static String[] Sale() {
		return new String[] { "FK_salesPatternID", "startTime", "endTime" };
	}

	public static String[] SaleCommentsReport() {
		return new String[] { "FK_saleID", "numberOfCustomersBought", "sumOfPurchases", "dateCreated" };
	}

	public static String[] SalesPattern() {
		return new String[] { "durationInMinutes" };
	}

	public static String[] ShipmentMethod() {
		return new String[] { "shipmentType", "shipmentPrice", "shipmentMultiplier", "deliveryTime" };
	}

	public static String[] User() {
		return new String[] { "username", "password", "connected", "email", "firstName", "surname" };
	}

}
