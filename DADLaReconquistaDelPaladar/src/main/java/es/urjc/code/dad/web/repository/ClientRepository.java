package es.urjc.code.dad.web.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.code.dad.web.model.Client;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {
	
	List<Client> findByLastName(String lastName);
	
	Client findByFirstName(String firstName);
	Client findByFirstNameAndPasswordHash(String firstName, String password);
	
	Client findById(int id);

}
