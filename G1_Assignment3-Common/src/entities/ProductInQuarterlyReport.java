package entities;

import java.io.Serializable;

/**
 * @author Elroy, Vlad, Lior
 */
@SuppressWarnings("serial")
public abstract class ProductInQuarterlyReport implements Serializable {

	// primary keys
	private int productInStationID;
	private int repQuarter;
	private String repYear;

	/**
	 * 
	 * @param productInStationID
	 * @param repQuarter
	 * @param repYear
	 */
	public ProductInQuarterlyReport(int productInStationID, int repQuarter, String repYear) {
		super();
		this.productInStationID = productInStationID;
		this.repQuarter = repQuarter;
		this.repYear = repYear;
	}

	public int getProductInStationID() {
		return productInStationID;
	}

	public int getRepQuarter() {
		return repQuarter;
	}

	public String getRepYear() {
		return repYear;
	}

	@Override
	public String toString() {
		return "ProductInQuarterlyReport [productInStationID=" + productInStationID + ", repQuarter=" + repQuarter
				+ ", repYear=" + repYear + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ProductInQuarterlyReport))
			return false;
		ProductInQuarterlyReport other = (ProductInQuarterlyReport) obj;
		return this.productInStationID == other.productInStationID && this.repQuarter == other.repQuarter
				&& this.repYear.equals(other.repYear);
	}

}
