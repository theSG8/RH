package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ClientReport extends Report {

	@Id
	String id;
	private Client client;
	private String name;
	private String description;

	public ClientReport() {
		id = "";
	}

	@Override
	public String getId() {
		return id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
