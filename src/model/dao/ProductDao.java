package model.dao;

import model.entities.Cart;
import model.entities.Product;

public interface ProductDao {

    void insert(Product obj);

    void update(Product obj);

    void deleteById(Integer id);

    Product findById(Integer id, ProductEspecificationDao productEspecificationDao);

}
