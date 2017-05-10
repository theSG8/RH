package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class RuralHouse implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id
	@GeneratedValue
	private Integer houseNumber;
	private String description;
	private String city;
	@OneToMany(fetch = FetchType.EAGER)
	public Vector<Offer> offers;
	public String OwnerName;
	public String nKitchens;
	public String nBedrooms;
	public String nBathrooms;
	public String nDrooms;
	public String nParking;

	public RuralHouse() {
		super();
	}

	public RuralHouse(String description, String city, String ow, String nKitchens, String nBedrooms, String nBathrooms,
			String nDrooms, String nParking) {
		this.description = description;
		this.city = city;
		this.nKitchens = nKitchens;
		this.nBedrooms = nBedrooms;
		this.nBathrooms = nBathrooms;
		this.nDrooms = nDrooms;
		this.nParking = nParking;
		offers = new Vector<Offer>();
		OwnerName = ow;
	}

	public Integer getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(Integer houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getnKitchens() {
		return nKitchens;
	}

	public void setnKitchens(String nKitchens) {
		this.nKitchens = nKitchens;
	}

	public String getnBedrooms() {
		return nBedrooms;
	}

	public void setnBedrooms(String nBedrooms) {
		this.nBedrooms = nBedrooms;
	}

	public String getnBathrooms() {
		return nBathrooms;
	}

	public void setnBathrooms(String nBathrooms) {
		this.nBathrooms = nBathrooms;
	}

	public String getnDrooms() {
		return nDrooms;
	}

	public void setnDrooms(String nDrooms) {
		this.nDrooms = nDrooms;
	}

	public String getnParking() {
		return nParking;
	}

	public void setnParking(String nParking) {
		this.nParking = nParking;
	}

	/**
	 * This method creates an offer with a house number, first day, last day and
	 * price
	 * 
	 * @param House
	 *            number, start day, last day and price
	 * @return None
	 */
	public Offer createOffer(Date firstDay, Date lastDay, float price) {
		System.out.println("LLAMADA RuralHouse createOffer, offerNumber=" + " firstDay=" + firstDay + " lastDay="
				+ lastDay + " price=" + price);
		Offer off = new Offer(firstDay, lastDay, price, this);
		offers.add(off);
		return off;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + houseNumber.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return this.houseNumber + ": " + this.city;
	}

	@Override
	public boolean equals(Object obj) {
		RuralHouse other = (RuralHouse) obj;
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		// if (houseNumber != other.houseNumber) // NO COMPARAR AS� ya que
		// houseNumber NO ES "int" sino objeto de "java.lang.Integer"
		if (!houseNumber.equals(other.houseNumber)) {
			return false;
		}
		return true;
	}

	/**
	 * This method obtains available offers for a concrete house in a certain
	 * period
	 * 
	 * @param houseNumber,
	 *            the house number where the offers must be obtained
	 * @param firstDay,
	 *            first day in a period range
	 * @param lastDay,
	 *            last day in a period range
	 * @return a vector of offers(Offer class) available in this period
	 */
	public Vector<Offer> getOffers(Date firstDay, Date lastDay) {

		Vector<Offer> availableOffers = new Vector<Offer>();
		Iterator<Offer> e = offers.iterator();
		Offer offer;
		while (e.hasNext()) {
			offer = e.next();
			if (offer.getFirstDay().compareTo(firstDay) >= 0 && offer.getLastDay().compareTo(lastDay) <= 0) {
				availableOffers.add(offer);
			}
		}
		return availableOffers;

	}

	public Vector<Offer> getAllOffers() {
		return offers;
	}

	/**
	 * This method obtains the first offer that overlaps with the provided dates
	 * 
	 * @param firstDay,
	 *            first day in a period range
	 * @param lastDay,
	 *            last day in a period range
	 * @return the first offer that overlaps with those dates, or null if there
	 *         is no overlapping offer
	 */

	public Offer overlapsWith(Date firstDay, Date lastDay) {

		Iterator<Offer> e = offers.iterator();
		Offer offer = null;
		while (e.hasNext()) {
			offer = e.next();
			if (offer.getFirstDay().compareTo(lastDay) < 0 && offer.getLastDay().compareTo(firstDay) > 0) {
				return offer;
			}
		}
		return null;

	}

}
