package es.urjc.code.dad.web.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.code.dad.web.model.Client;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
	
	List<Client> findByLastName(String lastName);
	
	Client findByFirstName(String firstName);
	Client findByFirstNameAndPassword(String firstName, String password);
	
	Client findById(long id);

}
