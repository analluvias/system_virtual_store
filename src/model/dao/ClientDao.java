package model.dao;

import model.entities.Client;
import model.entities.Login;

import java.util.List;

public interface ClientDao {
    void insert(Client obj);

    void update(Client obj);

    void deleteById(Integer id);

    Client findById(Integer id, LoginDao loginDao);

    List<Client> findAll(LoginDao loginDao);

    Client findByLogin(Login obj, LoginDao loginDao);
}
