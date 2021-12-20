package entities;

import java.util.Date;
import java.util.Map;

/**
 * @author Liad
 */
@SuppressWarnings("serial")
public class MyIncomeReport extends QuarterlyReport {

	// primary keys
	/* private int repQuarter; */
	/* private String repYear; */
	/* private int fuelStationID; */

	// fields
	private double totalIncome;
	private Map<ProductInStation, Double> incomePerProduct;

	/**
	 * 
	 * @param repQuarter
	 * @param repYear
	 * @param fuelStationID
	 * @param dateCreated
	 * @param totalIncome
	 */
	public MyIncomeReport(int repQuarter, String repYear, int fuelStationID, Date dateCreated, double totalIncome,
			Map<ProductInStation, Double> incomePerProduct) {
		super(repQuarter, repYear, fuelStationID, dateCreated);
		this.totalIncome = totalIncome;
		this.incomePerProduct = incomePerProduct;
	}

	public double getTotalIncome() {
		return totalIncome;
	}

	public Map<ProductInStation, Double> getIncomePerProduct() {
		return incomePerProduct;
	}

	/**
	 * 
	 * @param incomePerProduct
	 */
	public void setIncomePerProduct(Map<ProductInStation, Double> incomePerProduct) {
		this.incomePerProduct = incomePerProduct;
	}

	/**
	 * 
	 * @param totalIncome
	 */
	public void setTotalIncome(double totalIncome) {
		this.totalIncome = totalIncome;
	}

	@Override
	public String toString() {
		return "IncomeReport [" + super.toString() + ", totalIncome=" + totalIncome + "]";
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
}
