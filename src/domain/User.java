package domain;

public class User {

	String username;
	String password;
	String apellido;
	String nombre;
	String dni;

	@Override
	public String toString() {
		return "UN: " + username + ", N:" + nombre + " " + apellido + ", DNI:" + dni;

	}
}
