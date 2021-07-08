package es.urjc.code.dad.web.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String name;
	private double price;
	private int stock;
	
	private String country;	
	
	private String imageName;
	
	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	public Product() {}
	
	public Product(Product p) {
		this.name = p.getName();
		this.price = p.getPrice();
		this.stock = p.getStock();
		this.country = p.getCountry();
		this.imageName = p.getName().replaceAll(" ", "");
	}
	
	public Product(String n, double p, int s, String  c) {
		
		this.name = n;
		this.price = p;
		this.stock = s;
		this.country = c;
		this.imageName = n.replaceAll(" ", "");
		
	}	
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public boolean outOfStock() {
		return this.stock == 0;
	}
	
	public void subtractStock(int a) {
		this.stock -= a;
	}
	
	

}
