package uabc.videoclubs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uabc.videoclubs.entities.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer>{

}
