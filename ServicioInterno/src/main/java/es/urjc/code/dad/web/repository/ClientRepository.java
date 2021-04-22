package es.urjc.code.dad.web.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import entities.Client;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
	
	List<Client> findByLastName(String lastName);
	
	Client findByFirstName(String firstName);
	Client findByFirstNameAndPasswordHash(String firstName, String password);
	
	Client findById(long id);

}
