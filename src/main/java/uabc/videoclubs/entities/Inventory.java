package uabc.videoclubs.entities;

import java.util.Date;
//holi

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name ="inventory")
public class Inventory {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "inventory_id")
	private Integer inventoryId;
	       
	@OneToOne(fetch = FetchType.LAZY)    
	@JoinColumn(name="film_id") 
	private Film film;

	@Column(name = "last_update")
	private Date lastUpdate;

	public Inventory() {
		super();
	}

	public Inventory(Integer inventoryId) {
		super();
		this.inventoryId = inventoryId;
	}

	public Integer getInventoryId() {
		return inventoryId;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public void setInventoryId(Integer inventoryId) {
		this.inventoryId = inventoryId;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Override
	public String toString() {
		return "Inventory [inventoryId=" + inventoryId + ", lastUpdate=" + lastUpdate + "]";
	}
}
