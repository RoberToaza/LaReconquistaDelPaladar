package es.urjc.code.dad.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.code.dad.web.model.Client;
import es.urjc.code.dad.web.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {

	List<Ticket> findByClient(Client c);
}
