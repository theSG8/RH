package domain;

import java.util.Vector;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Client extends User {
	@Id
	String username;
	String password;
	String nombre;
	String apellido;
	String dni;
	String email;
	String cuenta;
	@OneToMany(fetch = FetchType.EAGER)
	Vector<Offer> bookedOffers;

	public Client(String username, String password, String nombre, String apellido, String dni, String email,
			String cuenta, Vector<Offer> bookedOffers) {
		super();
		this.username = username;
		this.password = password;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.email = email;
		this.cuenta = cuenta;
		this.bookedOffers = bookedOffers;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public boolean checkPassword(String password) {

		if (this.password.equals(password)) {
			return true;
		} else {
			return false;
		}
	}

	public Vector<Offer> getBookedOffers() {
		return bookedOffers;
	}

	public void addOffer(Offer of) {
		bookedOffers.addElement(of);
	}

	public void removeBooking(Offer o) {
		bookedOffers.remove(o);

	}

	@Override
	public String toString() {
		return "UN: " + username + ", N:" + nombre + " " + apellido + ", DNI:" + dni;

	}
}
