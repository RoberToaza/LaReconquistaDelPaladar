package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Client;
import com.example.demo.entities.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

	List<Ticket> findByClient( Client client);
}
