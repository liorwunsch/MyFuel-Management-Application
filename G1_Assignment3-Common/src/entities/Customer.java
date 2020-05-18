package entities;

/**
 * @version Final
 * @author Lior
 */
@SuppressWarnings("serial")
public class Customer extends User {

	private String customerID;
	private String creditCard;

	public Customer() {
		super();
	}

	/**
	 * @param customerID
	 * @param creditCard
	 */
	public Customer(String customerID, String creditCard) {
		super();
		this.customerID = customerID;
		this.creditCard = creditCard;
	}

	/**
	 * @return customerID
	 */
	public String getCustomerID() {
		return customerID;
	}

	/**
	 * @param customerID
	 */
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	/**
	 * @return creditCard
	 */
	public String getCreditCard() {
		return creditCard;
	}

	/**
	 * @param creditCard
	 */
	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((creditCard == null) ? 0 : creditCard.hashCode());
		result = prime * result + ((customerID == null) ? 0 : customerID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (creditCard == null) {
			if (other.creditCard != null)
				return false;
		} else if (!creditCard.equals(other.creditCard))
			return false;
		if (customerID == null) {
			if (other.customerID != null)
				return false;
		} else if (!customerID.equals(other.customerID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", creditCard=" + creditCard + ", toString()=" + super.toString()
				+ "]";
	}

}
