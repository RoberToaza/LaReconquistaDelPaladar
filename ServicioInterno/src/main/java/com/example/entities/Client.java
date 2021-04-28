package com.example.entities;

import javax.persistence.Entity;

import javax.persistence.Id;


@Entity
public class Client {
	
	
	@Id
	private int id;
	
	private String firstName;
	private String lastName;
	private int age;
	private String dni;
	private String mail;
	private int telephone;
	private String address;	
	
	protected Client() {}
	
	public Client(Client c) {
		this.firstName = c.getFisrtName();
		this.lastName = c.getLastName();
		this.age = c.getAge();
		this.dni = c.getDni();
		this.mail = c.getMail();
		this.telephone = c.getTelephone();
		this.address = c.getAddress();
	
	}
	
	public Client(String firstName, String lastName, int age, String dni, String mail, int telephone, String address) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.dni = dni;
		this.mail = mail;
		this.telephone = telephone;
		this.address = address;

	}
	
	
	public int getId() {
		return id;
	}


	public String getFisrtName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public int getAge() {
		return age;
	}



	public void setAge(int age) {
		this.age = age;
	}



	public String getDni() {
		return dni;
	}



	public void setDni(String dni) {
		this.dni = dni;
	}



	public String getMail() {
		return mail;
	}



	public void setMail(String mail) {
		this.mail = mail;
	}



	public int getTelephone() {
		return telephone;
	}



	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}
	

}
