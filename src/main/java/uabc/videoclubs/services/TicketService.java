package uabc.videoclubs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uabc.videoclubs.entities.Ticket;
import uabc.videoclubs.repository.TicketRepository;

@Service
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;

    public Ticket save(Ticket ticket){
        return ticketRepository.save(ticket);
    }
}
