package org.facturacion.facturacion.dto.venta;

import org.facturacion.facturacion.dto.detalleVenta.DetalleVentaDTO;

import java.util.List;

public record CrearVentaDTO(
        List<DetalleVentaDTO> listDetalleVenta,
        Integer usuario,
        String cliente,
        Double dineroRecibido,
        Double cambio,
        Double descuento
) {
}
