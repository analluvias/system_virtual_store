package model.dao.impl;

import model.dao.ClientDao;
import model.entities.Client;
import model.entities.Login;

import java.sql.Connection;
import java.util.List;

public class ClientDaoJDBC implements ClientDao {

    private Connection conn;

    public ClientDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Client obj) {

    }

    @Override
    public void update(Client obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Client findById(Integer id) {
        return null;
    }

    @Override
    public List<Client> findAll() {
        return null;
    }

    @Override
    public List<Client> findByLogin(Login obj) {
        return null;
    }
}
