package model.dao;

import model.entities.PurchaseItems;

public interface PurchaseItemsDao {
    void insert(PurchaseItems obj);

    void update(PurchaseItems obj);

    void deleteById(Integer id);

    PurchaseItems findById(Integer id, ProductDao productDao);

}
