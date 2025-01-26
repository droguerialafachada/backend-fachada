package org.facturacion.facturacion.dto.formaVenta;

public record ActualizarFormaVentaDTO(
        Long id,
        String nombre,
        Double precioCompra,
        Double precioVenta,
        Integer cantidad
) {
}
