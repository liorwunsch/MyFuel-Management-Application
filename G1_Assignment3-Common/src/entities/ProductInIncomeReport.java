package entities;

@SuppressWarnings("serial")
public class ProductInIncomeReport extends ProductInQuarterlyReport {

	// fields
	private double incomePerProduct;
	private double avgPrice;

	public ProductInIncomeReport(int productInStationID, int repQuarter, String repYear, double incomePerProduct,
			double avgPrice) {
		super(productInStationID, repQuarter, repYear);
		this.incomePerProduct = incomePerProduct;
		this.avgPrice = avgPrice;
	}

	public double getIncomePerProduct() {
		return incomePerProduct;
	}

	public void setIncomePerProduct(double incomePerProduct) {
		this.incomePerProduct = incomePerProduct;
	}

	public double getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(double avgPrice) {
		this.avgPrice = avgPrice;
	}

	@Override
	public String toString() {
		return "ProductInIncomeReport [" + super.toString() + ", incomePerProduct=" + incomePerProduct + ", avgPrice="
				+ avgPrice + "]";
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

}
