package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OwnerReport extends Report {
	@Id
	String id;
	private Owner owner;

	public OwnerReport() {
		id = "";
	}

	@Override
	public String getId() {
		return id;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}
}
