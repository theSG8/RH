package domain;

import java.util.Vector;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Client {

	@Id
	String username;
	String password;
	@OneToMany(fetch = FetchType.EAGER)
	Vector<Offer> reservedOffers;

	public Client(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		reservedOffers = new Vector<Offer>();
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

	public Vector<Offer> getReservedOffers() {
		return reservedOffers;
	}
	
	public void addOffer(Offer of ){
		reservedOffers.addElement(of);
	}
}
