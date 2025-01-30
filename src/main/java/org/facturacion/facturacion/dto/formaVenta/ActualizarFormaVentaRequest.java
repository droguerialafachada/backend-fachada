package org.facturacion.facturacion.dto.formaVenta;

public record ActualizarFormaVentaRequest(
        String codigo,
        String nombreFormaVenta,
        ActualizarFormaVentaDTO formaVentaDTO
) {
}
