package businessLogic;

import java.util.Date;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.swing.ImageIcon;

import domain.Admin;
import domain.Booking;
//import domain.Booking;
import domain.Client;
import domain.ClientReport;
import domain.HouseReport;
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

@WebService
public interface ApplicationFacadeInterfaceWS {

	/**
	 * This method creates an offer with a house number, first day, last day and
	 * price
	 * 
	 * @param House
	 *            number, start day, last day and price
	 * @return None
	 */

	@WebMethod
	Offer createOffer(RuralHouse ruralHouse, Date firstDay, Date lastDay, float price)
			throws OverlappingOfferExists, BadDates;

	/**
	 * This method creates a book with a corresponding parameters
	 * 
	 * @param First
	 *            day, last day, house number and telephone
	 * @return a book
	 */

	/**
	 * This method retrieves the existing rural houses
	 * 
	 * @return a Set of rural houses
	 */
	@WebMethod
	public Vector<RuralHouse> getAllRuralHouses();

	/**
	 * This method obtains the offers of a ruralHouse in the provided dates
	 * 
	 * @param ruralHouse,
	 *            the ruralHouse to inspect
	 * @param firstDay,
	 *            first day in a period range
	 * @param lastDay,
	 *            last day in a period range
	 * @return the first offer that overlaps with those dates, or null if there
	 *         is no overlapping offer
	 */

	@WebMethod
	public Vector<Offer> getOffers(RuralHouse rh, Date firstDay, Date lastDay);

	@WebMethod
	public void initializeBD();

	public Owner makeOwnerLogin(String user, String pass) throws WrongPassword, UserNotExist;

	public RuralHouse addHouse(String des, String city, Owner current, String nKitchens, String nBedRooms,
			String nBathRooms, String nDrooms, String nParking);

	public void checkAddOwner(String username, String pw1, String pw2, String nombre, String apellido, String dni,
			String email, String cuenta) throws PasswordsDoesNotMatch, UserAlreadyExists;

	public void removeOffer(Offer of);

	public void removeHouse(RuralHouse rh);

	public Vector<Offer> getAllOffers();

	public Vector<RuralHouse> getOwnerHouses(Owner ow);

	public Vector<Offer> getOwnerOffers(Owner ow);

	public void checkAddClient(String username, String pw1, String pw2, String nombre, String apellido, String dni,
			String email, String cuenta) throws PasswordsDoesNotMatch, UserAlreadyExists;

	public void addNewClient(String username, String password, String nombre, String apellido, String dni, String email,
			String cuenta) throws UserAlreadyExists;

	public Client makeClientLogin(String user, String pass) throws WrongPassword, UserNotExist;

	public ImageIcon getHouseImage(RuralHouse currentHouse);

	public Vector<Offer> getHouseOffers(RuralHouse rh);

	public void bookOffer(Client currentClient, int offerNumber);

	public void cancelBooking(Booking b);

	public Vector<Booking> getClientBookings(Client currentClient);

	public Admin makeAdminLogin(String userName, String pass) throws UserNotExist, WrongPassword;

	public void checkAddAdmin(String user, String pass1, String pass2) throws PasswordsDoesNotMatch, UserAlreadyExists;

	public Vector<HouseReport> getHouseReports();

	public Vector<ClientReport> getClientReports();

	public Vector<OwnerReport> getOwnerReports();

	public Vector<Client> getAllClients();

	public Vector<Owner> getAllOwners();

	public void addNewHouseReport(HouseReport hr) throws ExistingTitleException;

	public void addNewClientReport(ClientReport cr) throws ExistingTitleException;

	public void addNewOwnerReport(OwnerReport or) throws ExistingTitleException;

	void confirmHouse(RuralHouse rh);

	public void removeOwner(Owner ow);

	public void modifyOwner(Owner currentOwner, String pw1, String pw2, String nombre,
			String apellido, String dni, String email, String cuenta)
			throws PasswordsDoesNotMatch, UserAlreadyExists;

	public void modifyClient(Client currentClient, String pw1, String pw2,
			String nombre, String apellido, String dni, String email,
			String cuenta) throws PasswordsDoesNotMatch, UserAlreadyExists;

	RuralHouse updateHouse(Integer houseNumber, String des, String city,
		    String kitch, String bed, String bath,
			String din, String park);
}
