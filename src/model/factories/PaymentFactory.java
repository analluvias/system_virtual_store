package model.factories;

import model.services.Payment;

public class PaymentFactory {

    public static Payment createPayment(){
        return new Payment();
    }

}
