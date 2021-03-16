package es.urjc.code.dad.web.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Client {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String firstName;
	private String lastName;
	private int age;
	private String dni;
	private String mail;
	private int telephone;
	private String address;
	private String password;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Product> shoppingCart;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Ticket> tickets;
	
	
	protected Client() {}
	
	public Client(Client c) {
		this.firstName = c.getFisrtName();
		this.lastName = c.getLastName();
		this.age = c.getAge();
		this.dni = c.getDni();
		this.mail = c.getMail();
		this.telephone = c.getTelephone();
		this.address = c.getAddress();
		this.password = c.getPassword();
		this.tickets = new ArrayList<>();
		this.shoppingCart = new ArrayList<>();
		
	}

	public Client(String firstName, String lastName, int age, String dni, String mail, int telephone, String address,
			String password) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.dni = dni;
		this.mail = mail;
		this.telephone = telephone;
		this.address = address;
		this.password = password;
		this.tickets = new ArrayList<>();
		this.shoppingCart = new ArrayList<>();
	}
	
	public long getId() {
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



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public List<Ticket> getTickets() {
		return tickets;
	}



	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}



	public List<Product> getShoppingCar() {
		return shoppingCart;
	}



	public void setShoppingCar(List<Product> shoppingCar) {
		this.shoppingCart = shoppingCar;
	}
	
	
//	public void addTickets(Ticket ticket) {
//		tickets.add(ticket);
//		ticket.setClient(this);
//	}
//	
//	public void removeTicket(Ticket ticket) {
//		tickets.remove(ticket);
//		ticket.setClient(null);
//	}
	
	

}
