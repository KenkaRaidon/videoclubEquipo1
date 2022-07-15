package uabc.videoclubs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import uabc.videoclubs.entities.Inventory;
import uabc.videoclubs.entities.InventoryIndex;


public interface InventoryRepository extends JpaRepository<Inventory, Integer>{

	
	@Query(value="SELECT i FROM Inventory i "
			+ "INNER JOIN FETCH i.film f "
			+ "WHERE i.inventoryId = ?1")
	public Inventory findByInventoryId(Integer ticketId);

	@Query(nativeQuery = true)
	public InventoryIndex obtenerInventario(Integer inventoryId);
	
}
