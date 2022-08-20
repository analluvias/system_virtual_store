package model.services;

import model.entities.Cart;
import model.entities.Client;
import model.entities.Order;

public class Payment {
    private static Integer sequence=0;
    private int id;

    public Payment() {
        this.id = sequence++;
    }

    public int getId() {
        return id;
    }

    public String generatePayment(Client client, Cart cart, Order order){
        return "++++++++++++++++++++++++++++++++++++++++++++\n" +
                client.getName() + ", sua fatura foi gerada. \n" +
                "Código: " + order.getNumber() +
                "\nPreço: " + order.getTotal() +
                "\n\nLista de produtos: \n" + cart.printPurchaseItems() +
                "++++++++++++++++++++++++++++++++++++++++++++";
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                '}';
    }
}
