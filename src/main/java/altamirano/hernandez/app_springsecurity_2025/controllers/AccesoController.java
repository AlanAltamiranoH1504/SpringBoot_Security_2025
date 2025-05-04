package altamirano.hernandez.app_springsecurity_2025.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/acceso")
public class AccesoController {

    //Vista personalizada de  login con logout
    @GetMapping("/login")
    public String login(@RequestParam(value = "logout", required = false) String logout, Model model, HttpServletRequest request){
        return "acceso/login";
    }

//    //Acceso de login
//    @PostMapping("/login")
//    public String loginPost() {
//        // Este metodo no es necesario con Spring Security, ya que lo maneja automáticamente.
//        return "redirect:/liberadas/home";
//    }
}
