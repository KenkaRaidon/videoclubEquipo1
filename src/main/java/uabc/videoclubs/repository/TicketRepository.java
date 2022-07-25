package uabc.videoclubs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import uabc.videoclubs.entities.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer>{
    @Query(value = "select * from ticket t where t.active =true and t.rental_id= ?1", nativeQuery = true)
	public Ticket findTicketByRentalId(Integer rentalId);
}
