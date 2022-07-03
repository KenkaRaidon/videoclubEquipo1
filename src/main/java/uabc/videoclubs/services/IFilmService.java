package uabc.videoclubs.services;

import java.util.List;
import java.util.Optional;

import uabc.videoclubs.entities.CatalogoIndex;
import uabc.videoclubs.entities.Film;

public interface IFilmService {
	public List<CatalogoIndex> obtenerPeliculas();
	public List<CatalogoIndex> filtrarPeliculasTitulo(String titulo);
	public List<CatalogoIndex> filtrarPeliculasCategoria(String categoria);
	public List<CatalogoIndex> filtrarPeliculasActor(String actor);
	public Optional<Film> findById(Integer id);
	public List<String> obtenerCategorias(Integer filmId);
	List<String> obtenerActores(Integer filmId);
}
