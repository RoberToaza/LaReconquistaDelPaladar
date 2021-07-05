package es.urjc.code.dad.web.repository;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.code.dad.web.model.Client;
import es.urjc.code.dad.web.model.Ticket;

@CacheConfig(cacheNames="tickets")
public interface TicketRepository extends JpaRepository<Ticket,Integer> {
	
	@CacheEvict(allEntries=true)
	Ticket save(Ticket ticket);

	@CachePut(key = "#c.id")
	List<Ticket> findByClient(Client c);
}
