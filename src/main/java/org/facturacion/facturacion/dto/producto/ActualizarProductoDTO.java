package org.facturacion.facturacion.dto.producto;

public record ActualizarProductoDTO(
        String codigo,
        String nombre,
        String impuesto
) {
}
