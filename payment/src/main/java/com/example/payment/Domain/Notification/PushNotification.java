package com.example.payment.Domain.Notification;

public class PushNotification implements Notification {
    private String deviceToken;
    private String title;
    private String message;
    private String imageUrl;
    private String clickAction;
    private String priority;

    private PushNotification(Builder builder) {
        this.deviceToken = builder.deviceToken;
        this.title = builder.title;
        this.message = builder.message;
        this.imageUrl = builder.imageUrl;
        this.clickAction = builder.clickAction;
        this.priority = builder.priority;
    }

    public static class Builder {
        private String deviceToken;
        private String title;
        private String message;
        private String imageUrl;
        private String clickAction;
        private String priority;

        public Builder deviceToken(String deviceToken) { this.deviceToken = deviceToken; return this; }
        public Builder title(String title) { this.title = title; return this; }
        public Builder message(String message) { this.message = message; return this; }
        public Builder imageUrl(String imageUrl) { this.imageUrl = imageUrl; return this; }
        public Builder clickAction(String clickAction) { this.clickAction = clickAction; return this; }
        public Builder priority(String priority) { this.priority = priority; return this; }

        public PushNotification build() {
            return new PushNotification(this);
        }
    }

    @Override
    public void send(String message) {
        System.out.println("[PUSH] Enviado a: " + deviceToken + ", Título: " + title + ", Mensaje: " + message);
    }
}
