package com.example.payment.Application.Service;

import com.example.payment.Domain.Reports.PaymentReport;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;

@Service
public class PdfReportService {

    public void generate(PaymentReport report) {
        try {
            File reportsDir = new File("reports");
            if (!reportsDir.exists()) reportsDir.mkdirs();

            String filename = "reports/reporte_pago_" + report.getPaymentId() + ".pdf";
            PdfWriter writer = new PdfWriter(new FileOutputStream(filename));
            PdfDocument pdf = new PdfDocument(writer);

            // Color por defecto
            Color textColor = ColorConstants.BLACK;

            // Tema oscuro: pintar fondo negro y texto blanco
            if (report.getTheme() == PaymentReport.Theme.DARK) {
                PdfCanvas canvas = new PdfCanvas(pdf.addNewPage());
                canvas.setFillColor(ColorConstants.BLACK);
                canvas.rectangle(0, 0, pdf.getDefaultPageSize().getWidth(), pdf.getDefaultPageSize().getHeight());
                canvas.fill();
                textColor = ColorConstants.WHITE;
            } else {
                pdf.addNewPage();
            }

            Document document = new Document(pdf);

            // Título
            document.add(new Paragraph(report.getTitle())
                    .setBold()
                    .setFontSize(18)
                    .setFontColor(textColor)
                    .setTextAlignment(TextAlignment.CENTER));

            // Logo
            if (report.isIncludeLogo()) {
                try {
                    String logoPath = "src/main/resources/Imagenes/PayNet.png";
                    Image logo = new Image(ImageDataFactory.create(logoPath));
                    logo.scaleToFit(100, 100);
                    logo.setHorizontalAlignment(HorizontalAlignment.CENTER);
                    document.add(logo);
                } catch (Exception ex) {
                    System.err.println("❌ No se pudo cargar el logo: " + ex.getMessage());
                }
            }

            // Cuerpo
            document.add(new Paragraph("\nMonto: $" + String.format("%,.2f", report.getAmount())).setFontColor(textColor));
            document.add(new Paragraph("ID de Pago: " + report.getPaymentId()).setFontColor(textColor));

            if (report.isIncludeDetails()) {
                document.add(new Paragraph("Detalles del pago: método, transacción, etc.").setFontColor(textColor));
            }

            if (report.isIncludeUserInfo()) {
                document.add(new Paragraph("Usuario: Juan Pérez (Ejemplo)").setFontColor(textColor));
            }

            if (report.isIncludeTimestamp()) {
                document.add(new Paragraph("Fecha de emisión: " + LocalDateTime.now()).setFontColor(textColor));
            }

            // Footer
            document.add(new Paragraph("\n" + report.getFooterMessage())
                    .setFontColor(textColor)
                    .setTextAlignment(TextAlignment.CENTER));

            document.close();
            System.out.println("✅ PDF generado en: " + filename);

        } catch (Exception e) {
            System.err.println("❌ Error generando PDF: " + e.getMessage());
            throw new RuntimeException("Error al generar el PDF", e);
        }
    }
}
