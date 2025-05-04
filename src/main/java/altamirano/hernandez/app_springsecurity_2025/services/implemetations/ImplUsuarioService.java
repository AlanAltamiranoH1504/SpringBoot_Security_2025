package altamirano.hernandez.app_springsecurity_2025.services.implemetations;

import altamirano.hernandez.app_springsecurity_2025.models.Usuario;
import altamirano.hernandez.app_springsecurity_2025.repositories.IRepositoryUsuario;
import altamirano.hernandez.app_springsecurity_2025.services.IServiceUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImplUsuarioService implements IServiceUsuario {
    @Autowired
    IRepositoryUsuario iRepositoryUsuario;

    @Override
    public List<Usuario> findAll() {
        List<Usuario> usuarios = iRepositoryUsuario.findAll();
        return usuarios;
    }

    @Override
    public Usuario findById(int id) {
        try {
            Usuario foundUsuario = iRepositoryUsuario.findById(id).get();
            return foundUsuario;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Usuario usuario) {
        iRepositoryUsuario.save(usuario);
    }

    @Override
    public void deleteById(int id) {
        try {
            Usuario foundUsuario = iRepositoryUsuario.findById(id).get();
            if (foundUsuario != null) {
                iRepositoryUsuario.deleteById(foundUsuario.getId());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Usuario findByEmail(String email, int estado) {
        try{
            Usuario usuarioFound = iRepositoryUsuario.findByEmailAndEstado(email, estado);
            return usuarioFound;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
