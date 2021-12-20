package entities;

import java.io.Serializable;
import java.util.Date;

import enums.PricingModelName;

/**
 * @author Elroy, Lior
 */
@SuppressWarnings("serial")
public class PricingModelUpdateRequest implements Serializable {

	private int requestID;
	private PricingModelName pricingModelName;
	private Date requestDate;
	private Double requestedDiscount;
	private boolean assessed;
	private boolean approved;
	private String reasonDismissal;

	public PricingModelUpdateRequest(int requestID) {
		super();
		this.requestID = requestID;
	}

	public int getRequestID() {
		return requestID;
	}

	public PricingModelName getPricingModelName() {
		return pricingModelName;
	}

	public void setPricingModelName(PricingModelName pricingModelName) {
		this.pricingModelName = pricingModelName;
	}

	public Double getRequestedDiscount() {
		return requestedDiscount;
	}

	public void setRequestedDiscount(double requestedDiscount) {
		this.requestedDiscount = requestedDiscount;
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

	public String getReasonDismissal() {
		return reasonDismissal;
	}

	public void setReasonDismissal(String reasonDismissal) {
		this.reasonDismissal = reasonDismissal;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

}
