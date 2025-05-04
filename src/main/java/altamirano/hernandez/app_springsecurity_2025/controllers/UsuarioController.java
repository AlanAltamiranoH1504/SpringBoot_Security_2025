package altamirano.hernandez.app_springsecurity_2025.controllers;

import altamirano.hernandez.app_springsecurity_2025.models.Usuario;
import altamirano.hernandez.app_springsecurity_2025.services.IServiceUsuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    IServiceUsuario iServiceUsuario;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/prueba")
    ResponseEntity<?> prueba(){
        Map<String, Object> json = new HashMap<>();
        json.put("msg", "Funcionando controlador de usuarios");

        return ResponseEntity.status(200).body(json);
    }

    @PostMapping("/saveUsuario")
    ResponseEntity<?> saveUsuario(@Valid @RequestBody Usuario usuario, BindingResult bindingResult){
        Map<String, Object> json = new HashMap<>();
        if (bindingResult.hasErrors()){
            Map<String, Object> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errores.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.status(400).body(errores);
        } else{
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            iServiceUsuario.save(usuario);
            json.put("msg", "Usuario creado correctamenre");
            return ResponseEntity.status(201).body(json);
        }
    }
}
