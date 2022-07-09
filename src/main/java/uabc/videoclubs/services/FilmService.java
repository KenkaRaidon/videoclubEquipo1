package uabc.videoclubs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uabc.videoclubs.entities.CatalogoIndex;
import uabc.videoclubs.entities.Film;
import uabc.videoclubs.entities.FilmActor;
import uabc.videoclubs.repository.FilmRepository;

@Service
public class FilmService implements IFilmService{

	@Autowired
	private FilmRepository filmRepository;
	
	public List<CatalogoIndex> obtenerPeliculas(){
		return filmRepository.obtenerPeliculas();
	}
	
	public List<CatalogoIndex> filtrarPeliculasTitulo(String titulo){
		return filmRepository.filtrarPeliculasTitulo(titulo);
	}
	
	public List<CatalogoIndex> filtrarPeliculasCategoria(String categoria){
		return filmRepository.filtrarPeliculasCategoria(categoria);
	}
	
	public List<CatalogoIndex> filtrarPeliculasActor(String actor){
		return filmRepository.filtrarPeliculasActor(actor);
	}

	@Override
	public Optional<Film> findById(Integer id) {
		return filmRepository.findById(id);
	}

	@Override
	public List<String> obtenerCategorias(Integer filmId) {
		return filmRepository.obtenerCategorias(filmId);
	}

	@Override
	public List<String> obtenerActores(Integer filmId) {
		return filmRepository.obtenerActores(filmId);
	}
	
	public Film obtenerPelicula(Integer filmId){
		return filmRepository.obtenerPelicula(filmId);
	}
	
	public void saveFilmActor(Integer actorId, Integer filmId){
		filmRepository.saveFilmActor(actorId, filmId);
	}

	public void saveFilmCategory(Integer categoryId, Integer filmId){
		filmRepository.saveFilmCategory(categoryId, filmId);
	}

	public Film saveFilm(Film film){
		return filmRepository.save(film);
	}
}
