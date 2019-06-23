package app.models;

import app.enums.Privilege;

/**
 * @author dzimiks
 * Date: 23-06-2019 at 14:40
 */
public class User {

	private String ID;
	private String firstName;
	private String lastName;
	private Privilege privilegeLevel;
	private String username;
	private String password;

	public User(String ID, String firstName, String lastName, Privilege privilegeLevel, String username, String password) {
		this.ID = ID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.privilegeLevel = privilegeLevel;
		this.username = username;
		this.password = password;
	}

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Privilege getPrivilegeLevel() {
		return privilegeLevel;
	}

	public void setPrivilegeLevel(Privilege privilegeLevel) {
		this.privilegeLevel = privilegeLevel;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User{" +
				"ID='" + ID + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", privilegeLevel=" + privilegeLevel +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
