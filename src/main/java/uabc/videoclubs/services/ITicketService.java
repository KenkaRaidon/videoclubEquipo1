package uabc.videoclubs.services;

import java.util.List;

import uabc.videoclubs.entities.Ticket;
import uabc.videoclubs.entities.TicketIndex;

public interface ITicketService {
	public Ticket findByTicketId(Integer ticketId);
	//public List<TicketIndex> getTicket(Integer customerId);
	
}
