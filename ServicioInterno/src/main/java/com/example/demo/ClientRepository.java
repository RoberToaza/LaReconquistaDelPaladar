package com.example.demo;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {
	
	List<Client> findByLastName(String lastName);
	
	Client findByFirstName(String firstName);
	
	Client findById(long id);

}
