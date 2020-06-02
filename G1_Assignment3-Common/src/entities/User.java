package entities;

import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable {

	// primary keys
	private String username;

	// fields
	private String password;
	private boolean connected;
	private String email;
	private String firstName;
	private String surname;

	// java additions
	private String function; /* what the server will do to this */

	public User(String username) {
		this.username = username;
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public User(String username, String password, boolean connected, String email, String firstName, String surname) {
		super();
		this.username = username;
		this.password = password;
		this.connected = connected;
		this.email = email;
		this.firstName = firstName;
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", connected=" + connected + ", email=" + email
				+ ", firstName=" + firstName + ", surname=" + surname + ", function=" + function + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		return this.username.equals(other.username);
	}

}
