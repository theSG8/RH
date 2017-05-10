package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Admin extends User {

	@Id
	String username;
	String password;

	public Admin(String userName, String passWord) {

		this.username = userName;
		this.password = passWord;

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String passWord) {
		this.password = passWord;
	}

	public boolean checkPassword(String password) {

		if (this.password.equals(password)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "ADMINISTRADOR: " + username;
	}
}
