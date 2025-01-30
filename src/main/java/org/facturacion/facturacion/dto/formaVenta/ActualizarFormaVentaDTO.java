package org.facturacion.facturacion.dto.formaVenta;

public record ActualizarFormaVentaDTO(
        String nuevoNombre,
        Double precioCompra,
        Double precioVenta,
        Integer cantidad
) {
}
