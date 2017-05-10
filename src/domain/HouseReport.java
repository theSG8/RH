package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class HouseReport extends Report {

	private RuralHouse ruralHouse;
	@Id
	private String title;
	private String description;

	public HouseReport() {
	}

	public HouseReport(RuralHouse rh, String title, String desc) {
		this.ruralHouse = rh;
		this.title = title;
		this.description = desc;
	}

	public RuralHouse getRuralHouse() {
		return ruralHouse;
	}

	public void setRuralHouse(RuralHouse ruralHouse) {
		this.ruralHouse = ruralHouse;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "RH: " + ruralHouse + " TI: " + title;
	}
}
