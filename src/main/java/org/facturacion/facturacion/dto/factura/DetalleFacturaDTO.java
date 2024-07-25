package org.facturacion.facturacion.dto.factura;

import org.facturacion.facturacion.domain.DetalleFactura;

public record DetalleFacturaDTO(
    String codigoProducto,
    Integer cantidad
) {

    public static DetalleFactura toEntity(DetalleFacturaDTO detalle){
       DetalleFactura detalleFactura = new DetalleFactura();
       detalleFactura.setCantidad(detalle.cantidad());
       return detalleFactura;
    }

}
