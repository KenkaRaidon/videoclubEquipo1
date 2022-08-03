package uabc.videoclubs.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.management.loading.PrivateClassLoader;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
import uabc.videoclubs.entities.Ticket;
import uabc.videoclubs.services.CustomerService;
import uabc.videoclubs.services.InventoryService;
import uabc.videoclubs.services.RentalService;
import uabc.videoclubs.services.TicketService;

@Controller
public class MultaController {

	@Autowired
	private TicketService ticketService;

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private RentalService rentalService;
	
	@Autowired
	private InventoryService inventoryService;

	@RequestMapping(value = { "/multa", "multa" })
	public String multa(HttpServletRequest request, HttpServletResponse response, Principal principal) {
		return "views/multa";
	}

	@RequestMapping(value = "/multaBuscarCliente", method = RequestMethod.POST)
	public String multaBuscarCliente(Model model, @RequestParam(name = "customerId") Integer customId,
			RedirectAttributes redirectAtt) {
		Customer customer = customerService.FindMyCustomer(customId);
		// List<Ticket> listTicket = ticketService.findTicketByCustomerId(customId);
		System.out.println(customer.getFirstName());
		// redirectAtt.addFlashAttribute("customer", customer);
		// system
		// Ticket ticket = ticketService.fin
		return "redirect:/multa";
	}

	@GetMapping("findTicketCusById/{customerId}")
	@ResponseBody
	public Optional<Customer> findTicketCusById(@PathVariable Integer customerId) {
		return customerService.findById(customerId);
	}

	@GetMapping("findTicketByCusId/{customerId}")
	@ResponseBody
	public List<Ticket> findTicketByCusId(@PathVariable Integer customerId) {
		List<Ticket> listTicket = ticketService.findTicketByCustomerId(customerId);
		if(listTicket==null)
			return null;
		return listTicket;
	}

	@PostMapping("/pagoMulta")
	public void registerReturn(@RequestParam("customerId") Integer customerId, @RequestParam("listTicket[]") List<Integer> tickets, HttpServletResponse response, HttpServletRequest request) 
			throws DocumentException, IOException{
		List<String> titleFlim = new ArrayList<String>();
		//titleFlim.clear();
		float amount = 0;
		Integer i = 0;
		for(Integer ticketId: tickets){
			
			Optional<Ticket> ticket=ticketService.findById(ticketId);
			ticket.get().setActive(false);
			ticketService.save(ticket.get());
			Optional<Rental> rentalAux = rentalService.findById(ticket.get().getRentalId());
			Inventory inventoryIndex = inventoryService.findByInventoryId(rentalAux.get().getInventoryId());
			titleFlim.add(inventoryIndex.getFilm().getTitle());
			System.out.println("hola: " + titleFlim.get(i));
			amount = amount + ticket.get().getAmount().floatValue();
			i++;
		}
		Customer customer=customerService.FindMyCustomer(customerId);
		customer.setActiveBool(true);
		customerService.save(customer);
		System.out.println(customer);
		String customerName = customer.getFirstName() + " " + customer.getLastName();
		RentalPdfExporter exporter = new RentalPdfExporter();
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		StringBuilder sbHeaderValue = new StringBuilder("attachment; filename=film_");
		sbHeaderValue.append( customerName + "_");
		sbHeaderValue.append(currentDateTime + ".pdf");
		response.setHeader(headerKey, sbHeaderValue.toString());
		
		exporter.ticketPdfCreator(customerName, titleFlim, amount, response);
		
		//return null;
		
	}
	@GetMapping(value = { "/success", "success" })
	public String alertaMulta(HttpServletRequest request, HttpServletResponse response){
		return "views/success";
	}
}
