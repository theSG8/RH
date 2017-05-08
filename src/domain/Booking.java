package domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Booking {
	
	@Id
	@GeneratedValue
	int BookingNumber ;
	Offer of;
	Client client;
	
	public Booking(Offer of, Client c) {
		
		this.of = of;
		this.client = c;
		
	}
	public Offer getOf() {
		return of;
	}

	public Client getClient() {
		return client;
	}
	public int getBookingNumber() {
		return BookingNumber;
	}
	
	
	
	

}
