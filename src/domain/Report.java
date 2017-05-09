package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Report {

	@Id
	private
	String id;

	public Report() {
		setId("");
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
