package uabc.videoclubs.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

@Entity
@Table(name="rental")
public class Rental {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="rental_id")
	private Integer rentalId;
	
	@Column(name="rental_date")
	private java.sql.Timestamp rentalDate;

	@Column(name="inventory_id")
	private Integer inventoryId;
	
	@Column(name="customer_id")
	private Integer customerId;
	
	@Column(name="return_date")
	private java.sql.Timestamp returnDate;

	@Column(name="staff_id")
	private Integer staffId;
	
	@Column(name="last_update")
	private java.sql.Timestamp lastUpdate;

	public Integer getRentalId() {
		return rentalId;
	}

	public void setRentalId(Integer rentalId) {
		this.rentalId = rentalId;
	}

	public java.sql.Timestamp getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate(java.sql.Timestamp rentalDate) {
		this.rentalDate = rentalDate;
	}

	public Integer getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(Integer inventoryId) {
		this.inventoryId = inventoryId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public java.sql.Timestamp getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(java.sql.Timestamp returnDate) {
		this.returnDate = returnDate;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public java.sql.Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(java.sql.Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Rental(Integer rentalId, Timestamp rentalDate, Integer inventoryId, Integer customerId, Timestamp returnDate,
			Integer staffId, Timestamp lastUpdate) {
		super();
		this.rentalId = rentalId;
		this.rentalDate = rentalDate;
		this.inventoryId = inventoryId;
		this.customerId = customerId;
		this.returnDate = returnDate;
		this.staffId = staffId;
		this.lastUpdate = lastUpdate;
	}

	@Override
	public String toString() {
		return "Rental [rentalId=" + rentalId + ", rentalDate=" + rentalDate + ", inventoryId=" + inventoryId
				+ ", customerId=" + customerId + ", returnDate=" + returnDate + ", staffId=" + staffId + ", lastUpdate="
				+ lastUpdate + "]";
	}

	public Rental() {
		super();
		// TODO Auto-generated constructor stub
	}

}
