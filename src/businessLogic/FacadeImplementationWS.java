package businessLogic;

import java.util.Date;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.swing.ImageIcon;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Admin;
import domain.Booking;
import domain.Client;
import domain.ClientReport;
import domain.HouseReport;
//import domain.Booking;
import domain.Offer;
import domain.Owner;
import domain.OwnerReport;
import domain.RuralHouse;
import exceptions.BadDates;
import exceptions.ExistingTitleException;
import exceptions.OverlappingOfferExists;
import exceptions.PasswordsDoesNotMatch;
import exceptions.UserAlreadyExists;
import exceptions.UserNotExist;
import exceptions.WrongPassword;

//Service Implementation
@WebService(endpointInterface = "businessLogic.ApplicationFacadeInterfaceWS")
public class FacadeImplementationWS implements ApplicationFacadeInterfaceWS {

	private int nextHouseNumber;

	/**
	 * 
	 */

	public FacadeImplementationWS() {
		ConfigXML c = ConfigXML.getInstance();
		if (c.getDataBaseOpenMode().equals("initialize")) {
			DataAccess dbManager = new DataAccess();
			// dbManager.initializeDB();
			dbManager.close();
		}

	}

	/**
	 * This method creates an offer with a house number, first day, last day and
	 * price
	 * 
	 * @param House
	 *            number, start day, last day and price
	 * @return the created offer, or null, or an exception
	 */
	@Override
	public Offer createOffer(RuralHouse ruralHouse, Date firstDay, Date lastDay, float price)
			throws OverlappingOfferExists, BadDates {
		System.out.println(">> FacadeImplementationWS: createOffer=> ruralHouse= " + ruralHouse + " firstDay= "
				+ firstDay + " lastDay=" + lastDay + " price=" + price);

		DataAccess dbManager = new DataAccess();
		Offer o = null;
		if (firstDay.compareTo(lastDay) >= 0) {
			dbManager.close();
			throw new BadDates();
		}

		boolean b = dbManager.existsOverlappingOffer(ruralHouse, firstDay, lastDay);
		if (!b) {
			o = dbManager.createOffer(ruralHouse, firstDay, lastDay, price);
		}

		dbManager.close();
		System.out.println("<< FacadeImplementationWS: createOffer=> O= " + o);
		return o;
	}

	@Override
	public Vector<RuralHouse> getAllRuralHouses() {
		System.out.println(">> FacadeImplementationWS: getAllRuralHouses");

		DataAccess dbManager = new DataAccess();

		Vector<RuralHouse> ruralHouses = dbManager.getAllRuralHouses();
		dbManager.close();
		System.out.println("<< FacadeImplementationWS:: getAllRuralHouses");

		return ruralHouses;

	}

	/**
	 * This method obtains the offers of a ruralHouse in the provided dates
	 * 
	 * @param ruralHouse
	 *            , the ruralHouse to inspect
	 * @param firstDay
	 *            , first day in a period range
	 * @param lastDay
	 *            , last day in a period range
	 * @return the first offer that overlaps with those dates, or null if there
	 *         is no overlapping offer
	 */

	@Override
	@WebMethod
	public Vector<Offer> getOffers(RuralHouse rh, Date firstDay, Date lastDay) {

		DataAccess dbManager = new DataAccess();
		Vector<Offer> offers = new Vector<Offer>();
		offers = dbManager.getOffers(rh, firstDay, lastDay);
		dbManager.close();

		return offers;
	}

	public void close() {
		DataAccess dbManager = new DataAccess();

		dbManager.close();

	}

	@Override
	public void initializeBD() {

		DataAccess dbManager = new DataAccess();
		dbManager.initializeDB();
		dbManager.close();

	}

	@Override
	public Owner makeOwnerLogin(String user, String pass) throws WrongPassword, UserNotExist {
		DataAccess dbManager = new DataAccess();
		Owner ow = dbManager.getOwner(user);
		if (ow == null) {
			dbManager.close();
			throw new UserNotExist();
		} else {
			if (ow.checkPassword(pass)) {
				dbManager.close();
				return ow;
			} else {
				dbManager.close();
				throw new WrongPassword();
			}
		}

	}

	@Override
	public Client makeClientLogin(String user, String pass) throws WrongPassword, UserNotExist {
		DataAccess dbManager = new DataAccess();
		Client cl = dbManager.getClient(user);
		if (cl == null) {
			dbManager.close();
			throw new UserNotExist();
		} else {
			if (cl.checkPassword(pass)) {
				dbManager.close();
				return cl;
			} else {
				dbManager.close();
				throw new WrongPassword();
			}
		}

	}

	@Override
	public RuralHouse addHouse(String des, String city, Owner current, String nKitchens, String nBedRooms,
			String nBathRooms, String nDrooms, String nParking) {
		RuralHouse rh = new RuralHouse(des, city, current.getUsername(), nKitchens, nBedRooms, nBathRooms, nDrooms,
				nParking);
		DataAccess dbManager = new DataAccess();
		dbManager.storeHouse(rh, current);
		dbManager.close();

		return rh;
	}

	// A�ade un nuevo owner, llamando al metodo de la base de datos
	public void addNewOwner(String username, String password, String nombre, String apellido, String dni, String email,
			String cuenta) throws UserAlreadyExists {
		Owner o = new Owner(username, password, nombre, apellido, dni, email, cuenta);
		DataAccess dbManager = new DataAccess();
		dbManager.storeNewOwner(o);
		dbManager.close();
	}

	// Comprueba si dos contrase�as coinciden
	public boolean checkDoublePassword(String pw1, String pw2) throws PasswordsDoesNotMatch {
		if (pw1.equals(pw2)) {
			return true;
		} else {
			throw new PasswordsDoesNotMatch();
		}
	}

	// Comprueba las contrase�as del registro de owner, y si coinciden a�ade ese
	// owner a la BD
	@Override
	public void checkAddOwner(String username, String pw1, String pw2, String nombre, String apellido, String dni,
			String email, String cuenta) throws PasswordsDoesNotMatch, UserAlreadyExists {
		checkDoublePassword(pw1, pw2);
		addNewOwner(username, pw1, nombre, apellido, dni, email, cuenta);
	}

	@Override
	public void checkAddClient(String username, String pw1, String pw2, String nombre, String apellido, String dni,
			String email, String cuenta) throws PasswordsDoesNotMatch, UserAlreadyExists {
		checkDoublePassword(pw1, pw2);
		addNewClient(username, pw1, nombre, apellido, dni, email, cuenta);
	}

	@Override
	public void addNewClient(String username, String password, String nombre, String apellido, String dni, String email,
			String cuenta) throws UserAlreadyExists {
		Client c = new Client(username, password, nombre, apellido, dni, email, cuenta, null);
		DataAccess dbManager = new DataAccess();
		dbManager.storeNewClient(c);
		dbManager.close();
	}

	@Override
	public void removeOffer(Offer of) {
		DataAccess dbManager = new DataAccess();
		dbManager.removeOffer(of);
		dbManager.close();
	}

	@Override
	public void removeHouse(RuralHouse rh) {
		DataAccess dbManager = new DataAccess();
		dbManager.removeHouse(rh);
		dbManager.close();
	}

	@Override
	public Vector<Offer> getAllOffers() {
		DataAccess dbManager = new DataAccess();
		Vector<Offer> res = dbManager.getAllOffers();
		dbManager.close();
		return res;
	}

	@Override
	public Vector<RuralHouse> getOwnerHouses(Owner ow) {
		DataAccess dbManager = new DataAccess();
		Vector<RuralHouse> res = dbManager.getOwnerHouses(ow.getUsername());
		dbManager.close();
		return res;
	}

	@Override
	public Vector<Offer> getOwnerOffers(Owner ow) {

		Vector<Offer> res = new Vector<Offer>();
		DataAccess dbManager = new DataAccess();
		res = dbManager.getOwnerOffers(ow.getUsername());
		dbManager.close();
		return res;
	}

	@Override
	public ImageIcon getHouseImage(RuralHouse currentHouse) {

		return new ImageIcon("img/" + currentHouse.getHouseNumber() + ".png");
	}

	@Override
	public Vector<Offer> getHouseOffers(RuralHouse rh) {
		DataAccess dbManager = new DataAccess();
		Vector<Offer> offers = new Vector<Offer>();
		offers = dbManager.getHouseOffers(rh);
		dbManager.close();
		return offers;
	}

	@Override
	public void bookOffer(Client currentClient, int offerNumber) {
		DataAccess dbManager = new DataAccess();
		dbManager.bookOffer(currentClient.getUsername(), offerNumber);
		dbManager.close();

	}

	@Override
	public void cancelBooking(Booking b) {
		DataAccess dbManager = new DataAccess();
		dbManager.cancelBooking(b);
		dbManager.close();
	}

	@Override
	public Vector<Booking> getClientBookings(Client client) {

		Vector<Booking> res = new Vector<Booking>();
		DataAccess dbManager = new DataAccess();
		res = dbManager.getClientBookings(client.getUsername());
		dbManager.close();
		return res;
	}

	@Override
	public Admin makeAdminLogin(String userName, String pass) throws UserNotExist, WrongPassword {
		DataAccess dbManager = new DataAccess();
		Admin ad = dbManager.getAdmin(userName);

		if (ad == null) {
			dbManager.close();
			throw new UserNotExist();
		} else {
			if (ad.checkPassword(pass)) {
				dbManager.close();
				return ad;
			} else {
				dbManager.close();
				throw new WrongPassword();
			}
		}
	}

	@Override
	public void checkAddAdmin(String user, String pass1, String pass2) throws PasswordsDoesNotMatch, UserAlreadyExists {
		checkDoublePassword(pass1, pass2);
		addNewAdmin(user, pass1);
	}

	private void addNewAdmin(String user, String pass) throws UserAlreadyExists {
		Admin a = new Admin(user, pass);
		DataAccess dbManager = new DataAccess();
		dbManager.storeNewAdmin(a);
		dbManager.close();

	}

	@Override
	public Vector<HouseReport> getHouseReports() {
		Vector<HouseReport> res = new Vector<HouseReport>();
		DataAccess dbManager = new DataAccess();
		res = dbManager.getHouseReports();
		dbManager.close();
		return res;
	}

	@Override
	public Vector<ClientReport> getClientReports() {
		Vector<ClientReport> res = new Vector<ClientReport>();
		DataAccess dbManager = new DataAccess();
		res = dbManager.getClientReports();
		dbManager.close();
		return res;
	}

	@Override
	public Vector<OwnerReport> getOwnerReports() {
		Vector<OwnerReport> res = new Vector<OwnerReport>();
		DataAccess dbManager = new DataAccess();
		res = dbManager.getOwnerReports();
		dbManager.close();
		return res;
	}

	@Override
	public Vector<Client> getAllClients() {
		Vector<Client> res = new Vector<Client>();
		DataAccess dbManager = new DataAccess();
		res = dbManager.getAllClients();
		dbManager.close();
		return res;
	}

	@Override
	public Vector<Owner> getAllOwners() {
		Vector<Owner> res = new Vector<Owner>();
		DataAccess dbManager = new DataAccess();
		res = dbManager.getAllOwners();
		dbManager.close();
		return res;
	}

	@Override
	public void addNewHouseReport(HouseReport hr) throws ExistingTitleException {
		DataAccess dbManager = new DataAccess();
		dbManager.storeNewHouseReport(hr);
		dbManager.close();
	}

	@Override
	public void addNewClientReport(ClientReport cr) throws ExistingTitleException {
		DataAccess dbManager = new DataAccess();
		dbManager.storeNewClientReport(cr);
		dbManager.close();
	}

	@Override
	public void addNewOwnerReport(OwnerReport or) throws ExistingTitleException {
		DataAccess dbManager = new DataAccess();
		dbManager.storeNewOwnerReport(or);
		dbManager.close();
	}

}
