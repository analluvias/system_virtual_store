package model.dao;

import model.entities.Order;

public interface OrderDao {

    void insert(Order obj);

    void deleteById(Integer id);

    Order findById(Integer id);
}
