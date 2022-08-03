package uabc.videoclubs.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.springframework.format.annotation.DateTimeFormat;

public class TicketIndex {
	private Integer ticket_id;
	
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private java.sql.Timestamp ticketDate;
	private Integer cutomer_id;
	private Integer rental_id;
	private Float amount;
	private Boolean active;
	public Integer getTicket_id() {
		return ticket_id;
	}
	public void setTicket_id(Integer ticket_id) {
		this.ticket_id = ticket_id;
	}
	public java.sql.Timestamp getTicketDate() {
		return ticketDate;
	}
	public void setTicketDate(java.sql.Timestamp ticketDate) {
		this.ticketDate = ticketDate;
	}
	public Integer getCutomer_id() {
		return cutomer_id;
	}
	public void setCutomer_id(Integer cutomer_id) {
		this.cutomer_id = cutomer_id;
	}
	public Integer getRental_id() {
		return rental_id;
	}
	public void setRental_id(Integer rental_id) {
		this.rental_id = rental_id;
	}
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TicketIndex [ticket_id=");
		builder.append(ticket_id);
		builder.append(", ticketDate=");
		builder.append(ticketDate);
		builder.append(", cutomer_id=");
		builder.append(cutomer_id);
		builder.append(", rental_id=");
		builder.append(rental_id);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", active=");
		builder.append(active);
		builder.append(", getTicket_id()=");
		builder.append(getTicket_id());
		builder.append(", getTicketDate()=");
		builder.append(getTicketDate());
		builder.append(", getCutomer_id()=");
		builder.append(getCutomer_id());
		builder.append(", getRental_id()=");
		builder.append(getRental_id());
		builder.append(", getAmount()=");
		builder.append(getAmount());
		builder.append(", getActive()=");
		builder.append(getActive());
		builder.append(", getClass()=");
		builder.append(getClass());
		builder.append(", hashCode()=");
		builder.append(hashCode());
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
	public TicketIndex() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TicketIndex(Integer ticket_id, Timestamp ticketDate, Integer cutomer_id, Integer rental_id, Float amount,
			Boolean active) {
		super();
		this.ticket_id = ticket_id;
		this.ticketDate = ticketDate;
		this.cutomer_id = cutomer_id;
		this.rental_id = rental_id;
		this.amount = amount;
		this.active = active;
	}
	
}
