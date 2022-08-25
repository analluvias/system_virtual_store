package model.dao;

import model.entities.Cart;
import model.entities.Product;

import java.util.List;

public interface ProductDao {

    void insert(Product obj);

    void deleteById(Integer id);

    Product findById(Integer id, ProductEspecificationDao productEspecificationDao);

    List<Product> findAll(ProductEspecificationDao productEspecificationDao);

}
