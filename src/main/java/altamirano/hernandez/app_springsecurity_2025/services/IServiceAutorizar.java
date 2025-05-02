package altamirano.hernandez.app_springsecurity_2025.services;

import altamirano.hernandez.app_springsecurity_2025.models.Autorizar;

import java.util.List;

public interface IServiceAutorizar {
    public abstract List<Autorizar> findAll();
    public abstract Autorizar findById(int id);
    public abstract void save(Autorizar autorizar);
    public abstract void deleteById(int id);
}
