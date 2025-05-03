package com.example.payment.Infrastructure.Controller;

import com.example.payment.Application.Service.PdfReportService;
import com.example.payment.Domain.Reports.PaymentReport;
import com.example.payment.Domain.Reports.PaymentReport.Format;
import com.example.payment.Domain.Reports.PaymentReport.Theme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/reportes")
public class ReportController {

    @Autowired
    private PdfReportService pdfReportService;

    @PostMapping
    public ResponseEntity<String> generarReporte(@RequestBody ReporteDTO dto) {
        try {
            PaymentReport report = new PaymentReport.Builder()
                    .reportId("RPT-" + System.currentTimeMillis())
                    .paymentId(dto.paymentId())
                    .amount(dto.amount())
                    .title(dto.title())
                    .includeLogo(dto.includeLogo())
                    .includeDetails(dto.includePaymentDetails())
                    .includeUserInfo(dto.includeUserInfo())
                    .includeTimestamp(dto.includeTimestamp())
                    .theme(Theme.valueOf(dto.theme().toUpperCase()))
                    .format(Format.valueOf(dto.format().toUpperCase()))
                    .footerMessage(dto.footerMessage())
                    .build();

            pdfReportService.generate(report);

            return ResponseEntity.ok("Reporte generado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error al generar el reporte: " + e.getMessage());
        }
    }

    @GetMapping("/descargar/{paymentId}")
    public ResponseEntity<Resource> descargarReporte(@PathVariable String paymentId) {
        try {
            String path = "reports/reporte_pago_" + paymentId + ".pdf";
            File file = new File(path);

            if (!file.exists() || !file.isFile()) {
                System.err.println("❌ Archivo no encontrado: " + path);
                return ResponseEntity.notFound().build();
            }

            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                    .contentType(MediaType.APPLICATION_PDF)
                    .contentLength(file.length())
                    .body(resource);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    // DTO interno
    public record ReporteDTO(
            String paymentId,
            double amount,
            String title,
            boolean includeLogo,
            boolean includePaymentDetails,
            boolean includeUserInfo,
            boolean includeTimestamp,
            String footerMessage,
            String theme,
            String format
    ) {}
}
