package businessLogic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import java.util.Date;









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

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface ApplicationFacadeInterfaceWS  {
	

	/**
	 * This method creates an offer with a house number, first day, last day and price
	 * 
	 * @param House
	 *            number, start day, last day and price
	 * @return None
	 */


	@WebMethod Offer createOffer(RuralHouse ruralHouse, Date firstDay, Date lastDay,
			float price) throws  OverlappingOfferExists, BadDates;
	/**
	 * This method creates a book with a corresponding parameters
	 * 
	 * @param First
	 *            day, last day, house number and telephone
	 * @return a book
	 */
	
	
	
	/**
	 * This method retrieves the existing  rural houses 
	 * 
	 * @return a Set of rural houses
	 */
	@WebMethod public Vector<RuralHouse> getAllRuralHouses();
	
	/**
	 * This method obtains the  offers of a ruralHouse in the provided dates 
	 * 
	 * @param ruralHouse, the ruralHouse to inspect 
	 * @param firstDay, first day in a period range 
	 * @param lastDay, last day in a period range
	 * @return the first offer that overlaps with those dates, or null if there is no overlapping offer
	 */

	@WebMethod public Vector<Offer> getOffers( RuralHouse rh, Date firstDay,  Date lastDay) ;
	
	@WebMethod public void initializeBD();

	 public void addNewUser(String user, String pass) throws UserAlreadyExists;
	 public Owner makeOwnerLogin(String user, String pass)throws WrongPassword, UserNotExist;
	 public void addHouse(String des,String city,Owner current);
	 public void checkAddOwner(String username, String pw1, String pw2) throws PasswordsDoesNotMatch, UserAlreadyExists;
	 public void removeOffer(Offer of);
	 public void removeHouse(RuralHouse rh);
	 public Vector<Offer> getAllOffers();
	 public Vector<RuralHouse> getOwnerHouses(Owner ow);
	 public Vector<Offer>getOwnerOffers(Owner ow);
}
