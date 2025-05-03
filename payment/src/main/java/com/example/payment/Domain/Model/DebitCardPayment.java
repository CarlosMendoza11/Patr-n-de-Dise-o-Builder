package com.example.payment.Domain.Model;

public class DebitCardPayment implements Payment {
    @Override
    public double process(double amount) {
        double commissionRate = 0.01;
        double finalAmount = amount + (amount * commissionRate);
        System.out.println("Procesando pago con tarjeta de débito");

        if (amount > 500) {
            finalAmount += 5;
        }

        return finalAmount;
    }
}
