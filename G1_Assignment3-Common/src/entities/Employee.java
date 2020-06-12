package entities;

import java.io.Serializable;
import enums.Affiliation;

/**
 * @author Elroy, Vlad, Lior
 */
@SuppressWarnings("serial")
public class Employee implements Serializable {

	// primary keys
	private Integer employeeID;

	// foreign keys
	private String username; // unique

	// fields
	private String role;
	private Affiliation affiliation;

	/**
	 * without employeeID auto-inc
	 * 
	 * @param username
	 * @param role
	 * @param affiliation
	 */
	public Employee(String username, String role, Affiliation affiliation) {
		super();
		this.username = username;
		this.role = role;
		this.affiliation = affiliation;
	}

	/**
	 * with employeeID
	 * 
	 * @param employeeID
	 * @param username
	 * @param role
	 * @param affiliation
	 */
	public Employee(int employeeID, String username, String role, Affiliation affiliation) {
		super();
		this.employeeID = employeeID;
		this.username = username;
		this.role = role;
		this.affiliation = affiliation;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Affiliation getAffiliation() {
		return affiliation;
	}

	public void setAffiliation(Affiliation affiliation) {
		this.affiliation = affiliation;
	}

	@Override
	public String toString() {
		String str = "Employee [";
		if (employeeID != null)
			str += "employeeID=" + employeeID + ", ";
		str += "username=" + username + ", role=" + role + ", affiliation=" + affiliation + "]";
		return str;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Employee))
			return false;
		Employee other = (Employee) obj;
		return this.username.equals(other.username);
	}

}
