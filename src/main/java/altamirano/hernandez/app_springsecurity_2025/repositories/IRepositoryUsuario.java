package altamirano.hernandez.app_springsecurity_2025.repositories;

import altamirano.hernandez.app_springsecurity_2025.models.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IRepositoryUsuario extends CrudRepository<Usuario, Integer> {
    @Query("SELECT u FROM Usuario u")
    public List<Usuario> findAll();
}
