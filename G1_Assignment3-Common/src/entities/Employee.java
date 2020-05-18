package entities;

import enums.Affiliation;

/**
 * @version Final
 * @author Lior
 */
@SuppressWarnings("serial")
public class Employee extends User {

	private String employeeID;
	private String role;
	private Affiliation affiliation;

	public Employee() {
		super();
	}

	/**
	 * @param employeeID
	 * @param role
	 * @param affiliation
	 */
	public Employee(String employeeID, String role, Affiliation affiliation) {
		super();
		this.employeeID = employeeID;
		this.role = role;
		this.affiliation = affiliation;
	}

	/**
	 * @return employeeID
	 */
	public String getEmployeeID() {
		return employeeID;
	}

	/**
	 * @param employeeID
	 */
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	/**
	 * @return role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return affiliation
	 */
	public Affiliation getAffiliation() {
		return affiliation;
	}

	/**
	 * @param affiliation
	 */
	public void setAffiliation(Affiliation affiliation) {
		this.affiliation = affiliation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((affiliation == null) ? 0 : affiliation.hashCode());
		result = prime * result + ((employeeID == null) ? 0 : employeeID.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
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
		Employee other = (Employee) obj;
		if (affiliation != other.affiliation)
			return false;
		if (employeeID == null) {
			if (other.employeeID != null)
				return false;
		} else if (!employeeID.equals(other.employeeID))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [employeeID=" + employeeID + ", role=" + role + ", affiliation=" + affiliation
				+ ", toString()=" + super.toString() + "]";
	}

}
