package model.services;

import model.dao.*;
import model.entities.Cart;
import model.entities.Client;
import model.entities.Order;
import model.entities.PurchaseItems;

import java.util.List;

public class Payment {
    private static Integer sequence=0;
    private int id;

    public Payment() {
        this.id = sequence++;
    }

    public int getId() {
        return id;
    }

    public String generatePayment(ClientDao clientDao, LoginDao loginDao, ProductDao productDao,
                                  CartDao cartDao, OrderDao orderDao, PurchaseItemsDao purchaseItemsDao,
                                  ProductEspecificationDao productEspecificationDao,
                                  Client client, Cart cart, Order order, List<PurchaseItems> list){

        Order orderById = orderDao.findById(order.getId());

        String msg = "++++++++++++++++++++++++++++++++++++++++++++\n" +
                clientDao.findById(client.getId(), loginDao).getName() + ", sua fatura foi gerada. \n" +
                "Código: " + orderById.getNumber() +
                "\nPreço: " + orderById.getTotal() +
                "\n\nLista de produtos: \n" + cartDao.findById(cart.getId(), orderDao, purchaseItemsDao,
                productDao, productEspecificationDao).printPurchaseItems() +
                "++++++++++++++++++++++++++++++++++++++++++++";

        orderDao.closeOrder(purchaseItemsDao, list);

        return msg;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                '}';
    }
}
