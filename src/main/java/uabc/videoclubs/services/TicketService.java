package uabc.videoclubs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uabc.videoclubs.entities.Ticket;
import uabc.videoclubs.entities.TicketIndex;
import uabc.videoclubs.repository.TicketRepository;

@Service
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;
   /* public List<TicketIndex> getTicket(Integer customerId){
    	return ticketRepository.getTicket(customerId);
    }*/
    public Ticket save(Ticket ticket){
        return ticketRepository.save(ticket);
    }

    public Optional<Ticket> findById(Integer ticketId){
        return ticketRepository.findById(ticketId);
    }

    public Ticket findTicketByRentalId(Integer rentalId){
        return ticketRepository.findTicketByRentalId(rentalId);
    }
    
    public List<Ticket> findTicketByCustomerId(Integer customerId) {
    	
    	return ticketRepository.findByCustomerId(customerId);
    }

}
