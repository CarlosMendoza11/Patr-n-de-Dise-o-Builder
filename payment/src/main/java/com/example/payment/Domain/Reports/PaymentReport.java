package com.example.payment.Domain.Reports;

public class PaymentReport {

    private String reportId;
    private String paymentId;
    private double amount;
    private String title;
    private boolean includeLogo;
    private boolean includeDetails;
    private boolean includeUserInfo;
    private boolean includeTimestamp;
    private Theme theme;
    private Format format;
    private String footerMessage;

    public enum Theme {
        LIGHT, CORPORATE, DARK
    }

    public enum Format {
        A4, LETTER
    }

    public String getTitle() {
        return title;
    }

    public boolean isIncludeLogo() {
        return includeLogo;
    }

    public boolean isIncludeDetails() {
        return includeDetails;
    }

    public boolean isIncludeUserInfo() {
        return includeUserInfo;
    }

    public Theme getTheme() {
        return theme;
    }

    public Format getFormat() {
        return format;
    }

    public boolean isIncludeTimestamp() {
        return includeTimestamp;
    }

    public String getFooterMessage() {
        return footerMessage;
    }

    public String getReportId() {
        return reportId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public double getAmount() {
        return amount;
    }

    private PaymentReport(Builder builder) {
        this.reportId = builder.reportId;
        this.paymentId = builder.paymentId;
        this.amount = builder.amount;
        this.title = builder.title;
        this.includeLogo = builder.includeLogo;
        this.includeDetails = builder.includeDetails;
        this.includeUserInfo = builder.includeUserInfo;
        this.includeTimestamp = builder.includeTimestamp;
        this.theme = builder.theme;
        this.format = builder.format;
        this.footerMessage = builder.footerMessage;
    }

    public static class Builder {
        private String reportId;
        private String paymentId;
        private double amount;
        private String title;
        private boolean includeLogo;
        private boolean includeDetails;
        private boolean includeUserInfo;
        private boolean includeTimestamp;
        private Theme theme;
        private Format format;
        private String footerMessage;

        public Builder reportId(String reportId) { this.reportId = reportId; return this; }
        public Builder paymentId(String paymentId) { this.paymentId = paymentId; return this; }
        public Builder amount(double amount) { this.amount = amount; return this; }
        public Builder title(String title) { this.title = title; return this; }
        public Builder includeLogo(boolean includeLogo) { this.includeLogo = includeLogo; return this; }
        public Builder includeDetails(boolean includeDetails) { this.includeDetails = includeDetails; return this; }
        public Builder includeUserInfo(boolean includeUserInfo) { this.includeUserInfo = includeUserInfo; return this; }
        public Builder includeTimestamp(boolean includeTimestamp) { this.includeTimestamp = includeTimestamp; return this; }
        public Builder theme(Theme theme) { this.theme = theme; return this; }
        public Builder format(Format format) { this.format = format; return this; }
        public Builder footerMessage(String footerMessage) { this.footerMessage = footerMessage; return this; }

        public PaymentReport build() {
            return new PaymentReport(this);
        }
    }

}