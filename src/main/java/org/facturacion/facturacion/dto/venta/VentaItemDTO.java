package org.facturacion.facturacion.dto.venta;

import org.facturacion.facturacion.domain.Venta;

import java.util.Date;

public record VentaItemDTO(
        String cedulaCliente,
        Date fechaHora,
        Double total,
        Integer idVenta
) {


    public static VentaItemDTO fromEntity(Venta venta) {
        return new VentaItemDTO(
                venta.getCliente().getCedula(),
                venta.getFecha(),
                venta.getTotal(),
                venta.getId()
        );
    }
}
