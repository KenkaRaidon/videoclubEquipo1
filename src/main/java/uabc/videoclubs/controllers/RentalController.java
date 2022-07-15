package uabc.videoclubs.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uabc.videoclubs.entities.Customer;
import uabc.videoclubs.entities.InventoryIndex;
import uabc.videoclubs.entities.Rental;
import uabc.videoclubs.services.CustomerService;
import uabc.videoclubs.services.InventoryService;
import uabc.videoclubs.services.RentalService;

@Controller
public class RentalController {
	
	@Autowired
	private RentalService rentalService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private InventoryService inventoryService;

	@RequestMapping(value = {"/rental", "rental"})
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
	
	@PostMapping("/registerRental")
	public String registerRental(@ModelAttribute("rental") Rental rental){
		
		return "redirect:/rental";
	}
}
