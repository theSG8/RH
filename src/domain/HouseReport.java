package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class HouseReport extends Report {

	@Id
	String id;
	private RuralHouse ruralHouse;

	public HouseReport() {
		id = "";
	}

	@Override
	public String getId() {
		return id;
	}

	public RuralHouse getRuralHouse() {
		return ruralHouse;
	}

	public void setRuralHouse(RuralHouse ruralHouse) {
		this.ruralHouse = ruralHouse;
	}
}
