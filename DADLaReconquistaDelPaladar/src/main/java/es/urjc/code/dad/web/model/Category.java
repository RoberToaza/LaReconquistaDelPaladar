package es.urjc.code.dad.web.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String name;
	
	@OneToMany
	private List<Product> products;
	
	
	public Category() {}

	
	public Category(String name) {
		this.name = name;
		this.products = new ArrayList<>();
	}

	public Category(String name, List<Product> productosByCat) {
		this.name = name;
		this.products = new ArrayList<>(productosByCat);
	}


	public int getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<Product> getProducts() {
		return products;
	}


	public void setProducts(List<Product> productosByCat) {
		this.products = productosByCat;
	}
		

}
