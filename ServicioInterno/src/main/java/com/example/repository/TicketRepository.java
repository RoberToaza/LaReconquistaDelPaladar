package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Client;
import com.example.entities.SoldProduct;
import com.example.entities.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

	List<Ticket> findByClient( Client client);
	List<Ticket> findBySoldProduct(SoldProduct sP);
}
