package model.entities;

import model.dao.CartDao;
import model.dao.PurchaseItemsDao;
import model.entities.enums.Status;
import model.factories.PaymentFactory;
import model.services.Payment;

import java.util.List;

public class Order {
    private static Integer sequence=0;
    private static Integer sequenceNumber=0;

    private Integer id;

    private Integer number;
    private Payment payment;
    private Double total;


    public Order(Double total) {
        this.number = sequenceNumber++;
        this.id = sequence++;
        this.total = total;
    }

    public void createPayment(){
        this.payment = PaymentFactory.createPayment();
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Order{" +
                "number=" + number +
                ", payment=" + payment +
                ", total=" + total +
                '}';
    }
}
