package org.facturacion.facturacion.dto.formaVenta;

public record ActualizarFormaVentaDTO(
        Long id,
        String nombre,
        Double precio,
        Integer cantidad
) {
}
