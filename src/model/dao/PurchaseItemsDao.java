package model.dao;

import model.entities.PurchaseItems;

import java.util.List;

public interface PurchaseItemsDao {
    void insert(PurchaseItems obj);

    void update(PurchaseItems obj);

    void deleteById(Integer id);

    PurchaseItems findById(Integer id, ProductDao productDao,
                           ProductEspecificationDao productEspecificationDao);

    List<PurchaseItems> findAll(ProductDao productDao,
                                ProductEspecificationDao productEspecificationDao);

}
