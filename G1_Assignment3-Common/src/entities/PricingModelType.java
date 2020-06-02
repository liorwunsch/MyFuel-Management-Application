package entities;

import java.io.Serializable;
import enums.PricingModelName;

/**
 * @author Elroy, Vlad, Lior
 */
@SuppressWarnings("serial")
public class PricingModelType implements Serializable {

	// primary keys
	private PricingModelName pricingModelName;

	// fields
	private String description;
	private double defaultDiscount;

	/**
	 * 
	 * @param pricingModelName
	 * @param description
	 * @param defaultDiscount
	 */
	public PricingModelType(PricingModelName pricingModelName, String description, double defaultDiscount) {
		super();
		this.pricingModelName = pricingModelName;
		this.description = description;
		this.defaultDiscount = defaultDiscount;
	}

	public PricingModelName getPricingModelName() {
		return pricingModelName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getDefaultDiscount() {
		return defaultDiscount;
	}

	public void setDefaultDiscount(double defaultDiscount) {
		this.defaultDiscount = defaultDiscount;
	}

	@Override
	public String toString() {
		return "PricingModelType [pricingModelName=" + pricingModelName + ", description=" + description
				+ ", defaultDiscount=" + defaultDiscount + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof PricingModelType))
			return false;
		PricingModelType other = (PricingModelType) obj;
		return this.pricingModelName.equals(other.pricingModelName);
	}

}
