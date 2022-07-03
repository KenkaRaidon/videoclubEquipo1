package uabc.videoclubs.services;

import uabc.videoclubs.entities.Ticket;

public interface ITicketService {
	public Ticket findByTicketId(Integer ticketId);
}
