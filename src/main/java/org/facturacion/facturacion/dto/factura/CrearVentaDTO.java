package org.facturacion.facturacion.dto.factura;

import org.facturacion.facturacion.dto.detalleFactura.DetalleVentaDTO;

import java.util.List;

public record CrearVentaDTO(
        List<DetalleVentaDTO> listDetalleFactura,
        Integer usuario,
        Integer cliente
) {
}
