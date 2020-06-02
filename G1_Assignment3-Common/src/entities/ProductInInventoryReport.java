package entities;

/**
 * @author Elroy, Vlad, Lior
 */
@SuppressWarnings("serial")
public class ProductInInventoryReport extends ProductInQuarterlyReport {

	// fields
	private double amountSold;
	private double amountBegin;
	private double amountEnd;

	/**
	 * 
	 * @param productInStationID
	 * @param repQuarter
	 * @param repYear
	 * @param amountSold
	 * @param amountBegin
	 * @param amountEnd
	 */
	public ProductInInventoryReport(int productInStationID, int repQuarter, String repYear, double amountSold,
			double amountBegin, double amountEnd) {
		super(productInStationID, repQuarter, repYear);
		this.amountSold = amountSold;
		this.amountBegin = amountBegin;
		this.amountEnd = amountEnd;
	}

	public double getAmountSold() {
		return amountSold;
	}

	public void setAmountSold(double amountSold) {
		this.amountSold = amountSold;
	}

	public double getAmountBegin() {
		return amountBegin;
	}

	public void setAmountBegin(double amountBegin) {
		this.amountBegin = amountBegin;
	}

	public double getAmountEnd() {
		return amountEnd;
	}

	public void setAmountEnd(double amountEnd) {
		this.amountEnd = amountEnd;
	}

	@Override
	public String toString() {
		return "ProductInInventoryReport [" + super.toString() + ", amountSold=" + amountSold + ", amountBegin="
				+ amountBegin + ", amountEnd=" + amountEnd + "]";
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

}
