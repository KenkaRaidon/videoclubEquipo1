package uabc.videoclubs.controllers;

import java.security.Principal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uabc.videoclubs.entities.Film;
import uabc.videoclubs.entities.Inventory;
import uabc.videoclubs.entities.Rental;
import uabc.videoclubs.services.FilmService;
import uabc.videoclubs.services.InventoryService;
import uabc.videoclubs.services.RentalService;
import uabc.videoclubs.services.CustomerService;
import uabc.videoclubs.entities.Customer;
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
		
		List<Rental> rentals = rentalService.findAll();
		model.addAttribute("rentals", rentals);
		
		return "views/rental";
	}
	
	@RequestMapping(value = {"/rentalregister", "rentalregister"})
	public String rental(Model model) {
				 
				
				//System.out.print(cliente);
		   
		 model.addAttribute("renta",new Rental());
		    model.addAttribute("pelicula",new Film());
		
		    model.addAttribute("subtotal",0);
		    model.addAttribute("iva",0);
		    model.addAttribute("total",0);
		    model.addAttribute("pago",0);
				return "views/Renta";
				
			}
		
	@RequestMapping(value="/saverenta",method = RequestMethod.POST)
	public String Saverenta(@ModelAttribute("renta") Rental renta,@ModelAttribute("pelicula") Film film,@RequestParam("subtotal") Float subtotal, @RequestParam("iva") Float iva, @RequestParam("total") Float total,@RequestParam("pago")Float pago, Model model) {
		 
       
		if(renta!=null) {
			Customer user=customerService.GetbyId(renta.getCustomerId());
			if(user==null) {
				model.addAttribute("ErrorU",true);
			}
			if(!user.getActiveBool()) {
				model.addAttribute("Desactivado",true);
			}
			    
			//validamos que exista en el inventario
			Inventory inv =inventoryService.findByInventoryId(renta.getInventoryId());
			if(inv!=null) {
				Rental find=rentalService.getbyinventory(renta.getInventoryId());
				
				
				if(find!=null) {
			          model.addAttribute("Rentado",true);
			          model.addAttribute("pelicula",new Film());
			  		
					    model.addAttribute("subtotal",0);
					    model.addAttribute("iva",0);
					    model.addAttribute("total",0);
					    model.addAttribute("pago",0);
					
				}
				else
				{
					Film lapelicula=inv.getFilm();
					model.addAttribute("pelicula",lapelicula);//si no essta rentado pone la informacion
				    //calculamos valor de renta
				  Float sub=lapelicula.getReplacementCost();
				   Float iva2=(float) (sub*0.16);
				   Float total2=iva2+sub;
				    model.addAttribute("subtotal",sub);
				    model.addAttribute("iva",iva2);
				    model.addAttribute("total",total2);
				   
				}
					
			}else {
				model.addAttribute("Nofilm",true);
			}
			
			
	   //validamos pago
			if(pago!=0) {
				if(pago<=total)
					model.addAttribute("PagoM",true);
				else
				{
					Float cambio=pago-total;
					model.addAttribute("cambio",cambio);
					
					DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String currentDateTime = dateFormatter.format(new Date());
					renta.setRentalDate(Timestamp.valueOf(currentDateTime));
					renta.setStaffId(1);
					rentalService.saveRental(renta);
					model.addAttribute("exito",true);
					
				}
					
			}
			
			   // //valida que no esta rentada
	            
				
				//System.out.print(currentDateTime);
			/*
			
			*/
		}
		
	   
      

		return "views/Renta";
	}
	
	
	
	

  
}
