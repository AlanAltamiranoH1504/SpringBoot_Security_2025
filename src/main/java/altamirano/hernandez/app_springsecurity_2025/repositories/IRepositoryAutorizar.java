package altamirano.hernandez.app_springsecurity_2025.repositories;

import altamirano.hernandez.app_springsecurity_2025.models.Autorizar;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IRepositoryAutorizar extends CrudRepository<Autorizar, Integer> {
    public List<Autorizar> findAll();

    @Query("SELECT a.nombre FROM Autorizar a WHERE a.usuario.id =:id")
    public List<String>getPermisosByUserId(@Param("id") int id);
}
