package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Admin {
	
	@Id
	String userName;
	String passWord;

	public Admin(String userName, String passWord) {
		
		this.userName = userName;
		this.passWord = passWord;
		
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	public boolean checkPassword(String password) {

		if (this.passWord.equals(password)) {
			return true;
		} else {
			return false;
		}
	}
	
}
