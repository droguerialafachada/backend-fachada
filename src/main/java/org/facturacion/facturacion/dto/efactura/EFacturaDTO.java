package org.facturacion.facturacion.dto.efactura;

import org.facturacion.facturacion.domain.FacturaElectronica;

public record EFacturaDTO(
        Integer id,
        String fecha,
        Double total,
        String cliente,
        String usuario
) {
    public static EFacturaDTO fromEntity(FacturaElectronica eFactura) {
        return new EFacturaDTO(
                eFactura.getId(),
                eFactura.getFecha().toString(),
                eFactura.getVenta().getTotal(),
                eFactura.getVenta().getCliente().getNombre(),
                eFactura.getVenta().getUsuario().getNombre()
        );
    }
}