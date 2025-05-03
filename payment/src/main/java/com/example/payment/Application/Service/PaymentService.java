package com.example.payment.Application.Service;

import com.example.payment.Domain.Factory.CreditPaymentFactory;
import com.example.payment.Domain.Factory.DebitPaymentFactory;
import com.example.payment.Domain.Factory.PaymentFactory;
import com.example.payment.Domain.Factory.PaypalPaymentFactory;
import com.example.payment.Domain.Model.Payment;
import com.example.payment.Domain.Notification.Notification;
import com.example.payment.Domain.Notification.NotificationFactory;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public double processPayment(String paymentType, double amount, String channel) {
        PaymentFactory factory;

        switch (paymentType.toUpperCase()) {
            case "CREDIT":
                factory = new CreditPaymentFactory();
                break;
            case "DEBIT":
                factory = new DebitPaymentFactory();
                break;
            case "PAYPAL":
                factory = new PaypalPaymentFactory();
                break;
            default:
                throw new IllegalArgumentException("Método de pago no soportado");
        }

        Payment payment = factory.createPayment();
        double result = payment.process(amount);

        // Enviar notificación del resultado del pago
        Notification notification = NotificationFactory.createNotification(channel); // "EMAIL" o "SMS"
        notification.send("Pago de $" + amount + " procesado con método: " + paymentType);

        return result;
    }
}
