package altamirano.hernandez.app_springsecurity_2025.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/liberadas")
public class LibertadController {

    @GetMapping("/home")
    public String home() {
        return "liberado/home";
    }

    @GetMapping("/liberada2")
    public String liberada2() {
        return "liberado/liberado2";
    }
}
