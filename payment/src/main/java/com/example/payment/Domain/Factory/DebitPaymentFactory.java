package com.example.payment.Domain.Factory;

import com.example.payment.Domain.Model.DebitCardPayment;
import com.example.payment.Domain.Model.Payment;

public class DebitPaymentFactory extends PaymentFactory {
    @Override
    public Payment createPayment() {
        return new DebitCardPayment();
    }
}
