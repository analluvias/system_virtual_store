package model.entities;

import model.dao.CartDao;
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


    public Order(Double total) {
        this.number = sequenceNumber++;
        this.id = sequence++;
        this.total = total;
    }

    public void createPayment(CartDao cartDao, Cart cart){
        closeOrder(cartDao, cart);
        this.payment = PaymentFactory.createPayment();
    }

    private void closeOrder(CartDao cartDao, Cart cart){
        cartDao.deleteById(cart.getId());
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

    @Override
    public String toString() {
        return "Order{" +
                "number=" + number +
                ", payment=" + payment +
                ", total=" + total +
                '}';
    }
}
