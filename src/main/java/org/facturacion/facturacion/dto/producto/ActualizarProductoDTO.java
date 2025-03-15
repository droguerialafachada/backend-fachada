package org.facturacion.facturacion.dto.producto;

import java.util.Date;

public record ActualizarProductoDTO(
        String codigo,
        String nombre,
        String impuesto,
        Date fechaVencimiento
) {
}
