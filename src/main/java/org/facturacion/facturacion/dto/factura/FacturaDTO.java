package org.facturacion.facturacion.dto.factura;

import org.facturacion.facturacion.domain.Factura;
import org.facturacion.facturacion.domain.FacturaElectronica;
import org.facturacion.facturacion.dto.efactura.EFacturaDTO;

public record FacturaDTO(
        Integer id,
        String fecha,
        Double total,
        String cliente,
        String usuario
) {
    public static FacturaDTO fromEntity(Factura factura) {
        return new FacturaDTO(
                factura.getId(),
                factura.getFecha().toString(),
                factura.getTotal(),
                factura.getCliente().getNombre(),
                factura.getUsuario().getNombre()
        );
    }
}
