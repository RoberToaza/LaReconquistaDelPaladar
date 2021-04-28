package com.example.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Client;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {
	
	List<Client> findByLastName(String lastName);
	
	Client findByFirstName(String firstName);
	Client findByFirstNameAndPasswordHash(String firstName, String password);
	
	Client findById(long id);

}
