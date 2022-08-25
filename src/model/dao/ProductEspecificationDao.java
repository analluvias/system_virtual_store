package model.dao;

import model.entities.ProductEspecification;

public interface ProductEspecificationDao {

    void insert(ProductEspecification obj);

    void update(ProductEspecification obj);

    void deleteById(Integer id);

    ProductEspecification findById(Integer id);

}
