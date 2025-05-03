package com.example.payment.Domain.Factory;

import com.example.payment.Domain.Model.CreditCardPayment;
import com.example.payment.Domain.Model.Payment;

public class CreditPaymentFactory extends PaymentFactory {
    @Override
    public Payment createPayment() {
        return new CreditCardPayment();
    }
}
