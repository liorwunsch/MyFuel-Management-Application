package database;

/**
 * indicates the values of input of a query
 * 
 * @version Final
 * @author Lior, Vlad, Elroy
 */
public class FieldIndicatorsForInsert {
	
	/**
	 * 
	 * @return String[]
	 */
	public static String[] Activity() {
		return new String[] { "FK_employeeID", "time", "action" };
	}
	/**
	 * 
	 * @return String[]
	 */

	public static String[] Car() {
		return new String[] { "registrationPlate", "FK_customerID", "FK_productName", "ownerName", "deleted" };
	}
	/**
	 * 
	 * @return String[]
	 */

	public static String[] Customer() {
		return new String[] { "customerID", "FK_userName", "creditCard", "customerType", "deleted" };
	}
	/**
	 *  
	 * @return String[]
	 */
	public static String[] CustomerBoughtFromCompany() {
		return new String[] { "FK_customerID", "FK_fuelCompanyName", "dateOfPurchase", "amountBoughtFromCompany",
				"amountPaidCompany" };
	}
	/**
	 * 
	 * @return String[]
	 */
	public static String[] CustomerBoughtInSale() {
		return new String[] { "FK_saleID", "FK_customerID", "amountPaid" };
	}
	/**
	 * 
	 * @return String[]
	 */

	public static String[] Employee() {
		return new String[] { "FK_userName", "role", "affiliation" };
	}
	/**
	 * 
	 * @return String[]
	 */

	public static String[] FastFuel() {
		return new String[] { "FK_registrationPlate", "FK_customerID", "FK_productInStationID", "fastFuelTime",
				"amountBought", "finalPrice" };
	}
	/**
	 * 
	 * @return String[]
	 */

	public static String[] FuelCompany() {
		return new String[] { "fuelCompanyName", "FK_employeeID" };
	}
	/**
	 * 
	 * @return String[]
	 */

	public static String[] FuelStation() {
		return new String[] { "FK_fuelCompanyName", "FK_employeeID", "stationName", "address" };
	}
	/**
	 * 
	 * @return String[]
	 */

	public static String[] FuelStationManager() {
		return new String[] { "FK_employeeID", "phoneNo" };
	}
	/**
	 * 
	 * @return String[]
	 */

	public static String[] FuelStationOrder1() {
		return new String[] { "FK_ordersID", "FK_productInStationID", "assessed", "supplied" };
	}
	/**
	 * 
	 * @return String[]
	 */

	public static String[] FuelStationOrder2() {
		return new String[] { "FK_ordersID", "FK_productInStationID", "assessed", "approved", "reasonDismissal",
				"supplied" };
	}
	/**
	 * 
	 * @return String[]
	 */
	
	public static String[] FuelStationOrder3() {
		return new String[] { "FK_ordersID", "FK_productInStationID", "assessed", "approved", "reasonDismissal",
				"supplied", "timeSupplied" };
	}
	/**
	 * 
	 * @return String[]
	 */

	public static String[] HomeFuelOrder() {
		return new String[] { "FK_ordersID", "FK_customerID", "FK_product_Name", "FK_shipmentType", "duetime",
				"finalPrice" };
	}
	/**
	 * 
	 * @return String[]
	 */

	public static String[] IncomeReport() {
		return new String[] { "FK_repQuarter", "FK_repYear", "FK_fuelStationID", "totalIncome" };
	}
	/**
	 * 
	 * @return String[]
	 */

	public static String[] InventoryReport() {
		return new String[] { "FK_repQuarter", "FK_repYear", "FK_fuelStationID", "totalAmountSold" };
	}
	/**
	 * 
	 * @return String[]
	 */

	public static String[] Notification() {
		return new String[] { "FK_employeeID", "message", "dismissed", "dateCreated" };
	}
	/**
	 * 
	 * @return String[]
	 */

	public static String[] Orders() {
		return new String[] { "orderTime", "amountBought", "address" };
	}
	/**
	 * 
	 * @return String[]
	 */

	public static String[] OutcomeReport() {
		return new String[] { "FK_repQuarter", "FK_repYear", "FK_fuelStationID", "totalAmountBoughtFromSupplier" };
	}
	/**
	 * 
	 * @return String[]
	 */

	public static String[] PeriodicCustomersReport() {
		return new String[] { "dateFrom", "dateTo", "dateCreated" };
	}
	/**
	 * 
	 * @return String[]
	 */

	public static String[] PricingModel1() {
		return new String[] { "FK_customerID", "FK_pricingModelName", "currentDiscount" };
	}
	/**
	 * 
	 * @return String[]
	 */

	public static String[] PricingModel2() {
		return new String[] { "FK_customerID", "FK_pricingModelName", "currentDiscount", "lastMonthUtillization" };
	}
	/**
	 * 
	 * @return String[]
	 */

	public static String[] PricingModelType() {
		return new String[] { "pricingModelName", "description", "defaultDiscount" };
	}
	/**
	 * 
	 * @return String[]
	 */

	public static String[] Product() {
		return new String[] { "productName", "maxPrice", "currentPrice" };
	}
	/**
	 * 
	 * @return String[]
	 */

	public static String[] ProductInIncomeReport() {
		return new String[] { "FK_productInStationID", "FK_repQuarter_IncomeReport", "FK_repYear_IncomeReport",
				"incomePerProduct", "avgPrice" };
	}
	/**
	 * 
	 * @return String[]
	 */

	public static String[] ProductInInventoryReport() {
		return new String[] { "FK_productInStationID", "FK_repQuarter_inventoryReport", "FK_repYear_inventoryReport",
				"amountSold" };
	}
	/**
	 * 
	 * @return String[]
	 */

	public static String[] ProductInOutcomeReport() {
		return new String[] { "FK_productInStationID", "FK_repQuarter_outcomeReport", "FK_repYear_outcomeReport",
				"amountBoughtFromSupplier" };
	}

//	public static String[] ProductInRequest() {
//		return new String[] { "FK_updateRateRequestID", "FK_productName", "requestedRate" };
//	}
	/**
	 * 
	 * @return String[]
	 */

	public static String[] ProductInSalesPattern() {
		return new String[] { "FK_salesPatternID", "FK_productName", "salesDiscount" };
	}
	/**
	 * 
	 * @return String[]
	 */

	public static String[] ProductInStation() {
		return new String[] { "FK_productName", "FK_fuelStationID", "capacity", "threshold" };
	}
	

//	public static String[] ProductRatesUpdateRequest1() {
//		return new String[] { "requestDate", "assessed" };
//	}

//	public static String[] ProductRatesUpdateRequest2() {
//		return new String[] { "requestDate", "assessed", "approved" };
//	}
	/**
	 * 
	 * @return String[]
	 */

	public static String[] PurchasingProgram1() {
		return new String[] { "FK_customerID", "FK_purchasingProgramName", "FK_fuelCompanyName1" };
	}
	/**
	 * 
	 * @return String[]
	 */

	public static String[] PurchasingProgram2() {
		return new String[] { "FK_customerID", "FK_purchasingProgramName", "FK_fuelCompanyName1",
				"FK_fuelCompanyName2" };
	}
	/**
	 * 
	 * @return String[]
	 */

	public static String[] PurchasingProgram3() {
		return new String[] { "FK_customerID", "FK_purchasingProgramName", "FK_fuelCompanyName1", "FK_fuelCompanyName2",
				"FK_fuelCompanyName3" };
	}
	/**
	 * 
	 * @return String[]
	 */

	public static String[] PurchasingProgramType() {
		return new String[] { "purchasingProgramName", "description", "monthlyPrice" };
	}
	/**
	 * 
	 * @return String[]
	 */

	public static String[] QuarterlyReport() {
		return new String[] { "repQuarter", "repYear", "FK_fuelStationID", "dateCreated" };
	}
	/**
	 * 
	 * @return String[]
	 */

	public static String[] RankingSheet() {
		return new String[] { "FK_customerID", "customerTypeRank", "fuelingHoursRank", "fuelTypesRank",
				"updatedForDate" };
	}
	/**
	 * 
	 * @return String[]
	 */

	public static String[] Sale() {
		return new String[] { "FK_salesPatternID", "startTime", "endTime" };
	}
	/**
	 * 
	 * @return String[]
	 */

	public static String[] SaleCommentsReport() {
		return new String[] { "FK_saleID", "numberOfCustomersBought", "sumOfPurchases", "dateCreated" };
	}
	/**
	 * 
	 * @return String[]
	 */

	public static String[] SalesPattern() {
		return new String[] { "durationInMinutes" };
	}
	/**
	 * 
	 * @return String[]
	 */

	public static String[] ShipmentMethod() {
		return new String[] { "shipmentType", "shipmentPrice", "shipmentMultiplier", "deliveryTime" };
	}
	/**
	 * 
	 * @return String[]
	 */

	public static String[] User() {
		return new String[] { "username", "password", "connected", "email", "firstName", "surname" };
	}
	/**
	 * 
	 * @return String[]
	 */

	public static String[] PricingModelUpdateRequest1() {
		return new String[] { "FK_pricingModelName", "requestDate", "requestedDiscount", "assessed" };
	}
	/**
	 * 
	 * @return String[]
	 */

	public static String[] PricingModelUpdateRequest2() {
		return new String[] { "FK_pricingModelName", "requestDate", "requestedDiscount", "assessed", "approved",
				"reasonDismissal" };
	}

}
