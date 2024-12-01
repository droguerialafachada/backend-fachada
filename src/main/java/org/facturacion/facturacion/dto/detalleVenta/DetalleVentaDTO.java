package org.facturacion.facturacion.dto.detalleVenta;

import org.facturacion.facturacion.domain.DetalleVenta;

public record DetalleVentaDTO(
    String codigoProducto,
    Integer cantidad
) {

    public static DetalleVenta toEntity(DetalleVentaDTO detalle){
       DetalleVenta detalleVenta = new DetalleVenta();
       detalleVenta.setCantidad(detalle.cantidad());
       return detalleVenta;
    }

}
