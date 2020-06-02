package entities;

/**
 * @author Elroy, Vlad, Lior
 */
@SuppressWarnings("serial")
public class ProductInOutcomeReport extends ProductInQuarterlyReport {

	// fields
	private double amountBoughtFromSupplier;

	/**
	 * 
	 * @param productInStationID
	 * @param repQuarter
	 * @param repYear
	 * @param amountBoughtFromSupplier
	 */
	public ProductInOutcomeReport(int productInStationID, int repQuarter, String repYear,
			double amountBoughtFromSupplier) {
		super(productInStationID, repQuarter, repYear);
		this.amountBoughtFromSupplier = amountBoughtFromSupplier;
	}

	public double getAmountBoughtFromSupplier() {
		return amountBoughtFromSupplier;
	}

	public void setAmountBoughtFromSupplier(double amountBoughtFromSupplier) {
		this.amountBoughtFromSupplier = amountBoughtFromSupplier;
	}

	@Override
	public String toString() {
		return "ProductInOutcomeReport [" + super.toString() + ", amountBoughtFromSupplier=" + amountBoughtFromSupplier
				+ "]";
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

}
