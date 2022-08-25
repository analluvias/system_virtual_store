package model.dao;

import model.entities.ProductEspecification;

import java.util.List;

public interface ProductEspecificationDao {

    void insert(ProductEspecification obj);

    void update(ProductEspecification obj);

    void deleteById(Integer id);

    ProductEspecification findById(Integer id);

    List<ProductEspecification> findAll ();

}
