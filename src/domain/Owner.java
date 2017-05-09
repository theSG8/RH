package domain;

import java.util.Vector;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Owner extends User {

	@Id
	String username;
	String password;
	String nombre;
	String apellido;
	String dni;
	String email;
	String cuenta;
	Vector<RuralHouse> ruralHouses;

	public Owner(String username, String password, String nombre,
			String apellido, String dni, String email, String cuenta, Vector<RuralHouse> ruralHouses) {
		super();
		this.username = username;
		this.password = password;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.email = email;
		this.cuenta = cuenta;
		this.ruralHouses = ruralHouses;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public Vector<RuralHouse> getRuralHouses() {
		return ruralHouses;
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

	public int countHouses() {
		return ruralHouses.size();
	}

	public void addHouse(RuralHouse house) {
		ruralHouses.add(house);
	}

	public boolean checkPassword(String password) {

		System.out.println("La pass es:" + this.password + " y el intento es " + password);
		if (this.password.equals(password)) {
			return true;
		} else {
			return false;
		}
	}

}
