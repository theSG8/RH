package dataAccess;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
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
import exceptions.ExistingTitleException;
import exceptions.OverlappingOfferExists;
import exceptions.UserAlreadyExists;

public class DataAccess {

	public static String fileName;
	protected static EntityManagerFactory emf;
	protected static EntityManager db;

	ConfigXML c;

	public DataAccess() {

		c = ConfigXML.getInstance();

		System.out.println("Creating objectdb instance => isDatabaseLocal: " + c.isDatabaseLocal()
				+ " getDatabBaseOpenMode: " + c.getDataBaseOpenMode());

		String filename = c.getDbFilename();

		if (c.isDatabaseLocal()) {

			emf = Persistence.createEntityManagerFactory(c.getDbFilename());
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("javax.persistence.jdbc.user", c.getUser());
			properties.put("javax.persistence.jdbc.password", c.getPassword());
			emf = Persistence.createEntityManagerFactory(
					"objectdb://" + c.getDatabaseNode() + ":" + c.getDatabasePort() + "/" + c.getDbFilename(),
					properties);
			db = emf.createEntityManager();
		}
	}

	public void initializeDB() {

		db.getTransaction().begin();
		try {

			TypedQuery<RuralHouse> query = db.createQuery("SELECT c FROM RuralHouse c", RuralHouse.class);
			List<RuralHouse> results = query.getResultList();

			Iterator<RuralHouse> itr = results.iterator();

			while (itr.hasNext()) {
				RuralHouse rh = itr.next();
				db.remove(rh);
			}

			/*
			 * RuralHouse rh1 = new RuralHouse("Ezkioko etxea", "Ezkio",
			 * "none"); RuralHouse rh2 = new RuralHouse("Etxetxikia", "Iruna",
			 * "none"); RuralHouse rh3 = new RuralHouse("Udaletxea", "Bilbo",
			 * "none"); RuralHouse rh4 = new RuralHouse("Gaztetxea", "Renteria",
			 * "none");
			 * 
			 * db.persist(rh1); db.persist(rh2); db.persist(rh3);
			 * db.persist(rh4);
			 */

			db.getTransaction().commit();
			System.out.println("Db initialized");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Offer createOffer(RuralHouse ruralHouse, Date firstDay, Date lastDay, float price) {
		System.out.println(">> DataAccess: createOffer=> ruralHouse= " + ruralHouse + " firstDay= " + firstDay
				+ " lastDay=" + lastDay + " price=" + price);

		try {
			RuralHouse rh = db.find(RuralHouse.class, ruralHouse.getHouseNumber());

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
		System.out.println(">> DataAccess: getAllRuralHouses DA");
		Vector<RuralHouse> res = new Vector<>();

		TypedQuery<RuralHouse> query = db.createQuery("SELECT c FROM RuralHouse c", RuralHouse.class);
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
		TypedQuery<Owner> query = db.createQuery("SELECT o FROM Owner o", Owner.class);
		List<Owner> results = query.getResultList();
		Iterator<Owner> itr = results.iterator();

		while (itr.hasNext()) {
			res.add(itr.next());
		}

		return res;

	}

	public Vector<Client> getAllClients() {

		System.out.println(">> DataAccess: getAllUsers");
		Vector<Client> res = new Vector<>();
		TypedQuery<Client> query = db.createQuery("SELECT o FROM Client o", Client.class);
		List<Client> results = query.getResultList();
		Iterator<Client> itr = results.iterator();

		while (itr.hasNext()) {
			res.add(itr.next());
		}

		return res;

	}

	public boolean existsOverlappingOffer(RuralHouse rh, Date firstDay, Date lastDay) throws OverlappingOfferExists {
		try {
			RuralHouse rhn = db.find(RuralHouse.class, rh.getHouseNumber());
			if (rhn.overlapsWith(firstDay, lastDay) != null) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
			return true;
		}
		return false;
	}

	public void storeNewOwner(Owner ow) throws UserAlreadyExists {

		if (!chkIfOwnerExists(ow)) {
			throw new UserAlreadyExists();
		} else {
			db.getTransaction().begin();
			db.persist(ow);
			db.getTransaction().commit();

		}
	}

	public void storeNewClient(Client cl) throws UserAlreadyExists {

		if (!chkIfClientExists(cl)) {
			throw new UserAlreadyExists();
		} else {
			db.getTransaction().begin();
			db.persist(cl);
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

	public boolean chkIfClientExists(Client cl) {

		Vector<Client> clients = getAllClients();
		for (Client current : clients) {
			if (current.getUsername().equals(cl.getUsername())) {
				return false;
			}
		}
		return true;

	}

	public void storeHouse(RuralHouse rh, Owner ow) {

		db.getTransaction().begin();
		db.persist(rh);
		Owner dbOwner = db.find(Owner.class, ow.getUsername());
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

		TypedQuery<Offer> query = db.createQuery("SELECT c FROM Offer c", Offer.class);
		List<Offer> results = query.getResultList();

		Iterator<Offer> itr = results.iterator();

		while (itr.hasNext()) {
			res.add(itr.next());
		}

		return res;

	}

	public Vector<RuralHouse> getOwnerHouses(String username) {

		Vector<RuralHouse> res = new Vector<RuralHouse>();

		TypedQuery<RuralHouse> query = db.createQuery("SELECT r FROM RuralHouse r WHERE r.OwnerName =?1",
				RuralHouse.class);
		query.setParameter(1, username);

		List<RuralHouse> results = query.getResultList();
		Iterator<RuralHouse> itr = results.iterator();

		while (itr.hasNext()) {
			res.add(itr.next());
		}

		return res;
	}

	public void close() {
		db.close();
		System.out.println("DataBase closed");
	}

	public Vector<Offer> getOwnerOffers(String username) {

		Vector<Offer> res = new Vector<Offer>();

		TypedQuery<RuralHouse> query = db.createQuery("SELECT r FROM RuralHouse r WHERE r.OwnerName =?1",
				RuralHouse.class);
		query.setParameter(1, username);

		List<RuralHouse> results = query.getResultList();
		Iterator<RuralHouse> itr = results.iterator();

		while (itr.hasNext()) {

			Iterator<Offer> itr2 = itr.next().getAllOffers().iterator();
			while (itr2.hasNext()) {
				res.add(itr2.next());
			}
		}

		return res;

	}

	public Client getClient(String username) {
		return db.find(Client.class, username);
	}

	public Vector<Offer> getHouseOffers(RuralHouse rh) {
		Vector<Offer> res = new Vector<>();
		RuralHouse rhn = db.find(RuralHouse.class, rh.getHouseNumber());
		res = rhn.getAllOffers();
		return res;
	}

	public void bookOffer(String username, int offerNumber) {
		db.getTransaction().begin();
		Client client = db.find(Client.class, username);
		Offer of = db.find(Offer.class, offerNumber);

		// client.addOffer(of); Pre Booking

		of.bookOffer();
		Booking booking = new Booking(of, client);
		db.persist(booking);
		db.getTransaction().commit();
	}

	public void cancelBooking(Booking b) {
		db.getTransaction().begin();
		Client client = db.find(Client.class, b.getClient().getUsername());
		Offer of = db.find(Offer.class, b.getOf().getOfferNumber());
		// of.cancelOffer();
		// client.removeBooking(of);
		of.cancelBooking();
		Booking bk = db.find(Booking.class, b.getBookingNumber());
		db.remove(bk);
		db.getTransaction().commit();
	}

	public Vector<Booking> getClientBookings(String username) {
		Vector<Booking> res = new Vector<Booking>();
		Client client = db.find(Client.class, username);
		TypedQuery<Booking> query = db.createQuery("SELECT b FROM Booking b", Booking.class);
		List<Booking> results = query.getResultList();
		Iterator<Booking> itr = results.iterator();
		Booking b;
		while (itr.hasNext()) {
			b = itr.next();
			if (b.getClient().getUsername() == client.getUsername()) {
				res.add(b);
			}
		}
		return res;

	}

	public Admin getAdmin(String userName) {
		return db.find(Admin.class, userName);
	}

	public void storeNewAdmin(Admin a) throws UserAlreadyExists {
		if (!chkIfAdminExists(a)) {
			throw new UserAlreadyExists();
		} else {
			db.getTransaction().begin();
			db.persist(a);
			db.getTransaction().commit();

		}

	}

	private boolean chkIfAdminExists(Admin a) {
		Vector<Admin> admins = getAllAdmins();
		for (Admin current : admins) {
			if (current.getUsername().equals(a.getUsername())) {
				return false;
			}
		}
		return true;
	}

	private Vector<Admin> getAllAdmins() {
		System.out.println(">> DataAccess: getAllAdmins");
		Vector<Admin> res = new Vector<Admin>();
		TypedQuery<Admin> query = db.createQuery("SELECT o FROM Admin o", Admin.class);
		List<Admin> results = query.getResultList();
		Iterator<Admin> itr = results.iterator();

		while (itr.hasNext()) {
			res.add(itr.next());
		}

		return res;
	}

	public Vector<HouseReport> getHouseReports() {
		System.out.println(">> DataAccess: getHouseReports");
		Vector<HouseReport> res = new Vector<HouseReport>();
		TypedQuery<HouseReport> query = db.createQuery("SELECT a FROM HouseReport a", HouseReport.class);

		List<HouseReport> results = query.getResultList();
		Iterator<HouseReport> itr = results.iterator();

		while (itr.hasNext()) {
			res.add(itr.next());
		}

		return res;
	}

	public Vector<ClientReport> getClientReports() {
		System.out.println(">> DataAccess: getClientReports");
		Vector<ClientReport> res = new Vector<ClientReport>();
		TypedQuery<ClientReport> query = db.createQuery("SELECT a FROM ClientReport a", ClientReport.class);

		List<ClientReport> results = query.getResultList();
		Iterator<ClientReport> itr = results.iterator();

		while (itr.hasNext()) {
			res.add(itr.next());
		}

		return res;
	}

	public Vector<OwnerReport> getOwnerReports() {
		System.out.println(">> DataAccess: getOwnerReports");
		Vector<OwnerReport> res = new Vector<OwnerReport>();
		TypedQuery<OwnerReport> query = db.createQuery("SELECT a FROM OwnerReport a", OwnerReport.class);

		List<OwnerReport> results = query.getResultList();
		Iterator<OwnerReport> itr = results.iterator();

		while (itr.hasNext()) {
			res.add(itr.next());
		}

		return res;
	}

	public void storeNewHouseReport(HouseReport hr) throws ExistingTitleException {
		if (checkIfHouseReportExists(hr.getTitle())) {
			db.getTransaction().begin();
			db.persist(hr);
			db.getTransaction().commit();
		} else {
			System.out.println("Titulo repetido");
			throw new ExistingTitleException();
		}
	}

	public void storeNewClientReport(ClientReport cr) throws ExistingTitleException {
		if (checkIfClientReportExists(cr.getTitle())) {
			db.getTransaction().begin();
			db.persist(cr);
			db.getTransaction().commit();
		} else {
			System.out.println("Titulo repetido");
			throw new ExistingTitleException();
		}
	}

	public void storeNewOwnerReport(OwnerReport or) throws ExistingTitleException {
		if (checkIfOwnerReportExists(or.getTitle())) {
			db.getTransaction().begin();
			db.persist(or);
			db.getTransaction().commit();
		} else {
			System.out.println("Titulo repetido");
			throw new ExistingTitleException();
		}
	}

	public boolean checkIfHouseReportExists(String title) {

		Vector<HouseReport> hrs = getHouseReports();
		for (HouseReport current : hrs) {
			if (current.getTitle().equals(title)) {
				return false;
			}
		}
		return true;
	}

	public boolean checkIfClientReportExists(String title) {

		Vector<ClientReport> crs = getClientReports();
		for (ClientReport current : crs) {
			if (current.getTitle().equals(title)) {
				return false;
			}
		}
		return true;
	}

	public boolean checkIfOwnerReportExists(String title) {

		Vector<OwnerReport> ors = getOwnerReports();
		for (OwnerReport current : ors) {
			if (current.getTitle().equals(title)) {
				return false;
			}
		}
		return true;
	}

	public void confirmHouse(int hn) {
		db.getTransaction().begin();
		RuralHouse rh = db.find(RuralHouse.class, hn);
		rh.setConfirmed(true);
		db.getTransaction().commit();
	}

	public void removeOwner(Owner ow) {
		db.getTransaction().begin();
		Owner owner = db.find(Owner.class, ow);
		db.remove(owner);
		db.getTransaction().commit();
	}

	public void modifyOwner(String un, String password, String nombre,
			String apellido, String dni, String email, String cuenta) {
		db.getTransaction().begin();
		Owner ow = db.find(Owner.class, un);
		ow.setPassword(password);
		ow.setNombre(nombre);
		ow.setApellido(apellido);
		ow.setDni(dni);
		ow.setEmail(email);
		ow.setCuenta(cuenta);
		db.getTransaction().commit();
		
	}

	public void modifyClient(String un, String password, String nombre,
			String apellido, String dni, String email, String cuenta) {
		db.getTransaction().begin();
		Client cl = db.find(Client.class, un);
		cl.setPassword(password);
		cl.setNombre(nombre);
		cl.setApellido(apellido);
		cl.setDni(dni);
		cl.setEmail(email);
		cl.setCuenta(cuenta);
		db.getTransaction().commit();
		
	}

	public RuralHouse updateHouse(Integer houseNumber, String des, String city,
			String kitch, String bed, String bath, String din, String park) {
		db.getTransaction().begin();
		RuralHouse rh =db.find(RuralHouse.class, houseNumber);
		
		rh.setCity(city);
		rh.setDescription(des);
		rh.setnKitchens(kitch);
		rh.setnBedrooms(bed);
		rh.setnBathrooms(bath);
		rh.setnDrooms(din);
		rh.setnParking(park);
		db.getTransaction().commit();
		return rh;
	}

}
