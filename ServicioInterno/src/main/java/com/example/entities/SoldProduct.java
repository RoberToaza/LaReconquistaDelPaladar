package com.example.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class SoldProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private double price;
	private String name;
	private int amount;
	
	@ManyToOne
	private Ticket ticket;
	
	public SoldProduct() {
		
	}
	
	public SoldProduct(String n, double p, int a, Ticket t) {
		this.name = n;
		this.price = p;
		this.amount = a;
		this.ticket = t;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	
}
