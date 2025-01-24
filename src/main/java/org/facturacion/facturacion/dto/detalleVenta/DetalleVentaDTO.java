package org.facturacion.facturacion.dto.detalleVenta;

public record DetalleVentaDTO(
    String codigoProducto,
    Integer formaVenta,
    Integer cantidad
) {

}
