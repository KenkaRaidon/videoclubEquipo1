package uabc.videoclubs.controllers;

import java.security.Principal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uabc.videoclubs.entities.City;
import uabc.videoclubs.entities.Country;
import uabc.videoclubs.entities.Customer;
import uabc.videoclubs.entities.CustomerIndex;
import uabc.videoclubs.entities.EAddress;
import uabc.videoclubs.entities.Store;
import uabc.videoclubs.entities.StoreIndex;
import uabc.videoclubs.services.AddressService;
import uabc.videoclubs.services.CityService;
import uabc.videoclubs.services.CountryService;
import uabc.videoclubs.services.CustomerService;
import uabc.videoclubs.services.StoreService;

@Controller
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @Autowired
    CountryService countryService;

    @Autowired 
    CityService cityService;

    @Autowired
    StoreService storeService;

    @Autowired
    AddressService addressService;

    @SuppressWarnings("unchecked")
    @RequestMapping(value = {"/customers","customers"})
    public String listarCustomers(Model model, HttpServletRequest request, HttpServletResponse response, Principal principal){
        List<CustomerIndex> customers;
        List<Country> countries=countryService.findAll();
        List<StoreIndex> stores=storeService.findStoreAndAddress();

        if(model.getAttribute("customers")==null){
            customers=customerService.getCustomers();
        }else{
            customers=(List<CustomerIndex>) model.getAttribute("customers");
        }

        model.addAttribute("customer", new Customer());
        model.addAttribute("eaddress", new EAddress());
        model.addAttribute("countries", countries);
        model.addAttribute("customers", customers);
        model.addAttribute("stores", stores);
        return "views/customers";
    }

    @RequestMapping(value = "/registerCustomer" , method = RequestMethod.GET)
	public String registro(Model model, RedirectAttributes redirectAtt) {
		//System.out.println(cities);
        System.out.println("Hola");
        return "redirect:/";
	}

    @GetMapping(value="detallesCustomer/{customerId}")
	@ResponseBody 
	public Map<String, Object> cargarDetalles(@PathVariable Integer customerId){
		Map<String, Object> response = new HashMap<>();
		
		Customer customer = customerService.findById(customerId).get();
        EAddress address = customer.getAddressObject();
        City city= address.getCity();
		
		response.put("customer", customer);
        response.put("address", address);
        response.put("city", city);
		
		return response;
	}

    @GetMapping("filtroCiudad/{countryId}")
    @ResponseBody
	public List<City> filtroCiudad(@PathVariable Integer countryId) {
		return cityService.findCityByCountryId(countryId);
	}
    
    /*@GetMapping("/registerCustomer")
	public String showRegisterCustomer(Model model) {
		
		model.addAttribute("customer", new Customer());
        model.addAttribute("address", new EAddress());
		
		return "redirect:/";
	}*/
    @PostMapping("/registerCustomer")
	public String registerCustomer(@ModelAttribute("customer") Customer customer, @ModelAttribute("eaddress") EAddress address,  BindingResult result, Model model) {
		System.out.println(customer);
        System.out.println(address);
        
		if(result.hasErrors()) {
			System.out.println("hubo un error en el formulario");
			System.out.println(result.getErrorCount());
			return "views/customers";
		}
        Timestamp lastUpdate= new Timestamp(System.currentTimeMillis());
        address.setLast_update(lastUpdate);
		EAddress tempAddress= addressService.Save(address);
        customer.setActiveBool(true);
        customer.setCreate_date(new Date(System.currentTimeMillis()));
        customer.setAddressObject(tempAddress);
        customerService.save(customer);
		
		return "redirect:/customers";
	}
    
}
