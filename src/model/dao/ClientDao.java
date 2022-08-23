package model.dao;

import model.entities.Client;
import model.entities.Login;

import java.util.List;

public interface ClientDao {
    void insert(Client obj);

    void update(Client obj);

    void deleteById(Integer id);

    Client findById(Integer id);

    List<Client> findAll();

    List<Client> findByLogin(Login obj);
}
