package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Report {

	@Id
	private String title;
	private String description;

	public Report() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "TI: " + title;
	}

}
