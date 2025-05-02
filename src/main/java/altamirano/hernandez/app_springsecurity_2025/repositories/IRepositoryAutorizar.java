package altamirano.hernandez.app_springsecurity_2025.repositories;

import altamirano.hernandez.app_springsecurity_2025.models.Autorizar;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IRepositoryAutorizar extends CrudRepository<Autorizar, Integer> {
    public List<Autorizar> findAll();
}
