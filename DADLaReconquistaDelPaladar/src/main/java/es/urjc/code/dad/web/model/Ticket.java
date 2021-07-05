package es.urjc.code.dad.web.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private double total = 0.0;
	

	@JsonBackReference
	@OneToMany
	private List<SoldProduct> products = new ArrayList<>();
	
	@JsonBackReference
	@ManyToOne
	private Client client;
	
	public Ticket() {}
	
	public Ticket(Client c, List<SoldProduct> l) {
		this.client = c;
		
		for(SoldProduct sP: l) {
			this.products.add(sP);
			this.total += sP.getPrice();
		}
		
	}
	

	public int getId() {
		return id;
	}

	public List<SoldProduct> getProducts() {
		return products;
	}

	public void setProducts(List<SoldProduct> products) {
		this.total = 0;
		
		for(SoldProduct sP: products) {
			this.products.add(sP);
			this.total += sP.getPrice();
		}
	}
	
	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}
