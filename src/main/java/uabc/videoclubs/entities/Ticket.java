package uabc.videoclubs.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "ticket")
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ticket_id")
	private Integer ticketId;

	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@Column(name = "ticket_date")
	private java.sql.Timestamp ticketDate;

	@Column(name = "customer_id")
	private Integer customerId;

	@Column(name = "rental_id")
	private Integer rentalId;

	@Column(precision = 5, scale = 2, columnDefinition = "numeric(5,2)")
	private Float amount;

	private Boolean active;

	public Integer getTicketId() {
		return ticketId;
	}

	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}

	public java.sql.Timestamp getTicketDate() {
		return ticketDate;
	}

	public void setTicketDate(java.sql.Timestamp ticketDate) {
		this.ticketDate = ticketDate;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getRentalId() {
		return rentalId;
	}

	public void setRentalId(Integer rentalId) {
		this.rentalId = rentalId;
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

	public Ticket(Integer ticketId, Timestamp ticketDate, Integer customerId, Integer rentalId, Float amount,
			Boolean active) {
		this.ticketId = ticketId;
		this.ticketDate = ticketDate;
		this.customerId = customerId;
		this.rentalId = rentalId;
		this.amount = amount;
		this.active = active;
	}

	public Ticket() {
		super();
	}

	@Override
	public String toString() {
		return "Ticket [active=" + active + ", amount=" + amount + ", customerId=" + customerId + ", rentalId="
				+ rentalId + ", ticketDate=" + ticketDate + ", ticketId=" + ticketId + "]";
	}

}
