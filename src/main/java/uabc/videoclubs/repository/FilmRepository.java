package uabc.videoclubs.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import uabc.videoclubs.entities.CatalogoIndex;
import uabc.videoclubs.entities.Film;
import uabc.videoclubs.entities.FilmActor;


public interface FilmRepository extends JpaRepository<Film, Integer>{
	
	@Query(nativeQuery = true)
	List<CatalogoIndex> obtenerPeliculas();
	
	@Query(nativeQuery = true)
	List<CatalogoIndex> filtrarPeliculasTitulo(String titulo);
	
	@Query(nativeQuery = true)
	List<CatalogoIndex> filtrarPeliculasCategoria(String categoria);
	
	@Query(nativeQuery = true)
	List<CatalogoIndex> filtrarPeliculasActor(String actor);

	@Query(value ="select c.name from category c, film f, film_category fc where c.category_id=fc.category_id and f.film_id=fc.film_id and f.film_id=?1" , nativeQuery=true)
	List<String> obtenerCategorias(Integer filmId);
	
	@Query(value ="select concat(a.first_name,' ', a.last_name) as name from actor a, film f, film_actor fa where fa.actor_id=a.actor_id and fa.film_id=f.film_id and f.film_id=?1 " , nativeQuery=true)
	List<String> obtenerActores(Integer filmId);

	@Query(value ="select * from film f where f.film_id=?1 " , nativeQuery=true)
	Film obtenerPelicula(Integer filmId);

	@Transactional
	@Modifying
	@Query(value ="insert into film_actor (actor_id, film_id, last_update) values(?1, ?2, now())" , nativeQuery=true)
	void saveFilmActor(Integer actorId, Integer filmId);

	@Transactional
	@Modifying
	@Query(value ="insert into film_category (category_id, film_id, last_update) values(?1, ?2, now())" , nativeQuery=true)
	void saveFilmCategory(Integer categoryId, Integer filmId);

}
