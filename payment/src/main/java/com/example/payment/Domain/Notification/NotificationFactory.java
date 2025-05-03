package com.example.payment.Domain.Notification;

import java.time.LocalDateTime;
import java.util.List;

public class NotificationFactory {

    public static Notification createNotification(String channel) {
        switch (channel.toUpperCase()) {
            case "EMAIL":
                return new EmailNotification.Builder()
                        .to("destinatario@example.com")
                        .subject("Notificación de pago")
                        .body("Tu pago fue procesado correctamente.")
                        .priority("alta")
                        .build();

            case "SMS":
                return new SmsNotification.Builder()
                        .phoneNumber("+573001234567")
                        .message("Pago exitoso")
                        .deliveryReportRequired(true)
                        .scheduleTime(LocalDateTime.now())
                        .build();

            case "PUSH":
                return new PushNotification.Builder()
                        .deviceToken("token123456")
                        .title("Pago confirmado")
                        .message("Tu pago fue recibido con éxito")
                        .priority("urgente")
                        .build();

            case "WHATSAPP":
                return new WhatsappNotification.Builder()
                        .phoneNumber("+573001234567")
                        .message("Tu pago ha sido confirmado")
                        .language("es")
                        .build();

            default:
                throw new IllegalArgumentException("Canal no soportado: " + channel);
        }
    }
}
