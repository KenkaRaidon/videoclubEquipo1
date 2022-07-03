package uabc.videoclubs.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uabc.videoclubs.entities.City;
import uabc.videoclubs.entities.Country;
import uabc.videoclubs.entities.Customer;
import uabc.videoclubs.entities.CustomerIndex;
import uabc.videoclubs.services.CityService;
import uabc.videoclubs.services.CountryService;
import uabc.videoclubs.services.CustomerService;

@Controller
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @Autowired
    CountryService countryService;

    @Autowired 
    CityService cityService;

    @SuppressWarnings("unchecked")
    @RequestMapping(value = {"/customers","customers"})
    public String listarCustomers(Model model, HttpServletRequest request, HttpServletResponse response, Principal principal){
        List<CustomerIndex> customers;
        List<Country> countries=countryService.findAll();

        if(model.getAttribute("customers")==null){
            customers=customerService.getCustomers();
        }else{
            customers=(List<CustomerIndex>) model.getAttribute("customers");
        }
        model.addAttribute("customer", new Customer());
        model.addAttribute("countries", countries);
        model.addAttribute("customers", customers);
        return "views/customers";
    }

    @RequestMapping(value = "createCustomer" , method = RequestMethod.POST)
	public String registro(Model model, @RequestParam(name = "selectCountry") Integer country, RedirectAttributes redirectAtt) {
		return "redirect:/";
	}

    @GetMapping("filtroCiudad/{countryId}")
	public String filtroCiudad(@PathVariable Integer countryId) {
        System.out.println(countryId);
        List<City> cities=cityService.findCityByCountryId(countryId);
		System.out.println(cities);
		return "redirect:/";
	}

    @RequestMapping(value = {"/registercustomer","registercustomer"})
    public String registerCustomer(Model model, HttpServletRequest request, HttpServletResponse response, Principal principal){
        return "views/registercustomer";
    }
    /*@PostMapping("/createCustomer")
	public String registroGuardar(@Valid Customer customer, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			System.out.println("hubo un error en el formulario");
			System.out.println(result.getErrorCount());
			return "views/customers";
		}
		
		customerService.save(customer);
		
		return "redirect:/";
	}*/
    
}
