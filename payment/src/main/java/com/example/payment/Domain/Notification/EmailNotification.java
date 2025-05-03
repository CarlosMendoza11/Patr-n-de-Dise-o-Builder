package com.example.payment.Domain.Notification;

import java.util.List;

public class EmailNotification implements Notification {
    private String to;
    private String subject;
    private String body;
    private List<String> cc;
    private List<String> bcc;
    private List<String> attachments;
    private String priority;

    private EmailNotification(Builder builder) {
        this.to = builder.to;
        this.subject = builder.subject;
        this.body = builder.body;
        this.cc = builder.cc;
        this.bcc = builder.bcc;
        this.attachments = builder.attachments;
        this.priority = builder.priority;
    }

    public static class Builder {
        private String to;
        private String subject;
        private String body;
        private List<String> cc;
        private List<String> bcc;
        private List<String> attachments;
        private String priority;

        public Builder to(String to) { this.to = to; return this; }
        public Builder subject(String subject) { this.subject = subject; return this; }
        public Builder body(String body) { this.body = body; return this; }
        public Builder cc(List<String> cc) { this.cc = cc; return this; }
        public Builder bcc(List<String> bcc) { this.bcc = bcc; return this; }
        public Builder attachments(List<String> attachments) { this.attachments = attachments; return this; }
        public Builder priority(String priority) { this.priority = priority; return this; }

        public EmailNotification build() {
            return new EmailNotification(this);
        }
    }

    @Override
    public void send(String message) {
        System.out.println("[EMAIL] Enviado a: " + to + ", Asunto: " + subject + ", Cuerpo: " + body);
    }
}
