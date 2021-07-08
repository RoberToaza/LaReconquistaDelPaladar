package es.urjc.code.dad.web.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class Client {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String firstName;
	private String lastName;
	private int age;
	private String dni;
	private String mail;
	private int telephone;
	private String address;
	private String passwordHash;
	
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles = new ArrayList<String>();
	

	protected Client() {}
	
	public Client(Client c) {
		this.firstName = c.getFisrtName();
		this.lastName = c.getLastName();
		this.age = c.getAge();
		this.dni = c.getDni();
		this.mail = c.getMail();
		this.telephone = c.getTelephone();
		this.address = c.getAddress();
		this.passwordHash = new BCryptPasswordEncoder().encode(c.getPasswordHash());
		roles.add("ADMIN");
		
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
		this.passwordHash = new BCryptPasswordEncoder().encode(password);
		roles.add("ADMIN");
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



	public String getPasswordHash() {
		return passwordHash;
	}



	public void setPasswordHash(String password) {
		this.passwordHash = password;
	}
	
	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	
	

}
