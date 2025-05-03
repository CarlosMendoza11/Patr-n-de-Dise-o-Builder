package com.example.payment.Domain.Factory;

import com.example.payment.Domain.Model.Payment;

public abstract class PaymentFactory {
    public abstract Payment createPayment();
}
