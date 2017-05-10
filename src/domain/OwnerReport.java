package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OwnerReport extends Report {

	private Owner owner;
	@Id
	private String title;
	private String description;

	public OwnerReport() {
	}

	public OwnerReport(Owner ow, String title, String desc) {
		this.owner = ow;
		this.title = title;
		this.description = desc;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
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
		return "OW: " + owner + " TI: " + title;
	}
}
