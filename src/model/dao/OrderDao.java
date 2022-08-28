package model.dao;

import model.entities.Order;
import model.entities.PurchaseItems;

import java.util.List;

public interface OrderDao {

    void insert(Order obj);

    void deleteById(Integer id);

    Order findById(Integer id);

    public void closeOrder(PurchaseItemsDao purchaseItemsDao,
                           List<PurchaseItems> list);
}
