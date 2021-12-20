package entities;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Elroy, Vlad, Lior
 */
@SuppressWarnings("serial")
public class PeriodicCustomersReport implements Serializable {

	// primary keys
	private Date dateFrom;
	private Date dateTo;

	// fields
	private Date dateCreated;

	/**
	 * 
	 * @param dateFrom
	 * @param dateTo
	 * @param dateCreated
	 */
	public PeriodicCustomersReport(Date dateFrom, Date dateTo, Date dateCreated) {
		super();
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.dateCreated = dateCreated;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Override
	public String toString() {
		return "PeriodicCustomersReport [dateFrom=" + dateFrom + ", dateTo=" + dateTo + ", dateCreated=" + dateCreated
				+ "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof PeriodicCustomersReport))
			return false;
		PeriodicCustomersReport other = (PeriodicCustomersReport) obj;
		return this.dateFrom.equals(other.dateFrom) && this.dateTo.equals(other.dateTo);
	}

}
