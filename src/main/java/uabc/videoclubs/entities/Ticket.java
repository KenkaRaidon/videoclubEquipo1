package uabc.videoclubs.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@NamedNativeQuery(name = "Ticket.getTicket", query = "")
@Entity
@Table(name = "ticket")
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ticket_id")
	private Integer ticketId;
	
	@Column(name = "customer_id")
	private Integer customerId;
	
	@Column(name = "rental_id")
	private Integer rentalId;
	
	
	private BigDecimal amount;
	
	private Boolean active;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Ticket [ticketId=");
		builder.append(ticketId);
		builder.append(", customerId=");
		builder.append(customerId);
		builder.append(", rentalId=");
		builder.append(rentalId);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", active=");
		builder.append(active);
		builder.append("]");
		return builder.toString();
	}

	public Ticket() {
		super();
		// TODO Auto-generated constructor stub
	}
}
