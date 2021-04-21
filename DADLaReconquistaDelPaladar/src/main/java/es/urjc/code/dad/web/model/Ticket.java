package es.urjc.code.dad.web.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
//	@ManyToOne
//	@JsonIgnore
//	private Client client;

	@OneToMany
	private List<SoldProduct> products = new ArrayList<>();
	
	public Ticket() {}
	
	public Ticket(List<SoldProduct> l) {
		super();
		this.products = new ArrayList<>(l);
	}
	
//	public Ticket(Client c, List<Product> l) {
//		this.client = c;
//		this.products = new ArrayList<>(l);
//	}

	public long getId() {
		return id;
	}

	public List<SoldProduct> getProducts() {
		return products;
	}

	public void setProducts(List<SoldProduct> products) {
		this.products = products;
	}
	
//	public Client getClient() {
//		return client;
//	}
//
//	public void setClient(Client client) {
//		this.client = client;
//	}
	

}
