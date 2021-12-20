package entities;

/**
 * @author Elroy, Vlad, Lior
 */
@SuppressWarnings("serial")
public class ProductInInventoryReport extends ProductInQuarterlyReport {

	// fields
	private double amountSold;

	/**
	 * 
	 * @param productInStationID
	 * @param repQuarter
	 * @param repYear
	 * @param amountSold
	 */
	public ProductInInventoryReport(int productInStationID, int repQuarter, String repYear, double amountSold) {
		super(productInStationID, repQuarter, repYear);
		this.amountSold = amountSold;
	}

	public double getAmountSold() {
		return amountSold;
	}

	public void setAmountSold(double amountSold) {
		this.amountSold = amountSold;
	}

	@Override
	public String toString() {
		return "ProductInInventoryReport [" + super.toString() + ", amountSold=" + amountSold + "]";
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

}
