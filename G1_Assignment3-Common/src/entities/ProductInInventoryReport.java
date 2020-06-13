package entities;

/**
 * @author Elroy, Vlad, Lior
 */
@SuppressWarnings("serial")
public class ProductInInventoryReport extends ProductInQuarterlyReport {

	// fields
	private double amountSold;
	private double amountEnd;

	/**
	 * 
	 * @param productInStationID
	 * @param repQuarter
	 * @param repYear
	 * @param amountSold
	 * @param amountEnd
	 */
	public ProductInInventoryReport(int productInStationID, int repQuarter, String repYear, double amountSold,
			double amountEnd) {
		super(productInStationID, repQuarter, repYear);
		this.amountSold = amountSold;
		this.amountEnd = amountEnd;
	}

	public double getAmountSold() {
		return amountSold;
	}

	public void setAmountSold(double amountSold) {
		this.amountSold = amountSold;
	}

	public double getAmountEnd() {
		return amountEnd;
	}

	public void setAmountEnd(double amountEnd) {
		this.amountEnd = amountEnd;
	}

	@Override
	public String toString() {
		return "ProductInInventoryReport [" + super.toString() + ", amountSold=" + amountSold + ", amountEnd="
				+ amountEnd + "]";
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

}
