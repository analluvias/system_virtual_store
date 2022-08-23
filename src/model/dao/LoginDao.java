package model.dao;


import model.entities.Login;

import java.util.List;

public interface LoginDao {

    void insert(Login obj);

    void update(Login obj);

    void deleteById(Integer id);

    Login findBYId(Integer id);

    List<Login> findAll();

}
