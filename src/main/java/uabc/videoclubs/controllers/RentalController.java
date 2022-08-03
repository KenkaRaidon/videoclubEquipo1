package uabc.videoclubs.controllers;

import java.math.BigDecimal;
import java.io.IOException;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lowagie.text.DocumentException;

import uabc.videoclubs.entities.Customer;
import uabc.videoclubs.entities.Inventory;
import uabc.videoclubs.entities.InventoryIndex;
import uabc.videoclubs.entities.Rental;
import uabc.videoclubs.entities.ReturnIndex;
import uabc.videoclubs.entities.Staff;
import uabc.videoclubs.entities.Ticket;
import uabc.videoclubs.services.CustomerService;
import uabc.videoclubs.services.InventoryService;
import uabc.videoclubs.services.RentalService;
import uabc.videoclubs.services.TicketService;
import uabc.videoclubs.services.UserService;

@Controller
public class RentalController {

	@Autowired
	private RentalService rentalService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private InventoryService inventoryService;

	@Autowired
	private UserService userService;

	@Autowired
	private TicketService ticketService;

	private List<Integer> listRentalId = new ArrayList<Integer>();

	@RequestMapping(value = { "/rental", "rental" })
	public String rental(Model model, HttpServletRequest request, HttpServletResponse response, Principal principal) {
		List<Rental> rentals=rentalService.findAll();
		model.addAttribute("rentals", rentals);
		model.addAttribute("rental", new Rental());
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String formatDateTime = LocalDateTime.now().format(format);
		model.addAttribute("fechaAct", formatDateTime);
		return "views/rental";
	}

	@GetMapping("findCustomerById/{customerId}")
	@ResponseBody
	public Optional<Customer> findCustomerById(@PathVariable Integer customerId) {
		return customerService.findById(customerId);
	}

	@GetMapping("obtenerInventario/{inventoryId}")
	@ResponseBody
	public InventoryIndex obtenerInventario(@PathVariable Integer inventoryId) {
		/*
		 * Rental rental = rentalService.findRentalByInventoryId(inventoryId);
		 * Ticket ticket= ticketService.findTicketByRentalId(rental.getRentalId());
		 * System.out.println(ticket);
		 */
		if (rentalService.findRentalByInventoryId(inventoryId) == null) {
			return inventoryService.obtenerInventario(inventoryId);
		} else {
			return null;
		}

	}

	@GetMapping("obtenerInventarioReturn/{inventoryId}")
	@ResponseBody
	public ReturnIndex obtenerInventarioReturn(@PathVariable Integer inventoryId) {
		ReturnIndex returnIndex = new ReturnIndex();
		InventoryIndex inventoryIndex = inventoryService.obtenerInventario(inventoryId);
		Rental rental = rentalService.findRentalByInventoryId(inventoryId);

		if (inventoryIndex == null)
			return null;
		if (rental == null) {
			returnIndex.rentada = false;
			return returnIndex;

		}

		returnIndex.setTitle(inventoryIndex.getTitle());
		if (rental.getReturnDate() == null) {

			System.out.println(rental);
			System.out.println(inventoryIndex);

			int dia = inventoryIndex.getRental_duration();

			Timestamp rentadate = rental.getRentalDate();

			Calendar cal = Calendar.getInstance();
			cal.setTime(rentadate);
			cal.add(Calendar.DAY_OF_WEEK, dia);
			rentadate = new Timestamp(cal.getTime().getTime());
			System.out.println(rentadate);
			returnIndex.setTitle(inventoryIndex.getTitle());
			returnIndex.setReturnDate(rentadate);
			Customer micliente = customerService.FindMyCustomer(rental.getCustomerId());
			returnIndex.setCliente(micliente);
			returnIndex.rentada = true;
			return returnIndex;

		}
		return returnIndex;

	}

	@PostMapping("/registerRental")
	public String registerRental(@RequestParam("customerId") Integer customerId,
			@RequestParam("inventoryId[]") List<Integer> inventories, @RequestParam("amount") Float amount) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		Staff staff = userService.findStaffByUsername(username);
		Timestamp now = new Timestamp(System.currentTimeMillis());
		// InventoryIndex inventoryIndex=new InventoryIndex();
		listRentalId.clear();
		for (Integer inventory : inventories) {
			Rental rental = new Rental();
			//Ticket ticket = new Ticket();
			Calendar calendar = Calendar.getInstance();
			InventoryIndex inventoryIndex = inventoryService.obtenerInventario(inventory);
			System.out.println("Rental duration:  " + inventoryIndex.getRental_duration());
			calendar.add(Calendar.DAY_OF_MONTH, inventoryIndex.getRental_duration());
			Date date = calendar.getTime();
			Timestamp returnDate = new Timestamp(date.getTime());
			System.out.println(returnDate);

			rental.setCustomerId(customerId);
			rental.setInventoryId(inventory);
			rental.setStaffId(staff.getStaffId());
			rental.setRentalDate(now);
			// rental.setReturnDate(returnDate);
			rental.setLastUpdate(now);

			rentalService.save(rental);
			System.out.println("Renta ID: " + rental.getRentalId());

			// ticketService.save(ticket);

			// System.out.println("Ticket ID" + ticket.getTicketId());
			Integer aux = rental.getRentalId();
			listRentalId.add(aux);
		}
		return "redirect:/registerRental/exportPdf";
	}

	@PostMapping("/registerDevolucion")
	public String registerReturn(@RequestParam("milistainv[]") List<Integer> inventories,
			@RequestParam(name="listamulta[]", required = false) List<Float> multas) {
		int i = 0;
		// Suponiendo que se valido que todo existe en Js se continua con la creacion de
		// registros para la multa y la actualisacion de la devolucion
		for (Integer inventory : inventories) {

			Rental mirenta = rentalService.findRentalByInventoryId(inventory);
			mirenta.setReturnDate(new Timestamp(System.currentTimeMillis()));
			if(multas!=null){
				if (multas.get(i) > 0) {
					Ticket ticket = new Ticket();
					ticket.setTicketDate(new Timestamp(System.currentTimeMillis()));
					ticket.setRentalId(mirenta.getRentalId());
					ticket.setCustomerId(mirenta.getCustomerId());
					System.out.println(multas.get(i));
					ticket.setAmount(BigDecimal.valueOf(multas.get(i)).setScale(1, BigDecimal.ROUND_HALF_DOWN));
	
					ticket.setActive(true);
					ticketService.save(ticket);
					System.out.println(ticket);
					Customer micliente = customerService.FindMyCustomer(mirenta.getCustomerId());
					micliente.setActiveBool(false);
					customerService.save(micliente);
					System.out.println(micliente);
					
					
	
				}
			}
			
			
		
			rentalService.save(mirenta);
			i++;

		}

		return "redirect:/rental";
	}

	@GetMapping("/registerRental/exportPdf")
	public void exportToPDF(HttpServletResponse response, HttpServletRequest request)
			throws DocumentException, IOException {
		String nombreCliente = "";
		String sumita = "";
		List<Rental> listaRentas = new ArrayList<Rental>();
		List<String> lisTitles = new ArrayList<String>();
		for (int i = 0; i < listRentalId.size(); i++) {
			
			Integer rentalId = listRentalId.get(i); 
			listaRentas.add(rentalService.findById(rentalId).get());
			//Rental List<renta> = rentalService.findById(rentalId).get();
			Optional<Customer> customer = customerService.findById(listaRentas.get(i).getCustomerId());
			//Inventory inventory = inventoryService.findByInventoryId()
			
			InventoryIndex inventoryIndex = inventoryService.obtenerInventario(listaRentas.get(i).getInventoryId());

			//System.out.println("Renta "+ renta +" +Customer "+ customer);
			lisTitles.add(inventoryIndex.getTitle());
			 nombreCliente = customer.get().getFirstName() + " " + customer.get().getLastName();
			sumita = sumita + rentalId;
			
		}
		if(listaRentas.size() != 0) {
		RentalPdfExporter exporter = new RentalPdfExporter();
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		StringBuilder sbHeaderValue = new StringBuilder("attachment; filename=film_");
		sbHeaderValue.append(sumita + "_");
		sbHeaderValue.append(currentDateTime + ".pdf");
		response.setHeader(headerKey, sbHeaderValue.toString());
		exporter.generateRegisterRental(listaRentas, nombreCliente, lisTitles, response);
		}

	}

	/*
	 * @RequestMapping(value = "DevolucionRegistroMulta" , method =
	 * RequestMethod.POST)
	 * String devolucionCreacionDeMulta(Model model, @RequestParam(name =
	 * "fecharegreso") Date returnDate, RedirectAttributes
	 * redirectAtt, @RequestParam(name = "inventoryIdReturn[]") List<Integer>
	 * listInventory){
	 * for(Integer numIn: listInventory) {
	 * Rental rental = rentalService.findRentalByInventoryId(numIn);
	 * Timestamp date =
	 * rental.setRentalDate();
	 * }
	 * return "";}
	 */
}
