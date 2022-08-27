package model.dao;

import model.entities.Cart;

public interface CartDao {
    void insert(Cart obj);

    void deleteById(Integer id);

    Cart findById(Integer id, OrderDao orderDao, PurchaseItemsDao purchaseItemsDao,
                  ProductDao productDao, ProductEspecificationDao productEspecificationDao);
}
