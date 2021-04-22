package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@OneToMany
	private List<SoldProduct> products = new ArrayList<>();
	
	@ManyToOne
	private Client client;
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Ticket() {}
	
	public Ticket(List<SoldProduct> l) {
		super();
		this.products = new ArrayList<>(l);
	}
	

	public int getId() {
		return id;
	}

	public List<SoldProduct> getProducts() {
		return products;
	}

	public void setProducts(List<SoldProduct> products) {
		this.products = products;
	}
	

}
