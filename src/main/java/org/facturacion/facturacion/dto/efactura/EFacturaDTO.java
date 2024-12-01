package org.facturacion.facturacion.dto.efactura;


import org.facturacion.facturacion.domain.FacturaElectronica;
import org.facturacion.facturacion.dto.venta.VentaDTO;

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
                eFactura.getTotal(),
                eFactura.getCliente().getNombre(),
                eFactura.getUsuario().getNombre()
        );
    }
}