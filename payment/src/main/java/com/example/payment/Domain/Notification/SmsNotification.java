package com.example.payment.Domain.Notification;

import java.time.LocalDateTime;

public class SmsNotification implements Notification {
    private String phoneNumber;
    private String message;
    private String senderId;
    private boolean deliveryReportRequired;
    private LocalDateTime scheduleTime;

    private SmsNotification(Builder builder) {
        this.phoneNumber = builder.phoneNumber;
        this.message = builder.message;
        this.senderId = builder.senderId;
        this.deliveryReportRequired = builder.deliveryReportRequired;
        this.scheduleTime = builder.scheduleTime;
    }

    public static class Builder {
        private String phoneNumber;
        private String message;
        private String senderId;
        private boolean deliveryReportRequired;
        private LocalDateTime scheduleTime;

        public Builder phoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; return this; }
        public Builder message(String message) { this.message = message; return this; }
        public Builder senderId(String senderId) { this.senderId = senderId; return this; }
        public Builder deliveryReportRequired(boolean deliveryReportRequired) { this.deliveryReportRequired = deliveryReportRequired; return this; }
        public Builder scheduleTime(LocalDateTime scheduleTime) { this.scheduleTime = scheduleTime; return this; }

        public SmsNotification build() {
            return new SmsNotification(this);
        }
    }

    @Override
    public void send(String message) {
        System.out.println("[SMS] Enviado a: " + phoneNumber + ", Mensaje: " + message);
    }
}
