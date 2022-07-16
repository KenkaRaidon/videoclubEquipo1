package uabc.videoclubs.entities;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;

@NamedNativeQuery(name = "Inventory.obtenerInventario", query = "select i.inventory_id , i.film_id, f.title, f.rental_duration, f.rental_rate, f.replacement_cost from inventory i, film f where i.film_id=f.film_id and i.inventory_id=?", resultSetMapping = "Mapping.InventoryIndex")
@SqlResultSetMapping(name = "Mapping.InventoryIndex", classes = @ConstructorResult(targetClass = InventoryIndex.class, columns = {
		@ColumnResult(name = "inventory_id", type = Integer.class),
		@ColumnResult(name = "film_id", type = Integer.class),
		@ColumnResult(name = "title", type = String.class),
		@ColumnResult(name = "rental_duration", type = Short.class),
		@ColumnResult(name = "rental_rate", type = Float.class),
		@ColumnResult(name = "replacement_cost", type = Float.class)
}))
@Entity
@Table(name = "inventory")
public class Inventory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "inventory_id")
	private Integer inventoryId;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "film_id")
	private Film film;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id")
	private Store store;

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

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
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
		return "Inventory [film=" + film + ", inventoryId=" + inventoryId + ", lastUpdate=" + lastUpdate + ", store="
				+ store + "]";
	}

}
