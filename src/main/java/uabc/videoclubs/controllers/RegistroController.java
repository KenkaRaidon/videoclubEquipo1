package uabc.videoclubs.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.SessionFlashMapManager;

import uabc.videoclubs.entities.Staff;
import uabc.videoclubs.services.UserService;

@Controller
public class RegistroController {
	
	@Autowired
	private UserService staffService;
	
	SessionFlashMapManager flashManager = new SessionFlashMapManager();
    FlashMap flashMap = new FlashMap();
	

	@GetMapping("/registro")
	public String registro(Model model, Principal principal) {
		
		/*if(principal != null) {
			System.out.println("ya ha iniciado sesion");
			return "redirect:/rental";
		}*/
		
		model.addAttribute("staff", new Staff());
		return "views/registro";
	}
	
	@PostMapping("/registro")
	public String registroGuardar(@Valid Staff staff, BindingResult result, Model model, @RequestParam(name = "password_confirm") String password_confirm) {
		
		if(result.hasErrors()) {
			System.out.println("hubo un error en el formulario");
			System.out.println(result.getErrorCount());
			return "views/registro";
		}
		
		if(!staff.getPassword().equals(password_confirm)) {
			System.out.println("El password no coincide");
			model.addAttribute("passwordError","Las contraseñas no coinciden");
			return "views/registro";
		}
		
		if(staffService.findByEmail(staff.getEmail()) != null) {
			System.out.println("El email ya existe");
			model.addAttribute("emailError","El email ya se encuentra registrado");
			return "views/registro";
		}
		
		if(staffService.loadUserByUsername(staff.getUsername()) != null) {
			System.out.println("El username ya existe");
			model.addAttribute("usernameError","El username ya se encuentra registrado");
			return "views/registro";
		}
		staff.setAddressId(1);
		staff.setStoreId(1);
		staffService.save(staff);
		
		return "login";
	}
	
	/*
	@RequestMapping("/prueba")
	public String prueba(Model model, HttpServletRequest request, HttpServletResponse response, Principal principal, RedirectAttributes redirAttrs) {
		model.addAttribute("tipo","info");
		model.addAttribute("mensaje","HolA");
	
		redirAttrs.addFlashAttribute("mensaje", "Hola");
		redirAttrs.addFlashAttribute("tipo", "success");
		
        flashMap.put("tipo", "success");
        flashMap.put("mensaje", "Hola " + principal.getName() + "!");
        flashManager.saveOutputFlashMap(flashMap, request, response);
		
		return "redirect:/index";
	}
	*/
}
