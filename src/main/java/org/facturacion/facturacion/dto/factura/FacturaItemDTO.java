package org.facturacion.facturacion.dto.factura;

import org.facturacion.facturacion.domain.Factura;

import java.util.Date;

public record FacturaItemDTO(
        String cedulaCliente,
        Date fechaHora,
        Double total,
        Integer idFactura
) {


    public static FacturaItemDTO fromEntity(Factura factura) {
        return new FacturaItemDTO(
                factura.getCliente().getCedula(),
                factura.getFecha(),
                factura.getTotal(),
                factura.getId()
        );
    }
}
