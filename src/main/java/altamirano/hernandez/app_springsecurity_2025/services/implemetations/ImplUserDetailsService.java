package altamirano.hernandez.app_springsecurity_2025.services.implemetations;

import altamirano.hernandez.app_springsecurity_2025.models.Usuario;
import altamirano.hernandez.app_springsecurity_2025.repositories.IRepositoryAutorizar;
import altamirano.hernandez.app_springsecurity_2025.services.IServiceUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImplUserDetailsService implements UserDetailsService {
    @Autowired
    private IServiceUsuario iServiceUsuario;
    @Autowired
    private IRepositoryAutorizar iRepositoryAutorizar;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = iServiceUsuario.findByEmail(email, 1);

        //Verificamos si el usuario se encontro
        if (usuario == null){
            throw new UsernameNotFoundException("USUARIO NO ENCONTRADO CON ESE EMAIL: " + email);
        }

        //Configuracion de Permisos
        List<GrantedAuthority> permisos = new ArrayList<>();
        List<String> persmisosString = iRepositoryAutorizar.getPermisosByUserId(usuario.getId());
        for(var role : persmisosString){
            permisos.add(new SimpleGrantedAuthority("ROLE_" + role));
        }
        if (permisos.isEmpty()){
            throw new UsernameNotFoundException("EL USUARIO: " + usuario.getCorreo() + " NO TIENE ROLES ASIGNADOS");
        }

        System.out.println("IMPRIMIENDO PERSMISOS");
        System.out.println(permisos);
        //Retornamos el usuario
        return new User(usuario.getCorreo(), usuario.getPassword(), true, true, true, true, permisos);
    }
}
