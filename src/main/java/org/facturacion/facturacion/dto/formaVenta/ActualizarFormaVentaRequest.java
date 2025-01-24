package org.facturacion.facturacion.dto.formaVenta;

public record ActualizarFormaVentaRequest(
        String codigo,
        ActualizarFormaVentaDTO formaVentaDTO
) {
}
