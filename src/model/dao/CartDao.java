package model.dao;

import model.entities.Cart;

public interface CartDao {
    void insert(Cart obj);

    void update(Cart obj);

    void deleteById(Integer id);

    Cart findById(Integer id, LoginDao loginDao);
}
