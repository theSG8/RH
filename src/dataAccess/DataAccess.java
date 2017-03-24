package dataAccess;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
//import domain.Booking;
import domain.Offer;
import domain.Owner;
import domain.RuralHouse;
import exceptions.OverlappingOfferExists;
import exceptions.UserAlreadyExists;

public class DataAccess {

	public static String fileName;
	protected static EntityManagerFactory emf;
	protected static EntityManager db;

	ConfigXML c;

	public DataAccess() {

		c = ConfigXML.getInstance();

		System.out.println("Creating objectdb instance => isDatabaseLocal: "
				+ c.isDatabaseLocal() + " getDatabBaseOpenMode: "
				+ c.getDataBaseOpenMode());

		String filename = c.getDbFilename();

		if (c.isDatabaseLocal()) {

			emf = Persistence.createEntityManagerFactory(c.getDbFilename());
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("javax.persistence.jdbc.user", c.getUser());
			properties.put("javax.persistence.jdbc.password", c.getPassword());
			emf = Persistence.createEntityManagerFactory(
					"objectdb://" + c.getDatabaseNode() + ":"
							+ c.getDatabasePort() + "/" + c.getDbFilename(),
					properties);
			db = emf.createEntityManager();
		}
	}

	public void initializeDB() {

		db.getTransaction().begin();
		try {

			TypedQuery<RuralHouse> query = db.createQuery(
					"SELECT c FROM RuralHouse c", RuralHouse.class);
			List<RuralHouse> results = query.getResultList();

			Iterator<RuralHouse> itr = results.iterator();

			while (itr.hasNext()) {
				RuralHouse rh = itr.next();
				db.remove(rh);
			}

			RuralHouse rh1 = new RuralHouse("Ezkioko etxea", "Ezkio");
			RuralHouse rh2 = new RuralHouse("Etxetxikia", "Iruna");
			RuralHouse rh3 = new RuralHouse("Udaletxea", "Bilbo");
			RuralHouse rh4 = new RuralHouse("Gaztetxea", "Renteria");

			db.persist(rh1);
			db.persist(rh2);
			db.persist(rh3);
			db.persist(rh4);

			db.getTransaction().commit();
			System.out.println("Db initialized");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Offer createOffer(RuralHouse ruralHouse, Date firstDay,
			Date lastDay, float price) {
		System.out.println(">> DataAccess: createOffer=> ruralHouse= "
				+ ruralHouse + " firstDay= " + firstDay + " lastDay=" + lastDay
				+ " price=" + price);

		try {
			RuralHouse rh = db.find(RuralHouse.class,
					ruralHouse.getHouseNumber());

			db.getTransaction().begin();
			Offer o = rh.createOffer(firstDay, lastDay, price);
			db.persist(o);
			// System.out.println(rh.offers);
			db.getTransaction().commit();
			return o;

		} catch (Exception e) {
			System.out.println("Offer not created: " + e.toString());
			return null;
		}
	}

	public Vector<RuralHouse> getAllRuralHouses() {
		System.out.println(">> DataAccess: getAllRuralHouses");
		Vector<RuralHouse> res = new Vector<>();

		TypedQuery<RuralHouse> query = db.createQuery(
				"SELECT c FROM RuralHouse c", RuralHouse.class);
		List<RuralHouse> results = query.getResultList();

		Iterator<RuralHouse> itr = results.iterator();

		while (itr.hasNext()) {
			res.add(itr.next());
		}

		return res;

	}

	public Vector<Offer> getOffers(RuralHouse rh, Date firstDay, Date lastDay) {
		System.out.println(">> DataAccess: getOffers");
		Vector<Offer> res = new Vector<>();
		RuralHouse rhn = db.find(RuralHouse.class, rh.getHouseNumber());
		res = rhn.getOffers(firstDay, lastDay);
		return res;
	}

	public Vector<Owner> getAllOwners() {

		System.out.println(">> DataAccess: getAllUsers");
		Vector<Owner> res = new Vector<>();
		TypedQuery<Owner> query = db.createQuery("SELECT o FROM Owner o",
				Owner.class);
		List<Owner> results = query.getResultList();
		Iterator<Owner> itr = results.iterator();

		while (itr.hasNext()) {
			res.add(itr.next());
		}

		return res;

	}

	public boolean existsOverlappingOffer(RuralHouse rh, Date firstDay,
			Date lastDay) throws OverlappingOfferExists {
		try {
			RuralHouse rhn = db.find(RuralHouse.class, rh.getHouseNumber());
			if (rhn.overlapsWith(firstDay, lastDay) != null)
				return true;
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
			return true;
		}
		return false;
	}

	public void storeNewOwner(Owner ow) throws UserAlreadyExists {

		if (!chkIfOwnerExists(ow))
			throw new UserAlreadyExists();
		else {
			db.getTransaction().begin();
			db.persist(ow);
			db.getTransaction().commit();

		}
	}

	public Owner getOwner(String username) {
		return db.find(Owner.class, username);
	}

	public boolean chkIfOwnerExists(Owner ow) {

		Vector<Owner> owners = getAllOwners();
		for (Owner current : owners) {
			if (current.getUsername().equals(ow.getUsername())) {
				return false;
			}
		}
		return true;

	}

	public void storeHouse(RuralHouse rh, Owner ow) {

		db.getTransaction().begin();
		db.persist(rh);
		Owner dbOwner=db.find(Owner.class,ow.getUsername());
		dbOwner.addHouse(rh);
		db.getTransaction().commit();

	}

	public void removeOffer(Offer of) {
		db.getTransaction().begin();

		Offer dbOffer = db.find(Offer.class, of);
		RuralHouse rh = dbOffer.getRuralHouse();
		RuralHouse dbRH = db.find(RuralHouse.class, rh.getHouseNumber());
		for (Offer o : dbRH.offers) {
			if (o.getOfferNumber() == of.getOfferNumber()) {
				dbRH.offers.remove(o);
				break;
			}
		}

		db.remove(db.find(Offer.class, of));
		db.getTransaction().commit();
	}

	public void removeHouse(RuralHouse rh) {
		db.getTransaction().begin();

		for (Offer o : db.find(RuralHouse.class, rh).offers) {

			db.remove(db.find(Offer.class, o));
		}

		db.remove(db.find(RuralHouse.class, rh));
		db.getTransaction().commit();
	}

	public Vector<Offer> getAllOffers() {
		Vector<Offer> res = new Vector<Offer>();

		TypedQuery<Offer> query = db.createQuery("SELECT c FROM Offer c",
				Offer.class);
		List<Offer> results = query.getResultList();

		Iterator<Offer> itr = results.iterator();

		while (itr.hasNext()) {
			res.add(itr.next());
		}

		return res;

	}

	public void close() {
		db.close();
		System.out.println("DataBase closed");
	}

}
