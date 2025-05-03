package com.example.payment.Domain.Factory;

import com.example.payment.Domain.Model.Payment;
import com.example.payment.Domain.Model.PaypalPayment;

public class PaypalPaymentFactory extends PaymentFactory {

    @Override
    public Payment createPayment() {
        return new PaypalPayment();
    }
}
