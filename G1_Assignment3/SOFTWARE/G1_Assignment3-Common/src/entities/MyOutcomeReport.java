package entities;

import java.util.Date;
import java.util.Map;

/**
 * @author Liad
 */
@SuppressWarnings("serial")
public class MyOutcomeReport extends QuarterlyReport {

	// primary keys
	/* private int repQuarter; */
	/* private String repYear; */
	/* private int fuelStationID; */

	double totalAmountBoughtFromSupplier;
	Map<ProductInStation, Double> amountBoughtPerProduct;

	/**
	 * 
	 * @param repQuarter
	 * @param repYear
	 * @param fuelStationID
	 * @param dateCreated
	 * @param totalAmountBoughtFromSupplier
	 * @param amountBoughtPerProduct
	 */
	public MyOutcomeReport(int repQuarter, String repYear, int fuelStationID, Date dateCreated,
			double totalAmountBoughtFromSupplier, Map<ProductInStation, Double> amountBoughtPerProduct) {
		super(repQuarter, repYear, fuelStationID, dateCreated);
		this.totalAmountBoughtFromSupplier = totalAmountBoughtFromSupplier;
		this.amountBoughtPerProduct = amountBoughtPerProduct;
	}

	public double getTotalAmountBoughtFromSupplier() {
		return totalAmountBoughtFromSupplier;
	}

	/**
	 * 
	 * @param totalAmountBoughtFromSupplier
	 */
	public void setTotalAmountBoughtFromSupplier(double totalAmountBoughtFromSupplier) {
		this.totalAmountBoughtFromSupplier = totalAmountBoughtFromSupplier;
	}

	public Map<ProductInStation, Double> getAmountBoughtPerProduct() {
		return amountBoughtPerProduct;
	}

	/**
	 * 
	 * @param amountBoughtPerProduct
	 */
	public void setAmountProductBought(Map<ProductInStation, Double> amountBoughtPerProduct) {
		this.amountBoughtPerProduct = amountBoughtPerProduct;
	}

	@Override
	public String toString() {
		return "OutcomeReport [" + super.toString() + ", totalAmountBoughtFromSupplier=" + totalAmountBoughtFromSupplier
				+ "]";
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

}
