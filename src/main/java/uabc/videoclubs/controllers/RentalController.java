package uabc.videoclubs.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uabc.videoclubs.entities.Rental;
import uabc.videoclubs.services.RentalService;

@Controller
public class RentalController {
	
	@Autowired
	private RentalService rentalService;

	@RequestMapping(value = {"/rental", "rental"})
	public String rental(Model model, HttpServletRequest request, HttpServletResponse response, Principal principal) {
		
		List<Rental> rentals = rentalService.findAll();
		model.addAttribute("rentals", rentals);
		
		return "views/rental";
	}
	
	@RequestMapping(value = {"/rentalregister", "rentalregister"})
	public String rentalregister(Model model, HttpServletRequest request, HttpServletResponse response, Principal principal) {
		
		return "views/rentalregister";
		
		
	}
}
