package entities;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author Elroy, Vlad, Lior
 */
@SuppressWarnings("serial")
public class ProductRatesUpdateRequest implements Serializable {

	// primary keys
	private Integer updateRateRequestID;

	// fields
	private Date requestDate;
	private boolean assessed;
	private Boolean approved; // can be NULL

	/**
	 * w/o updateRateRequestID auto-inc, approved
	 * @param requestDate
	 * @param assessed
	 */
	public ProductRatesUpdateRequest(Date requestDate, boolean assessed) {
		super();
		this.requestDate = requestDate;
		this.assessed = assessed;
	}

	/**
	 * w/o updateRateRequestID auto-inc
	 * @param requestDate
	 * @param assessed
	 * @param approved
	 */
	public ProductRatesUpdateRequest(Date requestDate, boolean assessed, boolean approved) {
		super();
		this.requestDate = requestDate;
		this.assessed = assessed;
		this.approved = approved;
	}

	/**
	 * w/o approved optional
	 * @param updateRateRequestID
	 * @param requestDate
	 * @param assessed
	 */
	public ProductRatesUpdateRequest(int updateRateRequestID, Date requestDate, boolean assessed) {
		super();
		this.updateRateRequestID = updateRateRequestID;
		this.requestDate = requestDate;
		this.assessed = assessed;
	}

	/**
	 * 
	 * @param updateRateRequestID
	 * @param requestDate
	 * @param assessed
	 * @param approved
	 */
	public ProductRatesUpdateRequest(int updateRateRequestID, Date requestDate, boolean assessed, boolean approved) {
		super();
		this.updateRateRequestID = updateRateRequestID;
		this.requestDate = requestDate;
		this.assessed = assessed;
		this.approved = approved;
	}

	public int getUpdateRateRequestID() {
		return updateRateRequestID;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public boolean isAssessed() {
		return assessed;
	}

	public void setAssessed(boolean assessed) {
		this.assessed = assessed;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	@Override
	public String toString() {
		String str = "ProductRatesUpdateRequest [";
		if (updateRateRequestID != null)
			str += "updateRateRequestID=" + updateRateRequestID + ", ";
		str += ", requestDate=" + requestDate + ", assessed=" + assessed;
		if (approved != null)
			str += ", approved=" + approved;
		return str + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ProductRatesUpdateRequest))
			return false;
		ProductRatesUpdateRequest other = (ProductRatesUpdateRequest) obj;
		if (this.updateRateRequestID == null || other.updateRateRequestID == null)
			return false;
		return this.updateRateRequestID.equals(other.updateRateRequestID);
	}

}
