package domain;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Owner implements User {

	@Id
	String username;
	String password;
	ArrayList<RuralHouse> ruralHouses = new ArrayList<>();

	public Owner(String username, String password) {
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

	public ArrayList<RuralHouse> getRuralHouses() {
		return ruralHouses;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int countHouses() {
		return ruralHouses.size();
	}

	public void addHouse(RuralHouse house) {
		ruralHouses.add(house);
	}

	public boolean checkPassword(String password) {

		System.out.println("La pass es:"+ this.password +" y el intento es " + password);
		if (this.password.equals(password)) {
			return true;
		} else {
			return false;
		}
	}

}
