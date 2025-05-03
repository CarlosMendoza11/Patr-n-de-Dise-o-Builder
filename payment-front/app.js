function procesarPago() {
  const tipo = document.getElementById("paymentType").value;
  const monto = parseFloat(document.getElementById("amount").value);
  const canal = document.getElementById("channel").value;
  const tema = document.getElementById("themeSelector").value;

  if (isNaN(monto) || monto <= 0) {
    document.getElementById("resultado").innerText = "❗ Ingresa un monto válido.";
    return;
  }

  document.getElementById("resultado").innerText = "⏳ Procesando pago...";

  const url = `http://localhost:8080/api/pagos?tipo=${encodeURIComponent(tipo)}&monto=${monto}&canal=${encodeURIComponent(canal)}`;

  fetch(url, { method: "POST" })
    .then(response => {
      if (!response.ok) throw new Error("Error en la respuesta del servidor");
      return response.json();
    })
    .then(data => {
      document.getElementById("resultado").innerText = `✅ Pago procesado correctamente. Total: $${data}`;
    })
    .catch(error => {
      console.error("Error:", error);
      document.getElementById("resultado").innerText = "❌ Error al procesar el pago.";
    });
}

function generarReporte() {
  const paymentId = "PAY-" + Date.now();
  const dto = {
    paymentId,
    amount: parseFloat(document.getElementById("amount").value),
    title: document.getElementById("reportTitle").value || "Reporte de Pago",
    includeLogo: document.getElementById("includeLogo").checked,
    includePaymentDetails: document.getElementById("includeDetails").checked,
    includeUserInfo: document.getElementById("includeUserInfo").checked,
    includeTimestamp: document.getElementById("includeTimestamp").checked,
    footerMessage: document.getElementById("footerMessage").value || "Gracias por su preferencia.",
    theme: document.getElementById("reportTheme").value,
    format: document.getElementById("reportFormat").value
  };

  if (isNaN(dto.amount) || dto.amount <= 0) {
    document.getElementById("resultado").innerText = "❗ Ingresa un monto válido para el reporte.";
    return;
  }

  fetch("http://localhost:8080/api/reportes", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(dto)
  })
    .then(response => {
      if (!response.ok) throw new Error("Error al generar el reporte");
      return response.text();
    })
    .then(msg => {
      document.getElementById("resultado").innerText = "📄 " + msg;

      // Descarga automática del PDF
      const downloadUrl = `http://localhost:8080/api/reportes/descargar/${paymentId}`;
      const link = document.createElement("a");
      link.href = downloadUrl;
      link.download = `reporte_pago_${paymentId}.pdf`;
      link.click();
    })
    .catch(error => {
      console.error("Error:", error);
      document.getElementById("resultado").innerText = "❌ Error al generar el reporte.";
    });
}

function cambiarTema() {
  const tema = document.getElementById("themeSelector").value;
  document.getElementById("app").className = tema === "dark" ? "dark-theme" : "light-theme";
}
