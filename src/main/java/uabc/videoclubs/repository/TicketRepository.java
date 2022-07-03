package uabc.videoclubs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uabc.videoclubs.entities.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer>{

}
