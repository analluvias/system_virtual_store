package model.dao.impl;

import model.dao.CartDao;
import model.dao.LoginDao;
import model.entities.Cart;

import java.sql.Connection;

public class CartDaoJDBC implements CartDao {

    private Connection conn;

    @Override
    public void insert(Cart obj) {

    }

    @Override
    public void update(Cart obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Cart findById(Integer id, LoginDao loginDao) {
        return null;
    }
}
