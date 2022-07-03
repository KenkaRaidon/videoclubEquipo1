package uabc.videoclubs.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model, Principal principal) {
        if(error!=null){
            model.addAttribute("error","Nombre de usuario o contraseña incorrecta");
        }
        return "login";
    }
}
