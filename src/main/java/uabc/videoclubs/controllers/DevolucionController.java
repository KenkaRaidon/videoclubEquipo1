package uabc.videoclubs.controllers;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uabc.videoclubs.services.RentalService;
import uabc.videoclubs.entities.ReturnIndex;
@Controller
public class DevolucionController {
	@Autowired
	private RentalService rentalService;
	
    @SuppressWarnings("unchecked")
    @RequestMapping(value = {"/returns","return"})
    public String principalDevo(Model model, HttpServletRequest request, HttpServletResponse response, Principal principal) {

    	return "views/devolucion";
    }
  /*  @RequestMapping(value = "searchRental" , method = RequestMethod.POST)
    public String searchRental(Model model, @RequestParam(name = "inputMovieNum") Integer id, RedirectAttributes redirectAtt) {
    	List<ReturnIndex> filtroReturn = rentalService.returns(id);
    	redirectAtt.addFlashAttribute("",filtroReturn);
    }*/
	
}
