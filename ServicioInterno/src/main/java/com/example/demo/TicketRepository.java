package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

	List<Ticket> findByClient( Client client);
	//List<Ticket> findBySoldProduct(SoldProduct sP);
}
