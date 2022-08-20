package model.factories;

import model.entities.Order;

public class OrderFactory {

    public static Order createOrder(Double total){
        return new Order(total);
    }
}
