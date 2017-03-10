package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Client implements User {

	@Id
	String username;
	String password;

	public Client(String username, String password) {
		super();
		this.username = username;
		this.password = password;
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

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean checkPassword(String password) {

		if (password.equals(password)) {
			return true;
		} else {
			return false;
		}
	}
}
