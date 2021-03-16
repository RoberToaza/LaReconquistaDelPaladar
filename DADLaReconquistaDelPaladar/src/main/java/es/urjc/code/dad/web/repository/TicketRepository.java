package es.urjc.code.dad.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.code.dad.web.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
