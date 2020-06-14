package entities;

import java.io.Serializable;
import enums.CustomerType;

/**
 * @author Elroy, Vlad, Lior
 */
@SuppressWarnings("serial")
public class Customer implements Serializable {

	// primary keys
	private String customerID;

	// foreign keys
	private String username; // unique

	// fields
	private String creditCard;
	private CustomerType customerType;
	private boolean deleted;

	/**
	 * w/o deleted, username
	 * 
	 * @param customerID
	 * @param creditCard
	 * @param customerType
	 * @param deleted
	 */
	public Customer(String customerID, String creditCard, CustomerType customerType) {
		super();
		this.customerID = customerID;
		this.creditCard = creditCard;
		this.customerType = customerType;
	}

	/**
	 * w/o deleted
	 * 
	 * @param customerID
	 * @param username
	 * @param creditCard
	 * @param customerType
	 * @param deleted
	 */
	public Customer(String customerID, String username, String creditCard, CustomerType customerType) {
		super();
		this.customerID = customerID;
		this.username = username;
		this.creditCard = creditCard;
		this.customerType = customerType;
	}

	/**
	 * everything
	 * 
	 * @param customerID
	 * @param username
	 * @param creditCard
	 * @param customerType
	 * @param deleted
	 */
	public Customer(String customerID, String username, String creditCard, CustomerType customerType, boolean deleted) {
		super();
		this.customerID = customerID;
		this.username = username;
		this.creditCard = creditCard;
		this.customerType = customerType;
		this.deleted = deleted;
	}

	public String getCustomerID() {
		return customerID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	public CustomerType getCustomerType() {
		return customerType;
	}

	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		String str = "Customer [";
		if (customerID != null)
			str += "customerID=" + customerID + ", ";
		str += "username=" + username + ", creditCard=" + creditCard + ", customerType=" + customerType + ", deleted="
				+ deleted + "]";
		return str;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Customer))
			return false;
		Customer other = (Customer) obj;
		return this.username.equals(other.username);
	}

}
