package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Client;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {
	
	List<Client> findByLastName(String lastName);
	
	Client findByFirstName(String firstName);
	
	Client findById(long id);

}
