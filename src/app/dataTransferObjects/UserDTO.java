package app.dataTransferObjects;

/**
 * @author dzimiks
 * Date: 23-06-2019 at 17:40
 */
public class UserDTO {

	private String ID;
	private String firstName;
	private String lastName;
	private String privilegeLevel;
	private String username;
	private String password;

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

	public String getPrivilegeLevel() {
		return privilegeLevel;
	}

	public void setPrivilegeLevel(String privilegeLevel) {
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
}
