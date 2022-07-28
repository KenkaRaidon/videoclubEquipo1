package uabc.videoclubs.controllers;

//import java.io.IOException;
import java.security.Principal;
import java.sql.Date;
import java.sql.Timestamp;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uabc.videoclubs.entities.Actor;
import uabc.videoclubs.entities.CatalogoIndex;
import uabc.videoclubs.entities.Category;
import uabc.videoclubs.entities.Film;
import uabc.videoclubs.entities.FilmActor;
import uabc.videoclubs.entities.Inventory;
import uabc.videoclubs.entities.Language;
import uabc.videoclubs.entities.Staff;
import uabc.videoclubs.entities.Store;
import uabc.videoclubs.entities.mpaa_rating;
import uabc.videoclubs.services.ActorService;
import uabc.videoclubs.services.CategoryService;
import uabc.videoclubs.services.FilmService;
import uabc.videoclubs.services.InventoryService;
import uabc.videoclubs.services.LanguageService;
import uabc.videoclubs.services.StoreService;
import uabc.videoclubs.services.UserService;


@Controller
public class FilmController {

    @Autowired
	private FilmService filmService;

	@Autowired 
	private ActorService actorService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private LanguageService languageService;
	
	@Autowired
	private InventoryService inventoryService;

	@Autowired
	private UserService userService;

	@Autowired
	private StoreService storeService;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = {"/","index", "films"})
	public String index(Model model, HttpServletRequest request, HttpServletResponse response, Principal principal) {
		List<CatalogoIndex> catalogo;
		List<Language> languages=languageService.findAll();
		List <Category> categorias = categoryService.findAll();
		
		if(model.getAttribute("catalogo")==null) {
			catalogo = filmService.obtenerPeliculas();
		}
		else {
			catalogo = (List<CatalogoIndex>) model.getAttribute("catalogo");
		}
		
		model.addAttribute("film", new Film());
		model.addAttribute("actor", new Actor());
		model.addAttribute("languages",languages);
		model.addAttribute("catalogo",catalogo);
		model.addAttribute("categorias",categorias);
		
		return "views/films";
	}
	
	@RequestMapping(value = "filtroTitulo" , method = RequestMethod.POST)
	public String filtroTitulo(Model model, @RequestParam(name = "inputCambiar") String titulo, RedirectAttributes redirectAtt) {
		List<CatalogoIndex> filtroTitulo = filmService.filtrarPeliculasTitulo("%"+titulo.toUpperCase()+"%");
		redirectAtt.addFlashAttribute("catalogo",filtroTitulo);
		return "redirect:/";
	}
	
	@RequestMapping(value = "filtroCategoria" , method = RequestMethod.POST)
	public String filtroCategoria(Model model, @RequestParam(name = "selectCategoria") Integer categoria, RedirectAttributes redirectAtt) {
		Category category = categoryService.findById(categoria).get();
		
		List<CatalogoIndex> filtroCategoria = filmService.filtrarPeliculasCategoria(category.getName());
		redirectAtt.addFlashAttribute("catalogo",filtroCategoria);
		return "redirect:/";
	}
	
	@RequestMapping(value = "filtroActor" , method = RequestMethod.POST)
	public String filtroActor(Model model, @RequestParam(name = "inputCambiar") String actor, RedirectAttributes redirectAtt) {
		if(!actor.isBlank()) {
			List<CatalogoIndex> filtroActor = filmService.filtrarPeliculasActor("%"+actor.toUpperCase()+"%");
			redirectAtt.addFlashAttribute("catalogo",filtroActor);
		}
		return "redirect:/";
	}
	
	@GetMapping(value="detallesFilm/{filmId}")
	@ResponseBody 
	public Map<String, Object> cargarDetalles(@PathVariable Integer filmId){
		Map<String, Object> response = new HashMap<>();
		
		Film film = filmService.findById(filmId).get();
		List<String> categorias = filmService.obtenerCategorias(filmId);
		List<String> actores = filmService.obtenerActores(filmId);
		
		response.put("detalles", film);
		response.put("categorias", categorias);
		response.put("actores", actores);
		
		return response;
	}
	
	/*@GetMapping("detallesFilm/exportarpdf/{filmId}")
	public void exportToPDF(HttpServletResponse response, HttpServletRequest request, @PathVariable Integer filmId)
			throws DocumentException, IOException {
		
		Film film = filmService.findById(filmId).get();
		List<String> categorias = filmService.obtenerCategorias(filmId);
		List<String> actores = filmService.obtenerActores(filmId);
		
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		
		String headerKey = "Content-Disposition";
		StringBuilder sbHeaderValue = new StringBuilder("attachment; filename=film_");
		sbHeaderValue.append(film.getFilmId() + "_");
		sbHeaderValue.append(currentDateTime + ".pdf");
		response.setHeader(headerKey, sbHeaderValue.toString());

		FilmPDFExporter exporter = new FilmPDFExporter(film, categorias, actores);
		exporter.export(response);


	}*/
	
	@GetMapping(value="detallesInventory/{inventoryId}")
	@ResponseBody
	public Map<String, Object> cargarDetallesInventario(@PathVariable Integer inventoryId){
		Map<String, Object> response = new HashMap<>();		
		response.put("result", inventoryService.findByInventoryId(inventoryId));		
		return response;
	}

	@PostMapping("/registerFilm")
	public String registerCustomer(@ModelAttribute("film") Film film, @RequestParam("category[]") List<Integer> categories, @RequestParam("release_year") Integer releaseYear, @RequestParam("rating") String rating, @RequestParam("copy") Integer copy, @RequestParam("actorFirstName[]") List<String> actorsFirstName, @RequestParam("actorLastName[]") List<String> actorsLastName, Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		Staff staff=userService.findStaffByUsername(username);
		Short rentalDuration=3;

		Optional<Store> store=storeService.findStoreById(staff.getStoreId());
		System.out.println(releaseYear);
		
		Timestamp lastUpdate= new Timestamp(System.currentTimeMillis());
		film.setReleaseYear(releaseYear);
		//film.setRating(rating);
		film.setRentalDuration(rentalDuration);
		film.setRentalRate(4.99f);
		film.setReplacementCost(19.99f);
		film.setLast_update(lastUpdate);
		filmService.saveFilm(film);
		System.out.println("Id de la pelicula"+film.getFilmId());

		for (Integer category : categories) {
			filmService.saveFilmCategory(category, film.getFilmId());
			System.out.println(category);
		}

		for (int i=0, j=0; i<actorsFirstName.size() && j< actorsLastName.size(); i++, j++) {
			Actor actor= new Actor();
			actor.setFirstName(actorsFirstName.get(i));
			actor.setLastName(actorsLastName.get(i));
			actor.setLast_update(new Timestamp(System.currentTimeMillis()));
			actorService.save(actor);
			filmService.saveFilmActor(actor.getActorId(), film.getFilmId());
			System.out.println(i+": "+actorsFirstName.get(i)+" "+actorsLastName.get(i)+" id: "+ actor.getActorId());
		}
		
		for(int i=0; i<copy; i++){
			Inventory inventory= new Inventory();
			inventory.setFilm(film);
			inventory.setStore(store.get());
			inventory.setLastUpdate(lastUpdate);
			inventoryService.save(inventory);
		}
		
		return "redirect:/films";

	}
}
