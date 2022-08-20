package model.entities;

import model.entities.enums.Status;
import model.factories.PaymentFactory;
import model.services.Payment;

public class Order {
    private static Integer sequence=0;
    private static Integer sequenceNumber=0;

    private Integer id;

    private Integer number;
    private Payment payment;
    private Double total;

    private Status status;

    public Order(Double total) {
        this.status = Status.OPENED;
        this.number = sequenceNumber++;
        this.id = sequence++;
        this.total = total;
    }

    public void createPayment(){
        closeOrder();
        this.payment = PaymentFactory.createPayment();
    }

    private void closeOrder(){
        this.status = Status.CLOSED;
    }

    public Payment getPayment() {
        return payment;
    }

    public Integer getNumber() {
        return number;
    }

    public Double getTotal() {
        return total;
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
