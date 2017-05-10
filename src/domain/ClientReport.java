package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ClientReport extends Report {

	private Client client;
	@Id
	private String title;
	private String description;

	public ClientReport() {
	}

	public ClientReport(Client client, String title, String desc) {
		this.client = client;
		this.title = title;
		this.description = desc;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
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
	public String getTitle() {
		return title;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "CL: " + client + " TI: " + title;
	}
}
