package org.facturacion.facturacion.dto.factura;

import org.facturacion.facturacion.domain.Factura;
import org.facturacion.facturacion.domain.Venta;
import org.facturacion.facturacion.utils.Constants;

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
                factura.getVenta().getTotal(),
                factura.getVenta().getCliente().getNombre(),
                factura.getVenta().getUsuario().getNombre()
        );
    }

    public static Factura toEntity() {
        Factura factura = new Factura();
        //La fecha de la factura es la fecha de creación no la fecha de la venta
        factura.setFecha(new java.util.Date());
        return factura;
    }
}
