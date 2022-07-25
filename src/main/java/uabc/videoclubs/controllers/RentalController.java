package uabc.videoclubs.controllers;

import java.security.Principal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.Date;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import uabc.videoclubs.entities.Customer;
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

	@RequestMapping(value = { "/rental", "rental" })
	public String rental(Model model, HttpServletRequest request, HttpServletResponse response, Principal principal) {
		model.addAttribute("rental", new Rental());
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
		return inventoryService.obtenerInventario(inventoryId);
	}

	@GetMapping("obtenerInventarioReturn/{inventoryId}")
	@ResponseBody
	public ReturnIndex obtenerInventarioReturn(@PathVariable Integer inventoryId) {
		ReturnIndex returnIndex = new ReturnIndex();
		InventoryIndex inventoryIndex = inventoryService.obtenerInventario(inventoryId);
		Rental rental = rentalService.findReturnDateByInventoryId(inventoryId);
		System.out.println(rental);
		System.out.println(inventoryIndex);
		//DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"); 
		//LocalDateTime localDate= rental.getReturnDate().toLocalDateTime();
		System.out.println("Fecha "+rental.getReturnDate());
		returnIndex.setTitle(inventoryIndex.getTitle());
		returnIndex.setReturnDate(rental.getReturnDate());
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
		for (Integer inventory : inventories) {
			Rental rental = new Rental();
			Ticket ticket = new Ticket();
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
			rental.setReturnDate(returnDate);
			rental.setLastUpdate(now);

			rentalService.save(rental);
			System.out.println("Renta ID" + rental.getRentalId());

			ticket.setRentalId(rental.getRentalId());
			ticket.setCustomerId(rental.getCustomerId());
			ticket.setTicketDate(now);
			ticket.setAmount(amount);
			ticket.setActive(true);

			ticketService.save(ticket);

			System.out.println("Ticket ID" + ticket.getTicketId());
		}
		return "redirect:/rental";
	}

	@PostMapping("/registerDevolucion")
	public String registerReturn() {

		return "redirect:/rental";
	}
}
