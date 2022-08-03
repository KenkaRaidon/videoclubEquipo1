package uabc.videoclubs.repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import uabc.videoclubs.entities.InventoryIndex;
import uabc.videoclubs.entities.Rental;
import uabc.videoclubs.entities.ReturnIndex;

public interface RentalRepository extends JpaRepository<Rental, Integer>{
	@Query(value = "select r.return_date, f.title from rental r inner join inventory i ON i.inventory_id = r.inventory_id inner join film f on f.film_id = i.film_id where r.inventory_id = ?1", nativeQuery = true)
	public  List<ReturnIndex> returns(Integer id);

	public Optional<Rental>  findById(Integer rentalId);

	@Query(value = "select * from rental r where r.inventory_id = ?1 and return_date is null", nativeQuery = true)
	public Rental findRentalByInventoryId(Integer inventoryId);
	
	
	@Query(value ="update rental r set r.return_date=?2 where r.rental_id=?1 ",nativeQuery = true)
	public Rental ReturnFilm(Integer id,Timestamp fecha);
}
