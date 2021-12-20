package entities;

import java.util.Date;
import java.util.Map;

/**
 * @author Liad
 */
@SuppressWarnings("serial")
public class MyInventoryReport extends QuarterlyReport {

	// primary keys
	/* private int repQuarter; */
	/* private String repYear; */
	/* private int fuelStationID; */

	Map<ProductInStation, Double> amountsPerProduct;
	double totalAmountSold;

	/**
	 * 
	 * @param repQuarter
	 * @param repYear
	 * @param fuelStationID
	 * @param dateCreated
	 * @param amountsPerProduct
	 */
	public MyInventoryReport(int repQuarter, String repYear, int fuelStationID, Date dateCreated,
			Map<ProductInStation, Double> amountsPerProduct, double totalAmountSold) {
		super(repQuarter, repYear, fuelStationID, dateCreated);
		this.amountsPerProduct = amountsPerProduct;
		this.totalAmountSold = totalAmountSold;
	}

	public double getTotalAmountSold() {
		return totalAmountSold;
	}

	/**
	 * 
	 * @param totalAmountSold
	 */
	public void setTotalAmountSold(double totalAmountSold) {
		this.totalAmountSold = totalAmountSold;
	}

	public Map<ProductInStation, Double> getAmountsPerProduct() {
		return amountsPerProduct;
	}

	/**
	 * 
	 * @param amountsPerProduct
	 */
	public void setAmountsPerProduct(Map<ProductInStation, Double> amountsPerProduct) {
		this.amountsPerProduct = amountsPerProduct;
	}

	@Override
	public String toString() {
		return "InventoryReport [" + super.toString() + "totalAmountSold:" + totalAmountSold + "]";
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

}
