package org.facturacion.facturacion.dto.factura;

import java.util.Date;

public record CrearFacturaDTO(
        Integer idVenta,
        Date fecha
) {
}
