package entities;

import java.io.Serializable;

/**
 * @version Final
 * @author Lior
 */
@SuppressWarnings("serial")
public class User implements Serializable {

	private String function; // what the server will do to this

	private String username;
	private String password;
	private boolean connected;
	private String firstName;
	private String surname;
	private String email;

	public User() {
		super();
	}

	/**
	 * @param username
	 * @param password
	 * @param connected
	 * @param firstName
	 * @param surname
	 * @param email
	 */
	public User(String username, String password, boolean connected, String firstName, String surname, String email) {
		super();
		this.username = username;
		this.password = password;
		this.connected = connected;
		this.firstName = firstName;
		this.surname = surname;
		this.email = email;
	}

	/**
	 * @return function
	 */
	public String getFunction() {
		return function;
	}

	/**
	 * @param function
	 */
	public void setFunction(String function) {
		this.function = function;
	}

	/**
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return connected
	 */
	public boolean isConnected() {
		return connected;
	}

	/**
	 * @param connected
	 */
	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	/**
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (connected ? 1231 : 1237);
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (connected != other.connected)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [function=" + function + ", username=" + username + ", password=" + password + ", connected="
				+ connected + ", firstName=" + firstName + ", surname=" + surname + ", email=" + email + "]";
	}

}
