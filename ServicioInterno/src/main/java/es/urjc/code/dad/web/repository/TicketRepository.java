package es.urjc.code.dad.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
