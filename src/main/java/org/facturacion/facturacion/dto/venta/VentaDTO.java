package org.facturacion.facturacion.dto.venta;

import org.facturacion.facturacion.domain.Venta;

public record VentaDTO(
        Integer id,
        String fecha,
        Double total,
        String cliente,
        String usuario
) {
    public static VentaDTO fromEntity(Venta venta) {
        return new VentaDTO(
                venta.getId(),
                venta.getFecha().toString(),
                venta.getTotal(),
                venta.getCliente().getNombre(),
                venta.getUsuario().getNombre()
        );
    }
}
