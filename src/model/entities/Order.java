package model.entities;

import model.entities.enums.Status;
import model.factories.PaymentFactory;
import model.services.Payment;

public class Order {
    private static Integer sequence=0;

    private Integer id;
    private Payment payment;

    private Status status;

    public Order(Double price) {
        this.status = Status.OPENED;
        this.id = sequence++;
    }

    public Payment createPayment(Double price){
        closeOrder();
        return PaymentFactory.createPayment(price);
    }

    private void closeOrder(){
        this.status = Status.CLOSED;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", payment=" + payment +
                ", status=" + status +
                '}';
    }
}
