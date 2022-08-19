package model.factories;

import model.entities.Order;

public class OrderFactory {

    public static Order createOrder(Double price){
        return new Order(price);
    }
}
