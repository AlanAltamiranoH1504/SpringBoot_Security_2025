package altamirano.hernandez.app_springsecurity_2025.services;

import altamirano.hernandez.app_springsecurity_2025.models.Usuario;

import java.util.List;

public interface IServiceUsuario {
    public abstract List<Usuario> findAll();
    public abstract Usuario findById(int id);
    public abstract void save(Usuario usuario);
    public abstract void deleteById(int id);
}
