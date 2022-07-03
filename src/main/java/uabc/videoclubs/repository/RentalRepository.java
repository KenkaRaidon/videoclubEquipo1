package uabc.videoclubs.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import uabc.videoclubs.entities.Film;
import uabc.videoclubs.entities.Rental;
import uabc.videoclubs.entities.ReturnIndex;

public interface RentalRepository extends JpaRepository<Rental, Integer>{
	@Query(value = "select r.return_date, f.title from rental r inner join inventory i ON i.inventory_id = r.inventory_id inner join film f on f.film_id = i.film_id where r.inventory_id = ?1", nativeQuery = true)
	public  List<ReturnIndex> returns(Integer id);
	public Optional<Rental>  findById(Integer rentalId);
}
