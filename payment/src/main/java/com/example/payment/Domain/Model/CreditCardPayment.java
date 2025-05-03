package com.example.payment.Domain.Model;

public class CreditCardPayment implements Payment {
    @Override
    public double process(double amount) {
        double commissionRate = 0.03;
        double finalAmount = amount + (amount * commissionRate);
        System.out.println("Procesando pago con tarjeta de crédito");

        if (amount > 1000) {
            finalAmount += 10;
        }

        return finalAmount;
    }
}
