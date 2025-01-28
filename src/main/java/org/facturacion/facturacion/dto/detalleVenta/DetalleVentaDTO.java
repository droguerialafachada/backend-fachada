package org.facturacion.facturacion.dto.detalleVenta;

public record DetalleVentaDTO(
    String codigoProducto,
    String nombreformaVenta,
    Integer cantidad
) {

}
