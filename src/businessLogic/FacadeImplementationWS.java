package businessLogic;

import java.util.Date;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import dataAccess.DataAccess;

//import domain.Booking;
import domain.Offer;
import domain.Owner;
import domain.RuralHouse;
import exceptions.BadDates;
import exceptions.OverlappingOfferExists;
import exceptions.PasswordsDoesNotMatch;
import exceptions.UserAlreadyExists;
import exceptions.UserNotExist;
import exceptions.WrongPassword;

//Service Implementation
@WebService(endpointInterface = "businessLogic.ApplicationFacadeInterfaceWS")
public class FacadeImplementationWS implements ApplicationFacadeInterfaceWS {
	
	int erior;
	private int nextHouseNumber;
	/**
	 * 
	 */

	public FacadeImplementationWS() {
		ConfigXML c = ConfigXML.getInstance();
		if (c.getDataBaseOpenMode().equals("initialize")) {
			DataAccess dbManager = new DataAccess();
			//dbManager.initializeDB();
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
	public Offer createOffer(RuralHouse ruralHouse, Date firstDay,
			Date lastDay, float price) throws OverlappingOfferExists, BadDates {
		System.out
				.println(">> FacadeImplementationWS: createOffer=> ruralHouse= "
						+ ruralHouse
						+ " firstDay= "
						+ firstDay
						+ " lastDay="
						+ lastDay + " price=" + price);

		DataAccess dbManager = new DataAccess();
		Offer o = null;
		if (firstDay.compareTo(lastDay) >= 0) {
			dbManager.close();
			throw new BadDates();
		}

		boolean b = dbManager.existsOverlappingOffer(ruralHouse, firstDay,
				lastDay);
		if (!b)
			o = dbManager.createOffer(ruralHouse, firstDay, lastDay, price);

		dbManager.close();
		System.out.println("<< FacadeImplementationWS: createOffer=> O= " + o);
		return o;
	}

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

	public void initializeBD() {

		DataAccess dbManager = new DataAccess();
		dbManager.initializeDB();
		dbManager.close();

	}

	public void addNewUser(String user, String pass) throws UserAlreadyExists {
		Owner ow = new Owner(user, pass);
		DataAccess dbManager = new DataAccess();
		dbManager.storeNewOwner(ow);
		dbManager.close();

	}

	public boolean makeOwnerLogin(String user, String pass)
			throws WrongPassword, UserNotExist {
		DataAccess dbManager = new DataAccess();
		Owner ow = dbManager.getOwner(user);
		if (ow == null){
			dbManager.close();
			throw new UserNotExist();
			}
		else {
			if (ow.checkPassword(pass)){
				dbManager.close();
				return true;
			}
			else{
				dbManager.close();
				throw new WrongPassword();
			}
		}

	}
	
	public void addHouse(String des,String city){
		RuralHouse rh = new RuralHouse(des,city);
		DataAccess dbManager = new DataAccess();
		dbManager.storeHouse(rh);
		dbManager.close();
	}
	

	// Añade un nuevo owner, llamando al metodo de la base de datos
	public void addNewOwner(String username, String password) throws UserAlreadyExists {
		Owner o = new Owner(username, password);
		DataAccess dbManager = new DataAccess();
		dbManager.storeNewOwner(o);
		dbManager.close();
	}

	// Comprueba si dos contraseñas coinciden
	public boolean checkDoublePassword(String pw1, String pw2) throws PasswordsDoesNotMatch {
		if (pw1.equals(pw2)) {
			return true;
		} else {
			throw new PasswordsDoesNotMatch();
		}
	}

	// Comprueba las contraseñas del registro de owner, y si coinciden añade ese
	// owner a la BD
	public void checkAddOwner(String username, String pw1, String pw2) throws PasswordsDoesNotMatch, UserAlreadyExists {
		checkDoublePassword(pw1, pw2);
		addNewOwner(username, pw1);
	}
	
	
	public void removeOffer(Offer of) {
		  DataAccess dbManager = new DataAccess();
		  dbManager.removeOffer(of);
		  dbManager.close();
		 }

		 public void removeHouse(RuralHouse rh) {
		  DataAccess dbManager = new DataAccess();
		  dbManager.removeHouse(rh);
		  dbManager.close();
		 }

		@Override
		public Vector<Offer> getAllOffers() {
			DataAccess dbManager = new DataAccess();
			 Vector<Offer> res =dbManager.getAllOffers();
			  dbManager.close();
			  return res;
		}

}
