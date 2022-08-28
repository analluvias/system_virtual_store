package model.factories;

import model.entities.Order;
import model.services.Payment;

public class PaymentFactory {

    public static Payment createPayment(){
        return new Payment();
    }

}
