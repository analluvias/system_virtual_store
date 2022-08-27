package model.dao;

import model.entities.Cart;

public interface CartDao {
    void insert(Cart obj);

    void deleteById(Integer id);
}
