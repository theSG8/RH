package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Report {

	@Id
	String id;

	public Report() {
		id = "";
	}

	public String getId() {
		return id;
	}

}
