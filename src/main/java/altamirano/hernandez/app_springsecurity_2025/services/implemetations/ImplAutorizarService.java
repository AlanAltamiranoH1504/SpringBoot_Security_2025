package altamirano.hernandez.app_springsecurity_2025.services.implemetations;

import altamirano.hernandez.app_springsecurity_2025.models.Autorizar;
import altamirano.hernandez.app_springsecurity_2025.models.Usuario;
import altamirano.hernandez.app_springsecurity_2025.repositories.IRepositoryAutorizar;
import altamirano.hernandez.app_springsecurity_2025.services.IServiceAutorizar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImplAutorizarService implements IServiceAutorizar {
    @Autowired
    IRepositoryAutorizar iRepositoryAutorizar;

    @Override
    public List<Autorizar> findAll() {
        List<Autorizar> autorizarList = iRepositoryAutorizar.findAll();
        return autorizarList;
    }

    @Override
    public Autorizar findById(int id) {
        try {
            Autorizar autorizarFound = iRepositoryAutorizar.findById(id).get();
            return autorizarFound;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Autorizar autorizar) {
        iRepositoryAutorizar.save(autorizar);
    }

    @Override
    public void deleteById(int id) {
        try {
            Autorizar autorizarFound = iRepositoryAutorizar.findById(id).get();
            if (autorizarFound != null) {
                iRepositoryAutorizar.deleteById(autorizarFound.getId());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
