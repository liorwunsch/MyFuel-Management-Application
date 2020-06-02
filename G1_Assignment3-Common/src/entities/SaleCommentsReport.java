package entities;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Elroy, Vlad, Lior
 */
@SuppressWarnings("serial")
public class SaleCommentsReport implements Serializable {

	// primary key
	private int saleID;

	// fields
	private int numberOfCustomersBoughtInSale;
	private double sumOfPurchases;
	private Date dateCreated;

	/**
	 * 
	 * @param saleID
	 * @param numberOfCustomersBoughtInSale
	 * @param sumOfPurchases
	 * @param dateCreated
	 */
	public SaleCommentsReport(int saleID, int numberOfCustomersBoughtInSale, double sumOfPurchases, Date dateCreated) {
		super();
		this.saleID = saleID;
		this.numberOfCustomersBoughtInSale = numberOfCustomersBoughtInSale;
		this.sumOfPurchases = sumOfPurchases;
		this.dateCreated = dateCreated;
	}

	public int getSaleID() {
		return saleID;
	}

	public int getNumberOfCustomersBoughtInSale() {
		return numberOfCustomersBoughtInSale;
	}

	public void setNumberOfCustomersBoughtInSale(int numberOfCustomersBoughtInSale) {
		this.numberOfCustomersBoughtInSale = numberOfCustomersBoughtInSale;
	}

	public double getSumOfPurchases() {
		return sumOfPurchases;
	}

	public void setSumOfPurchases(double sumOfPurchases) {
		this.sumOfPurchases = sumOfPurchases;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Override
	public String toString() {
		return "SaleCommentsReport [saleID=" + saleID + ", numberOfCustomersBoughtInSale="
				+ numberOfCustomersBoughtInSale + ", sumOfPurchases=" + sumOfPurchases + ", dateCreated=" + dateCreated
				+ "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof SaleCommentsReport))
			return false;
		SaleCommentsReport other = (SaleCommentsReport) obj;
		return this.saleID == other.saleID;
	}

}
