package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ClientReport extends Report {

	@Id
	String id;
	private Client client;

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
}
