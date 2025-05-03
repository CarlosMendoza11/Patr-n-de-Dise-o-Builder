package com.example.payment.Domain.Notification;

import java.util.List;

public class WhatsappNotification implements Notification {
    private String phoneNumber;
    private String message;
    private String mediaUrl;
    private String caption;
    private List<String> interactiveButtons;
    private String language;

    private WhatsappNotification(Builder builder) {
        this.phoneNumber = builder.phoneNumber;
        this.message = builder.message;
        this.mediaUrl = builder.mediaUrl;
        this.caption = builder.caption;
        this.interactiveButtons = builder.interactiveButtons;
        this.language = builder.language;
    }

    public static class Builder {
        private String phoneNumber;
        private String message;
        private String mediaUrl;
        private String caption;
        private List<String> interactiveButtons;
        private String language;

        public Builder phoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; return this; }
        public Builder message(String message) { this.message = message; return this; }
        public Builder mediaUrl(String mediaUrl) { this.mediaUrl = mediaUrl; return this; }
        public Builder caption(String caption) { this.caption = caption; return this; }
        public Builder interactiveButtons(List<String> interactiveButtons) { this.interactiveButtons = interactiveButtons; return this; }
        public Builder language(String language) { this.language = language; return this; }

        public WhatsappNotification build() {
            return new WhatsappNotification(this);
        }
    }

    @Override
    public void send(String message) {
        System.out.println("[WHATSAPP] Enviado a: " + phoneNumber + ", Mensaje: " + message);
    }
}
